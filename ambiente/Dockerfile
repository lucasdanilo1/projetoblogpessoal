FROM maven:3.8.8-eclipse-temurin-21-alpine AS build

WORKDIR /aceleramaker

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

RUN groupadd -r nonroot && useradd -r -g nonroot nonroot

WORKDIR /aceleramaker

COPY --from=build /aceleramaker/target/*.jar /aceleramaker/blogpessoal.jar

RUN mkdir -p /aceleramaker/ehcache && \
    chown -R nonroot:nonroot /esg-backend

USER nonroot

CMD ["java", "-jar", "/aceleramaker/blogpessoal.jar"]
