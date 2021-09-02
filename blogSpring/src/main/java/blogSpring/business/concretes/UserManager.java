package blogSpring.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogSpring.business.abstracts.UserService;
import blogSpring.core.utilities.constants.Messages;
import blogSpring.core.dataAccess.abstracts.UserDao;
import blogSpring.core.entities.concretes.User;
import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.core.utilities.results.SuccessDataResult;
import blogSpring.core.utilities.results.SuccessResult;

@Service
public class UserManager implements UserService {
	
	private UserDao userDao;

	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccessResult(Messages.added);
	}

	@Override
	public Result update(User user) {
		this.userDao.save(user);
		return new SuccessResult(Messages.updated);
	}

	@Override
	public Result delete(User user) {
		this.userDao.delete(user);
		return new SuccessResult(Messages.deleted);
	}

	@Override
	public DataResult<User> getById(int id) {
		return new SuccessDataResult<User>(this.userDao.getById(id),Messages.found);
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(this.userDao.findAll(),Messages.listed);
	}
	
	
	// Custom JPA

	@Override
	public DataResult<User> getByEmail(String email) {
		return new SuccessDataResult<User>(this.userDao.getByEmail(email),Messages.found);
	}

	@Override
	public DataResult<List<User>> getByFirstName(String firstName) {
		return new SuccessDataResult<List<User>>(this.userDao.getByFirstName(firstName),Messages.listed);
	}

	@Override
	public DataResult<List<User>> getByLastName(String lastName) {
		return new SuccessDataResult<List<User>>(this.userDao.getByLastName(lastName),Messages.listed);
	}

	@Override
	public DataResult<List<User>> getByFirstNameAndLastName(String firstName, String lastName) {
		return new SuccessDataResult<List<User>>(this.userDao.getByFirstNameAndLastName(firstName,lastName),Messages.listed);
	}

	@Override
	public DataResult<List<User>> getByFirstNameOrLastName(String firstName, String lastName) {
		return new SuccessDataResult<List<User>>(this.userDao.getByFirstNameOrLastName(firstName, lastName),Messages.listed);
	}

	@Override
	public DataResult<List<User>> getByEmailIn(List<String> emailList) {
		return new SuccessDataResult<List<User>>(this.userDao.getByEmailIn(emailList),Messages.listed);
	}

	@Override
	public DataResult<List<User>> getByFirstNameIn(List<String> firstNameList) {
		return new SuccessDataResult<List<User>>(this.userDao.getByFirstNameIn(firstNameList),Messages.listed);
	}

	@Override
	public DataResult<List<User>> getByLastNameIn(List<String> lastNameList) {
		return new SuccessDataResult<List<User>>(this.userDao.getByLastNameIn(lastNameList),Messages.listed);
	}

	@Override
	public DataResult<List<User>> getByEmailContains(String email) {
		return new SuccessDataResult<List<User>>(this.userDao.getByEmailContains(email),Messages.listed);
	}

	@Override
	public DataResult<List<User>> getByFirstNameContains(String firstName) {
		return new SuccessDataResult<List<User>>(this.userDao.getByFirstNameContains(firstName),Messages.listed);
	}

	@Override
	public DataResult<List<User>> getByLastNameContains(String lastName) {
		return new SuccessDataResult<List<User>>(this.userDao.getByFirstNameContains(lastName),Messages.listed);
	}

}
