package ua.kpi.iasa.IASA_Organiser.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Table(name = "place")
@Entity(name = "place")
public class Place implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "place_id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "place_country")
    private String country;
    @Column(name = "place_city")
    private String city;
    @Column(name = "place_street")
    private String street;
    @Column(name = "place_number")
    private int number;
    @Column(name = "place_letter")
    private String letter;

    public Place() {
    }

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

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return number == place.number &&
                Objects.equals(country, place.country) &&
                Objects.equals(city, place.city) &&
                Objects.equals(street, place.street) &&
                Objects.equals(letter, place.letter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street, number, letter);
    }

    @Override
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
