package woks.woks.matthew.quest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.List;

import static woks.woks.WOKS.*;
import static woks.woks.matthew.quest.giveQuest.GiveQuest;

public class CommandNextQuest {
    public CommandNextQuest() {
        new CommandBase("nextquest", true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                PersistentDataContainer dataContainer = player.getPersistentDataContainer();

                // have they finished there current quest
                if (dataContainer.get(_quest_percent_done, PersistentDataType.DOUBLE) < 100.0d) {
                    Msg.send(player, "You have to finish your current quest, before you can continue.");
                    return true;
                }

                // check if they have claimed the reward for the current quest
                if (dataContainer.get(_quest_claimed, PersistentDataType.INTEGER) == 0) {
                    Msg.send(player, "You need to claim your rewards from your current quest.");
                    return true;
                }

                // give them the next quest

                GiveQuest(player);

                return true;
            }

            @Override
            public String getUsage() {
                return "/nextquest";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        }.enableDelay(2);
    }
}
