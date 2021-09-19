package Farkhad.blog.repo;

import Farkhad.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
