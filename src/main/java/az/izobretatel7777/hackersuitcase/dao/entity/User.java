package az.izobretatel7777.hackersuitcase.dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class User {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(nullable = false, unique = true)
	@Email
	@NotBlank
	@Size(max = 255)
	String email;

	@Column(nullable = false)
	@NotBlank
	@Size(min = 6, max = 255)
	String password;

	@Column(name = "first_name", columnDefinition = "varchar(30)")
	@Size(max = 30)
	String firstName;

	@Column(name = "last_name", columnDefinition = "varchar(50)")
	@Size(max = 50)
	String lastName;

	@Column(columnDefinition = "varchar(50)")
	@Size(max = 50)
	String company;

	@Column(name = "phone_number", columnDefinition = "varchar(15)")
	@Pattern(regexp = "(^[0-9,\\-\\+]{9,15}$|^$)")
	String phoneNumber;

	@Column(name = "OTP")
	@Pattern(regexp = "^[0-9]{6}$")
	String otp;

	@Column(name = "active", nullable = false, columnDefinition = "boolean default false")
	boolean active = false;

	@Column(name = "role", nullable = false, columnDefinition = "varchar(10) default 'USER'")
	String role = "USER";

	@Column(columnDefinition = "int default 1", nullable = false)
	Integer voteWeight = 1;

	@OneToMany(mappedBy = "author")
	List<Comment> comments;

	@OneToMany(mappedBy = "author")
	List<Post> posts;

	@OneToMany(mappedBy = "user")
	List<CommentVote> commentVotes;
}
