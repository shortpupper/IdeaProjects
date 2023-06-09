package woks.woks.matthew.quest;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Quest {
    private final String questId;
    private final ItemStack[] RewardItems;
    private final int RewardExpAmount;
    private final String name;
    private final Integer questIdInteger;
    private final Integer[] requiredQuests;

    private final Material material;
    private final String description;
    private final String requirements;
    private final Integer[] canDoQuests;

    public Quest(String questId, Integer questIdInteger, ItemStack[] RewardItems,
                 int RewardExpAmount, String name, Integer[] requiredQuests,
                 Material material, String description, String requirements, Integer[] canDoQuests) {
        this.questId = questId;
        this.questIdInteger = questIdInteger;
        this.RewardItems = RewardItems;
        this.RewardExpAmount = RewardExpAmount;
        this.name = name;
        this.requiredQuests = requiredQuests;
        this.material = material;
        this.description = description;
        this.requirements = requirements;
        this.canDoQuests = canDoQuests;
    }

    public Integer[] getCanDoQuests() {
        return canDoQuests;
    }

    public ItemStack[] getRewardItems() {
        return RewardItems;
    }


    public int getRewardExpAmount() {
        return RewardExpAmount;
    }

    public Integer getQuestIdInteger() {
        return questIdInteger;
    }

    public String getRequirements() {
        return requirements;
    }

    public String getDescription() {
        return description;
    }

    public Material getMaterial() {
        return material;
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

    public String getName() {
        return name;
    }

    // Add getters/setters or other methods as needed
}
