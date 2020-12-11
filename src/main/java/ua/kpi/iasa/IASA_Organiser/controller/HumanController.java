package ua.kpi.iasa.IASA_Organiser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kpi.iasa.IASA_Organiser.service.HumanService;

@Controller
@RequestMapping("/humans")
public class HumanController {

    private final HumanService humanService;

    public HumanController(HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping("/")
    public String getAllHumans(Model model) {
        model.addAttribute("humanList", humanService.getAllHumans());
        return "human/humanList";
    }
}
