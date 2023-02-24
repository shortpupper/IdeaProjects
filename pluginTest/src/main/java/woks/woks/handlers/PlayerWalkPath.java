package woks.woks.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import woks.woks.WOKS;

import java.util.Random;

public class PlayerWalkPath implements Listener {
    public PlayerWalkPath(WOKS plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onWalk(PlayerMoveEvent event) {
        Random random = new Random();
        int x = random.nextInt(25);
        if (x == 4) {
            Entity entity = event.getPlayer();
//            Bukkit.getLogger().info("doctor.");
            final Block block = entity.getLocation().subtract(0, 1, 0).getBlock();
            final Material type = block.getState().getType();
//            Bukkit.getLogger().info(String.valueOf(type));
            if (type.equals(Material.GRASS_BLOCK)) {
                block.setType(Material.DIRT_PATH);
            }
        }
    }
}