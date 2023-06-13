package woks.woks.matthew.gui;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.Msg;
import woks.woks.WOKS;
import woks.woks.matthew.quest.Quest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static woks.woks.WOKS.*;
import static woks.woks.items.CustomExpBottle.customExpBottle;
import static woks.woks.matthew.quest.QuestGUI.currentPage3;

public class GUIManager implements Listener {
    private final Map<Integer, questGUIToolClass> guiMap;

    public GUIManager() {
        WOKS plugin = WOKS.getInstance();
        this.guiMap = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void registerGUI(int id, String title, ItemStack[] items) {
        Inventory inventory = Bukkit.createInventory(null, 54, title);
        inventory.setContents(items);
        guiMap.put(id, new questGUIToolClass(inventory, title));
    }

    public questGUIToolClass getGUIByIntegerID(int id) {
        return guiMap.get(id);
    }

    public Inventory getGUIInventoryByIntegerID(int id) {
        return guiMap.get(id).getInventory();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();
//                ItemStack item2= event.getCursor();
        int guiId = 0;
        try {
            assert item != null;
            NBTItem nbtItem = new NBTItem(item);
            guiId = (nbtItem.getInteger("GuiLoc") != null) ? nbtItem.getInteger("GuiLoc") : 0;
        } catch (Exception exception) {
            Bukkit.getLogger().info("[WOKS][GUIManager.java#onInventoryClick][v6.12.2023]int guiId failed, " + exception);
        }
        Bukkit.getLogger().info("[WOKS][GUIManager.java#onInventoryClick][v6.12.2023]uhmm the guiId is: " + guiId);

        if (clickedInventory != null) { //  && guiMap.containsValue(clickedInventory)
            event.setCancelled(true);
            // Handle the click event for the registered GUI
//            try {
                int id = 0;
                try {
                    id = getKeyByValue(guiMap, new questGUIToolClass(clickedInventory, clickedInventory.getViewers().get(0).getOpenInventory().getTitle()));
                } catch (Exception e) {
                    Bukkit.getLogger().info("[WOKS][GUIManager.java#onInventoryClick][v6.12.2023]int id failed, " + e);
                }

                if (id != -1 || guiId != -1) {
                    PersistentDataContainer dataContainer = player.getPersistentDataContainer();
                    // Perform the action based on the GUI ID
                    if (id == 1 || guiId == 1) {
                        // Handle GUI with ID 1
                        // this is the main menu  or "/quest" gui

    //                    Bukkit.getLogger().info("GUI with ID 1 clicked by: " + player.getName());
                        ItemStack itemStack = event.getCurrentItem();
                        assert itemStack != null;

                        // this is to make the player go to the current quest gui
                        if (itemStack.getType() == Material.REDSTONE) {
                            // open the current quest ui
                            Integer currentQuestIntegerId = dataContainer.get(_quest_id_integer, PersistentDataType.INTEGER);

                            Quest quest = questManager.getQuestByIntegerId(currentQuestIntegerId);
                            if (quest == null) {
                                Msg.send(player, "Your current Quest does not exist.");
                                return;
                            }

                            player.openInventory(createCurrentQuestInventory(player));

                        } else if (itemStack.getType() == Material.TNT) {
                            // this is the ClaimQuest gui

                        } else if (itemStack.getType() == Material.BARREL) {
                            // this is the Completed Quests gui

                        } else if (itemStack.getType() == Material.BOOK) {
                            // this is the Stats GUI

                        }



                    } else if (id == 2 || guiId == 2) {
                        // current quest menu/gui
                        // TODO your gettigng off focuse rn so im quitying for today
                        // TODO You were working on how to set up the guis for invonoyts you wanted to maek a gui maneger but couldn't make one
                        // TODO that worked to well for the id wouldn't be good my be just do it some other way for current quest cus of the change
                        // TODO -ing things so quests did work but now not or not as well have yet to test more
                        // TODO 6/9/2023

                        // just get the players quest
    //                    Integer currentQuestIntegerId = dataContainer.get(_quest_id_integer, PersistentDataType.INTEGER);
    //
    //                    Quest quest = questManager.getQuestByIntegerId(currentQuestIntegerId);
                        if (item.getType() == Material.CHEST) {
                            Quest currentQuest = questManager.getQuestById(dataContainer.get(_quest_id, PersistentDataType.STRING));
                            List<ItemStack> items = new ArrayList<>(List.of(currentQuest.getRewardItems()));
                            items.add(customExpBottle(currentQuest.getExpAmount()));

                            RewardChestGUI.openGUIChest(player, "Current Quest Rewards", items, currentPage3);
                            Bukkit.getLogger().info("[WOKS][GUIManager.java#onInventoryClick][v6.12.2023]CHEST");
                        }
//                        player.openInventory(createCurrentQuestInventory(player));
                        Bukkit.getLogger().info("[WOKS][GUIManager.java#onInventoryClick][v6.12.2023]Should work.");
                    } else if (id == 3 || guiId == 3) {
                        Quest currentQuest = questManager.getQuestById(dataContainer.get(_quest_id, PersistentDataType.STRING));
                        List<ItemStack> items = new ArrayList<>(List.of(currentQuest.getRewardItems()));
                        items.add(customExpBottle(currentQuest.getExpAmount()));

                        // something, like, current rewards
                        Bukkit.getLogger().info("[WOKS][GUIManager.java][v6.12.2023]marko");
    //                    ItemStack item = event.getCurrentItem().getType();
                        // for next page

                        if (item.getType() == Material.LIME_STAINED_GLASS_PANE) {
                            currentPage3++;
                            RewardChestGUI.openGUIChest(player, "Current Quest Rewards", items, currentPage3);
                        }
                        // fro prevuis item
                        else if (item.getType() == Material.RED_STAINED_GLASS_PANE) {
                            currentPage3--;
                            RewardChestGUI.openGUIChest(player, "Current Quest Rewards", items, currentPage3);
                        } else if (item.getType() == Material.FEATHER) {
                            player.openInventory(createCurrentQuestInventory(player));
                        }
                    } else {

                    }
                }
//            } catch (Exception exception) {
//                Bukkit.getLogger().info("[WOKS][GUIManager.java#onInventoryClick][v6.12.2023]So the thing failed");
//                Bukkit.getLogger().severe("[WOKS][ERROR]" + exception);
//            }
        }
    }

    private static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}

