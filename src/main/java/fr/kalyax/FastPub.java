package fr.kalyax;

import com.mongodb.MongoClient;
import fr.kalyax.listeners.UserGuildJoinEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class FastPub {

    public static void main(String[] args) throws LoginException, InterruptedException {
        JDA jda = JDABuilder.createDefault(args[0]).build();
        jda.awaitReady();

        jda.addEventListener(new UserGuildJoinEvent());
    }

    public static MongoClient getClient(){
        return new MongoClient("localhost", 27017);
    }

}
