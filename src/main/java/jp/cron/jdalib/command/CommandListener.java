package jp.cron.jdalib.command;

import jp.cron.jdalib.JDALib;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class CommandListener extends ListenerAdapter {
    private JDALib jdaLib;

    public CommandListener(JDALib jdaLib) {
        this.jdaLib = jdaLib;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        jdaLib.commandManager.classCommandHashMap.forEach((str, cmd) -> {
            if (event.getMessage().getContentRaw().startsWith(str)) {
                String[] args = event.getMessage().getContentRaw().split(str, 2)[1].split(" ");
                cmd.call(event, args);
            }
        });

        jdaLib.commandManager.methodCommandHashMap.forEach((str, method) -> {
            if (event.getMessage().getContentRaw().startsWith(str)) {
                String[] args = event.getMessage().getContentRaw().split(str, 2)[1].split(" ");
                try {
                    method.invoke(event, args);
                } catch (IllegalAccessException | InvocationTargetException ignored) {}
            }
        });
    }
}
