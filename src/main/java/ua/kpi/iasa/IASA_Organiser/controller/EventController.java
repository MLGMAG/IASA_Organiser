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
import ua.kpi.iasa.IASA_Organiser.model.Link;
import ua.kpi.iasa.IASA_Organiser.model.Tag;
import ua.kpi.iasa.IASA_Organiser.service.EventService;

import java.util.UUID;

@Controller
@RequestMapping("/events")
@ComponentScan(basePackages = {"ua.kpi.iasa.IASA_Organiser.service"})
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public String getAllEvents(Model model) {
        model.addAttribute("eventList", eventService.getAllEvents());
        return "event/eventList";
    }

    @GetMapping("/{id}")
    public String getEvent(@PathVariable UUID id, Model model) {
        Event event = eventService.getEventAndHumansByEventId(id);
        model.addAttribute("event", event);
        model.addAttribute("tags", event.getTags());
        model.addAttribute("links", event.getLinks());
        model.addAttribute("tag", new Tag());
        model.addAttribute("link", new Link());
        return "event/eventView";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable UUID id) {
        eventService.deleteById(id);
        return "redirect:/events/";
    }

    @GetMapping("/add")
    public String addEventPage(Model model) {
        model.addAttribute("event", new Event());
        return "event/eventAdd";
    }

    @PostMapping("/add")
    public String addEvent(@ModelAttribute("Event") Event event) {
        eventService.createEvent(event);
        return "redirect:/events/";
    }

    @GetMapping("/update/{id}")
    public String updateEventPage(@PathVariable UUID id, Model model) {
        model.addAttribute("event", eventService.getEventById(id));
        return "event/eventUpdate";
    }

    @PostMapping("/update")
    public String updateEvent(@ModelAttribute("event") Event event) {
        eventService.updateEvent(event.getId(), event);
        return "redirect:/events/";
    }

}
