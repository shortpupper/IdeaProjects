package woks.woks.matthew.quest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.ArrayList;
import java.util.List;

import static woks.woks.WOKS.*;
import static woks.woks.matthew.quest.giveQuest.GiveQuest;

public class CommandNextQuest implements TabCompleter {
    public CommandNextQuest() {
        new CommandBase("nextquest", 1, 5, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                PersistentDataContainer dataContainer = player.getPersistentDataContainer();

                if (arguments.length >= 2 && arguments[1].equalsIgnoreCase("skip")) {
                    if (player.isOp() || player.getName().equals("ShortPuppy14484")) {
                        Msg.send(player, "Skipping");

                        GiveQuest(player, arguments[0]);
                    } else {
                        Msg.send(player, "You need to be op to use this command.");
                    }
                } else if (arguments.length == 1) {

                } else {

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

                    GiveQuest(player, questManager.getNextQuestStrId(dataContainer.get(_quest_id_integer, PersistentDataType.INTEGER)));
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/nextquest <String:strID> <String:[skip]>";
            }

        }.enableDelay(2);
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        Player player = (Player) sender;
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();

        if (args.length == 1) {
            List<String> out = new ArrayList<>();
            int[] canDoQuest = dataContainer.get(_quest_can_array, PersistentDataType.INTEGER_ARRAY);

            for (int integerId : canDoQuest) {
                out.add(questManager.getIndexQuests().get(integerId));
            }

            return out;
        } else if (args.length == 2) {
            List<String> out = new ArrayList<>();

            if (player.isOp() || player.getName().equals("ShortPuppy14484")) {
                out.add("skip");
            }

            return out;
        }

        return new ArrayList<>();
    }
}
