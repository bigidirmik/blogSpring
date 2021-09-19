package blogSpring.business.abstracts;

import java.util.List;

import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.entities.concretes.Comment;

public interface CommentService {
	
	Result add(int postId, String nick, String email, String content);
	
	DataResult<List<Comment>> getAll(); // ileride tüm yorumları tek sayfada kontrol etmek için!
	
	//Custom JPA
	
	Result deleteById(int id);
	
	DataResult<List<Comment>> getByPostId(int postId);

}
