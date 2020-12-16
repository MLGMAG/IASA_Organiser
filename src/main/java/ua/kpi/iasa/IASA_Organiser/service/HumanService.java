package ua.kpi.iasa.IASA_Organiser.service;

import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.repository.EventRepository;
import ua.kpi.iasa.IASA_Organiser.repository.HumanRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HumanService {

    private final HumanRepository humanRepository;

    private final EventRepository eventRepository;

    public HumanService(HumanRepository humanRepository, EventRepository eventRepository) {
        this.humanRepository = humanRepository;
        this.eventRepository = eventRepository;
    }

    public List<Human> getAllHumans() {
        List<Human> humans = humanRepository.findAll();
        return sortHumansByFirstName(humans);
    }

    public Human getHumanById(UUID id) {
        return humanRepository.findById(id).orElse(null);
    }

    public void createHuman(Human human) {
        humanRepository.save(human);
    }

    public void deleteById(UUID id) {
        Optional<Human> optHuman = humanRepository.findById(id);
        if (optHuman.isEmpty()) {
            return;
        }
        Human human = optHuman.get();
        if (human.getEvents().isEmpty()) {
            humanRepository.deleteById(id);
            return;
        }
        List<Event> allEvents = eventRepository.findAllById(human.getEvents().stream().map(Event::getId).collect(Collectors.toSet()));
        allEvents.forEach(event -> {
            event.getInvited().remove(human);
            eventRepository.save(event);
        });
        humanRepository.deleteById(id);
    }

    public void deleteListHuman(List<Human> humans) {
        humans.stream().map(Human::getId).forEach(this::deleteById);
    }

    public void changeEmailById(UUID id, String email) {
        Optional<Human> humanById = humanRepository.findById(id);
        if (humanById.isEmpty()) {
            return;
        }
        Human human = humanById.get();
        human.setEmail(email);
        humanRepository.save(human);
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
        Optional<Human> optHuman = humanRepository.findById(humanId);
        if (optHuman.isEmpty()) {
            return;
        }
        Optional<Event> optEvent = eventRepository.findById(eventId);
        if (optEvent.isEmpty()) {
            return;
        }
        Human human = optHuman.get();
        Event event = optEvent.get();
        event.getInvited().add(human);
//        human.getEvents().add(event);
//        humanRepository.save(human);
        eventRepository.save(event);
    }
}
