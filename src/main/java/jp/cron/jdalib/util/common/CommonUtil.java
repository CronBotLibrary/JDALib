package jp.cron.jdalib.util.common;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CommonUtil {
    public static int countServer(JDA jda){
        return jda.getGuilds().size();
    }

    public static long countMembers(JDA jda){
        return jda.getGuilds().stream().mapToLong(Guild::getMemberCount).sum();
    }


    /**
     * @author 01es
     * https://stackoverflow.com/questions/6593597/java-seek-a-method-with-specific-annotation-and-its-annotation-element
     */
    public static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
        final List<Method> methods = new ArrayList<Method>();
        Class<?> klass = type;
        while (klass != Object.class) { // need to iterated thought hierarchy in order to retrieve methods from above the current instance
            // iterate though the list of methods declared in the class represented by klass variable, and add those annotated with the specified annotation
            for (final Method method : klass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(annotation)) {
                    Annotation annotInstance = method.getAnnotation(annotation);
                    methods.add(method);
                }
            }
            // move to the upper class in the hierarchy in search for more methods
            klass = klass.getSuperclass();
        }
        return methods;
    }
}
