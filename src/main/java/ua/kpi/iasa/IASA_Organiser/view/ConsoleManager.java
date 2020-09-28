package main.java.ua.kpi.iasa.IASA_Organiser.view;

import main.java.ua.kpi.iasa.IASA_Organiser.controller.Controller;
import main.java.ua.kpi.iasa.IASA_Organiser.model.Event;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleManager {

    private static Controller controller;
    private static Scanner scanner = new Scanner(System.in);

    public static void startUp() {
        System.out.println("Hello!");
        System.out.println("It's IASA Organiser");
        System.out.println("Choose action:");
        while (true) {
            menu();
            int choice = scanner.nextInt();
            choiceSelector(choice);
        }
    }

    public static void menu() {
        System.out.println("1.Create Event;" +
                "\n2.GetAllEvents;");
    }

    public static void choiceSelector(int choice) {
        switch (choice) {
            case 1:
                controller.createNewEvent(creation());
                break;
            case 2:
                eventFormat(controller.getAllEvents());

        }
    }

    private static Event creation() {
        Event event = new Event();
        System.out.println("Enter name of event:");
        event.setName(scanner.nextLine());
        return event;
    }

    public void setController(Controller controller) {
        ConsoleManager.controller = controller;
    }

    private static void eventFormat(Event[] events) {
        for (Event event : events) {
            if (event == null) break;
            System.out.println("\t " + event.getName() + " : " + event.getId() + ";");
        }
    }
}
