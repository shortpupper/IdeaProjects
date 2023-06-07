package woks.woks.matthew.quest;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.Msg;

import java.util.Objects;

import static woks.woks.WOKS.*;
import static woks.woks.items.CustomExpBottle.customExpBottle;

public class rewordQuest {
    public static int DefaultExpAmounts = 5;
    public static ItemStack[] DefaultItemStacks = new ItemStack[]{};

    public static void RewordQuest(Player player, String questId) {
        Msg.send(player, questId);
        Quest questById = questManager.getQuestById(questId);
        Msg.send(player, "Good job.51");
        Msg.send(player, questById.toString());

        player.getInventory().addItem(questById.getItems());
        Msg.send(player, "Good job.212");
        player.getInventory().addItem(customExpBottle(questById.getExpAmount()));
        Msg.send(player, "Good job.4151");

        Msg.send(player, "You claimed " + questById.getName() + " Quest [" + questById.getQuestId() + "].");

        PersistentDataContainer dataContainer = player.getPersistentDataContainer();
//        Bukkit.getLogger().info("[woks] hello?");
        dataContainer.set(_quest_completed_array, PersistentDataType.INTEGER_ARRAY, addArrays(Objects.requireNonNull(dataContainer.get(_quest_completed_array, PersistentDataType.INTEGER_ARRAY)), new int[]{questById.getQuestIntegerId()}));
//        Bukkit.getLogger().info("[woks] bye?");

        Bukkit.getLogger().info("Quest reward given.");
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
