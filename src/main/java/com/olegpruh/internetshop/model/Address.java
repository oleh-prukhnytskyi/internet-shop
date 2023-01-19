package com.olegpruh.internetshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String city;
    private String house;
    private String postCode;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Address(String address, String city, String house, String postCode) {
        this.address = address;
        this.city = city;
        this.house = house;
        this.postCode = postCode;
    }
}
