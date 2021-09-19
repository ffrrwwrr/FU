package Farkhad.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @ManyToOne
    @JoinColumn(
            name = "author",
            nullable = false
    )
    private User author;
    private String text;
    @ManyToOne
    @JoinColumn(
            name = "post",
            nullable = false
    )
    private Post post;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Comment)) {
            return false;
        } else {
            Comment other = (Comment)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label59;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label59;
                    }

                    return false;
                }

                Object this$author = this.getAuthor();
                Object other$author = other.getAuthor();
                if (this$author == null) {
                    if (other$author != null) {
                        return false;
                    }
                } else if (!this$author.equals(other$author)) {
                    return false;
                }

                Object this$text = this.getText();
                Object other$text = other.getText();
                if (this$text == null) {
                    if (other$text != null) {
                        return false;
                    }
                } else if (!this$text.equals(other$text)) {
                    return false;
                }

                Object this$post = this.getPost();
                Object other$post = other.getPost();
                if (this$post == null) {
                    if (other$post != null) {
                        return false;
                    }
                } else if (!this$post.equals(other$post)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Comment;
    }

    public String toString() {
        Long id = this.getId();
        return "Comment(id = " + id + ", author = " + this.getAuthor() + ", text = " + this.getText() + ", post = " + this.getPost() + ")";
    }

    public Comment() {
    }
}