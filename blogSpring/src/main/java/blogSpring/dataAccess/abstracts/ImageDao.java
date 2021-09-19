package blogSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blogSpring.entities.concretes.Image;

public interface ImageDao extends JpaRepository<Image, Integer> {
	
	Image findById(int imageId);
	
	List<Image> getByPost_Id(int postId);
	
	Image deleteById(int imageId);

}
