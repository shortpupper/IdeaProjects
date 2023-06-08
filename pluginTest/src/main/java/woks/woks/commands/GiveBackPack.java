package woks.woks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.items.BackPack;

import java.util.ArrayList;
import java.util.List;

public class GiveBackPack implements TabCompleter {
    public GiveBackPack() {
        new CommandBase("GiveBackPack", 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                if ("ShortPuppy14484".equals(sender.getName())) {
                    player.getInventory().addItem(BackPack.BackPack(Integer.parseInt(arguments[0]), false));
                } else {
                    Msg.send(sender, "You are not Allowed to use this command you need to get permission.");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/givebackpack <int:count>";
            }

        };
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> out = new ArrayList<>();
            out.add("9");
            out.add("18");
            out.add("27");
            out.add("36");
            out.add("45");
            out.add("54");

            return out;
        }
        return null;
    }
}
