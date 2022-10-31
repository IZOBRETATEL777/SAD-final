package az.izobretatel7777.hackersuitcase.service.implementations;

import az.izobretatel7777.hackersuitcase.dao.entity.Comment;
import az.izobretatel7777.hackersuitcase.dao.entity.CommentVote;
import az.izobretatel7777.hackersuitcase.dao.entity.User;
import az.izobretatel7777.hackersuitcase.dao.repo.CommentRepository;
import az.izobretatel7777.hackersuitcase.dao.repo.CommentVoteRepository;
import az.izobretatel7777.hackersuitcase.dao.repo.UserRepository;
import az.izobretatel7777.hackersuitcase.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentVoteRepository commentVoteRepository;

    @Override
    public boolean deleteComment(long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Comment comment = commentRepository.getById(id);
        if (!auth.getName().equals(comment.getAuthor().getEmail()))
            return false;
        commentRepository.delete(comment);
        return true;
    }

    public void likeComment(long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Comment comment = commentRepository.getById(id);
        User user = userRepository.findByEmail(auth.getName());
        CommentVote commentVote = commentVoteRepository.findByUser_IdAndComment_Id(user.getId(), comment.getId());
        if (commentVote == null) {
            commentVote = CommentVote.builder().user(user).comment(comment).reaction("like").build();
        }
        else if (!commentVote.getReaction().equals("like")) {
            commentVote.setReaction("like");
        }
        else {
            return;
        }
        Long rating = comment.getRating() + user.getVoteWeight();
        comment.setRating(rating);
        commentRepository.save(comment);
        commentVoteRepository.save(commentVote);
    }

    @Override
    public void dislikeComment(long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Comment comment = commentRepository.getById(id);
        User user = userRepository.findByEmail(auth.getName());
        CommentVote commentVote = commentVoteRepository.findByUser_IdAndComment_Id(user.getId(), comment.getId());
        if (commentVote == null) {
            commentVote = CommentVote.builder().user(user).comment(comment).reaction("dislike").build();
        }
        else if (!commentVote.getReaction().equals("dislike")) {
            commentVote.setReaction("dislike");
        }
        else {
            return;
        }
        Long rating = comment.getRating() - user.getVoteWeight();
        comment.setRating(rating);
        commentRepository.save(comment);
        commentVoteRepository.save(commentVote);
    }
}
