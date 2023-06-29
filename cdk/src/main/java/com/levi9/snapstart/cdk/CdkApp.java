package com.levi9.snapstart.cdk;

import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;

public class CdkApp {
    public static void main(final String[] args) {
        final App app = new App();

        final String stage = System.getenv("DEPLOY_STAGE");

        if (stage == null || stage.equals("")) {
            throw new RuntimeException("Please ensure that the \"DEPLOY_STAGE\" environment variable is set to a valid value.");
        }

        new CdkStack(app, stage.toLowerCase(), StackProps.builder().build());

        app.synth();
    }
}
