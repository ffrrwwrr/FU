package Farkhad.blog.services;

import java.util.List;
import Farkhad.blog.models.Post;
import Farkhad.blog.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private PostRepo postRepo;

    @Autowired
    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public void createPost(Post post) {
        this.postRepo.save(post);
    }

    public Post getPostById(Long id) {
        return (Post)this.postRepo.findById(id).orElse((Post) null);
    }

    public List<Post> getPosts() {
        return this.postRepo.findAll();
    }

    public boolean updatePost(Post post, Long id) {
        if (this.getPostById(id) != null) {
            post.setId(id);
            this.postRepo.save(post);
            return true;
        } else {
            return false;
        }
    }

    public boolean deletePost(Long id) {
        if (this.getPostById(id) != null) {
            this.postRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}