package com.quirotech.quirotech.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "Contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contactValue;

    @Enumerated(EnumType.STRING)
    private ContactType contactType;

    @ManyToOne
    @JoinColumn(name = "Patient", referencedColumnName = "id")
    @JsonIgnore
    private Patient patient;


    @ManyToOne
    @JoinColumn(name = "HelthcareProfessional", referencedColumnName = "id")
    @JsonIgnore
    private  HelthcareProfessional helthcareProfessional;

    public Contact() {
    }

    // Construtor sem o id
    public Contact(String contactValue, ContactType contactType, Patient patient) {
        this.contactValue = contactValue;
        this.contactType = contactType;
        this.patient = patient;
    }
}