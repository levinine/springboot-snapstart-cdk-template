# Welcome to Celebrate9 project!

## Requirements

* AWS account configured
* [AWS CDK CLI installed](https://docs.aws.amazon.com/cdk/v2/guide/cli.html)

## Build locally

```shell
cd commons
./mvnw clean install
cd ..
cd api
./mvnw clean package
```

## Run locally

```shell
cd api
./mvnw spring-boot:run
```

## Deployment commands

```shell
cd commons
./mvnw clean install
cd ..
cd api
./mvnw install:install-file -Dfile='aws-serverless-java-container-springboot3-2.0-SNAPSHOT.jar' -DgroupId='com.amazonaws.serverless' -DartifactId='aws-serverless-java-container-springboot3' -Dversion='2.0-SNAPSHOT' -Dpackaging=jar
./mvnw clean package
cd ..
cd cdk
cdk deploy
```

Enjoy!
