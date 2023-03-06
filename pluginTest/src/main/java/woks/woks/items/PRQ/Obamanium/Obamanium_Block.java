package woks.woks.items.PRQ.Obamanium;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Obamanium_Block {
    public static ItemStack Obamanium_Block() {
        ItemStack item = new ItemStack(Material.DIAMOND_BLOCK, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§r§l§9Obamanium Block");
        itemMeta.setCustomModelData(12345678);
        item.setItemMeta(itemMeta);

        NBTItem nbti = new NBTItem(item);

        // custom data
        nbti.setBoolean("Disable", true);
        nbti.setBoolean("DisableCrafting", false);
        nbti.setBoolean("IsInvulnerableOnDrop", true);
        nbti.setBoolean("KeepDataOnPlace", true);
        nbti.setBoolean("IsCustomItem", true);
//        nbti.setBoolean("DisableNormalCrafting", true); // this is placeholder I would need to make this

        item = nbti.getItem();
        return item;
    }
}
