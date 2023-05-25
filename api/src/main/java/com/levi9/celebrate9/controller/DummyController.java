package com.levi9.celebrate9.controller;

import com.levi9.celebrate9.model.DummyObject;
import com.levi9.celebrate9.service.DummyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Import({DummyService.class})
@RequiredArgsConstructor
public class DummyController {

    private final DummyService dummyService;

    @GetMapping("/test")
    public DummyObject getDummyObject() {
        return dummyService.getDummyObject();
    }
}
