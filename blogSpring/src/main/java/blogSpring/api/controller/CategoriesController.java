package blogSpring.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import blogSpring.business.abstracts.CategoryService;
import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.ErrorDataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.entities.concretes.Category;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoriesController {

	private CategoryService categoryService;

	@Autowired
	public CategoriesController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Category category) {
		return ResponseEntity.ok(this.categoryService.add(category));
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestParam(name = "categoryId") int categoryId, @RequestParam(name = "categoryName") String categoryName) {
		return ResponseEntity.ok(this.categoryService.update(categoryId,categoryName));
	}

	@DeleteMapping("/deleteById")
	public ResponseEntity<?> deleteById(int categoryId) {
		return ResponseEntity.ok(this.categoryService.deleteById(categoryId));
	}
	
	@PutMapping("/setActivity")
	public Result setActivity(@RequestParam("categoryId") int categoryId, @RequestParam("status") boolean status) {
		return this.categoryService.setActivity(categoryId, status);
	}

	@GetMapping("/getAll")
	public DataResult<List<Category>> getAll() {
		return this.categoryService.getAll();
	}

	// Custom JPA
	
	@GetMapping("/getByCategoryName")
	public DataResult<List<Category>> getByCategoryName(@RequestParam(name = "categoryName") String categoryName) {
		return this.categoryService.getByCategoryName(categoryName);
	}

	@GetMapping("/getByIsActive")
	public DataResult<List<Category>> getByIsActive(boolean isActive) {
		return this.categoryService.getByIsActive(isActive);
	}

	@GetMapping("/findById")
	public DataResult<Category> findById(@RequestParam(name = "categoryId") int categoryId) {
		return this.categoryService.findById(categoryId);
	}

	// Global Ex. Handler (AOP)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama Hataları!");
		return errors;
	}

}
