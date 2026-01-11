FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY journalApp/.mvn/ ./.mvn
COPY journalApp/mvnw ./
COPY journalApp/pom.xml ./

RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline -DskipTests

COPY journalApp/src ./src

RUN ./mvnw clean package -DskipTests

# ============================================================================
FROM eclipse-temurin:21-jre-alpine

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

RUN chown -R appuser:appgroup /app

USER appuser

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
    CMD java -cp app.jar org.springframework.boot.loader.launch.JarLauncher 2>&1 | grep -q "started" || exit 1

ENV SPRING_PROFILES_ACTIVE=docker \
    JAVA_OPTS="-XX:+UseG1GC -XX:MaxRAMPercentage=75 -XX:+UseStringDeduplication"

ENTRYPOINT ["java", "-jar", "app.jar"]
