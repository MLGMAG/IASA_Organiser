package ua.kpi.iasa.IASA_Organiser.model;

import java.io.Serializable;
import java.util.Objects;

public class Place implements Serializable {
    private String country;
    private String city;
    private String street;
    private int number;
    private String letter;

    public Place(String country, String city, String street, int number, String letter) {
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

    public String getLetter() {
        return letter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return number == place.number &&
                letter == place.letter &&
                Objects.equals(country, place.country) &&
                Objects.equals(city, place.city) &&
                Objects.equals(street, place.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street, number, letter);
    }
    public String toString() {
        return "Place{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", letter='" + letter + '\'' +
                '}';
    }
}
