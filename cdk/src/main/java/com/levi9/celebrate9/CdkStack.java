package com.levi9.celebrate9;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.apigateway.LambdaIntegration;
import software.amazon.awscdk.services.apigateway.ProxyResourceOptions;
import software.amazon.awscdk.services.apigateway.RestApi;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.FunctionProps;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

import java.io.File;


public class CdkStack extends Stack {
    public CdkStack(final Construct scope, final String id, final String stage, final StackProps props) {
        super(scope, id, props);
        initializeResources(stage);
    }

    private void initializeResources(final String stage) {
        final Function apiFunction = new Function(this, stage + "-api",
                FunctionProps.builder()
                        .functionName("api")
                        .runtime(Runtime.JAVA_17)
                        .code(Code.fromAsset(new File(new File(System.getProperty("user.dir")), "./api/target/api.jar").toString()))
                        .handler("com.levi9.celebrate9.StreamLambdaHandler")
                        .memorySize(1024)
                        .timeout(Duration.seconds(10))
                        .build());
        
        final LambdaIntegration integration = new LambdaIntegration(apiFunction);

        final ProxyResourceOptions proxyR = ProxyResourceOptions.builder()
                .anyMethod(true)
                .defaultIntegration(integration)
                .build();

        final RestApi restApi = new RestApi(this, stage + "-RestApi");

        restApi.getRoot().addProxy(proxyR);
    }
}
