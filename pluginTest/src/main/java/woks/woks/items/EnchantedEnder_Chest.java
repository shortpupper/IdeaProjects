package woks.woks.items;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

//EnchatedLeather
public class EnchantedEnder_Chest {
    public static ItemStack EnchantedEnder_Chest() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST);
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
