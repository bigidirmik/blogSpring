package blogSpring.business.abstracts;

import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.entities.concretes.Image;

public interface ImageService {
	
	Result add(int postId, String url);
	
	Result update(int postId, String url);
	
//	Result add(Image image, MultipartFile imageFile);
//	
//	Result update(Image image, MultipartFile imageFile);
	
	// Custom JPA
	
	Result deleteById(int imageId);
	
	DataResult<Image> getByPostId(int postId);

}
