package org.example.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PropertiesConfigurationProviderTest {

    @Test
    void shouldReturnImplementationForExistingKey() {
        ConfigurationProvider provider = new PropertiesConfigurationProvider("test.properties");
        assertEquals("TestImpl", provider.getImplementation("TestInterface"));
    }

    @Test
    void shouldReturnNullForMissingKey() {
        ConfigurationProvider provider = new PropertiesConfigurationProvider("test.properties");
        assertNull(provider.getImplementation("NonExistentKey"));
    }
}