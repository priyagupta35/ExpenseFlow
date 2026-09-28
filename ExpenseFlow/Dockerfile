FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["sh", "-c", "java -jar target/ExpenseFlow-0.0.1-SNAPSHOT.jar"]