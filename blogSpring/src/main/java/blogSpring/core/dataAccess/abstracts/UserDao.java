package blogSpring.core.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import blogSpring.core.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	User findById(int userId);
	
	User getByEmail(String email);
	List<User> getByFirstName(String firstName);
	List<User> getByLastName(String lastName);
	
	List<User> getByFirstNameAndLastName(String firstName, String lastName); //And
	List<User> getByFirstNameOrLastName(String firstName, String lastName); //Or
	
	List<User> getByEmailIn(List<String> emailList); //In
	List<User> getByFirstNameIn(List<String> firstNameList); //In
	List<User> getByLastNameIn(List<String> lastNameList); //In
	
	List<User> getByEmailContains(String email); //Contains
	List<User> getByFirstNameContains(String firstName); //Contains
	List<User> getByLastNameContains(String lastName); //Contains
	
	
	// Example for Query
	@Query("From User where firstName=:firstName and lastName=:lastName")
	List<User> getByFirstAndLastNameWithQuery(String firstName,String lastName);

}
