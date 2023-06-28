# Spring Boot with Lambda SnapStart using AWS CDK

## Requirements

* Please ensure that Java 17 is installed on your local machine.

## Run locally

```shell
./mvnw spring-boot:run -f './api/pom.xml'
```

You should be able to access the dummy api on `http://localhost:8080/api/test`

## Requirements for deployment

* AWS account configured
* [AWS CDK CLI installed](https://docs.aws.amazon.com/cdk/v2/guide/cli.html)
* It is important to set the 'DEPLOY_STAGE' environment variable.

### Setting "DEPLOY_STAGE" environment variable

Windows Powershell:
```shell
$Env:DEPLOY_STAGE="{Put your stage name here}"
```

MacOS terminal:
```shell
export DEPLOY_STAGE=mystage
```

## Building and installing locally

```shell
./mvnw clean install
```

## Deployment

Before deploying, please make sure that you have:

* set the "DEPLOY_STAGE" variable
* built and installed code locally

Optinal:
* set the AWS_REGION environment variable or by default it will be deployed to us-east-1

```shell
cdk deploy
```

Or setting ad-hoc to deploy to eu-central-1:
```shell
AWS_REGION=eu-central-1 DEPLOY_STAGE=dev cdk deploy
```

Enjoy!
