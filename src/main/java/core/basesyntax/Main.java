package core.basesyntax;

import core.basesyntax.controller.ConsoleHandler;
import core.basesyntax.lib.Injector;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        ConsoleHandler handler = null;
        try {
            handler = (ConsoleHandler) Injector.getInstance(ConsoleHandler.class);
        } catch (NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException("Can't get instance ConsoleHandler");
        }
        System.out.println("Enter your name and age also value and risk");
        handler.handle();
    }
}
