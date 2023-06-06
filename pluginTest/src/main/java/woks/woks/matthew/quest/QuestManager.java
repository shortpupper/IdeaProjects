package woks.woks.matthew.quest;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class QuestManager {
    public final Map<String, Quest> activeQuests;

    public QuestManager() {
        activeQuests = new HashMap<>();
    }

    public void registerQuest(String questId, ItemStack[] items, int expAmount) {
        Quest quest = new Quest(questId, items, expAmount);
        activeQuests.put(questId, quest);
    }

    public Quest getQuestById(String questId) {
        return activeQuests.get(questId);
    }

//    public void completeQuest(Player player) {
//        Quest quest = activeQuests.get(player);
//        if (quest != null) {
//            // Handle quest completion logic here
//            // You can give rewards, update player stats, etc.
//
//            // Remove the completed quest from the activeQuests map
//            activeQuests.remove(player);
//        }
//    }
}

class Quest {
    private final String questId;
    private final ItemStack[] items;
    private final int expAmount;

    public Quest(String questId, ItemStack[] items, int expAmount) {
        this.questId = questId;
        this.items = items;
        this.expAmount = expAmount;
    }

    public String getQuestId() {
        return questId;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public int getExpAmount() {
        return expAmount;
    }


    // Add getters/setters or other methods as needed
}