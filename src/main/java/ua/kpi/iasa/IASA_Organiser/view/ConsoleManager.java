package ua.kpi.iasa.IASA_Organiser.view;

import ua.kpi.iasa.IASA_Organiser.builder.EventBuilder;
import ua.kpi.iasa.IASA_Organiser.controller.Controller;
import ua.kpi.iasa.IASA_Organiser.model.Calendar;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.model.Link;
import ua.kpi.iasa.IASA_Organiser.model.Place;
import ua.kpi.iasa.IASA_Organiser.model.Priority;
import ua.kpi.iasa.IASA_Organiser.model.Tag;
import ua.kpi.iasa.IASA_Organiser.model.Type;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

import static ua.kpi.iasa.IASA_Organiser.util.CreationUtility.inputDate;
import static ua.kpi.iasa.IASA_Organiser.util.CreationUtility.inputInvited;
import static ua.kpi.iasa.IASA_Organiser.util.CreationUtility.inputLinks;
import static ua.kpi.iasa.IASA_Organiser.util.CreationUtility.inputName;
import static ua.kpi.iasa.IASA_Organiser.util.CreationUtility.inputPlace;
import static ua.kpi.iasa.IASA_Organiser.util.CreationUtility.inputPriority;
import static ua.kpi.iasa.IASA_Organiser.util.CreationUtility.inputTags;
import static ua.kpi.iasa.IASA_Organiser.util.CreationUtility.inputTime;
import static ua.kpi.iasa.IASA_Organiser.util.CreationUtility.inputTypes;


public class ConsoleManager implements View {
    private Controller controller;

    public ConsoleManager() {
    }

