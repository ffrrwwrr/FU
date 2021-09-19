package Farkhad.blog.models;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String slug;
    @ManyToMany
    private Set<Post> posts;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Set<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Tag)) {
            return false;
        } else {
            Tag other = (Tag)o;
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

                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
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

                Object this$posts = this.getPosts();
                Object other$posts = other.getPosts();
                if (this$posts == null) {
                    if (other$posts != null) {
                        return false;
                    }
                } else if (!this$posts.equals(other$posts)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Tag;
    }



    public String toString() {
        Long id = this.getId();
        return "Tag(id = " + id + ", name = " + this.getName() + ", slug = " + this.getSlug() + ", posts = " + this.getPosts() + ")";
    }

    public Tag() {
    }
}
