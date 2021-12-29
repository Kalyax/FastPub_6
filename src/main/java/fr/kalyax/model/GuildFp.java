package fr.kalyax.model;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import fr.kalyax.FastPub;
import net.dv8tion.jda.api.entities.Guild;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class GuildFp {
    private final String _id;
    private String name;
    private String iconHash;
    private Long createdTimestamp;
    private String ownerId;

    private String description;
    private String channelId;
    private String inviteLink;
    private String prefix;
    private int pubSent;
    private Long bump;

    public GuildFp(Guild guild) {
        this._id = guild.getId();
        this.name = guild.getName();
        this.iconHash = guild.getIconId();
        this.createdTimestamp = guild.getTimeCreated().toInstant().toEpochMilli();
        this.ownerId = guild.getOwnerId();

        MongoClient client = FastPub.getClient();
        MongoCollection<Document> collection = client.getDatabase("fastpub").getCollection("guilds");
        Document guildFp = collection.find(eq("_id", guild.getId())).first();
        client.close();
        if (guildFp != null) {
            this.description = guildFp.getString("description");
            this.channelId = guildFp.getString("channelId");
            this.inviteLink = guildFp.getString("inviteLink");
            this.prefix = guildFp.getString("prefix");
            this.pubSent = guildFp.getInteger("pubSent");
            this.bump = guildFp.getLong("bump");
        } else {
            this.description = "";
            this.channelId = "";
            this.inviteLink = "";
            this.prefix = "f!";
            this.pubSent = 0;
            this.bump = null;
        }
    }

    public static void saveGuild(GuildFp guildFp) {
        MongoClient client = FastPub.getClient();
        MongoCollection<Document> collection = client.getDatabase("fastpub").getCollection("guilds");
        Gson gson = new Gson();
        collection.insertOne(Document.parse(gson.toJson(guildFp)));
        client.close();
    }

    public static boolean exists(String id){
        MongoClient client = FastPub.getClient();
        MongoCollection<Document> collection = client.getDatabase("fastpub").getCollection("guilds");
        Document guildFp = collection.find(eq("_id", id)).first();
        client.close();
        return guildFp != null;
    }


    //GETTERS AND SETTERS
    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconHash() {
        return iconHash;
    }

    public void setIconHash(String iconHash) {
        this.iconHash = iconHash;
    }

    public Long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getInviteLink() {
        return inviteLink;
    }

    public void setInviteLink(String inviteLink) {
        this.inviteLink = inviteLink;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getPubSent() {
        return pubSent;
    }

    public void setPubSent(int pubSent) {
        this.pubSent = pubSent;
    }

    public Long getBump() {
        return bump;
    }

    public void setBump(Long bump) {
        this.bump = bump;
    }
}