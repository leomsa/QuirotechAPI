package com.quirotech.quirotech.repositories;

import com.quirotech.quirotech.entities.HelathcareProfessional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HealthcareProfessionalRepository extends JpaRepository <HelathcareProfessional, Long> {
    HelathcareProfessional findByUserName(String username);

    HelathcareProfessional findByLicenseNumber(String licenseNumber);

    HelathcareProfessional findByCpf(String cpf);

    Boolean existsByCpf(String cpf);

    List<HelathcareProfessional> deleteHealthcareProfessionalByCpf(String cpf);

    Optional<HelathcareProfessional> findHelthcareProfessionalByCpf(String cpf);
}
