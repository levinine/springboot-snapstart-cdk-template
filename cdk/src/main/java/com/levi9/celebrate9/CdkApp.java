package com.levi9.celebrate9;

import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;

public class CdkApp {
    public static void main(final String[] args) {
        final App app = new App();

        final String stage = System.getenv("STAGE");

        if (stage == null || stage.equals("")) {
            throw new RuntimeException("Please ensure that the \"STAGE\" environment variable is set to a valid value.");
        }

        final String stackName = "celebrate9-be-" + stage.toLowerCase();
        new CdkStack(app, stackName, stage, StackProps.builder()
                .build());

        app.synth();
    }
}

