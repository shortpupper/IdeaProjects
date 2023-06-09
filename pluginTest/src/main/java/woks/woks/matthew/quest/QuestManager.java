package woks.woks.matthew.quest;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static woks.woks.WOKS.questManager;

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



    public void registerQuest(
            String       questId,
            Integer      questIdInteger,
            ItemStack[]  rewardItems,
            int          rewardExpAmount,
            String       name,
            Integer[]    requiredQuests,
            Material     material,
            String       description,
            String       requirements,
            Integer[]    canDoQuests

    ) {
        Quest quest = new Quest(
                questId,          questIdInteger,  rewardItems,
                rewardExpAmount,  name,            requiredQuests,
                material,         description,     requirements,
                canDoQuests
        );

        activeQuests.put(questId, quest);
        activeQuestsInteger.put(questIdInteger, questId);
        IndexQuests.put(questManager.activeQuests.size(), questId);
    }
    public void registerQuest(String questId,       Integer questIdInteger,  ItemStack[] rewardItems,
                              int rewardExpAmount,  String name,             Integer[] requiredQuests,
                              Material material,    String description,      Integer[] getCanDoQuests) {

        registerQuest(
                questId,          questIdInteger,  rewardItems,
                rewardExpAmount,  name,            requiredQuests,
                material,         description,     "None",
                getCanDoQuests
                );
    }

//    public void registerQuest(String questId,       Integer questIdInteger,  ItemStack[] rewardItems,
//                              int rewardExpAmount,  String name,             Material material,
//                              String description) {
//        registerQuest(
//                questId,          questIdInteger,  rewardItems,
//                rewardExpAmount,  name,            DefaultRequiredQuests,
//                material,         description,     "None");
//    }

    public List<Quest> getActiveQuestsAsListQuest() {
        return new ArrayList<>(activeQuests.values());
    }

//    public List<Quest> getActiveQuestsAsListQuestFromIntegerList() {
//        return (List<Quest>) activeQuests.values();
//    }

    public Map<String, Quest> getActiveQuests() {
        return activeQuests;
    }

    public static Integer[] getDefaultRequiredQuests() {
        return DefaultRequiredQuests;
    }

    public Map<Integer, String> getActiveQuestsInteger() {
        return activeQuestsInteger;
    }

    public Map<Integer, String> getIndexQuests() {
        return IndexQuests;
    }

    public static void setDefaultRequiredQuests(Integer[] defaultRequiredQuests) {
        DefaultRequiredQuests = defaultRequiredQuests;
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
//
//class QuestMap {
//    private final String uuid3PassPhrase;
//    private final Integer Index;
//    private final Quest quest;
//
//
//    public QuestMap(String uuid3PassPhrase, Quest quest) {
//        this.quest           = quest;
//        this.Index           = questManager.activeQuests.size();
//        this.uuid3PassPhrase = uuid3PassPhrase;
//    }
//
//    public UUID getUuidFromPassPhrase() {
//        return uuid3Generator(uuid3PassPhrase);
//    }
//
//    public Integer getIndex() {
//        return Index;
//    }
//
//    public Quest getQuest() {
//        return quest;
//    }
//
//}