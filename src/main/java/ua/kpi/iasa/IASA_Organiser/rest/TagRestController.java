package ua.kpi.iasa.IASA_Organiser.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.iasa.IASA_Organiser.model.Tag;
import ua.kpi.iasa.IASA_Organiser.service.TagService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/tags")
@ComponentScan(basePackages = "ua.kpi.iasa.IASA_Organiser.service")
public class TagRestController {

    private final TagService tagService;

    public TagRestController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/")
    public List<Tag> getTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable UUID id) {
        return tagService.getTagById(id);
    }

    @PostMapping("/")
    public void createTag(@RequestBody Tag tag) {
        tagService.createTag(tag);
    }

    @PostMapping("/list")
    public void createListTag(@RequestBody List<Tag> tags) {
        tagService.createListTags(tags);
    }

    @DeleteMapping("/{id}")
    public void removeTag(@PathVariable UUID id) {
        tagService.removeTagById(id);
    }

    @DeleteMapping("/list")
    public void deleteListTag(@RequestBody List<Tag> tags) {
        tagService.deleteListTag(tags);
    }

    @PutMapping("/{id}")
    public void updateTag(@PathVariable UUID id, @RequestBody Tag tag) {
        tagService.updateTag(id, tag);
    }

}
