package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import io.cucumber.junit.platform.engine.Constants;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/gildedrose")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.gildedrose")
public class RunCucumberTest {

    @Test
    void testGildedRoseConstructorIsPrivate() throws Exception {
        Constructor<GildedRose> constructor = GildedRose.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    void testItemRulesConstructorIsPrivate() throws Exception {
        Constructor<ItemRules> constructor = ItemRules.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    void testTexttestFixtureMain() {
        // 1. Test without arguments (covers default days = 2)
        assertDoesNotThrow(() -> TexttestFixture.main(new String[]{}));

        // 2. Test with arguments (covers the if(args.length > 0) branch)
        // We pass "1", which the code parses as days = 1 + 1 = 2
        assertDoesNotThrow(() -> TexttestFixture.main(new String[]{"1"}));
    }

}

