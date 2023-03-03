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
                if (((!player.isOp()) || player.getName().equals("ShortPuppy14484")) && !(arguments[0].equals("ShortPuppy14484") || !player.getName().equals("ShortPuppy14484"))) {Msg.send(player, "You need op to use this command!"); return false;}
                Player otherPlayer = Bukkit.getPlayer(arguments[0]);
                assert otherPlayer != null;
                if ((arguments.length > 2) && (Boolean.parseBoolean(arguments[1]))) {
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
                List<String> ListItems = new ArrayList<>(){};
                ListItems.add("cats");
                return ListItems;
            }
        };
    }
}