    @Override
    public void startUp() {
        Scanner scanner;
        System.out.println("Hello!");
        System.out.println("It's IASA Organiser");
        System.out.println("Loading....\n");

        while (true) {
            pause();
            System.out.println("\nChoose action:");
            menu();
            scanner = new Scanner(System.in);
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong input!");
                continue;
            }
            choiceSelector(choice);
        }
    }

    private void pause() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
    }

    private void menu() {
        System.out.println("\n1. Create Event;" +
                "\n2. GetAllEvents;" +
                "\n3. Change event;" +
                "\n4. Remove event;" +
                "\n5. Check events properties;" +
                "\n\n0. Exit;");
    }

    private void choiceSelector(int choice) {
        switch (choice) {
            case 0:
                System.out.println("It was nice to see you. Good bye! <3");
                System.exit(0);
                break;
            case 1:
                controller.createNewEvent(creation());
                break;
            case 2:
                eventFormat(controller.getAllEventsList());
                break;
            case 3:
                editEvent(chooseEvent(controller.getAllEventsList()));
                break;
            case 4:
                removeEvent(chooseEvent(controller.getAllEventsList()));
                break;
            case 5:
                showEventProperties(chooseEvent(controller.getAllEventsList()));
                break;
        }
    }

    private Event creation() {
        Scanner scanner = new Scanner(System.in);
        EventBuilder eventBuilder = new EventBuilder();
        eventBuilder.setId(UUID.randomUUID());

        Set<Type> types = inputTypes();
        eventBuilder.setTypes(types);

        String name = inputName();
        eventBuilder.setName(name);

        LocalDate date = inputDate();
        eventBuilder.setDate(date);

        System.out.println("\nEnter the time, when event starts:");
        LocalTime time = inputTime();
        eventBuilder.setTime(time);

        Priority priority = inputPriority();
        eventBuilder.setPriority(priority);

        System.out.print("\nDo you want to add duration?[Y/n]: ");
        String ans1 = scanner.next();
        if (ans1.toUpperCase().equals("Y")) {
            LocalTime duration = inputTime();
            eventBuilder.setDuration(duration);
        }

        System.out.print("\nDo you want to add invited people?[Y/n]: ");
        String ans2 = scanner.next();
        if (ans2.toUpperCase().equals("Y")) {
            List<Human> invited = inputInvited();
            eventBuilder.setInvited(invited);
        }

        System.out.print("\nDo you want to add place?[Y/n]: ");
        String ans3 = scanner.next();
        if (ans3.toUpperCase().equals("Y")) {
            Place place = inputPlace();
            eventBuilder.setPlace(place);
        }

        System.out.print("\nDo you want to add any tags?[Y/n]: ");
        String ans4 = scanner.next();
        if (ans4.toUpperCase().equals("Y")) {
            List<Tag> tags = inputTags();
            eventBuilder.setTags(tags);
        }

        System.out.print("\nDo you want to add any links?[Y/n]: ");
        String ans5 = scanner.next();
        if (ans5.toUpperCase().equals("Y")) {
            List<Link> links = inputLinks();
            eventBuilder.setLinks(links);
        }

        return eventBuilder.build();
    }

    private void eventFormat(List<Event> events) {
        int counter = 1;
        for (Event event : events) {
            if (event == null) continue;
            System.out.println("\t" + counter + ". " + event.getName() + " : " + event.getId() + ";");
            counter++;
        }
    }

    private Event chooseEvent(List<Event> events) {
        if (events.size() == 0) {
            System.out.println("There is no events!");
            return null;
        }

        System.out.println("Choose event:\n");
        eventFormat(events);
        System.out.println("Enter the number: ");
        Scanner scanner = new Scanner(System.in);
        int num;
        while (true) {
            try {
                num = scanner.nextInt();
                if (num < 1 || num > events.size()) throw new InputMismatchException();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input!");
            }
        }

        return events.get(num - 1);

    }

    private void editEvent(Event event) {
        EventBuilder eventBuilder = new EventBuilder();
        eventBuilder.setInitValues(event);
        Scanner scanner = new Scanner(System.in);
        int choice;
        String answ;
        System.out.println();
        do {
            System.out.println("1. Name.\n" +
                    "2. Place.\n" +
                    "3. Invited.\n" +
                    "4. Date.\n" +
                    "5. Time.\n" +
                    "6. Priority.\n" +
                    "7. Tags.\n" +
                    "8. Duration.\n" +
                    "9. Links.\n" +
                    "10. Type.");
            while (true) {
                System.out.print("\nChoose the field, that should be changed[1-9]: ");
                try {
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 9) throw new InputMismatchException();
                    break;
                } catch (InputMismatchException e) {
                    scanner = new Scanner(System.in);
                }
            }

            switch (choice) {
                case 1:
                    eventBuilder.setName(inputName());
                    break;
                case 2:
                    eventBuilder.setPlace(inputPlace());
                    break;
                case 3:
                    eventBuilder.setInvited(inputInvited());
                    break;
                case 4:
                    eventBuilder.setDate(inputDate());
                    break;
                case 5:
                    eventBuilder.setTime(inputTime());
                    break;
                case 6:
                    eventBuilder.setPriority(inputPriority());
                    break;
                case 7:
                    eventBuilder.setTags(inputTags());
                    break;
                case 8:
                    eventBuilder.setDuration(inputTime());
                    break;
                case 9:
                    eventBuilder.setLinks(inputLinks());
                    break;
                case 10:
                    eventBuilder.setTypes(inputTypes());
            }
            System.out.println("\nAny others?[Y/n]: ");
            answ = scanner.next();
        } while (answ.toUpperCase().equals("Y"));
        Event newEvent = eventBuilder.build();
        controller.updateEvent(newEvent);
    }

    private void removeEvent(Event event) {
        controller.removeEvent(event);
    }

    @Override
    public void configController(Controller controller) {
        this.controller = controller;
    }

    /**
     * @deprecated uses deprecated class
     */
    @Deprecated
    private void printCalendar(Calendar calendar) {
        Event[] events = calendar.getEvents();
        for (int i = 0; i < events.length; i++) {
            if (events[i] == null) {
                System.out.println((i + 1) + ". No events.");
                continue;
            }
            System.out.println((i + 1) + ". " + events[i]);
        }
    }

    public void showEventProperties(Event event) {
        System.out.println(event);
    }

}
