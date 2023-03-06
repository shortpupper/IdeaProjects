package woks.woks.items.PRQ.Obamanium;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
public class Obamanium_Ingot {
    public static ItemStack Obamanium_Ingot(int amount) {
        ItemStack item = new ItemStack(Material.DIAMOND, amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§r§l§9Obamanium Ingot");
        itemMeta.setCustomModelData(12345678);
        item.setItemMeta(itemMeta);

        NBTItem nbti = new NBTItem(item);

        // custom data
        nbti.setBoolean("Disable", true);
        nbti.setBoolean("DisableCrafting", false);
        nbti.setBoolean("IsInvulnerableOnDrop", true);
        nbti.setBoolean("IsCustomItem", true);
//        nbti.setBoolean("DisableNormalCrafting", true); // this is placeholder I would need to make this

        item = nbti.getItem();
        return item;
    }
}
