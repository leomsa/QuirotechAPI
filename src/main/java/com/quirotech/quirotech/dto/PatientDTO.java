package com.quirotech.quirotech.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.quirotech.quirotech.entities.Contact;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    private Date bornDate;
    private Character gender;

    private List<Contact> contact;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
    @CreationTimestamp
    private LocalDateTime createdAt;


    public PatientDTO(UUID id, String username, String name, String cpf, Date bornDate, char gender, List <Contact> contact, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.cpf = cpf;
        this.bornDate = bornDate;
        this.gender = gender;
        this.contact = contact;
        this.createdAt = createdAt;
    }
}
