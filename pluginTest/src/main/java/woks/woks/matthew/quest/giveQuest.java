package woks.woks.matthew.quest;

import org.bukkit.entity.Player;
import woks.woks.Msg;
import woks.woks.NKD;
import woks.woks.matthew.util.ExtraDataContainer;

import static woks.woks.WOKS.questManager;

public class giveQuest {

    public static void GiveQuest(Player player, String questId) {
        ExtraDataContainer dataContainer = new ExtraDataContainer(player.getPersistentDataContainer());
        boolean            isDone        = dataContainer.get(NKD.HAS_BEEN_CLAIMED);

        if (isDone && ((int) dataContainer.get(NKD.HOW_MANY_COMPLETED)) == 0) {
            dataContainer.set(NKD.STRING_ID, questId);
            dataContainer.set(NKD.INTEGER_ID, questManager.getQuestById(questId).getQuestIntegerId());
            dataContainer.set(NKD.PERCENT_OF_DONE, 100.0d);
            dataContainer.set(NKD.HAS_BEEN_CLAIMED, false);
        } else if (!isDone) {
            Msg.send(player, "Claim your quest before you can get a new one.");
        } else if (isDone && ((double) dataContainer.get(NKD.PERCENT_OF_DONE)) >= 100.0d) {
            // give a new quest via the quest id thing
            dataContainer.set(NKD.STRING_ID, questId);
            dataContainer.set(NKD.INTEGER_ID, questManager.getQuestById(questId).getQuestIntegerId());
            dataContainer.set(NKD.PERCENT_OF_DONE, 0.0d);
            dataContainer.set(NKD.HAS_BEEN_CLAIMED, false);
            Msg.send(player, "New quest added!");
        } else {
            Msg.send(player, "You still have a quest going.");
            Msg.send(player, "Complete it first before you can do another one.");
        }
    }
    public static void GiveQuest(Player player, Integer integerId) {
        GiveQuest(player, questManager.getQuestStringIdByIntegerId(integerId));
    }

    public static void GiveQuest(Player player) {
//        PersistentDataContainer dataContainer = player.getPersistentDataContainer();
//        String quest_id_get = dataContainer.get(_quest_id, PersistentDataType.STRING);
//        Quest quest = questManager.getQuestById(quest_id_get);
//        Integer questIntegerId =  quest.getQuestIntegerId();

//        questManager.activeQuests.

        GiveQuest(player, 2);
    }
}
