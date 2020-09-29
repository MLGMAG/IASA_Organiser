package ua.kpi.iasa.IASA_Organiser.view;

import ua.kpi.iasa.IASA_Organiser.controller.Controller;

public interface View {
    void startUp();

    void configController(Controller controller);
}
