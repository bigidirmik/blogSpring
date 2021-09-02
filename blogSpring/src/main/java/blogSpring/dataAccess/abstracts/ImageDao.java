package blogSpring.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import blogSpring.entities.concretes.Image;

public interface ImageDao extends JpaRepository<Image, Integer> {
	
	Image getByPost_Id(int postId); // ileride post'a ait birden fazla görsel eklenesi durumunda List<Image> olarak değiştirilecek.
	
	Image deleteById(int imageId);

}
