package blogSpring.business.abstracts;

import java.util.List;

import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.entities.concretes.Category;

public interface CategoryService {

	Result add(Category category);
	Result update(int id,String categoryName);
	Result deleteById(int id);
	
	Result setActivity(int categoryId, boolean status);
	
//	DataResult<Category> getById(int id);
	DataResult<List<Category>> getAll();
	
	
	// Custom JPA
	
	DataResult<List<Category>> getByIsActive(boolean isActive);
	
	DataResult<List<Category>> getByCategoryName(String categoryName);
	
	DataResult<Category> findById(int id);
	
}
