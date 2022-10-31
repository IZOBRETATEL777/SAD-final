package az.izobretatel7777.hackersuitcase.dao.repo;

import az.izobretatel7777.hackersuitcase.dao.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
