package com.quirotech.quirotech.controllers;

import com.quirotech.quirotech.dto.HealthcareProfessionalDTO;
import com.quirotech.quirotech.entities.HelathcareProfessional;
import com.quirotech.quirotech.services.HealthcareProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/HelthcareProfessional")
public class HelthcareProfessionalController {

    @Autowired
    private HealthcareProfessionalService healthcareProfessionalService;

    @PostMapping("/create")
    public ResponseEntity createHelthcareProfessional(@RequestBody HelathcareProfessional helathcareProfessional) throws Exception {
        return healthcareProfessionalService.createHelthcareProfessional(helathcareProfessional);
    }

    @GetMapping("/professional")
    public ResponseEntity<HealthcareProfessionalDTO> helthcareProfessionalByCpf(@RequestParam String cpf){
        return healthcareProfessionalService.helthcareProfessionalByCpf(cpf);
    }

    @GetMapping("/allprofessionals")
    public List<HelathcareProfessional> listAllProfessionals(){
        return healthcareProfessionalService.listAllProfessionals();
    }

    @CrossOrigin(origins = "**", methods = {RequestMethod.OPTIONS, RequestMethod.DELETE})
    @DeleteMapping(value = "/delete")
    public void deleteHealthCareProfessional(@RequestParam String cpf){
        healthcareProfessionalService.deleteHealthCareProfessional(cpf);
    }
}
