package blogSpring.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import blogSpring.core.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotBlank
	@NotNull
	@Column(name = "title")
	private String title;
	
	@NotBlank
	@NotNull
	@Column(name = "content")
	private String content;
	
	
	@Column(name = "create_date")
	private LocalDate createDate = LocalDate.now();
	
	@Column(name = "is_active")
	private boolean isActive;
	
	
	// ORM
	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToOne(mappedBy = "post")
	private Image image;
	
}