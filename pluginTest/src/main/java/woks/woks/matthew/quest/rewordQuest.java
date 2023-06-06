package woks.woks.matthew.quest;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import woks.woks.Msg;

import static woks.woks.WOKS.questManager;
import static woks.woks.items.CustomExpBottle.customExpBottle;

public class rewordQuest {
    public static int expAmounts = 5;
    public static ItemStack[] itemStacks = new ItemStack[]{};

    public static void RewordQuest(Player player, String questId) {
        Quest questById = questManager.getQuestById(questId);

        player.getInventory().addItem(questById.getItems());
        player.getInventory().addItem(customExpBottle(questById.getExpAmount()));

        Msg.send(player, "You claimed " + questById.getName() + " Quest [" + questById.getQuestId()+ "].");

        Bukkit.getLogger().info("Quest reward given.");
    }

    public static void RewordQuest(Player player) {
        player.getInventory().addItem(itemStacks);
        player.getInventory().addItem(customExpBottle(expAmounts));
        Bukkit.getLogger().info("Quest reward given.");
    }
    public static void RewordQuest(Player player, int expAmount) {
        player.getInventory().addItem(itemStacks);
        player.getInventory().addItem(customExpBottle(expAmount));
        Bukkit.getLogger().info("Quest reward given.");
    }
    public static void RewordQuest(Player player, int expAmount, ItemStack[] items) {
        player.getInventory().addItem(items);
        player.getInventory().addItem(customExpBottle(expAmount));
        Bukkit.getLogger().info("Quest reward given.");
    }
}
