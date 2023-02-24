package woks.woks.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.UserCheckPaying;

public class PayingUSers {
    public PayingUSers() {
        new CommandBase("USetUsers", true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                Msg.send(player, UserCheckPaying.CheckPaying().toString());
                return true;
            }

            @Override
            public String getUsage() {
                return "/USetUsers";
            }
        };
    }
}
