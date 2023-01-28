package ru.bryanin.dev.questionnairebackend.office.entity.project;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "addresses")
@Data
@Builder(toBuilder=true)
public class Address {
    @Id
    @SequenceGenerator(name = "addresses_sequence", sequenceName = "addresses_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresses_sequence")
    private Long id;
    @Column(name = "postal_code")
    private int postalCode;
    @Column(name = "country", columnDefinition = "varchar(255) default 'Russia'")
    private String country;
    @Column(name = "region")
    private String region;
    @Column(name = "city")
    private String city;
    @Column(name = "settlement")
    private String settlement;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private String house;
    @Column(name = "block")
    private String block;

    public Address() {
    }

    public Address(int postalCode, String country, String region, String city, String settlement, String street, String house, String block) {
        this.postalCode = postalCode;
        this.country = country;
        this.region = region;
        this.city = city;
        this.settlement = settlement;
        this.street = street;
        this.house = house;
        this.block = block;
    }

    public Address(Long id, int postalCode, String country, String region, String city, String settlement, String street, String house, String block) {
        this.id = id;
        this.postalCode = postalCode;
        this.country = country;
        this.region = region;
        this.city = city;
        this.settlement = settlement;
        this.street = street;
        this.house = house;
        this.block = block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (postalCode != address.postalCode) return false;
        if (!Objects.equals(country, address.country)) return false;
        if (!Objects.equals(region, address.region)) return false;
        if (!Objects.equals(city, address.city)) return false;
        if (!Objects.equals(settlement, address.settlement)) return false;
        if (!Objects.equals(street, address.street)) return false;
        if (!Objects.equals(house, address.house)) return false;
        return Objects.equals(block, address.block);
    }

    @Override
    public int hashCode() {
        int result = postalCode;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (settlement != null ? settlement.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + (block != null ? block.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "" + postalCode +
                ", " + country +
                ", " + region +
                ", " + city +
                ", " + settlement +
                ", " + street +
                ", " + house +
                ", " + block;
    }
}
