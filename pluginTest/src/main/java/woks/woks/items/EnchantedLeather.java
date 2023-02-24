package woks.woks.items;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

//EnchatedLeather
public class EnchantedLeather {
    public static ItemStack EnchantedLeather() {
        ItemStack item = new ItemStack(Material.LEATHER);
        NBTItem nbti = new NBTItem(item);

        // add custom data
        nbti.setBoolean("Disable", true);

        // make it back to ItemStack / save it
        item = nbti.getItem();

        // add a glint to show it's a enchanted
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

        return item;
    }
}
