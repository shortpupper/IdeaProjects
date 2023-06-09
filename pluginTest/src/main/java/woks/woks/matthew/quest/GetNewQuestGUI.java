package woks.woks.matthew.quest;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GetNewQuestGUI {
    public GetNewQuestGUI(Player player) {

        new QuestGUI(player);
        Bukkit.getLogger().info("[WOKS] Should be open.");
//        Inventory inv = Bukkit.createInventory(null, 54, "");
//
//        // Put the items into the inventory
//        ItemStack[] Items = new ItemStack[]{};
//
//        inv.setStorageContents(Items);
//
//        // access inventory
//        player.openInventory(inv);
    }
}
