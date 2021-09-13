package blogSpring.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import blogSpring.business.abstracts.CommentService;
import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.entities.concretes.Comment;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin
public class CommentsController {

	private CommentService commentService;

	@Autowired
	public CommentsController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	@PostMapping("/add")
	public Result add(@RequestParam(name = "postId") int postId, @RequestBody Comment comment) {
		return this.commentService.add(postId, comment);
	}

	@GetMapping("/getAll")
	public DataResult<List<Comment>> getAll() {
		return this.commentService.getAll();
	}

	// Custom JPA

	@DeleteMapping("/deleteById")
	public Result deleteById(@RequestParam(name = "commentId") int commentId) {
		return this.commentService.deleteById(commentId);
	}
	
	@GetMapping("/getByPostId")
	public DataResult<List<Comment>> getByPostId(@RequestParam(name = "postId") int postId) {
		return this.commentService.getByPostId(postId);
	}

}
