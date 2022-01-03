package Data;

import java.util.HashMap;

public class Container {
    private static final HashMap<String,Object> container = new HashMap<>();

    public static void put(Object obj) {
        container.put(obj.getClass().getName(),obj);
    }

    public static Object get(String className) {
        return container.get(className);
    }
}
