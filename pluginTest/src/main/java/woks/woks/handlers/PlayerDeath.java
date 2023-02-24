package woks.woks.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import woks.woks.Msg;
import woks.woks.WOKS;

public class PlayerDeath implements Listener {
    public PlayerDeath(WOKS plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        assert player != null;
        Location Cords = player.getLocation();
        Msg.send(player, "Your death cords are: " + Cords.getBlockX() + ", " + Cords.getBlockY() + ", " + Cords.getBlockZ() + ".");
    }
}