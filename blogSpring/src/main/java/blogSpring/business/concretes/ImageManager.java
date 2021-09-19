package blogSpring.business.concretes;

import java.util.List;

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
	
	@Autowired
	public ImageManager(ImageDao imageDao,PostDao postDao) {
		super();
		this.imageDao = imageDao;
		this.postDao = postDao;
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
	public Result update(int imageId, String url) {
		Image image = this.imageDao.findById(imageId);
		image.setUrl(url);
		this.imageDao.save(image);
		return new SuccessResult(Messages.updated);
	}
	
	// Custom JPA
	
	@Override
	public DataResult<Image> findById(int imageId) {
		return new SuccessDataResult<Image>(this.imageDao.findById(imageId),Messages.found);
	}

	@Override
	public Result deleteById(int imageId) {
		this.imageDao.deleteById(imageId);
		return new SuccessResult(Messages.deleted);
	}

	@Override
	public DataResult<List<Image>> getByPostId(int postId) {
		return new SuccessDataResult<List<Image>>(this.imageDao.getByPost_Id(postId),Messages.found);
	}

}
