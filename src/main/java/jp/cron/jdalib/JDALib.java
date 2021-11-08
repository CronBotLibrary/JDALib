package jp.cron.jdalib;

import jp.cron.jdalib.command.CommandListener;
import jp.cron.jdalib.command.CommandManager;
import jp.cron.jdalib.util.activity.ActivityListener;
import jp.cron.jdalib.util.activity.ActivityManager;
import jp.cron.jdalib.util.usage.UsageManager;
import net.dv8tion.jda.api.JDA;

public class JDALib {
    public static JDALib INSTANCE;
    public JDA jda;

    public Property property;

    // util futures
    public ActivityManager activityManager = null;
    public UsageManager usageManager = new UsageManager(this);
    public CommandManager commandManager = new CommandManager();

    public JDALib(JDA jda) {
        INSTANCE = this;
        this.jda = jda;
        this.property = new Property();

        jda.addEventListener(
                new Listener(),
                new ActivityListener(this),
                new CommandListener(this)
        );
    }

    public JDALib setActivity(Boolean status, String pattern){
        if (activityManager == null){
            this.activityManager = new ActivityManager(this, status, pattern);
        } else {
            this.activityManager.setStatus(status);
            this.activityManager.setPattern(pattern);
        }
        return this;
    }

    public JDALib setPrefix(String prefix) {
        property.setPrefix(prefix);
        return this;
    }
}
