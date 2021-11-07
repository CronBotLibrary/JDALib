package jp.cron.jdalib.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {
    public abstract void call(MessageReceivedEvent event, String[] args);
}
