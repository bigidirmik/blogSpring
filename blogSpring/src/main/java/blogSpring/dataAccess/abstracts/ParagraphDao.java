package blogSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blogSpring.entities.concretes.Paragraph;

public interface ParagraphDao extends JpaRepository<Paragraph, Integer> {
	
	Paragraph deleteById(int paragraphId);
	
	List<Paragraph> getByPost_Id(int postId);

}
