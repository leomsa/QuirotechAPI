package com.quirotech.quirotech.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.quirotech.quirotech.Utils.CPFvalidator;
import com.quirotech.quirotech.dto.HealthcareProfessionalDTO;
import com.quirotech.quirotech.entities.Contact;
import com.quirotech.quirotech.entities.HelathcareProfessional;
import com.quirotech.quirotech.repositories.HealthcareProfessionalRepository;
import com.quirotech.quirotech.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HealthcareProfessionalService {
    private final HealthcareProfessionalRepository healthcareProfessionalRepository;
    private final PatientRepository patientRepository;


    @Transactional
    public ResponseEntity createHelthcareProfessional(@RequestBody HelathcareProfessional helathcareProfessional) throws Exception {
        HelathcareProfessional user = this.healthcareProfessionalRepository.findByUserName(helathcareProfessional.getUserName());
        HelathcareProfessional cpf = this.healthcareProfessionalRepository.findByCpf(helathcareProfessional.getCpf());
        String validator = helathcareProfessional.getCpf().toString();
        HelathcareProfessional licenseNumber = this.healthcareProfessionalRepository.findByLicenseNumber(helathcareProfessional.getLicenseNumber());

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
                    .hashToString(12, helathcareProfessional.getPassword().toCharArray());
            helathcareProfessional.setPassword(passwordHashred);
            HelathcareProfessional savedHelathcareProfessional = this.healthcareProfessionalRepository.save(helathcareProfessional);

            for (Contact contact : helathcareProfessional.getContact()) {
                contact.setHelathcareProfessional(helathcareProfessional);
            }
            return new ResponseEntity<>(savedHelathcareProfessional, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<HealthcareProfessionalDTO> helthcareProfessionalByCpf(@RequestParam String cpf) {
        HelathcareProfessional helathcareProfessional = this.healthcareProfessionalRepository.findByCpf(cpf);

        if(helathcareProfessional == null || !healthcareProfessionalRepository.existsByCpf(cpf)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HealthcareProfessionalDTO healthcareProfessionalDTO = new HealthcareProfessionalDTO(
                helathcareProfessional.getId(),
                helathcareProfessional.getUserName(),
                helathcareProfessional.getName(),
                helathcareProfessional.getCpf(),
                helathcareProfessional.getBornDate(),
                helathcareProfessional.getGender(),
                helathcareProfessional.getSpecialization(),
                helathcareProfessional.getLicenseNumber(),
                helathcareProfessional.isActive(),
                helathcareProfessional.getContact(),
                helathcareProfessional.getAddress(),
                helathcareProfessional.getCreatedAt()
        );
        return new ResponseEntity<>(healthcareProfessionalDTO, HttpStatus.OK);
    }

    public List<HelathcareProfessional> listAllProfessionals(){
        return healthcareProfessionalRepository.findAll();
    }

    @Transactional
    public  ResponseEntity<Void> deleteHealthCareProfessional(String cpf){
        healthcareProfessionalRepository.findByCpf(cpf);

        if(cpf == null || !healthcareProfessionalRepository.existsByCpf(cpf)){
            return ResponseEntity.notFound().build();
        }
        healthcareProfessionalRepository.deleteHealthcareProfessionalByCpf(cpf);
        return ResponseEntity.noContent().build();
    }
}