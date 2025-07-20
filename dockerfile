# Используем официальный образ OpenJDK 17
FROM eclipse-temurin:17-jdk-jammy

# Рабочая директория
WORKDIR /app

# Копируем JAR-файл
COPY build/libs/*.jar app.jar

# Открываем порт приложения
EXPOSE 8080

# Команда запуска
ENTRYPOINT ["java", "-jar", "app.jar"]