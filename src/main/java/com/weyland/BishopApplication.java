package com.weyland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // Для асинхронной обработки команд CRITICAL/COMMON
public class BishopApplication {
    public static void main(String[] args) {
        SpringApplication.run(BishopApplication.class, args);

        System.out.println("\n" +
                "  ____  _     _     _           _   _   _       _   \n" +
                " | __ )(_)___| |__ | | __ _  __| | | \\ | | ___ | |_ \n" +
                " |  _ \\| / __| '_ \\| |/ _` |/ _` | |  \\| |/ _ \\| __|\n" +
                " | |_) | \\__ \\ | | | | (_| | (_| | | |\\  | (_) | |_ \n" +
                " |____/|_|___/_| |_|_|\\__,_|\\__,_| |_| \\_|\\___/ \\__|\n" +
                "                                                     \n" +
                "Weyland-Yutani Synthetic Human Core v1.0 initialized\n");
    }
}