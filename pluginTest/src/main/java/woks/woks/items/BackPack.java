package woks.woks.items;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.UUID;

public class BackPack {
    public static ItemStack BackPack(int Space) {
        UUID id = UUID.randomUUID();

        ItemStack item = new ItemStack(Material.CHEST);

        ItemMeta mete = item.getItemMeta();

        mete.setLore(Collections.singletonList(String.valueOf(id)));

        item.setItemMeta(mete);

        NBTItem nbti = new NBTItem(item);
        ItemStack[] LMODE = new ItemStack[]{};


        // add custom data
        nbti.setInteger("Space", Space);
        nbti.setItemStackArray("ItemsE", LMODE);
        nbti.setBoolean("BackPack", true);
        nbti.setBoolean("Using", false);
        nbti.setString("UUIDToPreventDuping", String.valueOf(id));

        // make it back to ItemStack / save it
        item = nbti.getItem();
//        item.getItemMeta().setLore(Collections.singletonList((id).toString()));

        // add a glint to show it's a backpack
//        item.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);

        return item;
    }
}
