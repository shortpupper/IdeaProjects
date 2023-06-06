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

    public void registerQuest(String questId, Integer questIdInteger, ItemStack[] RewardItems, int RewardExpAmount, String name) {
        Quest quest = new Quest(questId, questIdInteger, RewardItems, RewardExpAmount, name);

        activeQuests.put(questId, quest);
        activeQuestsInteger.put(questIdInteger, questId);
    }

    public void registerQuest(String questId, ItemStack[] RewardItems, int RewardExpAmount, String name) {
        Integer questIdInteger;
        questIdInteger = activeQuests.values().size() + 1;

        registerQuest(questId, questIdInteger, RewardItems, RewardExpAmount, name);
    }
    public void registerQuest(String questId, ItemStack[] RewardItems, String name) {
        Integer questIdInteger;
        int RewardExpAmount;

        RewardExpAmount = DefaultExpAmounts;
        questIdInteger = activeQuests.values().size() + 1;

        Quest quest = new Quest(questId, questIdInteger, RewardItems, RewardExpAmount, name);

        activeQuests.put(questId, quest);
        activeQuestsInteger.put(questIdInteger, questId);
    }
    public void registerQuest(String questId, int RewardExpAmount, String name) {
        Integer questIdInteger;
        ItemStack[] RewardItems;

        RewardItems = DefaultItemStacks;
        questIdInteger = activeQuests.values().size() + 1;

        Quest quest = new Quest(questId, questIdInteger, RewardItems, RewardExpAmount, name);

        activeQuests.put(questId, quest);
        activeQuestsInteger.put(questIdInteger, questId);
    }
    public void registerQuest(String questId, String name) {
        Integer questIdInteger;
        ItemStack[] RewardItems;
        int RewardExpAmount;

        RewardItems = DefaultItemStacks;
        RewardExpAmount = DefaultExpAmounts;
        questIdInteger = activeQuests.values().size() + 1;

        Quest quest = new Quest(questId, questIdInteger, RewardItems, RewardExpAmount, name);

        activeQuests.put(questId, quest);
        activeQuestsInteger.put(questIdInteger, questId);
    }

    public void registerQuest(Integer questIdInteger, ItemStack[] RewardItems, int RewardExpAmount, String name) {
        String questId;
        questId = String.valueOf(questIdInteger);

        Quest quest = new Quest(questId, questIdInteger, RewardItems, RewardExpAmount, name);

        activeQuests.put(questId, quest);
        activeQuestsInteger.put(questIdInteger, questId);
    }
    public void registerQuest(Integer questIdInteger, ItemStack[] RewardItems, String name) {
        String questId;
        int RewardExpAmount;

        RewardExpAmount = DefaultExpAmounts;
        questId = String.valueOf(questIdInteger);

        Quest quest = new Quest(questId, questIdInteger, RewardItems, RewardExpAmount, name);

        activeQuests.put(questId, quest);
        activeQuestsInteger.put(questIdInteger, questId);
    }
    public void registerQuest(Integer questIdInteger, int RewardExpAmount, String name) {
        String questId;
        ItemStack[] RewardItems;

        RewardItems = DefaultItemStacks;
        questId = String.valueOf(questIdInteger);

        Quest quest = new Quest(questId, questIdInteger, RewardItems, RewardExpAmount, name);

        activeQuests.put(questId, quest);
        activeQuestsInteger.put(questIdInteger, questId);
    }
    public void registerQuest(Integer questIdInteger, String name) {
        String questId;
        ItemStack[] RewardItems;
        int RewardExpAmount;

        RewardItems = DefaultItemStacks;
        RewardExpAmount = DefaultExpAmounts;
        questId = String.valueOf(questIdInteger);

        Quest quest = new Quest(questId, questIdInteger, RewardItems, RewardExpAmount, name);

        activeQuests.put(questId, quest);
        activeQuestsInteger.put(questIdInteger, questId);
    }

    public void registerQuest(String questId, Integer questIdInteger, ItemStack[] RewardItems, String name) {
        int RewardExpAmount;

        RewardExpAmount = DefaultExpAmounts;

        Quest quest = new Quest(questId, questIdInteger, RewardItems, RewardExpAmount, name);

        activeQuests.put(questId, quest);
        activeQuestsInteger.put(questIdInteger, questId);
    }
    public void registerQuest(String questId, Integer questIdInteger, int RewardExpAmount, String name) {
        ItemStack[] RewardItems;
        RewardItems = DefaultItemStacks;

        Quest quest = new Quest(questId, questIdInteger, RewardItems, RewardExpAmount, name);

        activeQuests.put(questId, quest);
        activeQuestsInteger.put(questIdInteger, questId);
    }

    public void registerQuest(String questId, Integer questIdInteger, String name) {
        ItemStack[] RewardItems;
        int RewardExpAmount;
        RewardItems = DefaultItemStacks;
        RewardExpAmount = DefaultExpAmounts;


        Quest quest = new Quest(questId, questIdInteger, RewardItems, RewardExpAmount, name);

        activeQuests.put(questId, quest);
        activeQuestsInteger.put(questIdInteger, questId);
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