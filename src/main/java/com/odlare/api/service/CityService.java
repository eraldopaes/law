package com.odlare.api.service;

import com.odlare.api.model.City;
import com.odlare.api.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<City> findByNameContaining(String name) {
        return cityRepository.findByNameContaining(name);
    }
}
