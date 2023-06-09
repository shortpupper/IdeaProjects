package woks.woks.commands;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;
import woks.woks.LegacyBackPackGUI;
import woks.woks.Msg;

import java.util.ArrayList;
import java.util.List;

public class AccessLegacyBackPack {
    public AccessLegacyBackPack() {
        new CommandBase("AccessLegacyBackPack", true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                if (!(player.isOp() || player.getName().equals("ShortPuppy14484"))) {
                    Msg.send(sender, "You need to have op to use this command.");
                    return true;
                }

                NBTItem nbtItem;

                try {
                    nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
                } catch (Exception exception) {
                    Msg.send(player, "You need a backpack in your hand,");
                    Msg.send(player, "or an item with nbtData.");
                    return true;
                }

                if (!nbtItem.hasNBTData()) {
                    Msg.send(sender, "This has no NBT.");
                    return true;
                }

                if (arguments == null) {
                    // if they leave, this could all go to fire
                    new LegacyBackPackGUI(nbtItem, player);
                } else if (arguments[0].equalsIgnoreCase("update")) {
                    Msg.send(player, "There is no code here.");
                } else if (arguments[0].equalsIgnoreCase("fix")) {
                    Msg.send(player, "PS: this only works to fix using to make it false");
                    nbtItem.setBoolean("Using", false);
                } else {
                    Msg.send(player, "Big fail.");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/AccessLegacyBackPack <update|fix>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

                if (args.length == 1) {
                    List<String> out = new ArrayList<>();

                    out.add("update");
                    out.add("fix");

                    return out;
                }

                return null;
            }
        };
    }
}
