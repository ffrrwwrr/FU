package Farkhad.blog.models;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            unique = true
    )
    private String login;
    @Column(
            unique = true
    )
    private String email;
    private String password;
    private boolean isActive;
    @OneToMany(
            mappedBy = "author"
    )
    private Set<Post> userPosts;
    @OneToMany(
            mappedBy = "author"
    )
    private Set<Comment> comments;
    @ManyToMany
    private Set<User> likedPosts;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public Set<Post> getUserPosts() {
        return this.userPosts;
    }

    public void setUserPosts(Set<Post> userPosts) {
        this.userPosts = userPosts;
    }

    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<User> getLikedPosts() {
        return this.likedPosts;
    }

    public void setLikedPosts(Set<User> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        } else {
            User other = (User)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isActive() != other.isActive()) {
                return false;
            } else {
                label97: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label97;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label97;
                    }

                    return false;
                }

                Object this$login = this.getLogin();
                Object other$login = other.getLogin();
                if (this$login == null) {
                    if (other$login != null) {
                        return false;
                    }
                } else if (!this$login.equals(other$login)) {
                    return false;
                }

                Object this$email = this.getEmail();
                Object other$email = other.getEmail();
                if (this$email == null) {
                    if (other$email != null) {
                        return false;
                    }
                } else if (!this$email.equals(other$email)) {
                    return false;
                }

                label76: {
                    Object this$password = this.getPassword();
                    Object other$password = other.getPassword();
                    if (this$password == null) {
                        if (other$password == null) {
                            break label76;
                        }
                    } else if (this$password.equals(other$password)) {
                        break label76;
                    }

                    return false;
                }

                Object this$userPosts = this.getUserPosts();
                Object other$userPosts = other.getUserPosts();
                if (this$userPosts == null) {
                    if (other$userPosts != null) {
                        return false;
                    }
                } else if (!this$userPosts.equals(other$userPosts)) {
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

                Object this$likedPosts = this.getLikedPosts();
                Object other$likedPosts = other.getLikedPosts();
                if (this$likedPosts == null) {
                    if (other$likedPosts != null) {
                        return false;
                    }
                } else if (!this$likedPosts.equals(other$likedPosts)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public String toString() {
        Long id= this.getId();
        return "User(id = " + id + ", login = " + this.getLogin() + ", email = " + this.getEmail() + ", password = " + this.getPassword() + ", isActive = " + this.isActive() + ", userPosts = " + this.getUserPosts() + ", comments = " + this.getComments() + ", likedPosts = " + this.getLikedPosts() + ")";
    }

    public User() {
    }
}
