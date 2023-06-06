package woks.woks.matthew.quest;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static woks.woks.matthew.quest.rewordQuest.DefaultExpAmounts;
import static woks.woks.matthew.quest.rewordQuest.DefaultItemStacks;

public class QuestManager {
    public final Map<String, Quest> activeQuests;
    public final Map<Integer, String> activeQuestsInteger;

    public QuestManager() {
        activeQuests        = new HashMap<>();
        activeQuestsInteger = new HashMap<>();
    }

    public void registerQuest(String questId, Integer questIdInteger, ItemStack[] rewardItems, int rewardExpAmount, String name) {
        Quest quest = new Quest(questId, questIdInteger, rewardItems, rewardExpAmount, name);
        activeQuests.put(questId, quest);
    }

    public void registerQuest(String questId, ItemStack[] rewardItems, int rewardExpAmount, String name) {
        Integer questIdInteger = activeQuests.size() + 1;
        registerQuest(questId, questIdInteger, rewardItems, rewardExpAmount, name);
    }

    public void registerQuest(String questId, ItemStack[] rewardItems, String name) {
        Integer questIdInteger = activeQuests.size() + 1;
        registerQuest(questId, questIdInteger, rewardItems, DefaultExpAmounts, name);
    }

    public void registerQuest(String questId, int rewardExpAmount, String name) {
        Integer questIdInteger = activeQuests.size() + 1;
        registerQuest(questId, questIdInteger, DefaultItemStacks, rewardExpAmount, name);
    }

    public void registerQuest(String questId, String name) {
        Integer questIdInteger = activeQuests.size() + 1;
        registerQuest(questId, questIdInteger, DefaultItemStacks, DefaultExpAmounts, name);
    }
    public void registerQuest(Integer questIdInteger, ItemStack[] rewardItems, int rewardExpAmount, String name) {
        registerQuest(String.valueOf(questIdInteger), questIdInteger, rewardItems, rewardExpAmount, name);
    }

    public void registerQuest(Integer questIdInteger, ItemStack[] rewardItems, String name) {
        registerQuest(String.valueOf(questIdInteger), questIdInteger, rewardItems, DefaultExpAmounts, name);
    }

    public void registerQuest(Integer questIdInteger, int rewardExpAmount, String name) {
        registerQuest(String.valueOf(questIdInteger), questIdInteger, DefaultItemStacks, rewardExpAmount, name);
    }

    public void registerQuest(Integer questIdInteger, String name) {
        registerQuest(String.valueOf(questIdInteger), questIdInteger, DefaultItemStacks, DefaultExpAmounts, name);
    }
    public void registerQuest(String questId, Integer questIdInteger, int rewardExpAmount, String name) {
        registerQuest(questId, questIdInteger, DefaultItemStacks, rewardExpAmount, name);
    }
    public void registerQuest(String questId, Integer questIdInteger, ItemStack[] rewardItems, String name) {
        registerQuest(questId, questIdInteger, rewardItems, DefaultExpAmounts, name);
    }
    public void registerQuest(String questId, Integer questIdInteger, String name) {
        registerQuest(questId, questIdInteger, DefaultItemStacks, DefaultExpAmounts, name);
    }

    public Quest getQuestById(String questId) {
        return activeQuests.get(questId);
    }

    public String getQuestStringIdByIntegerId(Integer id) {
        return activeQuestsInteger.get(id);
    }

    public Quest getQuestByIntegerId(Integer id) {
        return activeQuests.get(activeQuestsInteger.get(id));
    }
}

class Quest {
    private final String questId;
    private final ItemStack[] RewardItems;
    private final int RewardExpAmount;
    private final String name;
    private final Integer questIdInteger;

    public Quest(String questId, Integer questIdInteger, ItemStack[] RewardItems, int RewardExpAmount, String name) {
        this.questId = questId;
        this.questIdInteger = questIdInteger;
        this.RewardItems = RewardItems;
        this.RewardExpAmount = RewardExpAmount;
        this.name = name;
    }

    public String getQuestId() {
        return questId;
    }

    public Integer getQuestIntegerId() {
        return questIdInteger;
    }

    public ItemStack[] getItems() {
        return RewardItems;
    }

    public int getExpAmount() {
        return RewardExpAmount;
    }

    public String getName() { return name;}

    // Add getters/setters or other methods as needed
}