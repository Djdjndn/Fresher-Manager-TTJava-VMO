# ---------- Stage 1: Build ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copy pom.xml trước để cache dependency
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

# Copy source code và build
COPY src ./src
RUN mvn -B -q clean package -DskipTests

# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy file jar từ stage build
COPY --from=build /workspace/target/*.jar app.jar

# (tùy chọn) chạy bằng user non-root
RUN useradd -u 10001 appuser || adduser -u 10001 -D appuser || true
USER 10001

EXPOSE 8080
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -XX:+UseG1GC -XX:MaxRAMPercentage=75 -jar /app/app.jar"]
