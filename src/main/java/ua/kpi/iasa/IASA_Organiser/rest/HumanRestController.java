package ua.kpi.iasa.IASA_Organiser.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.service.HumanService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/humans")
@ComponentScan(basePackages = "ua.kpi.iasa.IASA_Organiser.service")
public class HumanRestController {

    private final HumanService humanService;

    public HumanRestController(HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping("/")
    public List<Human> getHumans() {
        return humanService.getAllHumans();
    }

    @GetMapping("/{id}")
    public Human getHumanById(@PathVariable UUID id) {
        return humanService.getHumanById(id);
    }

    @PostMapping("/")
    public void createHuman(@RequestBody Human human) {
        humanService.createHuman(human);
    }

    @PostMapping("/list")
    public void createListHuman(@RequestBody List<Human> humans) {
        humanService.createListHuman(humans);
    }

    @DeleteMapping("/{id}")
    public void deleteHuman(@PathVariable UUID id) {
        humanService.deleteById(id);
    }

    @DeleteMapping("/list")
    public void deleteListHuman(@RequestBody List<Human> humans) {
        humanService.deleteListHuman(humans);
    }

    @PutMapping("/{id}")
    public void updateHuman(@PathVariable UUID id, @RequestBody Human human) {
        humanService.updateHuman(id, human);
    }

    @PatchMapping("/email/{id}")
    public void changeHuman(@PathVariable UUID id, @RequestParam String email) {
        humanService.changeEmailById(id, email);
    }

}
