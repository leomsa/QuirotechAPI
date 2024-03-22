package com.quirotech.quirotech.controllers;

import com.quirotech.quirotech.entities.HelthcareProfessional;
import com.quirotech.quirotech.services.HelthcareProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/HelthcareProfessional")
public class HelthcareProfessionalController {

    @Autowired
    private HelthcareProfessionalService helthcareProfessionalService;

    @PostMapping("/create")
    public ResponseEntity createHelthcareProfessional(@RequestBody HelthcareProfessional helthcareProfessional) throws Exception {
        return helthcareProfessionalService.createHelthcareProfessional(helthcareProfessional);
    }
}
