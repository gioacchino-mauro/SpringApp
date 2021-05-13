FROM maven:3.6.0-jdk-11-slim as build

COPY pom.xml .
RUN  mvn dependency:go-offline
COPY src src
COPY VERSION .
ARG DEVOPS_BUILDNUM
ARG DEVOPS_GITSHA
RUN  echo "Build Num" $DEVOPS_BUILDNUM
RUN  echo "Git Sha" $DEVOPS_GITSHA

RUN  mvn package -Dbuild.number=$DEVOPS_BUILDNUM  -Dbuild.revision=$DEVOPS_GITSHA -Dbuild.version=$(head -n 1 VERSION)
RUN  mv target/*.jar app.jar

FROM openjdk:11.0-jdk-slim
VOLUME /tmp

COPY --from=build app.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]