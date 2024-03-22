package com.quirotech.quirotech.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.quirotech.quirotech.entities.Contact;
import com.quirotech.quirotech.entities.HelthcareProfessional;
import com.quirotech.quirotech.repositories.ContactRepository;
import com.quirotech.quirotech.repositories.HelthcareProfessionalRepository;
import com.quirotech.quirotech.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Service
public class HelthcareProfessionalService {
    private final HelthcareProfessionalRepository helthcareProfessionalRepository;
    private final PatientRepository patientRepository;
    private final ContactRepository contactRepository;


    @Transactional
    public ResponseEntity createHelthcareProfessional(@RequestBody HelthcareProfessional helthcareProfessional) throws Exception {
        HelthcareProfessional licenseNumber = this.helthcareProfessionalRepository.findByLicenseNumber(helthcareProfessional.getLicenseNumber());
        HelthcareProfessional user = this.helthcareProfessionalRepository.findByUserName(helthcareProfessional.getUserName());
        HelthcareProfessional cpf = this.helthcareProfessionalRepository.findByCpf(helthcareProfessional.getCpf());
        String validator = helthcareProfessional.getCpf().toString();

        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User name already exist!");
        } else if (cpf != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF already registerd!");
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


}