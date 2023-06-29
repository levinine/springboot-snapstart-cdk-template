package com.levi9.snapstart.cdk;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.apigateway.*;
import software.amazon.awscdk.services.iam.ServicePrincipal;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.lambda.*;
import software.constructs.Construct;

import java.io.File;
import java.util.Map;

public class CdkStack extends Stack {
    public CdkStack(final Construct scope, final String stage, final StackProps props) {
        super(scope, "levi9-snapstart-" + stage, props);

        // API Gateway and Lambda
        final Function apiFunction = new Function(this, stage + "-api",
                FunctionProps.builder()
                        .functionName(stage + "-api")
                        .runtime(Runtime.JAVA_17)
                        .code(Code.fromAsset(new File(new File(System.getProperty("user.dir")), "./api/target/api.jar").toString()))
                        .handler("com.levi9.snapstart.api.StreamLambdaHandler")
                        .memorySize(2048)
                        .timeout(Duration.seconds(30))
                        .build());

        final CfnFunction cfnFunction = (CfnFunction) apiFunction.getNode().getDefaultChild();
        if (cfnFunction != null) {
            cfnFunction.setSnapStart(CfnFunction.SnapStartProperty.builder()
                    .applyOn("PublishedVersions")
                    .build());
        }

        final Alias lambdaAlias = Alias.Builder.create(this, stage + "-snapstart-alias")
                .aliasName(stage + "-snapstart-alias")
                .version(apiFunction.getCurrentVersion())
                .build();
        lambdaAlias.grantInvoke(new ServicePrincipal("apigateway.amazonaws.com"));

        final LambdaIntegration integration = new LambdaIntegration(lambdaAlias);

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
        final Function dummyFunction = new Function(this, stage + "-dummy",
                FunctionProps.builder()
                        .functionName(stage + "-dummy")
                        .runtime(Runtime.JAVA_17)
                        .code(Code.fromAsset(new File(new File(System.getProperty("user.dir")), "./functions/target/functions.jar").toString()))
                        .handler("org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest")
                        .environment(Map.of(
                                "SPRING_CLOUD_FUNCTION_DEFINITION", "scheduleGroupArchiving",
                                "MAIN_CLASS", "com.levi9.snapstart.functions.FunctionsConfig"
                        ))
                        .memorySize(2048)
                        .timeout(Duration.seconds(30))
                        .build());

        final CfnFunction cfnDummyFunction = (CfnFunction) dummyFunction.getNode().getDefaultChild();
        if (cfnDummyFunction != null) {
            cfnDummyFunction.setSnapStart(CfnFunction.SnapStartProperty.builder()
                    .applyOn("PublishedVersions")
                    .build());
        }

        final Alias dummyLambdaAlias = Alias.Builder.create(this, stage + "-dummy-snapstart-alias")
                .aliasName(stage + "-dummy-snapstart-alias")
                .version(dummyFunction.getCurrentVersion())
                .build();
    }
}
