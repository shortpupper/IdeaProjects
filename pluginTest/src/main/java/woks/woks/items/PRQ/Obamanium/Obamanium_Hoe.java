package woks.woks.items.PRQ.Obamanium;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

// 3,6,83 arrom
public class Obamanium_Hoe {
    public static ItemStack Obamanium_Hoe() {
        ItemStack item = new ItemStack(Material.DIAMOND_HOE, 1);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName("§r§l§9Obamanium Hoe");
        Multimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();
        attributeModifiers.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier((UUID.randomUUID()), "generic.attack_damage", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        attributeModifiers.put(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier((UUID.randomUUID()), "generic.attack_speed", 0.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

        itemMeta.setAttributeModifiers(attributeModifiers);
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
