package fr.kalyax.listeners;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class UserGuildJoinEvent extends ListenerAdapter {

    /*@Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e){
    }*/

    /*@Override
    public void onMessageReceived(MessageReceivedEvent e){
        UserFP test = new UserFP(e.getAuthor());
        UserFP.saveUser(test);
        UserFP userFp = new UserFP(e.getAuthor().getId());
        Gson gson = new Gson();
        e.getChannel().sendMessage(gson.toJson(userFp)).queue();
    }*/

}
