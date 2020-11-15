package ua.kpi.iasa.IASA_Organiser.view;

import ua.kpi.iasa.IASA_Organiser.builder.Builder;
import ua.kpi.iasa.IASA_Organiser.controller.Controller;
import ua.kpi.iasa.IASA_Organiser.model.*;
import ua.kpi.iasa.IASA_Organiser.builder.BuilderDirector;
import ua.kpi.iasa.IASA_Organiser.builder.EventBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static ua.kpi.iasa.IASA_Organiser.util.CreationUtility.*;

public class ConsoleManager implements View {
    private Controller controller;
    private BuilderDirector director = new BuilderDirector();

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
            } catch (InputMismatchException e){
                System.out.println("Wrong input!");
                continue;
            }
            choiceSelector(choice);
        }
    }

    private void pause(){
        try{
            Thread.sleep(1500);
        } catch (InterruptedException exc){
            exc.printStackTrace();
        }
    }

    private void menu() {
        System.out.println("\n1. Create Event;" +
                "\n2. GetAllEvents;" +
                "\n3. Change event;" +
                "\n4. Remove event;" +
                "\n5. View calendar;" +
                "\n6. Check event;" +
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
                printCalendar(controller.getCalendar());
                break;
            case 6:
                showEventProperties(chooseEvent(controller.getAllEventsList()));
                break;
        }
    }

    private Event creation() {
        Scanner scanner = new Scanner(System.in);
        int type;
        String name;
        LocalDate date;
        LocalTime time;
        LocalTime duration = null;
        Priority priority;
        List<Human> invited = null;
        List<Tag> tags = null;
        List<Link> links = null;
        Place place = null;

        while (true){
            try{
                System.out.print("\nWhich type of event you want to create?\n" +
                        "1. Single.\n" +
                        "2. Periodic.\n" +
                        "3. Expire.\n" +
                        "4. Completable.\n" +
                        "5. Deadline.\n\n" +
                        "Enter the number[1-5]: ");
                type = scanner.nextInt();
                if (type < 1 || type > 5) throw new InputMismatchException();
                break;
            } catch (InputMismatchException e){
                scanner = new Scanner(System.in);
            }
        }

        name = inputName();
        date = inputDate();

        System.out.println("\nEnter the time, when event starts:");
        time = inputTime();

        priority = inputPriority();

        System.out.print("\nDo you want to add duration?[Y/n]: ");
        String ans1 = scanner.next();
        if(ans1.toUpperCase().equals("Y")){
            duration = inputTime();
        }
        System.out.print("\nDo you want to add invited people?[Y/n]: ");
        String ans2 = scanner.next();
        if(ans2.toUpperCase().equals("Y")){
            invited = inputInvited();
        }
        System.out.print("\nDo you want to add place?[Y/n]: ");
        String ans3 = scanner.next();
        if(ans3.toUpperCase().equals("Y")){
            place = inputPlace();
        }
        System.out.print("\nDo you want to add any tags?[Y/n]: ");
        String ans4 = scanner.next();
        if(ans4.toUpperCase().equals("Y")){
            tags = inputTags();
        }
        System.out.print("\nDo you want to add any links?[Y/n]: ");
        String ans5 = scanner.next();
        if(ans5.toUpperCase().equals("Y")){
            links = inputLinks();
        }
        switch (type){
            case 1:
                return director.getSingleEvent(name, place, invited, date, time, priority, tags, duration, links);
            case 2:
                return director.getPeriodicEvent(name, place, invited, date, time, priority, tags, duration, links);
            case 3:
                return director.getExpiredEvent(name, place, invited, date, time, priority, tags, duration, links);
            case 4:
                return director.getCompletableEvent(name, place, invited, date, time, priority, tags, duration, links);
            case 5:
                return director.getDeadlineEvent(name, place, invited, date, time, priority, tags, duration, links);
            default:
                throw new InputMismatchException("Wrong number of event type!");
        }
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
        if(events.size() == 0){
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
        Builder eventBuilder = new EventBuilder();
        eventBuilder.setInitValues(event);
        Scanner scanner = new Scanner(System.in);
        int choice;
        String answ;
        System.out.println();
        do{
            System.out.println( "1. Name.\n" +
                                "2. Place.\n" +
                                "3. Invited.\n" +
                                "4. Date.\n" +
                                "5. Time.\n" +
                                "6. Priority.\n" +
                                "7. Tags.\n" +
                                "8. Duration.\n" +
                                "9. Links.");
            while (true) {
                System.out.print("\nChoose the field, that should be changed[1-9]: ");
                try {
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 9) throw new InputMismatchException();
                    break;
                } catch (InputMismatchException e){
                    scanner = new Scanner(System.in);
                }
            }

            switch (choice){
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
            }
            System.out.println("\nAny others?[Y/n]: ");
            answ = scanner.next();
        }while (answ.toUpperCase().equals("Y"));
        Event newEvent = eventBuilder.getResult();
        controller.updateEvent(newEvent);
    }

    private void removeEvent(Event event) {
        controller.removeEvent(event);
    }

    @Override
    public void configController(Controller controller) {
        this.controller = controller;
    }

    private void printCalendar(Calendar calendar) {  //TODO: rewrite in next version!
        Event[] events = calendar.getEvents();
        for (int i = 0; i < events.length; i++) {
            if (events[i] == null) {
                System.out.println((i + 1) + ". No events.");
                continue;
            }
            System.out.println((i + 1) + ". " + events[i]);
        }
    }

    public void showEventProperties(Event event){
        System.out.println(event);
    }

}
