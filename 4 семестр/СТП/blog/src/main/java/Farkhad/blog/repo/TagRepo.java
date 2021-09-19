package Farkhad.blog.repo;

import Farkhad.blog.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Long> {
}