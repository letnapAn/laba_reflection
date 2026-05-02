package org.example.injector;

import org.example.config.PropertiesConfigurationProvider;
import org.example.model.SomeBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InjectorTest {

    @Test
    void shouldInjectDependenciesAndReturnAC() {
        Injector injector = new Injector(new PropertiesConfigurationProvider("injector-test.properties"));
        SomeBean bean = injector.inject(new SomeBean());
        assertEquals("AC", bean.foo());
    }

    @Test
    void shouldReturnBCWhenImplementationIsSwitched() {
        Injector injector = new Injector(new PropertiesConfigurationProvider("injector-test-switch.properties"));
        SomeBean bean = injector.inject(new SomeBean());
        assertEquals("BC", bean.foo());
    }

    @Test
    void shouldThrowOnNullTarget() {
        Injector injector = new Injector(new PropertiesConfigurationProvider("injector-test.properties"));
        assertThrows(IllegalArgumentException.class, () -> injector.inject(null));
    }
}