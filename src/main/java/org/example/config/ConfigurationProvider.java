package org.example.config;


public interface ConfigurationProvider {

    /**
     * Возвращает имя класса-реализации для указанного интерфейса.
     *
     * @param interfaceName полное имя интерфейса
     * @return полное имя класса-реализации или {@code null}, если маппинг не найден
     */
    String getImplementation(String interfaceName);
}