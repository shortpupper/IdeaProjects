package woks.woks.matthew.gui;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static woks.woks.WOKS.mapNumber;

public class RewardChestGUI {
    public static void openGUIChest(Player player, String title, List<ItemStack> items, Integer currentPage, Integer prevGUI) {
        Inventory gui = Bukkit.createInventory(null, 54, title);

//        List<ItemStack> items = List.of(items2);
        // Add quest items to the GUI
        for (int i = currentPage * 28; i < Math.min((currentPage + 1) * 28, items.size()); i++) {

            ItemStack questItem = items.get(i);
            if (questItem.getType() != Material.AIR) {
                NBTItem nbtItem = new NBTItem(questItem);
                nbtItem.setInteger("GuiLoc", 3);

                questItem = nbtItem.getItem();

            }
            gui.setItem(mapNumber(i % 28), questItem);
        }

        // Add navigation buttons
        if (items.size() > 28) {
            if (currentPage > 0) {
                ItemStack prevButton = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                ItemMeta prevMeta = prevButton.getItemMeta();
                assert prevMeta != null;
                prevMeta.setDisplayName("Prev");
                prevButton.setItemMeta(prevMeta);

                NBTItem nbtItem2 = new NBTItem(prevButton);
                nbtItem2.setInteger("GuiLoc", 3);
                prevButton = nbtItem2.getItem();

                gui.setItem(48, prevButton);
            }
            if (currentPage < (items.size() - 1) / 28) {
                ItemStack nextButton = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
                ItemMeta nextMeta = nextButton.getItemMeta();
                assert nextMeta != null;
                nextMeta.setDisplayName("Next");
                nextButton.setItemMeta(nextMeta);

                NBTItem nbtItemnextButton = new NBTItem(nextButton);
                nbtItemnextButton.setInteger("GuiLoc", 3);
                nextButton = nbtItemnextButton.getItem();

                gui.setItem(50, nextButton);
            }
        }


        ItemStack completedFeather = new ItemStack(Material.FEATHER);
        ItemMeta featherMeta = completedFeather.getItemMeta();

        assert featherMeta != null;

        featherMeta.setDisplayName("Back");

        completedFeather.setItemMeta(featherMeta);

        NBTItem nbtItemcompletedFeather = new NBTItem(completedFeather);
        nbtItemcompletedFeather.setInteger("GuiLoc", 3);
        nbtItemcompletedFeather.setInteger("prevGUI", prevGUI);
        completedFeather = nbtItemcompletedFeather.getItem();

        gui.setItem(49, completedFeather);

        player.openInventory(gui);
    }
}
