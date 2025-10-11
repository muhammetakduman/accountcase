# -------- 1) Build Stage (JDK 21 + Maven) --------
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Bağımlılık cache’i
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Kaynaklar (Java+Kotlin) ve paketleme
COPY src ./src
RUN mvn -q clean package -DskipTests

# -------- 2) Runtime Stage (JRE 21) --------
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Container-aware JVM + UTF-8
ENV JAVA_TOOL_OPTIONS="-XX:+UseContainerSupport -Dfile.encoding=UTF-8"
# Spring profil kullanacaksan aç:
# ENV SPRING_PROFILES_ACTIVE=prod

# Üretilen fat-jar'ı kopyala (Spring Boot)
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
