package blogSpring.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogSpring.business.abstracts.ImageService;
import blogSpring.core.utilities.constants.Messages;
import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.core.utilities.results.SuccessDataResult;
import blogSpring.core.utilities.results.SuccessResult;
import blogSpring.dataAccess.abstracts.ImageDao;
import blogSpring.dataAccess.abstracts.PostDao;
import blogSpring.entities.concretes.Image;
import blogSpring.entities.concretes.Post;

@Service
public class ImageManager implements ImageService {
	
	private ImageDao imageDao;
	private PostDao postDao;
//	private CloudinaryService cloudinaryService;
	
	@Autowired
	public ImageManager(ImageDao imageDao,PostDao postDao) {
		super();
		this.imageDao = imageDao;
		this.postDao = postDao;
//		this.cloudinaryService = cloudinaryService;
	}
	
	@Override
	public Result add(int postId, String url) {
		Post post = this.postDao.findById(postId);
		Image image = new Image();
		image.setPost(post);
		image.setUrl(url);
		this.imageDao.save(image);
		return new SuccessResult(Messages.added);
	}
	
	@Override
	public Result update(int postId, String url) {
		Image image = this.getByPostId(postId).getData();
		image.setUrl(url);
		this.imageDao.save(image);
		return new SuccessResult(Messages.updated);
	}

//	@Override
//	public Result add(Image image, MultipartFile imageFile) {
//		@SuppressWarnings("unchecked")
//		Map<String,String> uploadImage = this.cloudinaryService.upload(imageFile).getData();
//		image.setUrl(uploadImage.get("url"));
//		this.imageDao.save(image);
//		return new SuccessResult(Messages.added);
//	}
//
//	@Override
//	public Result update(Image image, MultipartFile imageFile) {
//		@SuppressWarnings("unchecked")
//		Map<String,String> uploadImage = this.cloudinaryService.upload(imageFile).getData();
//		image.setUrl(uploadImage.get("url"));
//		this.imageDao.save(image);
//		return new SuccessResult(Messages.updated);
//	}
	
	// Custom JPA

	@Override
	public Result deleteById(int imageId) {
		this.imageDao.deleteById(imageId);
		return new SuccessResult(Messages.deleted);
	}

	@Override
	public DataResult<Image> getByPostId(int postId) {
		return new SuccessDataResult<Image>(this.imageDao.getByPost_Id(postId),Messages.found);
	}

}
