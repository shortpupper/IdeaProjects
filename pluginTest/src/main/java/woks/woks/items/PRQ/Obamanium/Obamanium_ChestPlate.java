package woks.woks.items.PRQ.Obamanium;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

// 3,6,83 arrom
public class Obamanium_ChestPlate {
    public static ItemStack Obamanium_ChestPlate() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta itemMeta = item.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName("Obamanium Chestplate");
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("generic.armor", 9, AttributeModifier.Operation.ADD_NUMBER));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier("generic.armor_toughness", 4, AttributeModifier.Operation.ADD_NUMBER));
        itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier("generic.knockback_resistance", 2, AttributeModifier.Operation.ADD_NUMBER));


        item.setItemMeta(itemMeta);

        NBTItem nbti = new NBTItem(item);

        // custom data
        nbti.setBoolean("Disable", false);
        nbti.setBoolean("DisableCrafting", false);
//        nbti.setInteger("Exp", Exp);

        item = nbti.getItem();
        return item;
    }
}
