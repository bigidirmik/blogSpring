package blogSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blogSpring.entities.concretes.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {
	
	List<Category> getByCategoryName(String categoryName);
	
	List<Category> getByIsActive(boolean isActive);
	
	Category deleteById(int categoryId);
	
	Category findById(int categoryId);

}
