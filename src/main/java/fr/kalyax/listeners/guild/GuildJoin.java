package fr.kalyax.listeners.guild;

import fr.kalyax.model.GuildFp;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildJoin extends ListenerAdapter {

    @Override
    public void onGuildJoin(GuildJoinEvent e){
        GuildFp guild = new GuildFp(e.getGuild());
        GuildFp.saveGuild(guild);
    }

}
