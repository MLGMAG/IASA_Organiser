package ua.kpi.iasa.IASA_Organiser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kpi.iasa.IASA_Organiser.model.Tag;
import ua.kpi.iasa.IASA_Organiser.service.TagService;

import java.util.UUID;

@Controller
@RequestMapping("/tags/")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/{id}/add")
    public String addTagToEvent(@PathVariable UUID id, @ModelAttribute("Tag") Tag tag) {
        tagService.addTagToEvent(tag, id);
        return "redirect:/events/" + id;
    }

    @PostMapping("/{id}/remove")
    public String removeTagFromEvent(@PathVariable UUID id, @ModelAttribute("Tag") Tag tag) {
        tagService.removeTagFromEvent(tag, id);
        return "redirect:/events/" + id;
    }

}
