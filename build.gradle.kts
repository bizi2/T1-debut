

plugins {
    java
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
    id("com.google.protobuf") version "0.9.4"
    kotlin("kapt") version "1.9.22"
    id("io.freefair.lombok") version "8.4"
    id("idea") // Добавляем плагин IDEA
}



group = "com.authservice"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("io.micrometer:micrometer-core")
    // Database
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")
    implementation("org.liquibase:liquibase-core")

    // Lettuce (Redis клиент)
    implementation("io.lettuce:lettuce-core")

    // Redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.data:spring-data-redis")

    // Kafka
    implementation("org.springframework.kafka:spring-kafka")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
    // Monitoring
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("io.micrometer:micrometer-core")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    kapt("org.projectlombok:lombok")  // Для Kotlin

    // Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Jakarta Servlet (для Spring Boot 3+)
    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")

    // Jakarta Validation (Spring Boot 3+)
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // gRPC
/*    implementation("net.devh:grpc-server-spring-boot-starter:2.15.0.RELEASE")
    implementation("io.grpc:grpc-netty:1.58.0")
    implementation("io.grpc:grpc-protobuf:1.58.0")
    implementation("io.grpc:grpc-stub:1.58.0")
    compileOnly("org.apache.tomcat:annotations-api:6.0.53")*/
    //for rate limiter service
    implementation("com.github.vladimir-bukhtoyarov:bucket4j-core:7.6.0")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:kafka")

    //для Github
    implementation("com.github.vladimir-bukhtoyarov:bucket4j-core:7.6.0")

    // Для @Timed
    implementation("org.aspectj:aspectjweaver:1.9.19")

    // Для @Configuration и @Bean
    implementation("org.springframework.boot:spring-boot-starter")

    // Для JSON сериализации
    implementation("com.fasterxml.jackson.core:jackson-databind")

    // Для CORS
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Spring Data JPA (если используете UserRepository)
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Spring Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Для работы с датами
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Для Jakarta EE 9+ (Spring Boot 3.x)
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")


}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

