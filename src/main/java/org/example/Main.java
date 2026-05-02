package org.example;

import org.example.config.PropertiesConfigurationProvider;
import org.example.injector.Injector;
import org.example.model.SomeBean;

public class Main {
    public static void main(String[] args) {
        Injector injectorBC = new Injector(new PropertiesConfigurationProvider("AC.properties"));
        SomeBean bean = injectorBC.inject(new SomeBean());
        System.out.println(bean.foo());


        Injector injectorAC = new Injector(new PropertiesConfigurationProvider("BC.properties"));
        bean = injectorAC.inject(new SomeBean());
        System.out.println(bean.foo());
    }
}