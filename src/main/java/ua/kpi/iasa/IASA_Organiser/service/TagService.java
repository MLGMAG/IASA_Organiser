package ua.kpi.iasa.IASA_Organiser.service;

import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Tag;
import ua.kpi.iasa.IASA_Organiser.repository.EventRepository;
import ua.kpi.iasa.IASA_Organiser.repository.TagRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class TagService {

    private final TagRepository tagRepository;

    private final EventRepository eventRepository;

    public TagService(TagRepository tagRepository, EventRepository eventRepository) {
        this.tagRepository = tagRepository;
        this.eventRepository = eventRepository;
    }

    public void addTagToEvent(Tag tag, UUID eventId) {
        Optional<Event> optEvent = eventRepository.findById(eventId);
        if (optEvent.isEmpty()) {
            return;
        }
        Event event = optEvent.get();
        tag.getEvents().add(event);
        tagRepository.save(tag);
        event.getTags().add(tag);
        eventRepository.save(event);
    }

    public void removeTagFromEvent(Tag tag, UUID eventId) {
        tagRepository.delete(tag);
    }

}
