package com.gildedrose;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = GildedRoseApplication.class)
public class CucumberConfig {
}
