package az.izobretatel7777.hackersuitcase.security;

import az.izobretatel7777.hackersuitcase.dao.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    boolean deletePostById(long id);
    Post getPostById(long id);
    void savePost(String title, String content);
}
