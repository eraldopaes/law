package com.odlare.api.controller;

import com.odlare.api.model.City;
import com.odlare.api.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<City>> findByNameContaining(@PathVariable String name) {
        return new ResponseEntity<>(cityService.findByNameContaining(name), HttpStatus.OK);
    }
}
