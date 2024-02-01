package com.quirotech.quirotech.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.quirotech.quirotech.entities.Address;
import com.quirotech.quirotech.entities.Contact;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class PatientDTO {
    private UUID id;
    private String username;
    private String name;
    private String cpf;
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
    private Date bornDate;
    private Character gender;

    private List<Contact> contact;

    private Address address;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
    @CreationTimestamp
    private LocalDateTime createdAt;


    public PatientDTO(UUID id, String username, String name, String cpf, Date bornDate, char gender, List <Contact> contact, Address address, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.cpf = cpf;
        this.bornDate = bornDate;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
        this.createdAt = createdAt;
    }
}
