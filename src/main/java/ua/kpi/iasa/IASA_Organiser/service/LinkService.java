package ua.kpi.iasa.IASA_Organiser.service;

import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Link;
import ua.kpi.iasa.IASA_Organiser.repository.EventRepository;
import ua.kpi.iasa.IASA_Organiser.repository.LinkRepository;

import java.util.List;
import java.util.UUID;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    private final EventRepository eventRepository;

    public LinkService(LinkRepository linkRepository, EventRepository eventRepository) {
        this.linkRepository = linkRepository;
        this.eventRepository = eventRepository;
    }

    public void addLinkToEvent(Link link, UUID eventId) {
        eventRepository.findById(eventId).ifPresent(event -> {
            link.getEvents().add(event);
            linkRepository.save(link);
            event.getLinks().add(link);
            eventRepository.save(event);
        });
    }

    public void removeLinkFromEvent(Link link, UUID eventId) {
        linkRepository.delete(link);
    }

    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }

    public Link getLinkById(UUID id) {
        return linkRepository.findById(id).orElse(null);
    }

    public void createLink(Link link) {
        linkRepository.save(link);
    }

    public void createListLink(List<Link> links) {
        linkRepository.saveAll(links);
    }

    public void removeLinkById(UUID id) {
        linkRepository.deleteById(id);
    }

    public void deleteListLink(List<Link> links) {
        linkRepository.deleteAll(links);
    }

    public void updateLink(UUID id, Link link) {
        link.setId(id);
        linkRepository.save(link);
    }
}
