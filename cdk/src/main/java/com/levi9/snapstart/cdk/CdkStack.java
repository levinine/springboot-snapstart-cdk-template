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

public class CdkStack extends Stack {
    public CdkStack(final Construct scope, final String stage, final StackProps props) {
        super(scope, "levi9-snapstart-" + stage, props);

        final Function apiFunction = new Function(this, stage + "-api",
                FunctionProps.builder()
                        .functionName(stage + "-api")
                        .runtime(Runtime.JAVA_17)
                        .code(Code.fromAsset(new File(new File(System.getProperty("user.dir")), "./api/target/api.jar").toString()))
                        .handler("com.levi9.snapstart.api.StreamLambdaHandler")
                        .memorySize(2048)
                        .timeout(Duration.seconds(15))
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
    }
}
