package woks.woks.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.ArrayList;
import java.util.List;

public class PlayerInv {
    public PlayerInv() {
        new CommandBase("playerInventory", 1, 2, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                if (player.isOp() || player.getName().equals("ShortPuppy14484")) {
                    Msg.send(player, "You need op to use this command!");
                    return true;
                }
                Player otherPlayer = Bukkit.getPlayer(arguments[0]);
                assert otherPlayer != null;
                if ((arguments.length > 1) && (Boolean.parseBoolean(arguments[1]))) {
                    player.openInventory(otherPlayer.getEnderChest());
                } else {
                    player.openInventory(otherPlayer.getInventory());
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/playerInventory <string:name> <boolean:EnderChest>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                if (args.length == 1) {
                    return null;
                } else if (args.length == 2) {
                    List<String> out = new ArrayList<>(){};

                    out.add("true");
                    out.add("false");

                    return out;
                }

                return null;
            }
        };
    }
}
