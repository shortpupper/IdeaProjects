package woks.woks.matthew.quest;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.Msg;

import static woks.woks.WOKS.*;

public class giveQuest {

    public static void GiveQuest(Player player, String questId) {
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();
        int isDone = dataContainer.get(_quest_claimed, PersistentDataType.INTEGER);

        if (dataContainer.get(_quest_completed, PersistentDataType.INTEGER) == 0 && dataContainer.get(_quest_claimed, PersistentDataType.INTEGER) == 0) {
            dataContainer.set(_quest_id, PersistentDataType.STRING, questId);
            dataContainer.set(_quest_percent_done, PersistentDataType.DOUBLE, 100.0d);
            dataContainer.set(_quest_claimed, PersistentDataType.INTEGER, 0);

            Msg.send(player, "Do /claimreward to claim your quest reward.");
        } else if (isDone == 0) {
            Msg.send(player, "Claim your quest before you can get a new one.");
        } else if (isDone == 1 && dataContainer.get(_quest_percent_done, PersistentDataType.DOUBLE) >= 100.0d) {
            // give a new quest via the quest id thing
            dataContainer.set(_quest_id, PersistentDataType.STRING, questId);
            dataContainer.set(_quest_percent_done, PersistentDataType.DOUBLE, 0.0d);
            dataContainer.set(_quest_claimed, PersistentDataType.INTEGER, 0);


        } else {
            Msg.send(player, "You still have a quest going.");
            Msg.send(player, "Complete it first before you can do another one.");
        }

    }
}
