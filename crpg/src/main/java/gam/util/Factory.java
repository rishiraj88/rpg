package gam.util;

import java.lang.reflect.InvocationTargetException;

public class Factory {
    public static Object get(String className, Class<?>... params) {
        return Factory.get(className, false, params);
    }

    public static Object get(String className, boolean createNew, Class<?>... params) {
        try {
            if (createNew) return Class.forName(className).getConstructor(params).newInstance();
            Object singleton = Flyweight.get(className);
            if (null == singleton) return Class.forName(className).getConstructor(params).newInstance();
            return singleton;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
