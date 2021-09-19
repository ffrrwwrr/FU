package Farkhad.blog.models;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String slug;
    private String text;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private User author;

    @ManyToMany
    private Set<User> likes;

    @ManyToMany
    private Set<Tag> tags;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<User> getLikes() {
        return this.likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Post)) {
            return false;
        } else {
            Post other = (Post)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label107: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label107;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label107;
                    }

                    return false;
                }

                Object this$title = this.getTitle();
                Object other$title = other.getTitle();
                if (this$title == null) {
                    if (other$title != null) {
                        return false;
                    }
                } else if (!this$title.equals(other$title)) {
                    return false;
                }

                Object this$slug = this.getSlug();
                Object other$slug = other.getSlug();
                if (this$slug == null) {
                    if (other$slug != null) {
                        return false;
                    }
                } else if (!this$slug.equals(other$slug)) {
                    return false;
                }

                label86: {
                    Object this$text = this.getText();
                    Object other$text = other.getText();
                    if (this$text == null) {
                        if (other$text == null) {
                            break label86;
                        }
                    } else if (this$text.equals(other$text)) {
                        break label86;
                    }

                    return false;
                }

                label79: {
                    Object this$author = this.getAuthor();
                    Object other$author = other.getAuthor();
                    if (this$author == null) {
                        if (other$author == null) {
                            break label79;
                        }
                    } else if (this$author.equals(other$author)) {
                        break label79;
                    }

                    return false;
                }

                label72: {
                    Object this$likes = this.getLikes();
                    Object other$likes = other.getLikes();
                    if (this$likes == null) {
                        if (other$likes == null) {
                            break label72;
                        }
                    } else if (this$likes.equals(other$likes)) {
                        break label72;
                    }

                    return false;
                }

                Object this$tags = this.getTags();
                Object other$tags = other.getTags();
                if (this$tags == null) {
                    if (other$tags != null) {
                        return false;
                    }
                } else if (!this$tags.equals(other$tags)) {
                    return false;
                }

                Object this$comments = this.getComments();
                Object other$comments = other.getComments();
                if (this$comments == null) {
                    if (other$comments != null) {
                        return false;
                    }
                } else if (!this$comments.equals(other$comments)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Post;
    }

    public String toString() {
        Long id= this.getId();
        return "Post(id = " + id + ", title = " + this.getTitle() + ", slug = " + this.getSlug() + ", text = " + this.getText() + ", author = " + this.getAuthor() + ", likes = " + this.getLikes() + ", tags = " + this.getTags() + ", comments = " + this.getComments() + ")";
    }

    public Post() {
    }
}
