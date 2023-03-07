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
public class Obamanium_ChestPlate {
    public static ItemStack Obamanium_ChestPlate() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta itemMeta = item.getItemMeta();


        itemMeta.setDisplayName("§r§l§9Obamanium Chestplate");
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier((UUID.randomUUID()), "generic.armor", 9, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier((UUID.randomUUID()), "generic.armor_toughness", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier((UUID.randomUUID()), "generic.knockback_resistance", 0.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));


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
