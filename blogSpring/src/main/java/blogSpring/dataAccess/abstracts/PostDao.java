package blogSpring.dataAccess.abstracts;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blogSpring.entities.concretes.Post;

public interface PostDao extends JpaRepository<Post, Integer> {
	
	List<Post> getByCategory_Id(int categoryId);
	List<Post> getByUser_Id(int userId);
	
	Post deleteById(int id);
	Post findById(int id);
	
	List<Post> getByTitle(String title);
	List<Post> getByCreateDate(Date createDate);
	List<Post> getByIsActive(boolean isActive);
	
	List<Post> getByCategory_IdAndIsActive(int categoryId,boolean status); //And
	List<Post> getByUser_IdAndIsActive(int userId,boolean status); //And
	List<Post> getByTitleAndCreateDate(String title, Date createDate); //And
	List<Post> getByTitleOrCreateDate(String title, Date createDate); //Or
	
	List<Post> getByTitleIn(List<String> titleList); //In
	List<Post> getByCreateDateIn(List<Date> createDateList); //In
	
	List<Post> getByTitleContains(String title); //Contains
	List<Post> getByCreateDateContains(Date createYear); //Contains
	
	//List<Post> getByUser_Id(int userId);
	
	// DTO JPQL
//	@Query("Select blogSpring.entities.dtos.PostWithCategoryDto"
//			+ "(p.id, p.title, p.content, p.createDate, c.categoryName)"
//			+ "From Category c Inner Join c.posts p")
//	List<PostWithCategoryDto> getPostWithCategoryDetails();

}
