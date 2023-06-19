package woks.woks.matthew;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import woks.woks.Msg;
import woks.woks.NKD;
import woks.woks.WOKS;
import woks.woks.matthew.util.ExtraDataContainer;

import static woks.woks.WOKS.Ranks;

public class roles implements Listener {

    public roles(WOKS plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', ("[&c§c[WARNING] This exe does not run in DOS, please upgrade to windows 93. [WARNING]§c§r]")));

        ExtraDataContainer dataContainer = new ExtraDataContainer(player.getPersistentDataContainer());
        String role = getRole(dataContainer); // Replace this with your logic to determine the player's role

        String formattedMessage =  role + "<" + player.getName() + "> " + message;
        event.setFormat(ChatColor.translateAlternateColorCodes('&', formattedMessage));
        if (message.equalsIgnoreCase("hello")) {
            if (((int) dataContainer.get(NKD.INTEGER_ID)) == 2 && ((double) dataContainer.get(NKD.PERCENT_OF_DONE)) < 100.0d) {
                dataContainer.set(NKD.PERCENT_OF_DONE, 100.0d);
                Msg.send(player, "§bQuest completed!§r");
            }
        }
    }

    public String getRole(ExtraDataContainer dataContainer) {
        Integer role_rank_air_number = dataContainer.get(NKD.PLAYER_RANK);
        String role_rank_air = Ranks[role_rank_air_number];

        String admin = "";
        if (dataContainer.has(NKD.IS_ADMIN)) {
            admin = "§d[Admin]§f";
        }



        return admin + "[" + role_rank_air + "]";
    }
}




