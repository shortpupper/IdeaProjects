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
public class Obamanium_Axe {
    public static ItemStack Obamanium_Axe() {
        ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName("§r§l§9Obamanium Axe");
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier((UUID.randomUUID()), "generic.attack_damage",  11, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier((UUID.randomUUID()), "generic.attack_speed", 3.9, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

        item.setItemMeta(itemMeta);

        NBTItem nbti = new NBTItem(item);

        // custom data
        nbti.setBoolean("Disable", false);
        nbti.setBoolean("DisableCrafting", false);
        nbti.setBoolean("IsInvulnerableOnDrop", true);
        nbti.setString("IsCustomItem", "1");

        item = nbti.getItem();
        return item;
    }
}
