package com.levi9.celebrate9.commons.service;

import com.levi9.celebrate9.commons.model.DummyObject;
import com.levi9.celebrate9.commons.repository.DummyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

@Service
@Import(DummyRepository.class)
@RequiredArgsConstructor
public class DummyService {

    private final DummyRepository dummyRepository;

    public DummyObject getDummyObject() {
        return dummyRepository.getDummyObject();
    }

}
