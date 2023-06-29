package com.levi9.snapstart.api.controller;

import com.levi9.snapstart.commons.model.DummyObject;
import com.levi9.snapstart.commons.service.DummyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DummyController {

    private final DummyService dummyService;

    public DummyController(final DummyService dummyService) {
        this.dummyService = dummyService;
    }

    @GetMapping("/test")
    public DummyObject getDummyObject() {
        return dummyService.getDummyObject();
    }
}
