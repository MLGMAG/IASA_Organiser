package ua.kpi.iasa.IASA_Organiser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kpi.iasa.IASA_Organiser.model.Link;
import ua.kpi.iasa.IASA_Organiser.service.LinkService;

import java.util.UUID;

@Controller
@RequestMapping("/links")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/{id}/add")
    public String addLinkToEvent(@PathVariable UUID id, @ModelAttribute("Link") Link link) {
        linkService.addLinkToEvent(link, id);
        return "redirect:/events/" + id;
    }

    @PostMapping("/{id}/remove")
    public String removeLinkFromEvent(@PathVariable UUID id, @ModelAttribute("Link") Link link) {
        linkService.removeLinkFromEvent(link, id);
        return "redirect:/events/" + id;
    }

}
