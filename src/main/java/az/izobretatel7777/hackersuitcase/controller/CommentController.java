package az.izobretatel7777.hackersuitcase.controller;

import az.izobretatel7777.hackersuitcase.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/comments")
public class CommentController {
    private final CommentService commentService;

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id) {
        commentService.deleteComment(id);
        return "redirect:/posts";
    }

    @RequestMapping(value = "like/{id}", method = RequestMethod.GET)
    public String like(@PathVariable long id) {
        commentService.likeComment(id);
        return "redirect:/posts";
    }

    @RequestMapping(value = "dislike/{id}", method = RequestMethod.GET)
    public String dislike(@PathVariable long id) {
        commentService.dislikeComment(id);
        return "redirect:/posts";
    }
}
