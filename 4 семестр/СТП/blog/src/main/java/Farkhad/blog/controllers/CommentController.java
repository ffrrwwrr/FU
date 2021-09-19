package Farkhad.blog.controllers;

import java.util.List;
import Farkhad.blog.models.Comment;
import Farkhad.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/comments/"})
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> body = this.commentService.getComments();
        return body != null && !body.isEmpty() ? new ResponseEntity(body, HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<Comment> getCommentById(@PathVariable(name = "id") Long id) {
        Comment body = this.commentService.getCommentById(id);
        return body != null ? new ResponseEntity(body, HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void createComment(@RequestBody Comment comment) {
        this.commentService.createComment(comment);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<?> updateComment(@RequestBody Comment comment, @PathVariable(name = "id") Long id) {
        return this.commentService.updateComment(comment, id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<?> deleteComment(@PathVariable(name = "id") Long id) {
        return this.commentService.deleteComment(id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
