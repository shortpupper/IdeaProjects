package woks.woks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerInvtoryGUI {

    public static void PlayerInvtoryGUI(Player player, Player player2, int ender) {
        // 'PlayerInventoryGUI: ' is 20 chr long
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        Inventory inv = Bukkit.createInventory(null, 36, String.valueOf(ender) + "PlayerInventoryGUI: " + player2.getName());

        // Put the items into the inventory
        ItemStack[] Items;
        if (ender == 1) {
            Items = player2.getEnderChest().getStorageContents();
        } else {
            Items = player2.getInventory().getStorageContents();
        }

        inv.setStorageContents(Items);

        // access inventory
        player.openInventory(inv);
    }
}