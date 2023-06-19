package woks.woks.matthew.quest;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import woks.woks.Msg;
import woks.woks.NKD;
import woks.woks.matthew.util.ExtraDataContainer;

import static woks.woks.TypeConversionUtils.castIntegerArrayToIntArray;
import static woks.woks.WOKS.questManager;
import static woks.woks.items.CustomExpBottle.customExpBottle;

public class rewordQuest {
    public static int DefaultExpAmounts = 5;
    public static ItemStack[] DefaultItemStacks = new ItemStack[]{};

    public static void RewordQuest(Player player, String questId) {
        Msg.send(player, questId);

        Quest questById = questManager.getQuestById(questId);

//        Msg.send(player, questById.toString());

        player.getInventory().addItem(questById.getItems());
        player.getInventory().addItem(customExpBottle(questById.getExpAmount()));

        Msg.send(player, "You claimed " + questById.getName() + " Quest [" + questById.getQuestId() + "].");

        ExtraDataContainer dataContainer = new ExtraDataContainer(player.getPersistentDataContainer());

        dataContainer.add(NKD.COMPLETED_ARRAY, new int[]{questById.getQuestIntegerId()});
        dataContainer.add(NKD.CAN_DO_ARRAY, castIntegerArrayToIntArray(questById.getCanDoQuests()));
    }

    // These might just be useless
    //
    public static void RewordQuest(Player player) {
        player.getInventory().addItem(DefaultItemStacks);
        player.getInventory().addItem(customExpBottle(DefaultExpAmounts));
        Bukkit.getLogger().info("Quest reward given.");
    }
    public static void RewordQuest(Player player, int expAmount) {
        player.getInventory().addItem(DefaultItemStacks);
        player.getInventory().addItem(customExpBottle(expAmount));
        Bukkit.getLogger().info("Quest reward given.");
    }
    public static void RewordQuest(Player player, int expAmount, ItemStack[] items) {
        player.getInventory().addItem(items);
        player.getInventory().addItem(customExpBottle(expAmount));
        Bukkit.getLogger().info("Quest reward given.");
    }
}
