package woks.woks.items.PRQ.Obamanium;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

// 3,6,83 arrom
public class Obamanium_Scrap {
    public static ItemStack Obamanium_Scrap(int amount) {
        ItemStack item = new ItemStack(Material.DIAMOND, amount);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName("§r§l§9Obamanium Scrap");

        item.setItemMeta(itemMeta);

        NBTItem nbti = new NBTItem(item);

        // custom data
        nbti.setBoolean("Disable", false);
        nbti.setBoolean("DisableCrafting", false);
        nbti.setBoolean("IsInvulnerableOnDrop", true);

        item = nbti.getItem();
        return item;
    }
}
