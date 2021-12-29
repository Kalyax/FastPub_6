package fr.kalyax.model;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import fr.kalyax.FastPub;
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;
import java.time.Instant;

import static com.mongodb.client.model.Filters.eq;

public class UserFP {
    private final String _id;
    private String avatarHash;
    private final Long createdTimestamp;
    private String username;
    private String discriminator;

    private Long lastLogin;
    private Long lastPub;
    private int money;
    private int pubCounter;
    private String color;

    public UserFP(User user){
        this._id = user.getId();
        this.avatarHash = user.getAvatarId();
        this.createdTimestamp = user.getTimeCreated().toInstant().toEpochMilli();
        this.username = user.getName();
        this.discriminator = user.getDiscriminator();

        MongoClient client = FastPub.getClient();
        MongoCollection<Document> collection = client.getDatabase("fastpub").getCollection("users");
        Document userFP = collection.find(eq("_id", user.getId())).first();
        client.close();
        if(userFP != null){
            this.lastLogin = userFP.getLong("createdTimestamp");
            this.lastPub = userFP.getLong("lastPub");
            this.money = userFP.getInteger("money");
            this.pubCounter = userFP.getInteger("pubCounter");
            this.color = userFP.getString("color");
        }
        else{
            this.lastLogin = Instant.now().toEpochMilli();
            this.lastPub = null;
            this.money = 0;
            this.pubCounter = 0;
            this.color = "#ffffff";
        }
    }

    public UserFP(String id){
        this._id = id;
        MongoClient client = FastPub.getClient();
        MongoCollection<Document> collection = client.getDatabase("fastpub").getCollection("users");
        Document userFP = collection.find(eq("_id", id)).first();
        if(userFP != null && !userFP.isEmpty()){
            this.avatarHash = userFP.getString("avatarHash");
            this.createdTimestamp = userFP.getLong("createdTimestamp");
            this.username = userFP.getString("username");
            this.discriminator = userFP.getString("discriminator");

            this.lastLogin = userFP.getLong("createdTimestamp");
            this.lastPub = userFP.getLong("lastPub");
            this.money = userFP.getInteger("money");
            this.pubCounter = userFP.getInteger("pubCounter");
            this.color = userFP.getString("color");
        }
        else{
            throw new NullPointerException("No user exist with this ID");
        }
        client.close();
    }

    public static void saveUser(UserFP userFP){
        MongoClient client = FastPub.getClient();
        MongoCollection<Document> collection = client.getDatabase("fastpub").getCollection("users");
        Gson gson = new Gson();
        collection.insertOne(Document.parse(gson.toJson(userFP)));
        client.close();
    }

    public String getId() {
        return _id;
    }

    public String getAvatarHash() {
        return avatarHash;
    }

    public void setAvatarHash(String avatarHash) {
        this.avatarHash = avatarHash;
    }

    public Long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getLastPub() {
        return lastPub;
    }

    public void setLastPub(Long lastPub) {
        this.lastPub = lastPub;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPubCounter() {
        return pubCounter;
    }

    public void setPubCounter(int pubCounter) {
        this.pubCounter = pubCounter;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
