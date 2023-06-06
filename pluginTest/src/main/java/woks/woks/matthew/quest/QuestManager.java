package woks.woks.matthew.quest;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static woks.woks.WOKS.questManager;
import static woks.woks.WOKS.uuid3Generator;

public class QuestManager {
    public final Map<String, Quest> activeQuests;
    //               strId   quest
    public final Map<Integer, String> activeQuestsInteger;
    //               intId    strId
    public final Map<Integer, String> IndexQuests;
    //               Index    strId

//    public final Map<Integer, QuestMap> activeQuestsInteger;
    //               index    quest

//    public final Map<Integer, Map<Integer, String>> ZactiveQuestsInteger;
    //               autoInc      intID    strId

    public static Integer[] DefaultRequiredQuests = new Integer[]{};

    public QuestManager() {
        activeQuests         = new HashMap<>();
        activeQuestsInteger  = new HashMap<>();
        IndexQuests          = new HashMap<>();
    }

    public void registerQuest(String questId, Integer questIdInteger, ItemStack[] rewardItems, int rewardExpAmount, String name, Integer[] requiredQuests) {
        Quest quest = new Quest(questId, questIdInteger, rewardItems, rewardExpAmount, name, requiredQuests);

        activeQuests.put(questId, quest);
        activeQuestsInteger.put(questIdInteger, questId);
        IndexQuests.put(questManager.activeQuests.size(), questId);
    }

    public void registerQuest(String questId, Integer questIdInteger, ItemStack[] rewardItems, int rewardExpAmount, String name) {
        registerQuest(questId, questIdInteger, rewardItems, rewardExpAmount, name, DefaultRequiredQuests);
    }

    public Quest getQuestById(String questId) {
        return activeQuests.get(questId);
    }

    public String getNextQuestStrId(Integer Index) {
        return IndexQuests.get(Index + 1);
    }

    public Quest getNextQuest(Integer Index) {
        return activeQuests.get(getNextQuestStrId(Index));
    }
    public String getQuestStringIdByIntegerId(Integer id) {
        return activeQuestsInteger.get(id);
    }

    public Quest getQuestByIntegerId(Integer id) {
        return activeQuests.get(getQuestStringIdByIntegerId(id));
    }
}

class Quest {
    private final String questId;
    private final ItemStack[] RewardItems;
    private final int RewardExpAmount;
    private final String name;
    private final Integer questIdInteger;
    private final Integer[] requiredQuests;

    public Quest(String questId, Integer questIdInteger, ItemStack[] RewardItems, int RewardExpAmount, String name, Integer[] requiredQuests) {
        this.questId         = questId;
        this.questIdInteger  = questIdInteger;
        this.RewardItems     = RewardItems;
        this.RewardExpAmount = RewardExpAmount;
        this.name            = name;
        this.requiredQuests  = requiredQuests;
    }


    public Integer[] getRequiredQuests() {
        return requiredQuests;
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

class QuestMap {
    private final String uuid3PassPhrase;
    private final Integer Index;
    private final Quest quest;


    public QuestMap(String uuid3PassPhrase, Quest quest) {
        this.quest           = quest;
        this.Index           = questManager.activeQuests.size();
        this.uuid3PassPhrase = uuid3PassPhrase;
    }

    public UUID getUuidFromPassPhrase() {
        return uuid3Generator(uuid3PassPhrase);
    }

    public Integer getIndex() {
        return Index;
    }

    public Quest getQuest() {
        return quest;
    }

}