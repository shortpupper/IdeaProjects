package woks.woks.matthew;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class roles implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Bukkit.getLogger().info("[woks] DUCK THERES MSG");
        Player player = event.getPlayer();
        String message = event.getMessage();

        String role = getRole(player); // Replace this with your logic to determine the player's role

        String formattedMessage =  role + "<" + player.getName() + "> " + message;
        event.setFormat(ChatColor.translateAlternateColorCodes('&', formattedMessage));
    }

    private String getRole(Player player) {

        if (player.getScoreboardTags().contains("admin")) {
            Bukkit.getLogger().info("[woks] DUCK THERE IS AN ADMIN");
            return "[ADMIN]";
        }

        Bukkit.getLogger().info("[woks] player tags, "+player.getScoreboardTags());
        return "";
    }
}




