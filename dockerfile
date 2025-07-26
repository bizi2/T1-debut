# Используем официальный образ OpenJDK
FROM eclipse-temurin:17-jdk-jammy

# Рабочая директория в контейнере
WORKDIR /app

# Копируем собранный JAR-файл
COPY build/libs/*.jar app.jar

# Порт, который будет слушать приложение
EXPOSE 9095

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]