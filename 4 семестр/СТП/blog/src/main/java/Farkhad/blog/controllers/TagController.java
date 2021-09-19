package Farkhad.blog.controllers;

import java.util.List;
import Farkhad.blog.models.Tag;
import Farkhad.blog.services.TagService;
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
@RequestMapping({"/tags/"})
public class TagController {
    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getTags() {
        List<Tag> body = this.tagService.getTags();
        return body != null && !body.isEmpty() ? new ResponseEntity(body, HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<Tag> getTagById(@PathVariable(name = "id") Long id) {
        Tag body = this.tagService.getTagById(id);
        return body != null ? new ResponseEntity(body, HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void createTag(@RequestBody Tag tag) {
        this.tagService.createTag(tag);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<?> updateTag(@RequestBody Tag tag, @PathVariable(name = "id") Long id) {
        return this.tagService.updateTag(tag, id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<?> deleteTag(@PathVariable(name = "id") Long id) {
        return this.tagService.deleteTag(id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

