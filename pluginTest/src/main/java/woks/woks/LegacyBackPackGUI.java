package woks.woks;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
@Deprecated
public class LegacyBackPackGUI {

    public LegacyBackPackGUI(NBTItem Item, Player player) {
        int space = Item.getInteger("Space");

        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        Inventory inv = Bukkit.createInventory(null, space, "BackPack");

        // Put the items into the inventory
        ItemStack[] Items = Item.getItemStackArray("ItemsE");
        inv.setStorageContents(Items);

        // access inventory
        player.openInventory(inv);
    }
}
