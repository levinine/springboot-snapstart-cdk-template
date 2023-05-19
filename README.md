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
./mvnw install:install-file -f './api/pom.xml' -Dfile='aws-serverless-java-container-springboot3-2.0-SNAPSHOT.jar' -DgroupId='com.amazonaws.serverless' -DartifactId='aws-serverless-java-container-springboot3' -Dversion='2.0-SNAPSHOT' -Dpackaging=jar
./mvnw clean package -f './api/pom.xml'
cdk deploy
```

Enjoy!
