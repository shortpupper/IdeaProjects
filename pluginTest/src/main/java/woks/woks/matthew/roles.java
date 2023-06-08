package woks.woks.matthew;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.Msg;
import woks.woks.WOKS;

import static woks.woks.WOKS.*;

public class roles implements Listener {

    public roles(WOKS plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void chatCheck(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
//        Bukkit.getLogger().info("[woks] DUCK THERES MSG");
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', String.valueOf("[&c§c[WARNING] This exe does not run in DOS, please upgrade to windows 93. [WARNING]§c§r]")));

        String role = getRole(player); // Replace this with your logic to determine the player's role

        String formattedMessage =  role + "<" + player.getName() + "> " + message;
        event.setFormat(ChatColor.translateAlternateColorCodes('&', formattedMessage));
        if (message.equalsIgnoreCase("hello")) {
            PersistentDataContainer dataContainer = player.getPersistentDataContainer();
            if (dataContainer.get(_quest_id_integer, PersistentDataType.INTEGER) == 2 && dataContainer.get(_quest_percent_done, PersistentDataType.DOUBLE) < 100.0d) {
                dataContainer.set(_quest_percent_done, PersistentDataType.DOUBLE, 100.0d);
                Msg.send(player, "§bQuest completed!§r");
            }
        }
    }

    public String getRole(Player player) {

        PersistentDataContainer dataContainer = player.getPersistentDataContainer();

//        // COULD DO THIS ON JOIN
//        if (!dataContainer.has(_namespacedKeyNumberRank, PersistentDataType.INTEGER)) {
//            dataContainer.set(_namespacedKeyNumberRank, PersistentDataType.INTEGER, 0);
//        }

        Integer role_rank_air_number = dataContainer.get(_namespacedKeyNumberRank, PersistentDataType.INTEGER);
        String role_rank_air = Ranks[role_rank_air_number];

        String admin = "";
        if (dataContainer.has(_admin, PersistentDataType.INTEGER)) {
            admin = "§d[Admin]§f";
        }



        return admin + "[" + role_rank_air + "]";


        // old stuff
//        if (player.getScoreboardTags().contains("roleAdmin")) {
//            return "[ADMIN]";
//        } else if (player.getScoreboardTags().contains("roleVeteran")) {
//            return "[Veteran]";
//        } else if (player.getScoreboardTags().contains("rolePlebe")) {
//            return "[Plebe]";
//        } else if (player.getScoreboardTags().contains("roleRecruit")) {
//            return "[Recruit]";
//        }

//        Bukkit.getLogger().info("[woks] player tags, "+player.getScoreboardTags());
//        return "";
    }
}




