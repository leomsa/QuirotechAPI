package com.quirotech.quirotech.controllers;

import com.quirotech.quirotech.dto.PatientDTO;
import com.quirotech.quirotech.entities.Patient;
import com.quirotech.quirotech.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @PostMapping("/create")
    public ResponseEntity createPatient(@RequestBody Patient patient) throws Exception {
        return patientService.createPatient(patient);
    }

    @GetMapping("/user")
    public ResponseEntity<PatientDTO> userBycpf(@RequestParam String cpf) throws Exception{
        return patientService.userBycpf(cpf);
    }

    @GetMapping("/allpatients")
    public List<Patient> listAllPatients(){
        return patientService.listAllPatients();
    }

    @PutMapping("/update")
    public ResponseEntity<Patient> update(@RequestParam UUID id,
                                          @RequestBody Patient patient) throws Exception{
        return  patientService.update(id, patient);
    }

    @CrossOrigin(origins = "**", methods = {RequestMethod.OPTIONS, RequestMethod.DELETE})
    @DeleteMapping(value = "/delete")
    public void deletePatient(@RequestParam String cpf,
                              @RequestParam UUID id){
        patientService.deletePatient(cpf);
    }
}