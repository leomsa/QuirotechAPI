package com.quirotech.quirotech.repositories;

import com.quirotech.quirotech.entities.HelthcareProfessional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HelthcareProfessionalRepository extends JpaRepository <HelthcareProfessional, Long> {
    HelthcareProfessional findByUserName(String username);

    HelthcareProfessional findByLicenseNumber(String licenseNumber);

    HelthcareProfessional findByCpf(String cpf);

    Boolean existsByCpf(String cpf);

    List<HelthcareProfessional> deleteHelthcareProfessionalByCpf(String cpf);

    Optional<HelthcareProfessional> findHelthcareProfessionalByCpf(String cpf);
}
