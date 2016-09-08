package apl;

import java.util.Map;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by DNLab on 2016-05-06.
 */
public class DynamicClassProvider implements IPrintable {
    static Map<Pair<String, String>, String> m_cls = new HashMap<Pair<String, String>, String>();
    static Map<Pair<String, String>, Object> m_cache = new WeakHashMap<Pair<String, String>, Object>();

    public static void register(String nick, String creator, String path) {
        m_cls.put(new Pair<String, String>(nick, creator), path);
        System.out.println("[PUT] m_cls Size: " + Integer.toString(m_cls.size()));
    }

    public static Object newInstance(String nick, String creator) {
        Pair<String, String> inputPair = new Pair<String, String>(nick, creator);

        if( m_cache.containsKey(inputPair) ) {
            return m_cache.get(inputPair);
        } else {
            Class aClass = null;
            Object newClass = null;
            try {
                //ClassLoader classloader = ClassLoader.getSystemClassLoader();
                String classPath = m_cls.get(inputPair);
                System.out.println("[NEW] ClassPath: " + classPath);
                //aClass = classloader.loadClass("");
                aClass = Class.forName(classPath);
                try {
                    newClass = aClass.newInstance();
                    ((Tablet)newClass).printName();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            m_cache.put(inputPair, newClass);
            return newClass;
        }
    }

    @Override
    public void printName() {
        System.out.println("DynamicClassProvider");
    }
}
