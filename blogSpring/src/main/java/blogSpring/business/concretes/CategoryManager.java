package blogSpring.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogSpring.business.abstracts.CategoryService;
import blogSpring.core.utilities.constants.Messages;
import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.core.utilities.results.SuccessDataResult;
import blogSpring.core.utilities.results.SuccessResult;
import blogSpring.dataAccess.abstracts.CategoryDao;
import blogSpring.dataAccess.abstracts.CommentDao;
import blogSpring.dataAccess.abstracts.ImageDao;
import blogSpring.dataAccess.abstracts.PostDao;
import blogSpring.entities.concretes.Category;
import blogSpring.entities.concretes.Comment;
import blogSpring.entities.concretes.Image;
import blogSpring.entities.concretes.Post;

@Service
public class CategoryManager implements CategoryService {

	private CategoryDao categoryDao;
	private PostDao postDao;
	private ImageDao imageDao;
	private CommentDao commentDao;

	@Autowired
	public CategoryManager(CategoryDao categoryDao, PostDao postDao, ImageDao imageDao, CommentDao commentDao) {
		super();
		this.categoryDao = categoryDao;
		this.postDao = postDao;
		this.imageDao = imageDao;
		this.commentDao = commentDao;
	}

	@Override
	public Result add(Category category) {
		this.categoryDao.save(category);
		return new SuccessResult(Messages.added);
	}

	@Override
	public Result update(int id, String categoryName) {
		Category category = this.findById(id).getData();
		category.setCategoryName(categoryName);
		this.categoryDao.save(category);
		return new SuccessResult(Messages.updated);
	}

	@Override
	public Result deleteById(int id) {
		// posts delete
		List<Post> posts = this.postDao.getByCategory_Id(id);
		for (Post post : posts) {
			// image of post delete
			Image image = this.imageDao.getByPost_Id(post.getId());
			if (image != null) {
				this.imageDao.deleteById(image.getId());
			}
			// comments of post delete
			List<Comment> comments = this.commentDao.getByPost_Id(id);
			if (comments != null) {
				for (Comment comment : comments) {
					this.commentDao.deleteById(comment.getId());
				}
			}
			this.postDao.deleteById(post.getId());
		}
		// category delete
		this.categoryDao.deleteById(id);
		return new SuccessResult(Messages.deleted);
	}

//	@Override
//	public DataResult<Category> getById(int id) {
//		return new SuccessDataResult<Category>(this.categoryDao.getById(id),Messages.found);
//	}

	@Override
	public DataResult<List<Category>> getAll() {
		return new SuccessDataResult<List<Category>>(this.categoryDao.findAll(), Messages.listed);
	}

	// Custom JPA

	@Override
	public DataResult<List<Category>> getByCategoryName(String categoryName) {
		return new SuccessDataResult<List<Category>>(this.categoryDao.getByCategoryName(categoryName), Messages.listed);
	}

	@Override
	public DataResult<List<Category>> getByIsActive(boolean isActive) {
		return new SuccessDataResult<List<Category>>(this.categoryDao.getByIsActive(isActive), Messages.listed);
	}

	@Override
	public DataResult<Category> findById(int id) {
		return new SuccessDataResult<Category>(this.categoryDao.findById(id), Messages.found);
	}

	// business codes

	@Override
	public Result setActivity(int categoryId, boolean status) {
		Category category = this.categoryDao.findById(categoryId);
		category.setActive(status);
		this.categoryDao.save(category);
		return new SuccessResult("Active : " + status);
	}

}
