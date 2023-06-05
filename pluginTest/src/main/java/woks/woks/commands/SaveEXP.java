package woks.woks.commands;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;
import woks.woks.CommandBase;
import woks.woks.ExperienceManager;
import woks.woks.Msg;
import woks.woks.items.CustomExpBottle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SaveEXP {
    public SaveEXP() {
        new CommandBase("savexp", 1, 2, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                int ExpCount;
                Bukkit.getLogger().info("command for save ran.");
                if ("all".equals(arguments[0])) {
                    ExpCount = ExperienceManager.getTotalExperience(player);
                } else if (arguments.length == 2 && ("levels").contains(arguments[1].toLowerCase())) {
                    ExpCount = ExperienceManager.getTotalExperience(Integer.parseInt(arguments[0]));
                } else {
                    ExpCount = Integer.parseInt(arguments[0]);
                }
//                int ExpCount = Integer.parseInt(arguments[0]);
                if (ExperienceManager.getTotalExperience(player) >= ExpCount && ExpCount >= 1) {
                    ItemStack item = player.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        // remove the Exp
//                        player.setExp(player.getExp() - ExpCount);
                        ExperienceManager.setTotalExperience(player, ExperienceManager.getTotalExperience(player) - ExpCount);

                        // add it to an item lore and then give it to the player
                        player.getInventory().addItem(CustomExpBottle.customExpBottle(ExpCount));
//                        player.getInventory().setItemInMainHand(CustomExpBottle.customExpBottle(ExpCount));

                        Bukkit.getLogger().info("exp item dones.");
                    } else if (item.getType() == Material.EXPERIENCE_BOTTLE) {
                        NBTItem nbtItem = new NBTItem(item);
                        Bukkit.getLogger().info("nbt");
                        if (item == CustomExpBottle.customExpBottle(ExpCount) && nbtItem.hasNBTData()) {
                            ExperienceManager.setTotalExperience(player, ExperienceManager.getTotalExperience(player) - ExpCount);

                            // add it to a item lore and then give it to the player
                            item.setAmount(item.getAmount() + 1);
                        } else {
                            Msg.send(player, "Different exp levels and i don't want to fix.");
                        }
                    } else {
                        Msg.send(player, "You do not have enough inventory space.");
                    }
                } else {
                    Msg.send(player, "You do not have enough EXP, You only have " + ExperienceManager.getTotalExperience(player) + " points.");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/savexp <int:amount|all> <level>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
                if (cmd.getName().equalsIgnoreCase("savexp")) {
//                    if (!sender.hasPermission("commanditems.give")) return Collections.EMPTY_LIST;
                    final List<String> completions = new ArrayList<>(List.of(new String[]{"c", "t"}));

                    final List<String> oneArgList = new ArrayList<>(List.of(new String[]{"cats", "turtle"}));

                    // Adds all custom items to the list

                    if (args.length == 1){
                        StringUtil.copyPartialMatches(args[0], oneArgList, completions);
                    }
                    if (args.length == 2){
                        return null;
                        // Returns null to get all online players
                    }
                    Collections.sort(completions);
                    return completions;
                }
                return Collections.EMPTY_LIST;
            }
        };
    }
}