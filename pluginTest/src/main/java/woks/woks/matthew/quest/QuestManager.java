package woks.woks.matthew.quest;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class QuestManager {
    public final Map<String, Quest> activeQuests;

    public QuestManager() {
        activeQuests = new HashMap<>();
    }

    public void registerQuest(String questId, ItemStack[] items, int expAmount, String name) {
        Quest quest = new Quest(questId, items, expAmount, name);
        activeQuests.put(questId, quest);
    }

    public Quest getQuestById(String questId) {
        return activeQuests.get(questId);
    }

}

class Quest {
    private final String questId;
    private final ItemStack[] items;
    private final int expAmount;
    private final String name;

    public Quest(String questId, ItemStack[] items, int expAmount, String name) {
        this.questId = questId;
        this.items = items;
        this.expAmount = expAmount;
        this.name = name;
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

    public String getName() { return name;}

    // Add getters/setters or other methods as needed
}