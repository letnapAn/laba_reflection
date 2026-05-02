package org.example.injector;

import org.example.annotation.AutoInjectable;
import org.example.config.ConfigurationProvider;

import java.lang.reflect.Field;

/**
 * Reflection-based dependency injection container.
 * Scans objects for fields annotated with {@link AutoInjectable},
 * resolves implementations via {@link ConfigurationProvider}, and injects them.
 *
 * @since 1.0
 */
public class Injector {

    private final ConfigurationProvider configurationProvider;

    /**
     * Constructs injector with configuration source.
     *
     * @param configurationProvider provider for interface-to-implementation mappings
     */
    public Injector(ConfigurationProvider configurationProvider) {
        this.configurationProvider = configurationProvider;
    }

    /**
     * Injects dependencies into all marked fields of the target object.
     *
     * @param target object to process
     * @param <T>    type of target
     * @return same target instance with dependencies injected
     * @throws RuntimeException if configuration is missing, class cannot be loaded,
     *                          or reflection operations fail
     */
    public <T> T inject(T target) {
        if (target == null) {
            throw new IllegalArgumentException("Target cannot be null");
        }

        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                injectField(target, field);
            }
        }
        return target;
    }

    private void injectField(Object target, Field field) {
        field.setAccessible(true);
        String interfaceName = field.getType().getName();
        String implementationName = configurationProvider.getImplementation(interfaceName);

        if (implementationName == null) {
            throw new RuntimeException("No implementation configured for: " + interfaceName);
        }

        try {
            Class<?> implementationClass = Class.forName(implementationName);
            if (!field.getType().isAssignableFrom(implementationClass)) {
                throw new RuntimeException("Class " + implementationName + " does not implement " + interfaceName);
            }
            Object instance = implementationClass.getDeclaredConstructor().newInstance();
            field.set(target, instance);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 java.lang.reflect.InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Injection failed for field: " + field.getName(), e);
        }
    }
}