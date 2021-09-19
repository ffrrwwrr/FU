package Farkhad.blog.services;

import java.util.List;
import Farkhad.blog.models.Comment;
import Farkhad.blog.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public void createComment(Comment comment) {
        this.commentRepo.save(comment);
    }

    public Comment getCommentById(Long id) {
        return (Comment)this.commentRepo.findById(id).orElse((Comment) null);
    }

    public List<Comment> getComments() {
        return this.commentRepo.findAll();
    }

    public boolean updateComment(Comment comment, Long id) {
        if (this.getCommentById(id) != null) {
            comment.setId(id);
            this.commentRepo.save(comment);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteComment(Long id) {
        if (this.getCommentById(id) != null) {
            this.commentRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
