package woks.woks.matthew.quest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.ArrayList;
import java.util.List;

public class ResetQuestCommand {
    public ResetQuestCommand() {
        new CommandBase("resetquest", 0, 5, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                if (player.isOp() || player.getName().equals("ShortPuppy14484")) {
                    PersistentDataContainer dataContainer = player.getPersistentDataContainer();
                    if (arguments.length == 0) {

                    }
                } else {
                    Msg.send(player, "You need OP to use this command.");
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/resetquest";
            }


            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                if (args.length == 1) {
                    List<String> out = new ArrayList<>();



                    return out;
                } else if (args.length == 2) {
                    List<String> out = new ArrayList<>();


                    return out;
                } else if (args.length == 3) {
                    List<String> out = new ArrayList<>();


                    return out;
                }

                return null;
            }
        };
    }

}
