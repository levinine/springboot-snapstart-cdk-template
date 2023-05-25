package com.levi9.celebrate9.commons.repository;

import com.levi9.celebrate9.commons.model.DummyObject;
import org.springframework.stereotype.Repository;

@Repository
public class DummyRepository {
    public DummyObject getDummyObject() {
        return new DummyObject("dummy name");
    }
}
