package az.izobretatel7777.hackersuitcase.dao.repo;

import az.izobretatel7777.hackersuitcase.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);

	User findByOtp(String otp);
	
}
