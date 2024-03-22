package com.quirotech.quirotech.repositories;

import com.quirotech.quirotech.entities.HelthcareProfessional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HelthcareProfessionalRepository extends JpaRepository <HelthcareProfessional, UUID> {
    HelthcareProfessional findByUserName(String username);

    HelthcareProfessional findByLicenseNumber(String licenseNumber);

    HelthcareProfessional findByCpf(String cpf);

    Boolean existsByCpf(String cpf);

    List<HelthcareProfessional> deleteHelthcareProfessionalByCpf(String cpf);

    Optional<HelthcareProfessional> findHelthcareProfessionalByCpf(String cpf);
}
