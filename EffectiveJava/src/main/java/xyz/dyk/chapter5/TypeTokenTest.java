package xyz.dyk.chapter5;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

public class TypeTokenTest {
    static Annotation getAnnotation(AnnotatedElement element, String annotationTypeName) {
        Class<?> annotationType = null;
        try {
            annotationType = Class.forName(annotationTypeName);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return element.getAnnotation(annotationType.asSubclass(Annotation.class));
    }
}
