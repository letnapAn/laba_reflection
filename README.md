# laba_reflection

Простой контейнер внедрения зависимостей на основе рефлексии.

## Суть проекта

Класс `Injector` автоматически инициализирует поля объектов, помеченные аннотацией `@AutoInjectable`, подставляя реализации интерфейсов из конфигурационного файла `.properties`.

## Компоненты

| Компонент | Назначение |
|-----------|-----------|
| `@AutoInjectable` | Маркер для полей, которые нужно автоматически заполнить |
| `ConfigurationProvider` | Интерфейс для получения маппинга `интерфейс - реализация` |
| `PropertiesConfigurationProvider` | Читает настройки из `.properties`-файла |
| `Injector` | Сканирует объект, находит помеченные поля и внедряет зависимости через Reflection API |

## Основной класс с аннотацией
public class SomeBean {
    @AutoInjectable private SomeInterface field1;
    @AutoInjectable private SomeOtherInterface field2;
    
    public void foo() {
        field1.doSomething(); // выведет "A" или "B" в зависимости от файла конфигурации
        field2.doSomeOther(); // выведет "C"
    }
}
