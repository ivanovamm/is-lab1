package org.example.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Embedded
    private Coordinates coordinates;

    @Column(nullable = false)
    private LocalDate creationDate;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private Address officialAddress;

    @Column(nullable = false)
    private double annualTurnover;

    @Column(nullable = false)
    private Long employeesCount;

    @Column(nullable = false)
    private int rating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrganizationType type;

    @OneToOne(cascade = CascadeType.ALL)
    private Address postalAddress;

    public Organization() {}

    public Organization(String name, Coordinates coordinates, Address officialAddress,
                        double annualTurnover, Long employeesCount, int rating, OrganizationType type, Address postalAddress) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.officialAddress = officialAddress;
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.rating = rating;
        this.type = type;
        this.postalAddress = postalAddress;
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Coordinates getCoordinates() { return coordinates; }
    public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }

    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }

    public Address getOfficialAddress() { return officialAddress; }
    public void setOfficialAddress(Address officialAddress) { this.officialAddress = officialAddress; }

    public double getAnnualTurnover() { return annualTurnover; }
    public void setAnnualTurnover(double annualTurnover) { this.annualTurnover = annualTurnover; }

    public Long getEmployeesCount() { return employeesCount; }
    public void setEmployeesCount(Long employeesCount) { this.employeesCount = employeesCount; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public OrganizationType getType() { return type; }
    public void setType(OrganizationType type) { this.type = type; }

    public Address getPostalAddress() { return postalAddress; }
    public void setPostalAddress(Address postalAddress) { this.postalAddress = postalAddress; }
}
