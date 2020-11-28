package ua.kpi.iasa.IASA_Organiser.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.model.Link;
import ua.kpi.iasa.IASA_Organiser.model.Place;
import ua.kpi.iasa.IASA_Organiser.model.Priority;
import ua.kpi.iasa.IASA_Organiser.model.Tag;
import ua.kpi.iasa.IASA_Organiser.model.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CreationUtility {
    private static final Logger logger = LoggerFactory.getLogger(CreationUtility.class);

    private CreationUtility() {
    }

    public static String inputName() {
        logger.debug("Method was called...");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter name of event:");
                String name = scanner.nextLine();
                logger.debug("Method returns: {}", name);
                return name;
            } catch (InputMismatchException e) {
                scanner = new Scanner(System.in);
            }
        }
    }

    public static LocalDate inputDate() {
        logger.debug("Method was called...");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("\nEnter the date of event:");
                System.out.print("Enter the day: ");
                int day = scanner.nextInt();
                System.out.print("Enter the month: ");
                int month = scanner.nextInt();
                System.out.print("Enter the year: ");
                int year = scanner.nextInt();
                LocalDate date = LocalDate.of(year, month, day);
                logger.debug("Method returns: {}", date);
                return date;
            } catch (DateTimeException e) {
                scanner = new Scanner(System.in);
            }
        }
    }

    public static LocalTime inputTime() {
        logger.debug("Method was called...");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter the hours: ");
                int hours = scanner.nextInt();
                System.out.print("Enter the minutes: ");
                int minutes = scanner.nextInt();
                LocalTime time = LocalTime.of(hours, minutes);
                logger.debug("Method returns: {}", time);
                return time;
            } catch (InputMismatchException e) {
                scanner = new Scanner(System.in);
            }
        }
    }

    public static Priority inputPriority() {
        logger.debug("Method was called...");
        Scanner scanner = new Scanner(System.in);
        Priority priority;
        while (true) {
            try {
                System.out.println("Enter the priority of this event[1, 2 or 3]: ");
                int priorityLevel = scanner.nextInt();
                switch (priorityLevel) {
                    case 1:
                        priority = Priority.LOW;
                    case 2:
                        priority = Priority.MEDIUM;
                    case 3:
                        priority = Priority.HIGH;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + priorityLevel);
                }
                logger.debug("Method returns: {}", priority);
                return priority;
            } catch (InputMismatchException e) {
                scanner = new Scanner(System.in);
            }
        }

    }

    public static List<Human> inputInvited() {
        logger.debug("Method was called...");
        Scanner scanner = new Scanner(System.in);
        List<Human> invited = new ArrayList<>();
        String answer;
        do {
            try {
                Human human = inputHuman();
                invited.add(human);
            } catch (InputMismatchException e) {
                System.out.println("Wrong input!\n");
            }
            System.out.print("Any others?[Y/n]: ");
            answer = scanner.next();
        } while (answer.toUpperCase().equals("Y"));
        logger.debug("Method returns: {}", invited);
        return invited;
    }

    public static Human inputHuman() {
        logger.debug("Method was called...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first name: ");
        String firstName = scanner.next();
        System.out.print("Enter the last name: ");
        String lastName = scanner.next();
        System.out.print("Enter the phone number: ");   //TODO add validation
        String phoneNumber = scanner.next();
        System.out.print("Enter the email: ");
        String email = scanner.next();
        Human human = new Human(firstName, lastName, phoneNumber, email);
        logger.debug("Method returns: {}", human);
        return human;
    }

    public static Place inputPlace() {
        logger.debug("Method was called...");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter the country: ");
                String country = scanner.next();
                System.out.print("Enter the city: ");
                String city = scanner.next();
                System.out.print("Enter the street: ");
                String street = scanner.next();
                System.out.print("Enter the house number: ");
                int number = scanner.nextInt();
                System.out.print("Enter the house` letter: ");
                String letter = scanner.next();
                Place place = new Place(country, city, street, number, letter);
                logger.debug("Method returns: {}", place);
                return place;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input!\n");
                scanner = new Scanner(System.in);
            }
        }
    }

    public static List<Tag> inputTags() {
        logger.debug("Method was called...");
        Scanner scanner = new Scanner(System.in);
        List<Tag> tags = new ArrayList<>();
        String answer;
        do {
            try {
                Tag tag = inputOneTag();
                tags.add(tag);
            } catch (InputMismatchException e) {
                System.out.println("Wrong input!\n");
            }
            System.out.print("Any others?[Y/n]: ");
            answer = scanner.next();
        } while (answer.toUpperCase().equals("Y"));
        logger.debug("Method returns: {}", tags);
        return tags;
    }

    public static Tag inputOneTag() {
        logger.debug("Method was called...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the tag: ");
        String tagName = scanner.nextLine();
        Tag tag = new Tag(tagName);
        logger.debug("Method returns: {}", tag);
        return tag;
    }

    public static List<Link> inputLinks() {
        logger.debug("Method was called...");
        Scanner scanner = new Scanner(System.in);
        List<Link> links = new ArrayList<>();
        String answer;
        do {
            try {
                Link link = inputOneLink();
                links.add(link);
            } catch (InputMismatchException e) {
                System.out.println("Wrong input!\n");
            }
            System.out.print("Any others?[Y/n]: ");
            answer = scanner.next();
        } while (answer.toUpperCase().equals("Y"));
        logger.debug("Method returns: {}", links);
        return links;
    }

    public static Link inputOneLink() {
        logger.debug("Method was called...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the link: ");
        String linkURL = scanner.nextLine();
        Link link = new Link(linkURL);
        logger.debug("Method returns: {}", link);
        return link;
    }

    public static Set<Type> inputTypes() {
        logger.debug("Method was called...");
        Scanner scanner = new Scanner(System.in);
        Set<Type> types = new HashSet<>();
        String answer;
        do {
            System.out.print("\nWhich type of event you want to create?\n" +
                    "1. Single.\n" +
                    "2. Periodic.\n" +
                    "3. Expire.\n" +
                    "4. Completable.\n" +
                    "5. Deadline.\n\n" +
                    "Enter the number[1-5]: ");
            int type;
            try {
                type = scanner.nextInt();
                switch (type) {
                    case 1:
                        types.add(Type.SINGLE);
                        break;
                    case 2:
                        types.add(Type.PERIODIC);
                        break;
                    case 3:
                        types.add(Type.EXPIRED);
                        break;
                    case 4:
                        types.add(Type.COMPLETABLE);
                        break;
                    case 5:
                        types.add(Type.DEADLINE);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input!\n");
            }

            System.out.print("Any others?[Y/n]: ");
            answer = scanner.next();
        } while (answer.toUpperCase().equals("Y"));
        logger.debug("Method returns: {}", types);
        return types;
    }
}
