package blogSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blogSpring.entities.concretes.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer> {
	
	Comment deleteById(int commentId);
	
	List<Comment> getByPost_Id(int postId);

}
