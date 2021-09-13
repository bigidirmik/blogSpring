package blogSpring.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogSpring.business.abstracts.CommentService;
import blogSpring.core.utilities.constants.Messages;
import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.core.utilities.results.SuccessDataResult;
import blogSpring.core.utilities.results.SuccessResult;
import blogSpring.dataAccess.abstracts.CommentDao;
import blogSpring.dataAccess.abstracts.PostDao;
import blogSpring.entities.concretes.Comment;
import blogSpring.entities.concretes.Post;

@Service
public class CommentManager implements CommentService {

	private CommentDao commentDao;
	private PostDao postDao;

	@Autowired
	public CommentManager(CommentDao commentDao,PostDao postDao) {
		super();
		this.commentDao = commentDao;
		this.postDao = postDao;
	}

	@Override
	public Result add(int postId, Comment comment) {
		Post post = this.postDao.findById(postId);
		post.setId(postId);
		comment.setPost(post);
		this.commentDao.save(comment);
		return new SuccessResult(Messages.added);
	}

	@Override
	public DataResult<List<Comment>> getAll() {
		return new SuccessDataResult<List<Comment>>(this.commentDao.findAll(), Messages.listed);
	}

	@Override
	public Result deleteById(int id) {
		this.commentDao.deleteById(id);
		return new SuccessResult(Messages.deleted);
	}

	@Override
	public DataResult<List<Comment>> getByPostId(int postId) {
		return new SuccessDataResult<List<Comment>>(this.commentDao.getByPost_Id(postId), Messages.listed);
	}

}
