package woks.woks.matthew.quest;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.CommandBase;
import woks.woks.Msg;

import static woks.woks.WOKS.*;
import static woks.woks.matthew.quest.rewordQuest.RewordQuest;

public class claimReward {
    public claimReward()  {
        new CommandBase("claimreward",  true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                PersistentDataContainer dataContainer = player.getPersistentDataContainer();

                // check if they have 100% it
                if (dataContainer.get(_quest_percent_done, PersistentDataType.DOUBLE) >= 100.0d && dataContainer.get(_quest_claimed, PersistentDataType.INTEGER) == 0) {
                    Msg.send(sender, "Good job.");

                    dataContainer.set(_quest_claimed, PersistentDataType.INTEGER, 1);
                    dataContainer.set(_quest_completed, PersistentDataType.INTEGER, dataContainer.get(_quest_completed, PersistentDataType.INTEGER) + 1);

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

            //it just returns NULL if absolutely nothing has happened
        }.enableDelay(2);


    }

}
