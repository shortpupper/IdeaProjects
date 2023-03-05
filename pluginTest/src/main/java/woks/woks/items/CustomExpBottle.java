package woks.woks.items;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import woks.woks.WOKS;

public class CustomExpBottle {
    public static ItemStack customExpBottle(int Exp) {
        ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE);
        NBTItem nbti = new NBTItem(item);
        nbti.setInteger("Exp", Exp);
        nbti.setBoolean("DisableCrafting", true);
        item = nbti.getItem();
        item.addEnchantment(WOKS.getInstance().FALK, 1);
        return item;
    }
}
