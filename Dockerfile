FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

# Copy pom.xml first for dependency caching
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy project files
COPY src ./src
COPY testng.xml .

# Compile project
RUN mvn clean compile

# Run TestNG suite
CMD ["mvn", "test", "-DsuiteXmlFile=testng.xml"]
