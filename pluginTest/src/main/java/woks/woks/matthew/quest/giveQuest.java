package woks.woks.matthew.quest;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.Msg;

import static woks.woks.WOKS._quest_claimed;

public class giveQuest {

    public void GiveQuest(Player player, int questId) {
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();
        int isDone = dataContainer.get(_quest_claimed, PersistentDataType.INTEGER);
        if (isDone == 1) {
//            dataContainer.
        } else {
            Msg.send(player, "You still have a quest going.");
            Msg.send(player, "Complete it first before you can do another one.");
        }

    }
}
