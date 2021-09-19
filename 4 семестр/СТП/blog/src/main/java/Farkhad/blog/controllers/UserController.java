package Farkhad.blog.controllers;

import java.util.List;
import Farkhad.blog.models.User;
import Farkhad.blog.services.UserService;
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
@RequestMapping({"/users/"})
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> body = this.userService.getUsers();
        return body != null && !body.isEmpty() ? new ResponseEntity(body, HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
        User body = this.userService.getUserById(id);
        return body != null ? new ResponseEntity(body, HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        this.userService.createUser(user);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable(name = "id") Long id) {
        return this.userService.updateUser(user, id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        return this.userService.deleteUser(id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
