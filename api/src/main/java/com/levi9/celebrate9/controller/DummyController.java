package com.levi9.celebrate9.controller;

import com.levi9.celebrate9.model.DummyObject;
import com.levi9.celebrate9.service.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Import({DummyService.class})
public class DummyController {

    @Autowired
    private DummyService dummyService;

    @GetMapping("/test")
    public DummyObject getDummyObject() {
        return dummyService.getDummyObject();
    }
}
