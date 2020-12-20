package ua.kpi.iasa.IASA_Organiser.service;

import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.repository.EventRepository;
import ua.kpi.iasa.IASA_Organiser.repository.HumanRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HumanService {

    private final HumanRepository humanRepository;

    private final EventRepository eventRepository;

    private final EventService eventService;

    public HumanService(HumanRepository humanRepository, EventRepository eventRepository, EventService eventService) {
        this.humanRepository = humanRepository;
        this.eventRepository = eventRepository;
        this.eventService = eventService;
    }

    public List<Human> getAllHumans() {
        List<Human> humans = humanRepository.findAll();
        return sortHumansByFirstName(humans);
    }

    public Human getHumanById(UUID id) {
        Human human = humanRepository.findById(id).orElse(null);
        if (human == null) {
            return null;
        }
        human.setEvents(Collections.emptySet());
        return human;
    }

    public Human getHumanAndEventsByHumanId(UUID id) {
        return humanRepository.findHumanAndEvents(id).orElseGet(() -> getHumanById(id));
    }

    public void createHuman(Human human) {
        humanRepository.save(human);
    }

    public void deleteById(UUID id) {
        humanRepository.findHumanAndEvents(id).ifPresent(human -> human.getEvents().forEach(event -> {
            Event eventWithHumans = eventService.getEventAndHumansByEventId(event.getId());
            eventWithHumans.getInvited().remove(human);
            eventRepository.save(eventWithHumans);
        }));
        humanRepository.deleteById(id);
    }

    public void deleteListHuman(List<Human> humans) {
        humans.stream().map(Human::getId).forEach(this::deleteById);
    }

    public void changeEmailById(UUID id, String email) {
        humanRepository.findById(id).ifPresent(human -> {
            human.setEmail(email);
            humanRepository.save(human);
        });
    }

    public void updateHuman(UUID id, Human human) {
        human.setId(id);
        humanRepository.save(human);
    }

    public void createListHuman(List<Human> humans) {
        humanRepository.saveAll(humans);
    }

    List<Human> sortHumansByFirstName(List<Human> humans) {
        return humans.stream().
                sorted(Comparator.comparing(Human::getFirstName))
                .collect(Collectors.toList());
    }

    public void joinEvent(UUID humanId, UUID eventId) {
        humanRepository.findById(humanId).ifPresent(human -> {
            Event event = eventService.getEventAndHumansByEventId(eventId);
            event.getInvited().add(human);
            eventRepository.save(event);
        });
    }

    public void leaveEvent(UUID humanId, UUID eventId) {
        humanRepository.findById(humanId).ifPresent(human -> {
            Event event = eventService.getEventAndHumansByEventId(eventId);
            event.getInvited().remove(human);
            eventRepository.save(event);
        });
    }
}
