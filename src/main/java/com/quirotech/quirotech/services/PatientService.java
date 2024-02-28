package com.quirotech.quirotech.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.quirotech.quirotech.Utils.CPFvalidator;
import com.quirotech.quirotech.dto.PatientDTO;
import com.quirotech.quirotech.entities.Address;
import com.quirotech.quirotech.entities.Contact;
import com.quirotech.quirotech.entities.Patient;
import com.quirotech.quirotech.repositories.ContactRepository;
import com.quirotech.quirotech.repositories.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final ContactRepository contactRepository;

    public PatientService(PatientRepository patientRepository, ContactRepository contactRepository) {
        this.patientRepository = patientRepository;
        this.contactRepository = contactRepository;
    }

    @Transactional
    public ResponseEntity createPatient(@RequestBody Patient patient) throws Exception {
        Patient user = this.patientRepository.findByUsername(patient.getUsername());
        Patient cpf = this.patientRepository.findByCpf(patient.getCpf());
        String validator = patient.getCpf().toString();

        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exist!");

        } else if (cpf != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF already registerd!");

        } else if (CPFvalidator.validateCPF(validator)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF invalid!");

        } else {

            String passwordHashred = BCrypt.withDefaults()
                    .hashToString(12, patient.getPassword().toCharArray());
            patient.setPassword(passwordHashred);
            Patient savedPatient = this.patientRepository.save(patient);

            for (Contact contact : patient.getContact()) {
                contact.setPatient(patient);
            }

            return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<PatientDTO> userBycpf(@RequestParam String cpf) {
        Patient patient = this.patientRepository.findByCpf(cpf);

        if (patient == null || !patientRepository.existsByCpf(cpf)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PatientDTO patientDTO = new PatientDTO(
                patient.getId(),
                patient.getUsername(),
                patient.getName(),
                patient.getCpf(),
                patient.getBornDate(),
                patient.getGender(),
                patient.getContact(),
                patient.getAddress(),
                patient.getCreatedAt()
        );
        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
    }

    public List<Patient> listAllPatients() {
        return patientRepository.findAll();
    }

    public ResponseEntity<Patient> update(String cpf, Patient patient) throws Exception {

        Optional<Patient> patientOptional = patientRepository.findPatientByCpf(cpf);
        if (patientOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Patient existingPatient = patientRepository.findPatientByCpf(cpf).get();
        existingPatient.setUsername(patient.getUsername());
        existingPatient.setName(patient.getName());
        existingPatient.setCpf(patient.getCpf());
        existingPatient.setBornDate(patient.getBornDate());
        existingPatient.setGender(patient.getGender());
        existingPatient.setPassword(patient.getPassword());

        List<Contact> contactList = patient.getContact()
                .stream()
                .map(contact -> contactOverwriter(contact, existingPatient))
                .collect(Collectors.toList());
        existingPatient.setContact(contactList);

        Address address = patient.getAddress();
        existingPatient.getAddress().setAddress(address.getAddress());
        existingPatient.getAddress().setHouseNumber(address.getHouseNumber());
        existingPatient.getAddress().setDetails(address.getDetails());
        existingPatient.getAddress().setCity(address.getCity());
        existingPatient.getAddress().setDistrict(address.getDistrict());
        existingPatient.getAddress().setZipCode(address.getZipCode());

        Patient updated = patientRepository.save(existingPatient);
        return ResponseEntity.ok().body(updated);
    }

    public static Contact contactOverwriter(Contact newContact, Patient existingPatient) {

        return Contact.builder()
                .id(newContact.getId())
                .contactValue(newContact.getContactValue())
                .contactType(newContact.getContactType())
                .patient(existingPatient)
                .build();
    }

    @Transactional
    public ResponseEntity<Void> deletePatient(String cpf) {
        patientRepository.findByCpf(cpf).getCpf();

        if (cpf == null || !patientRepository.existsByCpf(cpf)) {
            return ResponseEntity.notFound().build();
        }
        patientRepository.deletePatientByCpf(cpf);
        return ResponseEntity.noContent().build();
    }
}