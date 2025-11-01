package gam.util;

import java.lang.reflect.InvocationTargetException;

public class Factory {
    public static Object get(String className, Class<?>... params) {
        return Factory.get(className, false, params);
    }

    public static Object get(String className, boolean createNew, Class<?>... params) {
        Object singleton = new Object();
        
        try {
            if (createNew) {
                return Class.forName(className).getConstructor(params).newInstance();
            }

            singleton = Flyweight.get(className);
            if (null == singleton) {
                return Class.forName(className).getConstructor(params).newInstance();
            }
            return singleton;
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return singleton;
    }
}