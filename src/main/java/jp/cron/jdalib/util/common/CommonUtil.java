package jp.cron.jdalib.util.common;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

public class CommonUtil {
    public static int countServer(JDA jda){
        return jda.getGuilds().size();
    }

    public static long countMembers(JDA jda){
        return jda.getGuilds().stream().mapToLong(Guild::getMemberCount).sum();
    }
}
