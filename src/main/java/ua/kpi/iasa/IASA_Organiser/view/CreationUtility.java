package ua.kpi.iasa.IASA_Organiser.view;

import ua.kpi.iasa.IASA_Organiser.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CreationUtility {
    private CreationUtility(){}

    public static String inputName(){
        Scanner scannerName = new Scanner(System.in);
        System.out.print("Enter name of event:");
        return scannerName.nextLine();
    }

    public static LocalDate inputDate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the date of event:\n");
        System.out.print("Enter the day: ");
        int day = scanner.nextInt();
        System.out.print("Enter the month: ");
        int month = scanner.nextInt();
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();
        return LocalDate.of(year, month, day);
    }

    public static LocalTime inputTime(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the time, when event starts:\n");
        System.out.print("Enter the hours: ");
        int hours = scanner.nextInt();
        System.out.print("Enter the minutes: ");
        int minutes = scanner.nextInt();
        return LocalTime.of(hours, minutes);
    }

    public static Priority inputPriority(){
        System.out.println("Enter the priority of this event[1, 2 or 3]: ");
        Scanner scannerPriority = new Scanner(System.in);
        int priorityLevel = scannerPriority.nextInt();
        switch (priorityLevel){
            case 1:
                return Priority.LOW;
            case 2:
                return Priority.MEDIUM;
            case 3:
                return Priority.HIGH;
            default:
                throw new InputMismatchException("Wrong priority level!");
        }
    }

    public static List<Human> inputInvited(){
        Scanner scanner = new Scanner(System.in);
        List<Human> invited = new ArrayList<>();
        String answer;
        do{
            Human human = inputHuman();
            invited.add(human);
            System.out.print("Any others?[Y/n]: ");
            answer = scanner.next();
        }while (answer.equals("Y"));
        return invited;
    }

    public static Human inputHuman(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first name: ");
        String firstName = scanner.next();
        System.out.print("Enter the last name: ");
        String lastName = scanner.next();
        System.out.print("Enter the phone number: ");
        String phoneNumber = scanner.next();
        System.out.print("Enter the email: ");
        String email = scanner.next();
        return new Human(firstName, lastName, phoneNumber, email);
    }

    public static Place inputPlace(){
        Scanner scanner = new Scanner(System.in);
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
        return new Place(country, city, street, number, letter);
    }

    public static List<Tag> inputTags(){
        Scanner scanner = new Scanner(System.in);
        List<Tag> tags = new ArrayList<>();
        String answer;
        do{
            Tag tag = inputOneTag();
            tags.add(tag);
            System.out.print("Any others?[Y/n]: ");
            answer = scanner.next();
        }while (answer.equals("Y"));
        return tags;
    }

    public static Tag inputOneTag(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the tag: ");
        String tagName = scanner.nextLine();
        return new Tag(tagName);
    }

    public static List<Link> inputLinks(){
        Scanner scanner = new Scanner(System.in);
        List<Link> links = new ArrayList<>();
        String answer;
        do{
            Link link = inputOneLink();
            links.add(link);
            System.out.print("Any others?[Y/n]: ");
            answer = scanner.next();
        }while (answer.equals("Y"));
        return links;
    }

    public static Link inputOneLink(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the link: ");
        String linkURL = scanner.nextLine();
        return new Link(linkURL);
    }
}
