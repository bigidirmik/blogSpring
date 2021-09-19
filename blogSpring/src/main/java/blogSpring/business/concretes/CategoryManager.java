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
import blogSpring.dataAccess.abstracts.ParagraphDao;
import blogSpring.dataAccess.abstracts.PostDao;
import blogSpring.entities.concretes.Category;
import blogSpring.entities.concretes.Comment;
import blogSpring.entities.concretes.Image;
import blogSpring.entities.concretes.Paragraph;
import blogSpring.entities.concretes.Post;

@Service
public class CategoryManager implements CategoryService {

	private CategoryDao categoryDao;
	private PostDao postDao;
	private ImageDao imageDao;
	private ParagraphDao paragraphDao;
	private CommentDao commentDao;

	@Autowired
	public CategoryManager(CategoryDao categoryDao, PostDao postDao, ImageDao imageDao, ParagraphDao paragraphDao,CommentDao commentDao) {
		super();
		this.categoryDao = categoryDao;
		this.postDao = postDao;
		this.imageDao = imageDao;
		this.paragraphDao = paragraphDao;
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
	public Result deleteById(int categoryId) {
		// posts delete
		List<Post> posts = this.postDao.getByCategory_Id(categoryId);
		if(posts != null) {
			for (Post post : posts) {
				// post content delete
				Result result = this.postContentDelete(post.getId());
				// post delete
				if(result.isSuccess()) {
					this.postDao.deleteById(post.getId());
				}
			}
		}
		// category delete
		this.categoryDao.deleteById(categoryId);
		return new SuccessResult(Messages.deleted);
	}

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
	public DataResult<Category> findById(int categoryId) {
		return new SuccessDataResult<Category>(this.categoryDao.findById(categoryId), Messages.found);
	}

	// business codes

	@Override
	public Result setActivity(int categoryId, boolean status) {
		Category category = this.categoryDao.findById(categoryId);
		category.setActive(status);
		this.categoryDao.save(category);
		return new SuccessResult("Active : " + status);
	}
	
	private Result postContentDelete(int postId) {
		// images delete
		List<Image> images = this.imageDao.getByPost_Id(postId);
		if (images != null) {
			for (Image image : images) {
				this.imageDao.deleteById(image.getId());
			}
		}
		// paragraphs delete
		List<Paragraph> paragraphs = this.paragraphDao.getByPost_Id(postId);
		if (paragraphs != null) {
			for (Paragraph paragraph : paragraphs) {
				this.paragraphDao.deleteById(paragraph.getId());
			}
		}
		// comments delete
		List<Comment> comments = this.commentDao.getByPost_Id(postId);
		if (comments != null) {
			for (Comment comment : comments) {
				this.commentDao.deleteById(comment.getId());
			}
		}
		return new SuccessResult("Post content : " + Messages.deleted);
	}

}
