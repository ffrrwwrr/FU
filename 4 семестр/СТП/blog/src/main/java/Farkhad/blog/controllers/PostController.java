package Farkhad.blog.controllers;

import java.util.List;
import Farkhad.blog.models.Post;
import Farkhad.blog.services.PostService;
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
@RequestMapping({"/posts/"})
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> body = this.postService.getPosts();
        return body != null && !body.isEmpty() ? new ResponseEntity(body, HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<Post> getPostById(@PathVariable(name = "id") Long id) {
        Post body = this.postService.getPostById(id);
        return body != null ? new ResponseEntity(body, HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void createPost(@RequestBody Post post) {
        this.postService.createPost(post);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<?> updatePost(@RequestBody Post post, @PathVariable(name = "id") Long id) {
        return this.postService.updatePost(post, id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") Long id) {
        return this.postService.deletePost(id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

