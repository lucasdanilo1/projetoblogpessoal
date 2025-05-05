FROM maven:3-eclipse-temurin-21-alpine AS build

WORKDIR /aceleramaker

COPY pom.xml ./

COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /aceleramaker

COPY --from=build /aceleramaker/target/*.jar /aceleramaker/blogpessoal.jar

CMD ["java", "-jar", "/aceleramaker/blogpessoal.jar"]