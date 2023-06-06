package woks.woks.handlers;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import woks.woks.WOKS;

public class EntitysDeathKillCount implements Listener {
    public EntitysDeathKillCount(WOKS plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        String Name = event.getEntity().getName();
//        Entity dead = event.getEntity();
        Player player = event.getEntity().getKiller();

        if (player != null) {
            Bukkit.getLogger().info("[woks] player is teh killer.");

            ItemStack item = player.getInventory().getItemInMainHand();
            ItemStack item2 = player.getInventory().getItemInOffHand();
            int count;

            if (item.getType() == Material.AIR) {
                if (item2.getType() == Material.AIR) {
                    return;
                } else {
                    NBTItem nbtItem = new NBTItem(item);
//                    int count = (nbtItem.getInteger((Name + "_killed")) == null) ? 1 : nbtItem.getInteger(("killed_count_" + Name) + 1);
                    if (nbtItem.getInteger((Name + "_killed")) == null || (nbtItem.getInteger((Name + "_killed")) == 0)) {
                        count = 1;
                    } else {
                        count = nbtItem.getInteger(Name + "_killed")+ 1;
                    }
                    Bukkit.getLogger().info(Integer.valueOf(count).toString());
                    nbtItem.setInteger(Name + "_killed", count);

                    item = nbtItem.getItem();
                    player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
                }
            } else {
                NBTItem nbtItem = new NBTItem(item);

                if (nbtItem.getInteger((Name + "_killed")) == null || (nbtItem.getInteger((Name + "_killed")) == 0)) {
                    count = 1;
                } else {
                    count = nbtItem.getInteger(Name + "_killed")+ 1;
                }

                Bukkit.getLogger().info(Integer.valueOf(count).toString());
                nbtItem.setInteger(Name + "_killed", count);

                item = nbtItem.getItem();
                player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
            }
        }
    }
}
