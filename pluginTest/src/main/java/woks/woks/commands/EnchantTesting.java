package woks.woks.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.List;

public class EnchantTesting {
    public EnchantTesting() {
        new CommandBase("EnchantTesting",2, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                if ((player.isOp())) {
                    if (itemStack.getType() != Material.AIR) {
//                    ItemMeta itemMeta = itemStack.getItemMeta();
                        Enchantment enchantment = Enchantment.getByName(arguments[0]);
                        if (enchantment != null) {
                            itemStack.addUnsafeEnchantment(enchantment, Integer.parseInt(arguments[1]));
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
                return "/EnchantTesting <String:name> <level>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        };
    }
}
