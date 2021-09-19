package blogSpring.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogSpring.business.abstracts.ParagraphService;
import blogSpring.core.utilities.constants.Messages;
import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.core.utilities.results.SuccessDataResult;
import blogSpring.core.utilities.results.SuccessResult;
import blogSpring.dataAccess.abstracts.ParagraphDao;
import blogSpring.dataAccess.abstracts.PostDao;
import blogSpring.entities.concretes.Paragraph;
import blogSpring.entities.concretes.Post;

@Service
public class ParagraphManager implements ParagraphService {
	
	private ParagraphDao paragraphDao;
	private PostDao postDao;
	
	@Autowired
	public ParagraphManager(ParagraphDao paragraphDao, PostDao postDao) {
		super();
		this.paragraphDao = paragraphDao;
		this.postDao = postDao;
	}

	@Override
	public Result add(int postId, String subTitle, String content) {
		Post post = this.postDao.findById(postId);
		Paragraph paragraph = new Paragraph();
		paragraph.setPost(post);
		paragraph.setSubTitle(subTitle);
		paragraph.setContent(content);
		this.paragraphDao.save(paragraph);
		return new SuccessResult(Messages.added);
	}

	@Override
	public Result deleteById(int paragraphId) {
		this.paragraphDao.deleteById(paragraphId);
		return new SuccessResult(Messages.deleted);
	}

	@Override
	public DataResult<List<Paragraph>> getByPostId(int postId) {
		return new SuccessDataResult<List<Paragraph>>(this.paragraphDao.getByPost_Id(postId),Messages.listed);
	}

}
