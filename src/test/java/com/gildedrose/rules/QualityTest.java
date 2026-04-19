package com.gildedrose.rules;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QualityTest {

    @Test
    @DisplayName("Should maintain value within normal bounds (0-50)")
    void normalBounds() {
        assertEquals(25, new Quality(25).value());
        assertEquals(0, new Quality(-1).value());
        assertEquals(50, new Quality(51).value());
    }

    @Test
    @DisplayName("Should allow legendary quality value (80) without clamping")
    void legendaryValue() {
        assertEquals(80, new Quality(80).value());
    }

    @Test
    @DisplayName("Should increase quality by 1 but not exceed 50")
    void increaseQuality() {
        Quality q = new Quality(10);
        assertEquals(11, q.increase().value());

        Quality max = new Quality(50);
        assertEquals(50, max.increase().value());
    }

    @Test
    @DisplayName("Should decrease quality by amount but not go below 0")
    void decreaseQuality() {
        Quality q = new Quality(10);
        assertEquals(8, q.decrease(2).value());

        Quality min = new Quality(1);
        assertEquals(0, min.decrease(5).value());
    }

    @Test
    @DisplayName("Should reset quality to initial value (0)")
    void resetQuality() {
        Quality q = new Quality(45);
        assertEquals(0, q.reset().value());
    }

    @ParameterizedTest
    @ValueSource(ints = {Quality.MINIMAL, Quality.MAXIMAL, Quality.LEGENDARY_QUALITY})
    @DisplayName("Constants should match business rules")
    void constantsMatch(int val) {
        Quality q = new Quality(val);
        assertEquals(val, q.value());
    }
}