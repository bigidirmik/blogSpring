package blogSpring.api.controller;

import java.sql.Date;
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

import blogSpring.business.abstracts.ImageService;
import blogSpring.business.abstracts.PostService;
import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.ErrorDataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.entities.concretes.Post;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class PostsController {

	private PostService postService;
	
	@Autowired
	public PostsController(PostService postService) {
		super();
		this.postService = postService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestParam(name = "categoryId") int categoryId,@RequestBody Post post) {
		return ResponseEntity.ok(this.postService.add(categoryId,post));
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestParam(name = "id") int id,@RequestParam(name = "title") String title,@RequestParam(name = "content") String content) {
		return ResponseEntity.ok(this.postService.update(id,title,content));
	}

	@DeleteMapping("/deleteById")
	public ResponseEntity<?> deleteById(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.postService.deleteById(id));
	}
	
	@PutMapping("/setActivity")
	public Result setActivity(@RequestParam("postId") int postId, @RequestParam("status") boolean status) {
		return this.postService.setActivity(postId, status);
	}

	@GetMapping("/getAll")
	public DataResult<List<Post>> getAll() {
		return this.postService.getAll();
	}

	@GetMapping("/getAllByPage")
	public DataResult<List<Post>> getAll(@RequestParam(name = "pageNo") int pageNo,
			@RequestParam(name = "pageSize") int pageSize) {
		return this.postService.getAll(pageNo, pageSize);
	}

	@GetMapping("/getAllAsc")
	public DataResult<List<Post>> getAllAsc() {
		return this.postService.getAllAsc();
	}

	@GetMapping("/getAllDesc")
	public DataResult<List<Post>> getAllDesc() {
		return this.postService.getAllDesc();
	}

	// Custom JPA
	@GetMapping("/findById")
	public DataResult<Post> findById(@RequestParam(name = "id") int id) {
		return this.postService.findById(id);
	}
	
	@GetMapping("/getByIsActive")
	public DataResult<List<Post>> getByIsActive(boolean status){
		return this.postService.getByIsActive(status);
	}
	
	@GetMapping("/getByCategoryId")
	public DataResult<List<Post>> getByCategoryId(@RequestParam(name = "categoryId") int categoryId) {
		return this.postService.getByCategoryId(categoryId);
	}
	
	@GetMapping("/getByUserId")
	public DataResult<List<Post>> getByUserId(@RequestParam(name = "userId") int userId) {
		return this.postService.getByUserId(userId);
	}
	
	@GetMapping("/getByTitle")
	public DataResult<List<Post>> getByTitle(@RequestParam(name = "title") String title) {
		return this.postService.getByTitle(title);
	}

	@GetMapping("/getByCreateDate")
	public DataResult<List<Post>> getByCreateDate(@RequestParam(name = "createDate") Date createDate) {
		return this.postService.getByCreateDate(createDate);
	}
	
	@GetMapping("/getByCategoryIdAndIsActive")
	public DataResult<List<Post>> getByCategoryIdAndIsActive(@RequestParam(name = "categoryId") int categoryId,@RequestParam(name = "status") boolean status){
		return this.postService.getByCategoryIdAndIsActive(categoryId, status);
	}
	
	@GetMapping("/getByUserIdAndIsActive")
	public DataResult<List<Post>> getByUserIdAndIsActive(@RequestParam(name = "userId") int userId,@RequestParam(name = "status") boolean status){
		return this.postService.getByUserIdAndIsActive(userId, status);
	}

	@GetMapping("/getByTitleAndCreateDate")
	public DataResult<List<Post>> getByTitleAndCreateDate(@RequestParam(name = "title") String title,
			@RequestParam(name = "createDate") Date createDate) {
		return this.postService.getByTitleAndCreateDate(title, createDate);
	}

	@GetMapping("/getByTitleOrCreateDate")
	public DataResult<List<Post>> getByTitleOrCreateDate(@RequestParam(name = "title") String title,
			@RequestParam(name = "createDate") Date createDate) {
		return this.postService.getByTitleOrCreateDate(title, createDate);
	}

	@GetMapping("/getByTitleIn")
	public DataResult<List<Post>> getByTitleIn(@RequestParam(name = "titleList") List<String> titleList) {
		return this.postService.getByTitleIn(titleList);
	}

	@GetMapping("/getByCreateDateIn")
	public DataResult<List<Post>> getByCreateDateIn(@RequestParam(name = "createDateList") List<Date> createDateList) {
		return this.postService.getByCreateDateIn(createDateList);
	}

	@GetMapping("/getByTitleContains")
	public DataResult<List<Post>> getByTitleContains(@RequestParam(name = "title") String title) {
		return this.postService.getByTitleContains(title);
	}

	@GetMapping("/getByCreateDateContains")
	public DataResult<List<Post>> getByCreateDateContains(@RequestParam(name = "createYear") Date createYear) {
		return this.postService.getByCreateDateContains(createYear);
	}

//	@GetMapping("/getByUserId")
//	public DataResult<List<Post>> getByUserId(@RequestParam(name = "userId") int userId){
//		return this.postService.getByUserId(userId);
//	}

	// DTO JQPL
//	@GetMapping("/getPostWithCategoryDetails")
//	public DataResult<List<PostWithCategoryDto>> getPostWithCategoryDetails() {
//		return this.postService.getPostWithCategoryDetails();
//	}

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
