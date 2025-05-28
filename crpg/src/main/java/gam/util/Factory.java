package gam.util;

import java.lang.reflect.InvocationTargetException;

public class Factory {
    public static Object get(String className,Class<?>... params) {
        try {
          return Class.forName(className).getConstructor(params).newInstance();
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
