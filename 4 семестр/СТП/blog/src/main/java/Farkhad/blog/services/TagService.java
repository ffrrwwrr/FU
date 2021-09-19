package Farkhad.blog.services;

import java.util.List;
import Farkhad.blog.models.Tag;
import Farkhad.blog.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    private TagRepo tagRepo;

    @Autowired
    public TagService(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    public void createTag(Tag tag) {
        this.tagRepo.save(tag);
    }

    public Tag getTagById(Long id) {
        return (Tag)this.tagRepo.findById(id).orElse((Tag) null);
    }

    public List<Tag> getTags() {
        return this.tagRepo.findAll();
    }

    public boolean updateTag(Tag tag, Long id) {
        if (this.getTagById(id) != null) {
            tag.setId(id);
            this.tagRepo.save(tag);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteTag(Long id) {
        if (this.getTagById(id) != null) {
            this.tagRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
