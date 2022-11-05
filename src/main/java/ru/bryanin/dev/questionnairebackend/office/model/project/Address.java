package ru.bryanin.dev.questionnairebackend.office.model.project;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table (name = "addresses")
@Data
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
