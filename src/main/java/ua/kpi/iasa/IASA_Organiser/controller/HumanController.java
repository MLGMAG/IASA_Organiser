package ua.kpi.iasa.IASA_Organiser.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.service.EventService;
import ua.kpi.iasa.IASA_Organiser.service.HumanService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/humans")
@ComponentScan(basePackages = {"ua.kpi.iasa.IASA_Organiser.service"})
public class HumanController {

    private final HumanService humanService;

    private final EventService eventService;

    public HumanController(HumanService humanService, EventService eventService) {
        this.humanService = humanService;
        this.eventService = eventService;
    }

    @GetMapping("/")
    public String getAllHumans(Model model) {
        model.addAttribute("humanList", humanService.getAllHumans());
        return "human/humanList";
    }

    @GetMapping("/{id}")
    public String getHuman(@PathVariable UUID id, Model model) {
        Human human = humanService.getHumanById(id);
        List<Event> allEvents = eventService.getAllEvents();
        allEvents.removeAll(human.getEvents());
        model.addAttribute("human", human);
        model.addAttribute("allEvents", allEvents);
        return "human/humanView";
    }

    @GetMapping("/delete/{id}")
    public String deleteHuman(@PathVariable UUID id) {
        humanService.deleteById(id);
        return "redirect:/humans/";
    }

    @GetMapping("/add")
    public String addHumanPage(Model model) {
        model.addAttribute("human", new Human());
        return "human/humanAdd";
    }

    @PostMapping("/add")
    public String addHuman(@ModelAttribute("human") Human human) {
        humanService.createHuman(human);
        return "redirect:/humans/";
    }

    @GetMapping("/update/{id}")
    public String updateHumanPage(@PathVariable UUID id, Model model) {
        model.addAttribute("human", humanService.getHumanById(id));
        return "human/humanUpdate";
    }

    @PostMapping("/update")
    public String updateHuman(@ModelAttribute("human") Human human) {
        humanService.createHuman(human);
        return "redirect:/humans/";
    }

    @GetMapping("/{humanId}/join/{eventId}")
    public String joinEvent(@PathVariable(name = "humanId") UUID humanId, @PathVariable(name = "eventId") UUID eventId) {
        humanService.joinEvent(humanId, eventId);
        return "redirect:/humans/" + humanId;
    }

}
