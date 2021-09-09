package blogSpring.business.abstracts;

import java.util.List;

import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.entities.concretes.Comment;

public interface CommentService {
	
	Result add(int postId, Comment comment);
	
	DataResult<List<Comment>> getAll();
	
	//Custom JPA
	
	Result deleteById(int id);
	
	DataResult<List<Comment>> getByPostId(int postId);

}
