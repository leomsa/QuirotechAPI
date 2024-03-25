package com.quirotech.quirotech.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.quirotech.quirotech.Utils.CPFvalidator;
import com.quirotech.quirotech.dto.HelthcareProfessionalDTO;
import com.quirotech.quirotech.entities.Contact;
import com.quirotech.quirotech.entities.HelthcareProfessional;
import com.quirotech.quirotech.repositories.HelthcareProfessionalRepository;
import com.quirotech.quirotech.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Service
public class HelthcareProfessionalService {
    private final HelthcareProfessionalRepository helthcareProfessionalRepository;
    private final PatientRepository patientRepository;


    @Transactional
    public ResponseEntity createHelthcareProfessional(@RequestBody HelthcareProfessional helthcareProfessional) throws Exception {
        HelthcareProfessional user = this.helthcareProfessionalRepository.findByUserName(helthcareProfessional.getUserName());
        HelthcareProfessional cpf = this.helthcareProfessionalRepository.findByCpf(helthcareProfessional.getCpf());
        String validator = helthcareProfessional.getCpf().toString();
        HelthcareProfessional licenseNumber = this.helthcareProfessionalRepository.findByLicenseNumber(helthcareProfessional.getLicenseNumber());

        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User name already exist!");

        } else if (cpf != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF already registerd!");

        } else if (CPFvalidator.validateCPF(validator)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF invalid!");

        } else if (licenseNumber != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("License number already registered!");

        } else {
            String passwordHashred = BCrypt.withDefaults()
                    .hashToString(12, helthcareProfessional.getPassword().toCharArray());
            helthcareProfessional.setPassword(passwordHashred);
            HelthcareProfessional savedHelthcareProfessional = this.helthcareProfessionalRepository.save(helthcareProfessional);

            for (Contact contact : helthcareProfessional.getContact()) {
                contact.setHelthcareProfessional(helthcareProfessional);
            }
            return new ResponseEntity<>(savedHelthcareProfessional, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<HelthcareProfessionalDTO> helthcareProfessionalByCpf(@RequestParam String cpf) {
        HelthcareProfessional helthcareProfessional = this.helthcareProfessionalRepository.findByCpf(cpf);

        if(helthcareProfessional == null || !helthcareProfessionalRepository.existsByCpf(cpf)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HelthcareProfessionalDTO helthcareProfessionalDTO = new HelthcareProfessionalDTO(
                helthcareProfessional.getId(),
                helthcareProfessional.getUserName(),
                helthcareProfessional.getName(),
                helthcareProfessional.getCpf(),
                helthcareProfessional.getBornDate(),
                helthcareProfessional.getGender(),
                helthcareProfessional.getSpecialization(),
                helthcareProfessional.getLicenseNumber(),
                helthcareProfessional.isActive(),
                helthcareProfessional.getContact(),
                helthcareProfessional.getAddress(),
                helthcareProfessional.getCreatedAt()
        );
        return new ResponseEntity<>(helthcareProfessionalDTO, HttpStatus.OK);
    }
}