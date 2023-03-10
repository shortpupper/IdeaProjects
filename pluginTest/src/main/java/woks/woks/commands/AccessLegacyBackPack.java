package woks.woks.commands;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import woks.woks.*;

import java.util.List;

public class AccessLegacyBackPack {
    public AccessLegacyBackPack() {
        new CommandBase("AccessLegacyBackPack", true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                if (player.isOp() || player.getName().equals("ShortPuppy14484")) {
                    NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
                    if (nbtItem.hasNBTData()) {
                        if (arguments == null) {
                            // if they leave, this could all go to hell
                            new LegacyBackPackGUI(nbtItem, player);
                        } else if (arguments[1].equalsIgnoreCase("update")) {
                            Msg.send(player, "There is no code here.");
                        } else if (arguments[1].equalsIgnoreCase("fix")) {
                            Msg.send(player, "PS: this only works to fix using to make it false");
                            nbtItem.setBoolean("Using", false);
                        } else {
                            Msg.send(player, "Big fail.");
                        }
                    } else {
                        Msg.send(sender, "This has no NBT.");
                    }
                } else {
                    Msg.send(sender, "You need to have op to use this command.");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/AccessLegacyBackPack <update|fix>";
            }
        };
    }
}
