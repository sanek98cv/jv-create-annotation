package core.basesyntax.lib;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.exception.NoAnnotationException;
import core.basesyntax.factory.FactoryBet;
import core.basesyntax.factory.FactoryUser;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Injector {
    public static Object getInstance(Class clazz) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor declaredConstructor = clazz.getDeclaredConstructor();
        Object instance = declaredConstructor.newInstance();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.getAnnotation(Inject.class) != null) {
                field.setAccessible(true);
                if (!FactoryBet.getBetDao().getClass().isAnnotationPresent(Dao.class)
                        || !FactoryUser.getUserDao().getClass().isAnnotationPresent(Dao.class)) {
                    throw new NoAnnotationException("Can't find annotation");
                }
                if (field.getType() == BetDao.class) {
                    field.set(instance, FactoryBet.getBetDao());
                }
                if (field.getType() == UserDao.class) {
                    field.set(instance, FactoryUser.getUserDao());
                }
            }
        }
        return instance;
    }
}
