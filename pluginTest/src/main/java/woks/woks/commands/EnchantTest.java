package woks.woks.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.WOKS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static woks.woks.romanNumbers.integerToRoman;

public class EnchantTest {
    public EnchantTest() {
        new CommandBase("enchanttest", 2, 2, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                if (itemStack.getType() != Material.AIR) {
                    Enchantment enchantment;
                    switch (arguments[0]) {
                        case "throw_down" -> {
                            enchantment = WOKS.getInstance().THROWDOWN;
                            break;
                        }
                        case "auto_put" -> {
                            enchantment = WOKS.getInstance().AUTOPUT;
                            break;
                        }
                        case "falk" -> {
                            enchantment = WOKS.getInstance().FALK;
                            break;
                        }
                        default -> {
                            Msg.send(player, "Unexpected value: " + arguments[0]);
                            throw new IllegalStateException("Unexpected value: " + arguments[0]);
                        }
                    }
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    final int EnchantmentLevel = Integer.parseInt(arguments[1]);
                    assert itemMeta != null;
                    itemMeta.addEnchant(enchantment, EnchantmentLevel, true);
                    if (itemMeta.hasLore()) {
                        List<String> lores = itemMeta.getLore();
                        assert lores != null;
                        lores.add(ChatColor.GRAY + enchantment.getName() + ' ' + integerToRoman(EnchantmentLevel));
                        itemMeta.setLore(lores);
                    } else {
                        itemMeta.setLore(Collections.singletonList(ChatColor.GRAY + enchantment.getName() + ' ' + integerToRoman(EnchantmentLevel)));
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
                return "/enchanttest <string:name> <int:level>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                //define the possible possibility's for argument 1
                if (args.length == 1){
                    List<String> l = new ArrayList<String>(); //makes a ArrayList

                    l.add("throw_down");
                    l.add("auto_put");
                    l.add("falk");

                    return l;
                } else if (args.length == 2){
                    List<String> f = new ArrayList<String>(); //makes a ArrayList

                    f.add("1");
                    f.add("2");
                    f.add("3");
                    f.add("4");
                    f.add("5");

                    return f;
                }

                return null; //this is a little confusing but just put it there
            }
        };
    }
}
