package com.odlare.api.controller;

import com.odlare.api.model.Estimate;
import com.odlare.api.model.SystemUser;
import com.odlare.api.repository.EstimateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estimate")
public class EstimateController {

    private final EstimateRepository estimateRepository;

    @Autowired
    public EstimateController(EstimateRepository estimateRepository) {
        this.estimateRepository = estimateRepository;
    }

    @PostMapping
    public ResponseEntity<Estimate> save(@RequestBody Estimate estimate) {
        return new ResponseEntity<>(estimateRepository.save(estimate), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Estimate>> findAll() {
        return new ResponseEntity<>(estimateRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Estimate> update(@RequestBody Estimate estimate) {
        return new ResponseEntity<>(estimateRepository.save(estimate), HttpStatus.OK);
    }
}
