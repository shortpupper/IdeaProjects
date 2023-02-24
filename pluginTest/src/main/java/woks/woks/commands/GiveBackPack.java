package woks.woks.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.items.BackPack;

public class GiveBackPack {
    public GiveBackPack() {
        new CommandBase("GiveBackPack", 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                if ("ShortPuppy14484".equals(sender.getName())) {
                    player.getInventory().setItemInMainHand(BackPack.BackPack(Integer.parseInt(arguments[0])));
                } else {
                    Msg.send(sender, "You are not Allowed to use this command you need to get permission.");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/GiveBackPack <int:count>";
            }
        };
    }
}
