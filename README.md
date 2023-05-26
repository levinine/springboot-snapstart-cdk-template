# Welcome to Celebrate9 project!

## Requirements

* Please ensure that Java 17 is installed on your local machine.

## Run locally

```shell
./mvnw spring-boot:run -f './api/pom.xml'
```

## Requirements for deployment

* AWS account configured
* [AWS CDK CLI installed](https://docs.aws.amazon.com/cdk/v2/guide/cli.html)
* It is important to set the 'DEPLOY_STAGE' environment variable.

## Setting "DEPLOY_STAGE" environment variable

```shell
$Env:DEPLOY_STAGE="{Put your stage name here}"
```

## Building and installing locally

```shell
./mvnw clean install
```

## Deployment commands

Before deploying, please make sure that you have:

* set the "DEPLOY_STAGE" variable
* built and installed code locally

You can see instructions for that above.

```shell
cdk deploy
```

Enjoy!
