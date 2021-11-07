package jp.cron.jdalib;

import jp.cron.jdalib.util.activity.ActivityManager;
import jp.cron.jdalib.util.activity.ActivityListener;
import net.dv8tion.jda.api.JDA;

public class JDALib {

    public JDA jda;

    public Property property;

    // futures
    public ActivityManager activityManager = null;

    public JDALib(JDA jda) {
        this.jda = jda;
        this.property = new Property();

        jda.addEventListener(
                new Listener(),
                new ActivityListener(this)
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
}
