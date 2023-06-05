package woks.woks.handlers;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import woks.woks.WOKS;

import java.util.Objects;

public class EntitysDeathKillCount implements Listener {
    public EntitysDeathKillCount(WOKS plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        String Name = event.getEventName();
        Entity dead = event.getEntity();

        Bukkit.getLogger().info(Objects.requireNonNull(dead.getLastDamageCause()).toString());

        if (dead.getLastDamageCause() instanceof Player) {
            Player player = ((Player) dead.getLastDamageCause()).getPlayer();
            assert player != null;

            ItemStack item = player.getItemInUse();
            assert item != null;

            NBTItem nbtItem = new NBTItem(item);


            int count = (nbtItem.getInteger((Name + "_killed")) == null) ? 1 : nbtItem.getInteger(("killed_count_" + Name) + 1);
            Bukkit.getLogger().info(Objects.requireNonNull(count).toString());
            nbtItem.setInteger("killed_count_" + Name, count);

            item = nbtItem.getItem();
            player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
        }
    }
}
