package blogSpring.business.abstracts;

import java.sql.Date;
import java.util.List;

import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.entities.concretes.Post;

public interface PostService {
	
	Result add(int categoryId,Post post);
	Result update(int id,String title,String content);
	Result deleteById(int id);
	
	Result setActivity(int postId, boolean status);
	
	DataResult<List<Post>> getAll();
	DataResult<List<Post>> getAll(int pageNo, int pageSize);
	DataResult<List<Post>> getAllAsc();
	DataResult<List<Post>> getAllDesc();
	
	
	// Custom JPA
	
	DataResult<Post> findById(int id);
	
	DataResult<List<Post>> getByIsActive(boolean isActive);
	DataResult<List<Post>> getByCategoryId(int categoryId);
	DataResult<List<Post>> getByUserId(int userId);
	
	DataResult<List<Post>> getByTitle(String title);
	DataResult<List<Post>> getByCreateDate(Date createDate);

	DataResult<List<Post>> getByCategoryIdAndIsActive(int categoryId,boolean status); //And
	DataResult<List<Post>> getByUserIdAndIsActive(int userId,boolean status); //And
	DataResult<List<Post>> getByTitleAndCreateDate(String title, Date createDate); //And
	DataResult<List<Post>> getByTitleOrCreateDate(String title, Date createDate); //Or
	
	DataResult<List<Post>> getByTitleIn(List<String> titleList); //In
	DataResult<List<Post>> getByCreateDateIn(List<Date> createDateList); //In
	
	DataResult<List<Post>> getByTitleContains(String title); //Contains
	DataResult<List<Post>> getByCreateDateContains(Date createYear); //Contains
	
//	DataResult<List<Post>> getByUserId(int userId);
	
	//DTO JPQL
//	DataResult<List<PostWithCategoryDto>> getPostWithCategoryDetails();

}
