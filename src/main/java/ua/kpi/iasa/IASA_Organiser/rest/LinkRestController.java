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
import ua.kpi.iasa.IASA_Organiser.model.Link;
import ua.kpi.iasa.IASA_Organiser.service.LinkService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/tags")
@ComponentScan(basePackages = "ua.kpi.iasa.IASA_Organiser.service")
public class LinkRestController {

    private final LinkService linkService;

    public LinkRestController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/")
    public List<Link> getLink() {
        return linkService.getAllLinks();
    }

    @GetMapping("/{id}")
    public Link getLinkById(@PathVariable UUID id) {
        return linkService.getLinkById(id);
    }

    @PostMapping("/")
    public void createLink(@RequestBody Link link) {
        linkService.createLink(link);
    }

    @PostMapping("/list")
    public void createListLink(@RequestBody List<Link> links) {
        linkService.createListLink(links);
    }

    @DeleteMapping("/{id}")
    public void removeLink(@PathVariable UUID id) {
        linkService.removeLinkById(id);
    }

    @DeleteMapping("/list")
    public void deleteListLink(@RequestBody List<Link> links) {
        linkService.deleteListLink(links);
    }

    @PutMapping("/{id}")
    public void updateLink(@PathVariable UUID id, @RequestBody Link link) {
        linkService.updateLink(id, link);
    }

}
