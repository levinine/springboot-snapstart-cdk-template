package com.levi9.snapstart.cdk;


import software.amazon.awscdk.services.lambda.Alias;
import software.amazon.awscdk.services.lambda.CfnFunction;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.FunctionProps;
import software.constructs.Construct;

public class SnapStartFunction extends Construct {

    private final Function function;
    private final Alias alias;

    public SnapStartFunction(final Construct scope, final String id, final FunctionProps props) {
        super(scope, id);

        this.function = new Function(this, id + "-lambda", props);

        final CfnFunction cfnFunction = (CfnFunction) this.function.getNode().getDefaultChild();
        if (cfnFunction != null) {
            cfnFunction.setSnapStart(CfnFunction.SnapStartProperty.builder()
                    .applyOn("PublishedVersions")
                    .build());
        }

        this.alias = Alias.Builder.create(this, id + "-snapstart-alias")
                .aliasName(id + "-snapstart-alias")
                .version(this.function.getCurrentVersion())
                .build();
    }

    public Function getFunction() {
        return this.function;
    }

    public Alias getAlias() {
        return this.alias;
    }
}
