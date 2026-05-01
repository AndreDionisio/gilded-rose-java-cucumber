package com.gildedrose;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GildedRoseApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(GildedRoseApplication.class, args);
    }
    @Override
    public void run(String... args) {
        System.out.println("--- Gilded Rose Log Iniciado ---");

        System.out.println("--- Processamento Finalizado ---");
    }
}