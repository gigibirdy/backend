package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

@Entity
@Table(name = "Divisions")
@Getter
@Setter
public class Division {

    @Column(name = "division")
    private String division_name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_id", nullable = false)
    private Long id;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false, insertable = false, updatable = false)
    private Country country;

    @Column(name = "country_id")
    private Long country_id;

    public void setCountry(Country country) {
        setCountry_id(country.getId());
        this.country = country;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "division", fetch = FetchType.LAZY)
    private Set<Customer> customers;
}
