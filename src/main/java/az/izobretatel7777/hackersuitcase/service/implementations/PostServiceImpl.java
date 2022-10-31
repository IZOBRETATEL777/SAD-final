package az.izobretatel7777.hackersuitcase.service.implementations;

import az.izobretatel7777.hackersuitcase.dao.entity.Post;
import az.izobretatel7777.hackersuitcase.dao.repo.PostRepository;
import az.izobretatel7777.hackersuitcase.dao.repo.UserRepository;
import az.izobretatel7777.hackersuitcase.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public boolean deletePostById(long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Post post = postRepository.getById(id);
        if (!post.getAuthor().getEmail().equals(auth.getName())) {
            return false;
        }
        postRepository.deleteById(id);
        return true;
    }

    public Post getPostById(long id) {
        return postRepository.findById(id).orElseThrow();
    }

    public void savePost(String title, String content) {
        Post post = new Post();
        Authentication auth  = SecurityContextHolder.getContext().getAuthentication();
        post.setAuthor(userRepository.findByEmail(auth.getName()));
        post.setTitle(title);
        post.setContent(content);
        post.setCreated(new Timestamp(System.currentTimeMillis()));
        postRepository.save(post);
        postRepository.save(post);
    }

}
