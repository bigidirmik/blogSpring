package blogSpring.business.abstracts;

import java.util.List;

import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.entities.concretes.Image;

public interface ImageService {
	
	Result add(int postId, String url);
	
	Result update(int imageId, String url);
	
	// Custom JPA
	
	DataResult<Image> findById(int imageId);
	
	Result deleteById(int imageId);
	
	DataResult<List<Image>> getByPostId(int postId);

}
