package com.levi9.celebrate9.model;

import java.beans.ConstructorProperties;

public class DummyObject {
    private String name;

    @ConstructorProperties({"name"})
    public DummyObject(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
