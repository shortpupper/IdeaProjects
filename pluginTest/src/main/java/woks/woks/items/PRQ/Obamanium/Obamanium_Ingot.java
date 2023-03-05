package woks.woks.items.PRQ.Obamanium;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
public class Obamanium_Ingot {
    public static ItemStack Obamanium_Ingot() {
        ItemStack item = new ItemStack(Material.IRON_INGOT, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§r§l§9Obamanium Ingot");
        itemMeta.setCustomModelData(12345678);
        item.setItemMeta(itemMeta);
//        item.getItemMeta().setDisplayName("Obamanium Ingot");

        NBTItem nbti = new NBTItem(item);

        // custom data
        nbti.setBoolean("Disable", true);
        nbti.setBoolean("DisableCrafting", false);
        nbti.setBoolean("IsInvulnerableOnDrop", true);
        nbti.setBoolean("DisableNormalCrafting", true); // this is placeholder I would need to make this
//        nbti.setInteger("Exp", Exp);

        item = nbti.getItem();
        return item;
    }
}
