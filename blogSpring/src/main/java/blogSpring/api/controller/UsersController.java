package blogSpring.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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

import blogSpring.business.abstracts.UserService;
import blogSpring.core.entities.concretes.User;
import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.ErrorDataResult;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {

	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody User user) {
		return ResponseEntity.ok(this.userService.add(user));
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody User user) {
		return ResponseEntity.ok(this.userService.update(user));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody User user) {
		return ResponseEntity.ok(this.userService.delete(user));
	}

	@GetMapping("/getById")
	public DataResult<User> getById(@RequestParam(name = "id") int id) {
		return this.userService.getById(id);
	}

	@GetMapping("/getAll")
	public DataResult<List<User>> getAll() {
		return this.userService.getAll();
	}

	// Custom JPA
	@GetMapping("/getByEmail")
	public DataResult<User> getByEmail(@RequestParam(name = "email") String email) {
		return this.userService.getByEmail(email);
	}

	@GetMapping("/getByFirstName")
	public DataResult<List<User>> getByFirstName(@RequestParam(name = "firstName") String firstName) {
		return this.userService.getByFirstName(firstName);
	}

	@GetMapping("/getByLastName")
	public DataResult<List<User>> getByLastName(@RequestParam(name = "lastName") String lastName) {
		return this.userService.getByLastName(lastName);
	}

	@GetMapping("/getByFirstNameAndLastName")
	public DataResult<List<User>> getByFirstNameAndLastName(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) {
		return this.userService.getByFirstNameAndLastName(firstName, lastName);
	}

	@GetMapping("/getByFirstNameOrLastName")
	public DataResult<List<User>> getByFirstNameOrLastName(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) {
		return this.userService.getByFirstNameOrLastName(firstName, lastName);
	}

	@GetMapping("/getByEmailIn")
	public DataResult<List<User>> getByEmailIn(@RequestParam(name = "emailList") List<String> emailList) {
		return this.userService.getByEmailIn(emailList);
	}

	@GetMapping("/getByFirstNameIn")
	public DataResult<List<User>> getByFirstNameIn(@RequestParam(name = "firstNameList") List<String> firstNameList) {
		return this.userService.getByFirstNameIn(firstNameList);
	}

	@GetMapping("/getByLastNameIn")
	public DataResult<List<User>> getByLastNameIn(@RequestParam(name = "lastNameList") List<String> lastNameList) {
		return this.userService.getByLastNameIn(lastNameList);
	}

	@GetMapping("/getByEmailContains")
	public DataResult<List<User>> getByEmailContains(@RequestParam(name = "email") String email) {
		return this.userService.getByEmailContains(email);
	}

	@GetMapping("/getByFirstNameContains")
	public DataResult<List<User>> getByFirstNameContains(@RequestParam(name = "firstName") String firstName) {
		return this.userService.getByFirstNameContains(firstName);
	}

	@GetMapping("/getByLastNameContains")
	public DataResult<List<User>> getByLastNameContains(@RequestParam(name = "lastName") String lastName) {
		return this.userService.getByLastNameContains(lastName);
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
