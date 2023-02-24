package woks.woks.handlers;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import woks.woks.WOKS;

public class ItemDropped implements Listener {
    public ItemDropped(WOKS plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
//        Player player = event.getPlayer();
        ItemStack Item = event.getItemDrop().getItemStack();
        if (Item.getType() == Material.CHEST) {
            NBTItem NBTItem = new NBTItem(Item);
            if (NBTItem.hasNBTData() && NBTItem.getBoolean("BackPack")) {
                event.getItemDrop().setInvulnerable(true);
            }
        }
    }
}