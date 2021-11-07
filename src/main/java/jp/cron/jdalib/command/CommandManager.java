package jp.cron.jdalib.command;

import jp.cron.jdalib.command.entity.Command;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CommandManager {

    public HashMap<String, Command> classCommandHashMap = new HashMap<>();
    public HashMap<String, Method> methodCommandHashMap = new HashMap<>();

    public void registerCommand(Command command) {
        jp.cron.jdalib.command.Command annotation = command.getClass().getAnnotation(jp.cron.jdalib.command.Command.class);
        Category category = (Category)annotation.category().getAnnotation(Category.class);

        String str = category.prefix()+annotation.name();

        classCommandHashMap.put(str, command);
    }

    public void registerCategory(jp.cron.jdalib.command.entity.Category category){
        Category annotation = category.getClass().getAnnotation(Category.class);

        for (Method method : category.getClass().getMethods()) {

            jp.cron.jdalib.command.Command methodAnnotation = method.getAnnotation(jp.cron.jdalib.command.Command.class);

            String str = annotation.prefix()+methodAnnotation.name();

            methodCommandHashMap.put(str, method);
        }
    }
}
