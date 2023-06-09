package woks.woks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetLore {
    public SetLore() {
        new CommandBase("setlore", 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                if (player.getName().equals("ShortPuppy14484") || player.isOp()) {
                    ItemMeta mete = itemStack.getItemMeta();
                    assert mete != null;
                    mete.setLore(Collections.singletonList(arguments[0]));
                    itemStack.setItemMeta(mete);
                    player.getInventory().setItemInMainHand(itemStack);
                    Msg.send(player, "Should have worked.");
                } else {
                    Msg.send(player, "You need to be op to use this command and or have something in your main hand.");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/setlore <string:line1>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                if (sender.isOp()) {
                    if (args.length == 1) {
                        List<String> out = new ArrayList<>();

                        out.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");

                        return out;
                    }
                }
                return null;
            }
        };
    }
}
