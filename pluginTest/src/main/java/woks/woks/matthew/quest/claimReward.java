package woks.woks.matthew.quest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.TypeConversionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.persistence.PersistentDataType.INTEGER_ARRAY;
import static woks.woks.WOKS.*;
import static woks.woks.matthew.quest.QuestGUI.getActiveQuestFromListInteger;
import static woks.woks.matthew.quest.rewordQuest.RewordQuest;

public class claimReward {
    public claimReward()  {
        new CommandBase("claimreward", 0, 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
//                Bukkit.getLogger().info("[woks] YELLOW 1");
                PersistentDataContainer dataContainer = player.getPersistentDataContainer();
//                Bukkit.getLogger().info("[woks] Pint 2");

                if (arguments.length >= 1) {
                    if (arguments[0].equalsIgnoreCase("gui")) {
                        QuestGUI questGUI = new QuestGUI(player);

                        /*
                         TODO You have to make a new thing where the player has a new persistant stoarge thigny
                          so and that it is where this error is so that it fixes its self cus rn its bad
                          ADD in the questManger that quest class must have a new field that adds or
                          gives the player new quests with teh integerId are an integer_array, good luck and good
                          Night cus im sleepy rn, just go fix it.
                        */

                        int[] numbers = dataContainer.get(_quest_can_array, INTEGER_ARRAY);

                        assert numbers != null;
                        questGUI.openGUI(getActiveQuestFromListInteger(TypeConversionUtils.castIntArrayToList(numbers)), 0);


                        return true;
                    }
                }
//                Bukkit.getLogger().info("[woks] ok 3");


                // check if they hav11e 100% it
                if (dataContainer.get(_quest_percent_done, PersistentDataType.DOUBLE) >= 100.0d && dataContainer.get(_quest_claimed, PersistentDataType.INTEGER) == 0) {
                    Msg.send(sender, "Good job.");
//                    Bukkit.getLogger().info("[woks] Corn 3");


                    dataContainer.set(_quest_claimed, PersistentDataType.INTEGER, 1);
                    dataContainer.set(_quest_completed, PersistentDataType.INTEGER, dataContainer.get(_quest_completed, PersistentDataType.INTEGER) + 1);
//                    dataContainer.set(_quest_completed, PersistentDataType.INTEGER, dataContainer.get(_quest_completed, PersistentDataType.INTEGER));

                    RewordQuest(player, dataContainer.get(_quest_id, PersistentDataType.STRING));
                } else if (dataContainer.get(_quest_claimed, PersistentDataType.INTEGER) == 1) {
                    Msg.send(player, "You have already claim this quest.");
                } else {
                    Msg.send(player, "Your not done yet you've only done " + dataContainer.get(_quest_percent_done, PersistentDataType.DOUBLE) + "%");
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/claimreward";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                if (args.length == 1) {
                    List<String> out = new ArrayList<>();

                    out.add("gui");

                    return out;
                }
                return null;
            }
            //it just returns NULL if absolutely nothing has happened
        }.enableDelay(1);
    }

}
