package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String street;

    public Address() {}

    public Address(String street) {
        this.street = street;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
}
