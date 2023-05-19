package com.levi9.celebrate9;

import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;

public class CdkApp {
    public static void main(final String[] args) {
        final App app = new App();

        new CdkStack(app, "Celebrate9CdkStack", StackProps.builder()
                .build());

        app.synth();
    }
}

