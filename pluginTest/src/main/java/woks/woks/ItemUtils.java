package woks.woks;

import org.bukkit.inventory.ItemStack;

public class ItemUtils {
    public static void addItem(ItemStack[] itemStackArray, int index, ItemStack item) {
        itemStackArray[index] = item;
    }
}
