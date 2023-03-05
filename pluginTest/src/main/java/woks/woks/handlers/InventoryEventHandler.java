package woks.woks.handlers;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import woks.woks.Msg;
import woks.woks.WOKS;

//InventoryCloseEventHandler
public class InventoryEventHandler implements Listener {
    public InventoryEventHandler(WOKS plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        // should really make more of a check cus of renaming
        String Title = event.getView().getTitle();
        if (Title.startsWith("PlayerInventoryGUI: ", 1)) {
            Player player2 = Bukkit.getPlayer(Title.substring(1 + "PlayerInventoryGUI: ".length()));
            if (player2 != null) {
                if (Title.startsWith("0")) {
                    player2.getInventory().setStorageContents(player.getOpenInventory().getTopInventory().getStorageContents());
                } else if (Title.startsWith("1")) {
                    player2.getEnderChest().setStorageContents(player.getOpenInventory().getTopInventory().getStorageContents());
                } else {
                    Msg.send(player, "An error has sdcard.");
                }
            } else {
                Msg.send(player, "there was a problem player2 might have eq null.");
            }
        }
        else {
            ItemStack Item = inventory.getItemInMainHand();
            if (!(Item.getType() == Material.AIR || Item.getAmount() == 0)) {
                if (Item.getType() != Material.CHEST) {
                    NBTItem nbtItem = new NBTItem(inventory.getItemInOffHand());
                    if (nbtItem.getBoolean("Using") && inventory.getItemInOffHand().getType() == Material.CHEST) {
                        Bukkit.getLogger().info("Bye bye say hello to air.7");
                        player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
//                ItemStack[] ItemsE = event.getInventory().getStorageContents();
//                nbtItem.setItemStackArray("ItemsE", ItemsE);
//                nbtItem.setBoolean("Using", false);
//
//                // might need fixing
//                player.getInventory().setItemInOffHand(nbtItem.getItem());
                    }

                } else if (Item.getType() == Material.CHEST) {
                    NBTItem nbtItem = new NBTItem(Item);
                    if ((nbtItem.getBoolean("BackPack")) && (event.getView().getTitle().equals("BackPack"))) {
                        ItemStack[] ItemsE = event.getInventory().getStorageContents();
                        nbtItem.setItemStackArray("ItemsE", ItemsE);
                        nbtItem.setBoolean("Using", false);

                        // might need fixing
                        player.getInventory().setItemInMainHand(nbtItem.getItem());
                    }
                }
            }
        }
    }
}
