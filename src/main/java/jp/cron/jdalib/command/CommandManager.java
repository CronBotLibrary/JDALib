package jp.cron.jdalib.command;

import jp.cron.jdalib.command.entity.Category;
import jp.cron.jdalib.util.common.CommonUtil;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandManager {

    public List<Category> categories;

    public void register(Category category){
        categories.add(category);
        category.registerMethodMap();
    }
}
