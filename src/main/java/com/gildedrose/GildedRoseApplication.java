package com.gildedrose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GildedRoseApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(GildedRoseApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GildedRoseApplication.class, args);
    }
    @Override
    public void run(String... args) {
        log.debug("--- Gilded Rose Log Iniciado ---");

        log.debug("--- Processamento Finalizado ---");
    }
}