package com.quirotech.quirotech.repositories;

import com.quirotech.quirotech.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUsername(String username);

    Patient findByCpf(String cpf);

    Boolean existsByCpf(String cpf);

    List<Patient> deletePatientByCpf(String cpf);

    Optional<Patient> findPatientByCpf(String cpf);

}
