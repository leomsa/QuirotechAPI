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

@Data
@Entity
@Table(name = "HelthcareProfessional")
public class HelathcareProfessional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;
    private String name;

    @Column(unique = true)
    @Length(min = 11, max = 11, message = "CPF must have exactly 11 digits")
    private String cpf;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
    private Date bornDate;

    private Character gender;
    private String password;
    private String specialization;
    @Column(unique = true)
    private String licenseNumber;
    private boolean active;

    @OneToMany(mappedBy = "helathcareProfessional", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contact = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_hcprofessional_address_id"))
    private Address address;

    @CreationTimestamp
    private LocalDateTime createdAt;
}