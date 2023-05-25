package com.levi9.celebrate9.repository;

import com.levi9.celebrate9.model.DummyObject;
import org.springframework.stereotype.Repository;

@Repository
public class DummyRepository {
    public DummyObject getDummyObject() {
        return new DummyObject("dummy name");
    }
}
