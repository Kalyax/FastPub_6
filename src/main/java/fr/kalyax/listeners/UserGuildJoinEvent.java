package fr.kalyax.listeners;

import com.google.gson.Gson;
import fr.kalyax.model.UserFP;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class UserGuildJoinEvent extends ListenerAdapter {

    /*@Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e){
    }*/

    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        if(!e.getAuthor().isBot()){
            /*UserFP test = new UserFP(e.getAuthor());
            UserFP.saveUser(test);*/

            UserFP userFp = new UserFP(e.getAuthor().getId());
            Gson gson = new Gson();
            e.getChannel().sendMessage(gson.toJson(userFp)).queue();
        }
    }

}
