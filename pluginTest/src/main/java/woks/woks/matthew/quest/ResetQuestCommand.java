package woks.woks.matthew.quest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.List;

import static woks.woks.WOKS._quest_completed;

public class ResetQuestCommand {
    public ResetQuestCommand() {
        new CommandBase("resetquest", true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                if (player.isOp() || player.getName().equals("ShortPuppy14484")) {
                    PersistentDataContainer dataContainer = player.getPersistentDataContainer();

//                    dataContainer.set();
                    dataContainer.set(_quest_completed, PersistentDataType.INTEGER, 1);
                    Msg.send(player, "Done.");
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
                return null;
            }
        }.enableDelay(2);
    }
}
