package woks.woks.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.ArrayList;
import java.util.List;

import static woks.woks.WOKS.parseStringNameSpaceKey;

public class EnchantTesting {
    public EnchantTesting() {
        new CommandBase("EnchantTesting",3, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                if ((player.isOp())) {
                    if (itemStack.getType() != Material.AIR) {
//                    ItemMeta itemMeta = itemStack.getItemMeta();
                        Enchantment enchantment;
                        if (!Boolean.parseBoolean(arguments[0])) {
                            enchantment = Enchantment.getByName(arguments[1]);
                        } else {
                            enchantment = Enchantment.getByKey(parseStringNameSpaceKey(arguments[1]));
                        }

                        if (enchantment != null) {
                            itemStack.addUnsafeEnchantment(enchantment, Integer.parseInt(arguments[2]));
                        } else {
                            Msg.send(player, "Sorry the enchant does not exist.");
                        }

                    } else {
                        Msg.send(player, "You can't enchant air, lol.");
                    }
                } else {
                    Msg.send(player, "You need op to use this command.");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/EnchantTesting <namespaceKey:boolean> <String:name|String:namespaceKey> <int:level>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                if (sender.isOp()) {
                    if (args.length == 1) {
                        List<String> out = new ArrayList<>();

                        out.add("true");
                        out.add("false");

                        return out;
                    } else if (args.length == 2) {
                        List<String> out = new ArrayList<>();

                        if (args[0].equals("true")) {
                            for (Enchantment enchantment : Enchantment.values()) {
                                out.add(String.valueOf(enchantment.getKey()));
                            }
                        } else if (args[0].equals("false")) {
                            for (Enchantment enchantment : Enchantment.values()) {
                                out.add(enchantment.getName());
                            }
                        }

                        return out;
                    }
                }
                return null;
            }
        };
    }
}
