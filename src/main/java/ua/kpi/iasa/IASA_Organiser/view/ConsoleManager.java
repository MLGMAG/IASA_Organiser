package ua.kpi.iasa.IASA_Organiser.view;

import ua.kpi.iasa.IASA_Organiser.controller.Controller;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleManager {

    private Controller controller;
    private Scanner scanner;

    public ConsoleManager(Controller controller){
        this.controller = controller;
    }

    public void startUp() {

        System.out.println("Hello!");
        System.out.println("It's IASA Organiser");
        System.out.println("Choose action:");
        while (true) {
            menu();
            scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            choiceSelector(choice);
        }
    }

    public void menu() {
        System.out.println("1. Create Event;" +
                "\n2. GetAllEvents;" +
                "\n3. Change event;" +
                "\n4. Remove event;" +
                "\n\n0. Exit;");
    }

    public void choiceSelector(int choice) {
        switch (choice) {
            case 0:
                System.out.println("It was nice to see you. Good bye! <3");
                System.exit(0);
                break;
            case 1:
                controller.createNewEvent(creation());
                break;
            case 2:
                eventFormat(controller.getAllEvents());
                break;
            case 3:
                editEvent(chooseEvent(controller.getAllEvents()));
                break;
            case 4:
                removeEvent(chooseEvent(controller.getAllEvents()));
        }
    }

    private Event creation() {       //TODO : change to Builder in lab3
        Event event = new Event();
        System.out.println("Enter name of event:");
        scanner = new Scanner(System.in);
        event.setName(scanner.nextLine());
        return event;
    }

    private void eventFormat(Event[] events) {
        for (int i = 0; i < events.length; i++) {
            if (events[i] == null) break;
            System.out.println("\t" + (i + 1) + ". " + events[i].getName() + " : " + events[i].getId() + ";");
        }
    }

    private Event chooseEvent(Event[] events){
        System.out.println("Choose event:\n");
        eventFormat(events);
        System.out.println("Enter the number: ");
        scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        return events[num - 1];
    }

    private void editEvent(Event event){
        System.out.println("Input new name: ");
        scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        event.setName(name);
        controller.changeEvent(event);
    }

    private void removeEvent(Event event){
        controller.removeEvent(event);
    }
}
