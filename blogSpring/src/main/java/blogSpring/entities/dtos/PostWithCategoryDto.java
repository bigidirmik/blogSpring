package blogSpring.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostWithCategoryDto { // Inactive Class
	
	private int id;
	
	private String title;
	
	private String content;
	
	private LocalDate createDate = LocalDate.now();
	
	private String categoryName;

}
