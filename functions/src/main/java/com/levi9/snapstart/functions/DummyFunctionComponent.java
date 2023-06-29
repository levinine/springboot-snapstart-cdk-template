package com.levi9.snapstart.functions;

import com.levi9.snapstart.commons.model.DummyObject;
import com.levi9.snapstart.commons.service.DummyService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DummyFunctionComponent {

    public Function<?, DummyObject> dummyFunction(final DummyService dummyService) {
        return value -> dummyService.getDummyObject();
    }
}
