package woks.woks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import woks.woks.CommandBase;

import java.util.List;

@Deprecated
// deprecated, might come back to, was an idea but never came back
public class EnchantMe {
    public EnchantMe() {
        new CommandBase("enchantme", 0, 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
//                Bukkit.getLogger().info("command for enchant ran.");
//                Player player = (Player) sender;
//                String Enchamet = arguments[0];
////                if (Enchantment.getByKey(Enchamet)) {
////
////                }
//                int playerEXP = ExperienceManager.getTotalExperience(player);
//                ItemStack Item = player.getInventory().getItemInMainHand();
//                if (playerEXP >= ExperienceManager.getTotalExperience(30)) {
//                    if (Item.getType() != Material.AIR) {
//                        if (!Item.getItemMeta().hasLore()) {
//                            ItemMeta im = Item.getItemMeta();
//                            assert im != null;
//                            im.setLore(Collections.singletonList("1"));
//                            Item.setItemMeta(im);
//                        }
//                        int EXPCost = Integer.parseInt(Item.getItemMeta().getLore().get(0)) * 100;
//                        if (0 <= playerEXP-EXPCost) {
//                            ExperienceManager.setTotalExperience(player, ExperienceManager.getTotalExperience(player)-EXPCost);
//                            if (Item.containsEnchantment(Enchamet)) {
//
//                            }
//                            Item.addUnsafeEnchantment();
//                        } else {
//                            Msg.send(player, "You do not have enough EXP.");
//                        }
//
//
//                    } else {
//                        Msg.send(player, "You can't enchant air or multiply it.");
//                    }
//                } else {
//                    Msg.send(player, "You need to be at least level 30 to run this command.");
//                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/enchantme";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        };
    }
}
