package com.levi9.celebrate9.service;

import com.levi9.celebrate9.model.DummyObject;
import com.levi9.celebrate9.repository.DummyRepository;
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
