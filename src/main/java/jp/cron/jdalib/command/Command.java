package jp.cron.jdalib.command.entity.annotations;

import jp.cron.jdalib.command.DefaultCategory;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.TYPE,
        ElementType.METHOD
})
public @interface Command {
    String name();
    String description() default "";
    Class category() default DefaultCategory.class;
}
