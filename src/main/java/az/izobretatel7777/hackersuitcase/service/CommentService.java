package az.izobretatel7777.hackersuitcase.service;

import az.izobretatel7777.hackersuitcase.dao.entity.Comment;

import java.util.List;

public interface CommentService {
    boolean deleteComment(long id);
    void likeComment(long id);
    void dislikeComment(long id);
}
