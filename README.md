# Welcome to Celebrate9 project!

## Requirements

* AWS account configured
* [AWS CDK CLI installed](https://docs.aws.amazon.com/cdk/v2/guide/cli.html)

## Build locally

```shell
./mvnw clean install -f './commons/pom.xml'
./mvnw clean package -f './api/pom.xml'
```

## Run locally

```shell
./mvnw spring-boot:run -f './api/pom.xml'
```

## Deployment commands

```shell
./mvnw clean install -f './commons/pom.xml'
./mvnw clean package -f './api/pom.xml'
cdk deploy
```

Enjoy!
