package ua.kpi.iasa.IASA_Organiser.service;

import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.repository.HumanRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HumanService {

    private final HumanRepository humanRepository;

    public HumanService(HumanRepository humanRepository, EventService eventService) {
        this.humanRepository = humanRepository;
    }

    public List<Human> getAllHumans() {
        List<Human> humans = humanRepository.findAll();
        return sortHumansByFirstName(humans);
    }

    public Human getHumanById(UUID id) {
        Optional<Human> optHuman = humanRepository.findById(id);
        if (optHuman.isEmpty()) {
            return null;
        }
        Human human = optHuman.get();
        Human humanAndEventsById = humanRepository.getHumanAndEventsById(id);
        if (humanAndEventsById == null) {
            human.setEvents(Collections.emptySet());
            return human;
        }
        return humanAndEventsById;
    }

    public void createHuman(Human human) {
        humanRepository.save(human);
    }

    public void deleteById(UUID id) {
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

}
