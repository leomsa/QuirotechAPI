package com.quirotech.quirotech.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "HelthcareProfessional")
public class HelthcareProfessional {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String userName;
    private String name;
    private String specialization;
    private String licenseNumber;
    private boolean active;

    @Column(unique = true)
    @Length(min = 11, max = 11, message = "CPF must have exactly 11 digits")
    private String cpf;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
    private Date bornDate;

    private Character gender;
    private String password;

    @OneToMany(mappedBy = "helthcareProfessional", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contact = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @CreationTimestamp
    private LocalDateTime createdAt;
}