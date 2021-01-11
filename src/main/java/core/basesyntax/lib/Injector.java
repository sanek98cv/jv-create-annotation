package core.basesyntax.lib;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.exception.NoAnnotationException;
import core.basesyntax.factory.Factory;
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
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Object dao = new Object();
                if (field.getType() == BetDao.class) {
                    dao = Factory.getBetDao();
                }
                if (field.getType() == UserDao.class) {
                    dao = Factory.getUserDao();
                }
                if (dao.getClass().getAnnotation(Dao.class) == null) {
                    throw new NoAnnotationException("Can't find annotation @Dao in "
                            + dao.getClass());
                }
                field.set(instance, dao);
            }
        }
        return instance;
    }
}
