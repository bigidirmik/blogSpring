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

import blogSpring.business.abstracts.ParagraphService;
import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.entities.concretes.Paragraph;

@RestController
@RequestMapping("/api/paragraphs")
@CrossOrigin
public class ParagraphsController {
	
	private ParagraphService paragraphService;

	@Autowired
	public ParagraphsController(ParagraphService paragraphService) {
		super();
		this.paragraphService = paragraphService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestParam(name = "postId") int postId, @RequestParam(name = "subTitle") String subTitle, @RequestParam(name = "content") String content) {
		return this.paragraphService.add(postId, subTitle, content);
	}
	
	// Custom JPA
	
	@DeleteMapping("deleteById")
	public Result deleteById(@RequestParam(name = "paragraphId") int paragraphId) {
		return this.paragraphService.deleteById(paragraphId);
	}
	
	@GetMapping("/getByPostId")
	public DataResult<List<Paragraph>> getByPostId(@RequestParam(name = "postId") int postId){
		return this.paragraphService.getByPostId(postId);
	}

}
