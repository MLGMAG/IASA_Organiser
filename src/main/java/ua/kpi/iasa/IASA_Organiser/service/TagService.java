package ua.kpi.iasa.IASA_Organiser.service;

import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Tag;
import ua.kpi.iasa.IASA_Organiser.repository.EventRepository;
import ua.kpi.iasa.IASA_Organiser.repository.TagRepository;

import java.util.List;
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
        eventRepository.findById(eventId).ifPresent(event -> {
            tag.getEvents().add(event);
            tagRepository.save(tag);
            event.getTags().add(tag);
            eventRepository.save(event);
        });
    }

    public void removeTagFromEvent(Tag tag, UUID eventId) {
        tagRepository.delete(tag);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public void removeTagById(UUID tagId) {
        tagRepository.deleteById(tagId);
    }

    public Tag getTagById(UUID id) {
        return tagRepository.findById(id).orElse(null);
    }

    public void createTag(Tag tag) {
        tagRepository.save(tag);
    }

    public void createListTags(List<Tag> tags) {
        tagRepository.saveAll(tags);
    }

    public void deleteListTag(List<Tag> tags) {
        tagRepository.deleteAll(tags);
    }

    public void updateTag(UUID id, Tag tag) {
        tag.setId(id);
        tagRepository.save(tag);
    }
}
