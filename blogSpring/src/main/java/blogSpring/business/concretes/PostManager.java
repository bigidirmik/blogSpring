package blogSpring.business.concretes;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import blogSpring.business.abstracts.PostService;
import blogSpring.core.entities.concretes.User;
import blogSpring.core.utilities.constants.Messages;
import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.core.utilities.results.SuccessDataResult;
import blogSpring.core.utilities.results.SuccessResult;
import blogSpring.dataAccess.abstracts.ImageDao;
import blogSpring.dataAccess.abstracts.PostDao;
import blogSpring.entities.concretes.Category;
import blogSpring.entities.concretes.Image;
import blogSpring.entities.concretes.Post;

@Service
public class PostManager implements PostService {
	
	private PostDao postDao;
	private ImageDao imageDao;

	@Autowired
	public PostManager(PostDao postDao, ImageDao imageDao) {
		super();
		this.postDao = postDao;
		this.imageDao = imageDao;
	}

	@Override
	public Result add(int categoryId,Post post) {
		Category category = new Category();
		User user = new User();
		category.setId(categoryId);
		user.setId(1);
		post.setCategory(category);
		post.setUser(user);
		this.postDao.save(post);
		return new SuccessResult(Messages.added);
	}

	@Override
	public Result update(int id,String title,String content) {
		Post post = this.findById(id).getData();
		post.setTitle(title);
		post.setContent(content);
		this.postDao.save(post);
		return new SuccessResult(Messages.updated);
	}

	@Override
	public Result deleteById(int id) {
		Image image = this.imageDao.getByPost_Id(id);
		if (image!=null) {
			imageDao.deleteById(image.getId());
		}
		this.postDao.deleteById(id);
		return new SuccessResult(Messages.deleted);
	}

	@Override
	public DataResult<List<Post>> getAll() {
		return new SuccessDataResult<List<Post>>(this.postDao.findAll(),Messages.listed);
	}
	
	@Override
	public DataResult<List<Post>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return new SuccessDataResult<List<Post>>(this.postDao.findAll(pageable).getContent(),Messages.listed);
	}
	
	@Override
	public DataResult<List<Post>> getAllAsc() {
		Sort sort = Sort.by(Sort.Direction.ASC,"createDate");
		return new SuccessDataResult<List<Post>>(this.postDao.findAll(sort),Messages.listed);
	}
	
	@Override
	public DataResult<List<Post>> getAllDesc() {
		Sort sort = Sort.by(Sort.Direction.DESC,"createDate");
		return new SuccessDataResult<List<Post>>(this.postDao.findAll(sort),Messages.listed);
	}

	
	// Custom JPA
	
	@Override
	public DataResult<Post> findById(int id) {
		return new SuccessDataResult<Post>(this.postDao.findById(id),Messages.found);
	}
	
	@Override
	public DataResult<List<Post>> getByIsActive(boolean status) {
		return new SuccessDataResult<List<Post>>(this.postDao.getByIsActive(status),Messages.listed);
	}
	
	@Override
	public DataResult<List<Post>> getByCategoryId(int categoryId) {
		return new SuccessDataResult<List<Post>>(this.postDao.getByCategory_Id(categoryId),Messages.listed);
	}
	
	@Override
	public DataResult<List<Post>> getByUserId(int userId) {
		return new SuccessDataResult<List<Post>>(this.postDao.getByUser_Id(userId),Messages.listed);
	}
	
	@Override
	public DataResult<List<Post>> getByTitle(String title) {
		return new SuccessDataResult<List<Post>>(this.postDao.getByTitle(title),Messages.listed);
	}

	@Override
	public DataResult<List<Post>> getByCreateDate(Date createDate) {
		return new SuccessDataResult<List<Post>>(this.postDao.getByCreateDate(createDate),Messages.listed);
	}
	
	@Override
	public DataResult<List<Post>> getByCategoryIdAndIsActive(int categoryId, boolean status) {
		return new SuccessDataResult<List<Post>>(this.postDao.getByCategory_IdAndIsActive(categoryId,status),Messages.listed);
	}
	
	@Override
	public DataResult<List<Post>> getByUserIdAndIsActive(int userId, boolean status) {
		return new SuccessDataResult<List<Post>>(this.postDao.getByUser_IdAndIsActive(userId,status),Messages.listed);
	}

	@Override
	public DataResult<List<Post>> getByTitleAndCreateDate(String title, Date createDate) {
		return new SuccessDataResult<List<Post>>(this.postDao.getByTitleAndCreateDate(title, createDate),Messages.listed);
	}

	@Override
	public DataResult<List<Post>> getByTitleOrCreateDate(String title, Date createDate) {
		return new SuccessDataResult<List<Post>>(this.postDao.getByTitleOrCreateDate(title, createDate),Messages.listed);
	}

	@Override
	public DataResult<List<Post>> getByTitleIn(List<String> titleList) {
		return new SuccessDataResult<List<Post>>(this.postDao.getByTitleIn(titleList),Messages.listed);
	}

	@Override
	public DataResult<List<Post>> getByCreateDateIn(List<Date> createDateList) {
		return new SuccessDataResult<List<Post>>(this.postDao.getByCreateDateIn(createDateList),Messages.listed);
	}

	@Override
	public DataResult<List<Post>> getByTitleContains(String title) {
		return new SuccessDataResult<List<Post>>(this.postDao.getByTitleContains(title),Messages.listed);
	}

	@Override
	public DataResult<List<Post>> getByCreateDateContains(Date createYear) {
		return new SuccessDataResult<List<Post>>(this.postDao.getByCreateDateContains(createYear),Messages.listed);
	}

//	@Override
//	public DataResult<List<Post>> getByUserId(int userId) {
//		return new SuccessDataResult<List<Post>>(this.postDao.getByUser_Id(userId),Messages.listed);
//	}


	// DTO JQPL
//	@Override
//	public DataResult<List<PostWithCategoryDto>> getPostWithCategoryDetails() {
//		return new SuccessDataResult<List<PostWithCategoryDto>>(this.postDao.getPostWithCategoryDetails(),Messages.listed);
//	}
	

	// business codes
	
	@Override
	public Result setActivity(int postId, boolean status) {
		Post post = this.postDao.findById(postId);
		post.setActive(status);
		this.postDao.save(post);
		return new SuccessResult("Active : " + status);
	}
}
