package com.levi9.snapstart.cdk;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.apigateway.*;
import software.amazon.awscdk.services.iam.ServicePrincipal;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.FunctionProps;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

import java.io.File;
import java.util.Map;

public class CdkStack extends Stack {
    public CdkStack(final Construct scope, final String stage, final StackProps props) {
        super(scope, "levi9-snapstart-" + stage, props);

        // API Gateway and Lambda
        final SnapStartFunction apiFunction = new SnapStartFunction(this, stage + "-api",
                FunctionProps.builder()
                        .runtime(Runtime.JAVA_17)
                        .code(Code.fromAsset(new File(new File(System.getProperty("user.dir")), "./api/target/api.jar").toString()))
                        .handler("com.levi9.snapstart.api.StreamLambdaHandler")
                        .memorySize(2048)
                        .timeout(Duration.seconds(30))
                        .build());

        apiFunction.getAlias().grantInvoke(new ServicePrincipal("apigateway.amazonaws.com"));

        final LambdaIntegration integration = new LambdaIntegration(apiFunction.getAlias());

        final ProxyResourceOptions proxyR = ProxyResourceOptions.builder()
                .anyMethod(true)
                .defaultIntegration(integration)
                .build();

        final RestApiProps restApiProps = RestApiProps.builder()
                .deployOptions(StageOptions.builder().stageName(stage).build())
                .build();
        final RestApi restApi = new RestApi(this, stage + "-rest-api", restApiProps);

        restApi.getRoot().addProxy(proxyR);

        // Standalone Lambda function
        final SnapStartFunction dummyFunction = new SnapStartFunction(this, stage + "-dummy",
                FunctionProps.builder()
                        .runtime(Runtime.JAVA_17)
                        .code(Code.fromAsset(new File(new File(System.getProperty("user.dir")), "./functions/target/functions.jar").toString()))
                        .handler("org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest")
                        .environment(Map.of(
                                "SPRING_CLOUD_FUNCTION_DEFINITION", "dummyFunction",
                                "MAIN_CLASS", "com.levi9.snapstart.functions.FunctionsConfig"
                        ))
                        .memorySize(2048)
                        .timeout(Duration.seconds(30))
                        .build());
    }
}
