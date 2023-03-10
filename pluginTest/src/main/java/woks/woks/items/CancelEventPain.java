package woks.woks.items;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import woks.woks.WOKS;

@Deprecated
public class CancelEventPain {
    public static ItemStack CancelEventPain(String data) {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        NBTItem nbti = new NBTItem(item);

        nbti.setBoolean("DisableCrafting", true);
        nbti.setBoolean("Disable", true);
        nbti.setString("ExtraData", data);

        item = nbti.getItem();
        return item;
    }
}