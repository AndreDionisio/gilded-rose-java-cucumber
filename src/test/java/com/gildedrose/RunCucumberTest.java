package com.gildedrose;

import com.gildedrose.constants.DomainConstants;
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
class RunCucumberTest {

    @Test
    void testGildedRoseConstructorIsPrivate() throws Exception {
        Constructor<GildedRose> constructor = GildedRose.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    void testDomainConstantsConstructorIsPrivate() throws Exception {
        Constructor<DomainConstants> constructor = DomainConstants.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    void testTexttestFixtureMain() {
        assertDoesNotThrow(() -> TexttestFixture.main(new String[]{}));

        assertDoesNotThrow(() -> TexttestFixture.main(new String[]{"1"}));
    }

}

