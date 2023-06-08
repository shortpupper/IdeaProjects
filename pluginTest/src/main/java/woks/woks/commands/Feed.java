package woks.woks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;

import java.util.ArrayList;
import java.util.List;

public class Feed {
    public Feed() {
        new CommandBase("feed", true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                player.setFoodLevel(20);
                return true;
            }

            @Override
            public String getUsage() {
                return "/feed";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                if (args.length == 1) {
                    List<String> out = new ArrayList<>();

                    out.add("");

                    return out;
                }
                return null;
            }
        }.enableDelay(2);
    }
}
