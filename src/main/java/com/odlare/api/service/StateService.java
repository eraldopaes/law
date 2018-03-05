package com.odlare.api.service;

import com.odlare.api.model.State;
import com.odlare.api.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StateService {

    private final StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public State findOne(Long id) {
        return stateRepository.findOne(id);
    }
}
