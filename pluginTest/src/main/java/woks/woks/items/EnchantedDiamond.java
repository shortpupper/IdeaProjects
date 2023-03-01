package woks.woks.items;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import woks.woks.WOKS;

//EnchatedLeather
public class EnchantedDiamond {
    public static ItemStack EnchantedDiamond() {
        ItemStack item = new ItemStack(Material.DIAMOND);
        NBTItem nbti = new NBTItem(item);

        // add custom data
        nbti.setBoolean("Disable", true);

        // make it back to ItemStack / save it
        item = nbti.getItem();

        // add a glint to show it's a enchanted
        item.addUnsafeEnchantment(WOKS.getInstance().FALK, 1);

        return item;
    }
}
