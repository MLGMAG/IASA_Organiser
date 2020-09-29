package ua.kpi.iasa.IASA_Organiser;

import ua.kpi.iasa.IASA_Organiser.controller.Controller;
import ua.kpi.iasa.IASA_Organiser.view.ConsoleManager;

public class Main {
    public static void main(String[] args) {
        new Controller(new ConsoleManager());
    }
}
