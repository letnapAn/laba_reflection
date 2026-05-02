package org.example.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Маркерная аннотация для полей, подлежащих автоматическому внедрению зависимостей.
 * <p>
 * Класс {@code Injector} сканирует объект на наличие полей с данной аннотацией,
 * определяет их интерфейс и инициализирует классом-реализацией,
 * указанным в конфигурационном {@code .properties} файле.
 * </p>
 *
 * @see Injector
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {
}
