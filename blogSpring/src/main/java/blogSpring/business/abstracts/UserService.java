package blogSpring.business.abstracts;

import java.util.List;

import blogSpring.core.entities.concretes.User;
import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;

public interface UserService {
	
	Result add(User user);
	Result update(User user);
	Result delete(User user);

	DataResult<List<User>> getAll();
	
	
	// Custom JPA
	
	DataResult<User> findById(int userId);
	
	DataResult<User> getByEmail(String email);
	DataResult<List<User>> getByFirstName(String firstName);
	DataResult<List<User>> getByLastName(String lastName);
	
	DataResult<List<User>> getByFirstNameAndLastName(String firstName, String lastName); //And
	DataResult<List<User>> getByFirstNameOrLastName(String firstName, String lastName); //Or
	
	DataResult<List<User>> getByEmailIn(List<String> emailList); //In
	DataResult<List<User>> getByFirstNameIn(List<String> firstNameList); //In
	DataResult<List<User>> getByLastNameIn(List<String> lastNameList); //In
	
	DataResult<List<User>> getByEmailContains(String email); //Contains
	DataResult<List<User>> getByFirstNameContains(String firstName); //Contains
	DataResult<List<User>> getByLastNameContains(String lastName); //Contains

}
