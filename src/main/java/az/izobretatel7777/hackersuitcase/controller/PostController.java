package az.izobretatel7777.hackersuitcase.controller;

import az.izobretatel7777.hackersuitcase.dao.entity.Post;
import az.izobretatel7777.hackersuitcase.dao.repo.CommentRepository;
import az.izobretatel7777.hackersuitcase.dto.NewCommentForm;
import az.izobretatel7777.hackersuitcase.dto.NewPostForm;
import az.izobretatel7777.hackersuitcase.service.CommentService;
import az.izobretatel7777.hackersuitcase.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class PostController {
    private final PostService  postService;
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllPosts(Model model) {
        var posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id,
                         RedirectAttributes model) {
        if (!postService.deletePostById(id))
            return "redirect:/posts";
        return "redirect:/posts";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getPostById(@PathVariable long id,
                              Authentication authentication,
                              Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("authentication", authentication);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentRepository.findByPostId(post.getId()));
        model.addAttribute("newComment", new NewCommentForm());
        return "post";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public String addComment(@Valid @ModelAttribute("newComment") NewCommentForm newComment,
                          BindingResult result,
                          @PathVariable long id,
                          Model model) {

        if (result.hasErrors()) {
            model.addAttribute("post", postService.getPostById(id));
            model.addAttribute("comments", commentRepository.findByPostId(id));
            return "index";
        }
        commentService.addComment(id, newComment.getContent());
        model.asMap().clear();
        return "redirect:/posts/" + id;
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String getNewPostForm(Model model) {
        model.addAttribute("newPost", new NewPostForm());
        return "post_form";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String processAndAddNewPost(@Valid @ModelAttribute("newPost") NewPostForm newPost,
                                        BindingResult result,
                                        Model model) {

        if (result.hasErrors()) {
            return "post_form";
        }
        postService.savePost(newPost.getTitle(), newPost.getContent());
        return "redirect:/posts";
    }
}
