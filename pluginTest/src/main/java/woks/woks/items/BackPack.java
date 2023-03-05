package woks.woks.items;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import woks.woks.WOKS;

import java.util.Collections;
import java.util.UUID;

public class BackPack {
    public static ItemStack BackPack(int Space, boolean uuid) {
        ItemStack item = new ItemStack(Material.CHEST);
        NBTItem nbti = new NBTItem(item);
        ItemStack[] LMODE = new ItemStack[]{};

//        if (!uuid) {
//            ItemMeta mete = item.getItemMeta();
//            UUID id = UUID.randomUUID();
//            nbti.setString("UUIDToPreventDuping", String.valueOf(id));
//            assert mete != null;
//            mete.setLore(Collections.singletonList(String.valueOf(id)));
//            item.setItemMeta(mete);
//        }


        // add custom data
        nbti.setInteger("Space", Space);
        nbti.setItemStackArray("ItemsE", LMODE);
        nbti.setBoolean("BackPack", true);
        nbti.setBoolean("Using", false);
        nbti.setBoolean("DisableCrafting", false);

            // make it back to ItemStack / save it
            item = nbti.getItem();
//        item.getItemMeta().setLore(Collections.singletonList((id).toString()));

        // add a glint to show it's a backpack
        item.addEnchantment(WOKS.getInstance().FALK, 1);

        return item;
    }
}
