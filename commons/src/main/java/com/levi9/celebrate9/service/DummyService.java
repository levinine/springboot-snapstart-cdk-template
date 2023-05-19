package com.levi9.celebrate9.service;

import com.levi9.celebrate9.model.DummyObject;
import com.levi9.celebrate9.repository.DummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyService {

    @Autowired
    private DummyRepository dummyRepository;

    public DummyObject getDummyObject() {
        return dummyRepository.getDummyObject();
    }

}
