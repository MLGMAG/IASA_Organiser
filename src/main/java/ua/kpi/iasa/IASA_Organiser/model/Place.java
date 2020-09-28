package main.java.ua.kpi.iasa.IASA_Organiser.model;

public class Place {
    private String country;
    private String city;
    private String street;
    private int number;
    private char letter;

    public Place(String country, String city, String street, int number, char letter) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
        this.letter = letter;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }
}
