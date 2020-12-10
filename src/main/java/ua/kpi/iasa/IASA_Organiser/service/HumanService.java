package ua.kpi.iasa.IASA_Organiser.service;

import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.repository.HumanRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HumanService {

    private final HumanRepository humanRepository;

    public HumanService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    public List<Human> getAllHumans() {
        return humanRepository.findAll();
    }

    public Human getHumanById(UUID id) {
        return humanRepository.findById(id).orElse(null);
    }

    public void createHuman(Human human) {
        humanRepository.save(human);
    }

    public void deleteById(UUID id) {
        humanRepository.deleteById(id);
    }

    public void deleteListHuman(List<Human> humans) {
        humanRepository.deleteAll(humans);
    }

    public void changeEmailById(UUID id, String email) {
        Optional<Human> humanById = humanRepository.findById(id);
        if (humanById.isEmpty()){
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

}
