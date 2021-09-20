package blogSpring.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import blogSpring.business.abstracts.ImageService;
import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.entities.concretes.Image;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ImagesController {

	private ImageService imageService;
//	private PostService postService;
	
	@Autowired
	public ImagesController(ImageService imageService) {
		super();
		this.imageService = imageService;
//		this.postService = postService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestParam(name = "postId") int postId, @RequestParam(name = "url") String url) {
		return this.imageService.add(postId, url);
	}
	
	@PutMapping("/update")
	public Result update(@RequestParam(name = "imageId") int imageId, @RequestParam(name = "url") String url) {
		return this.imageService.update(imageId, url);
	}
	
	// Custom JPA

	@DeleteMapping("/deleteById")
	public Result deleteById(@RequestParam(name = "imageId") int imageId) {
		return this.imageService.deleteById(imageId);
	}

	@GetMapping("/getByPostId")
	public DataResult<List<Image>> getByPostId(@RequestParam(name = "postId") int postId) {
		return this.imageService.getByPostId(postId);
	}

}
