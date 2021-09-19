package Farkhad.blog.services;

import java.util.List;
import Farkhad.blog.models.User;
import Farkhad.blog.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void createUser(User user) {
        this.userRepo.save(user);
    }

    public User getUserById(Long id) {
        return (User)this.userRepo.findById(id).orElse((User) null);
    }

    public List<User> getUsers() {
        return this.userRepo.findAll();
    }

    public boolean updateUser(User user, Long id) {
        if (this.getUserById(id) != null) {
            user.setId(id);
            this.userRepo.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUser(Long id) {
        if (this.getUserById(id) != null) {
            this.userRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

