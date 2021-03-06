package xyz.dyk.runtimeAnnotations;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ActionListenerInstaller {
    /**
     * Process all ActionListenerFor annotations in the given object.
     *
     * @param obj an object whose methods may have ActionListenerFor annotations
     */
    public static void processAnnotations(Object obj) {
        try {
            Class<?> cl = obj.getClass();
            for (Method m : cl.getDeclaredMethods()) {
                ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
                if (a != null) {
                    Field f = cl.getDeclaredField(a.source());
                    f.setAccessible(true);
                    addListener(f.get(obj), obj, m);
                }
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds an action listener that calls a given method.
     *
     * @param source the event source to which an action listener is added
     * @param param  the implicit parameter of the method that the listener calls
     * @param m      the method that the listener calls
     * @throws ReflectiveOperationException
     */
    public static void addListener(Object source, final Object param, final Method m) throws ReflectiveOperationException {
        InvocationHandler handler = (proxy, method, args) -> m.invoke(param);
        Object listener = Proxy.newProxyInstance(null,
                new Class[]{ActionListener.class}, handler);
        Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
        adder.invoke(source, listener);
    }
}
