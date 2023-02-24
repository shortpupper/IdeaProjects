package woks.woks.items;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CustomExpBottle {
    public static ItemStack customExpBottle(int Exp) {
        ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE);
        NBTItem nbti = new NBTItem(item);
        nbti.setInteger("Exp", Exp);
        item = nbti.getItem();
        return item;
    }
}
