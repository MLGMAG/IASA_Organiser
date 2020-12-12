package ua.kpi.iasa.IASA_Organiser.service;

import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.repository.HumanRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HumanService {

    private final HumanRepository humanRepository;

    private final EventService eventService;

    public HumanService(HumanRepository humanRepository, EventService eventService) {
        this.humanRepository = humanRepository;
        this.eventService = eventService;
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
        Optional<Human> humanById = humanRepository.findById(id);
        if (humanById.isEmpty()) {
            return;
        }
        Human human = humanById.get();
        List<Event> allEvents = eventService.getAllEventsList();
        allEvents.stream().filter(event -> event.getInvited().contains(human)).forEach(event -> {
            event.getInvited().remove(human);
            eventService.createEvent(event);
        });
        humanRepository.delete(human);
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

    public List<Event> findHumanEvents(Human human) {
        return eventService.getAllEventsList().stream()
                .filter(event -> event.getInvited().contains(human))
                .collect(Collectors.toList());
    }

    List<Human> sortHumansByFirstName(List<Human> humans) {
        return humans.stream().
                sorted(Comparator.comparing(Human::getFirstName))
                .collect(Collectors.toList());
    }

}
