package woks.woks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;

import java.util.ArrayList;
import java.util.List;

public class Feed implements TabCompleter {
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


        }.enableDelay(2);
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
}
