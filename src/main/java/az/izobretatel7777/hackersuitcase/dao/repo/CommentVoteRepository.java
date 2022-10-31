package az.izobretatel7777.hackersuitcase.dao.repo;

import az.izobretatel7777.hackersuitcase.dao.entity.CommentVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentVoteRepository extends JpaRepository<CommentVote, Long> {
    CommentVote findByUser_IdAndComment_Id(Long userId, Long commentId);
}
