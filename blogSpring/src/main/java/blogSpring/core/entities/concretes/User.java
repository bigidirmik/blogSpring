package blogSpring.core.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import blogSpring.entities.concretes.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Email
	@NotBlank
	@NotNull
	@Column(name = "email")
	private String email;
	
	@NotBlank
	@NotNull
	@Column(name = "password")
	private String password;
	
	@NotBlank
	@NotNull
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank
	@NotNull
	@Column(name = "last_name")
	private String lastName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Post> posts;

}
