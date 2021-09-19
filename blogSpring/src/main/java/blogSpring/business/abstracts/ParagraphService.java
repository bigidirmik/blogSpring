package blogSpring.business.abstracts;

import java.util.List;

import blogSpring.core.utilities.results.DataResult;
import blogSpring.core.utilities.results.Result;
import blogSpring.entities.concretes.Paragraph;

public interface ParagraphService {
	
	Result add(int postId, String subTitle, String content);
	
	//Custom JPA
	
	Result deleteById(int paragraphId);
	
	DataResult<List<Paragraph>> getByPostId(int postId);

}
