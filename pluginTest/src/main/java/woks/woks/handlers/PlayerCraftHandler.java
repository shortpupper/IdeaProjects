package woks.woks.handlers;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import woks.woks.Msg;
import woks.woks.WOKS;

public class PlayerCraftHandler implements Listener {
    public PlayerCraftHandler(WOKS plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerCraft(CraftItemEvent event) {
        for (HumanEntity entity : event.getViewers()) {
            if (entity instanceof Player player) {
                ItemStack[] item = event.getInventory().getMatrix();
                for(int i =0; i < 8; i++) {
                    if(new NBTItem(item[i]).getBoolean("DisableCrafting")) {
                        event.setCancelled(true);
                        Msg.send(player, "Can't craft with this item.");
                    }
                }
            }
        }
    }
}
