package com.levi9.snapstart.commons.service;

import com.levi9.snapstart.commons.model.DummyObject;
import com.levi9.snapstart.commons.repository.DummyRepository;
import org.springframework.stereotype.Service;

@Service
public class DummyService {

    private final DummyRepository dummyRepository;

    public DummyService(final DummyRepository dummyRepository) {
        this.dummyRepository = dummyRepository;
    }

    public DummyObject getDummyObject() {
        return dummyRepository.getDummyObject();
    }

}
