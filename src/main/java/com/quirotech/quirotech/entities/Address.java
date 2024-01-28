package com.quirotech.quirotech.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String address;
    String houseNumber;
    String details;
    String city;
    @Length(min = 2, max = 2, message = "Only two letters to represent a District")
    String district;
    String zipCode;

    public Address() {

    }
}