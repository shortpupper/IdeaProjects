package woks.woks.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import woks.woks.CommandBase;
import woks.woks.romanNumbers;
import woks.woks.Msg;
import woks.woks.WOKS;

import java.util.Collections;

import static woks.woks.romanNumbers.integerToRoman;

public class EnchantTest {
    public EnchantTest() {
        new CommandBase("enchanttest", 0, 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                if (itemStack.getType() == Material.DIAMOND_SHOVEL) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    final int EnchantmentLevel = Integer.parseInt(arguments[0]);
                    itemMeta.addEnchant(WOKS.getInstance().THROWDOWN, EnchantmentLevel, true);
                    if (itemMeta.hasLore()) {
                        itemMeta.getLore().add(ChatColor.GRAY + "Throw Down " + integerToRoman(EnchantmentLevel));

                    } else {
                        itemMeta.setLore(Collections.singletonList(ChatColor.GRAY + "Throw Down " + integerToRoman(EnchantmentLevel)));
                    }
                    itemStack.setItemMeta(itemMeta);
                    player.getInventory().setItemInMainHand(itemStack);
                } else {
                    Msg.send(player, "Sorry you can only enchant diamond shovels");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/enchanttest <int:level>";
            }
        };
    }
}
