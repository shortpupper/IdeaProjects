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
import woks.woks.TypeConversionUtils;
import woks.woks.WOKS;
import woks.woks.matthew.quest.Quest;
import woks.woks.matthew.quest.QuestGUI;

import java.util.*;

import static org.bukkit.persistence.PersistentDataType.INTEGER_ARRAY;
import static woks.woks.WOKS.*;
import static woks.woks.items.CustomExpBottle.customExpBottle;
import static woks.woks.matthew.quest.QuestGUI.*;
import static woks.woks.matthew.quest.rewordQuest.RewordQuest;

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
            if (config.getBoolean("log__GUIManager_java_onInventoryClick__int_guiId_failed")) {
                Bukkit.getLogger().info("[WOKS][GUIManager.java#onInventoryClick][v6.12.2023]int guiId failed, " + exception);
            }
        }
        if (config.getBoolean("log__GUIManager_java_onInventoryClick__uhmm_the_guiId_is") && guiId != 0) {
            Bukkit.getLogger().info("[WOKS][GUIManager.java#onInventoryClick][v6.12.2023]uhmm the guiId is: " + guiId);
        }
        if (clickedInventory != null) { //  && guiMap.containsValue(clickedInventory)
            event.setCancelled(true);
            // Handle the click event for the registered GUI
//            try {
//                int id = 0;
//                try {
////                    id = getKeyByValue(guiMap, new questGUIToolClass(clickedInventory, event.getView().getTitle()));
//                } catch (Exception e) {
//                    if (config.getBoolean("log__GUIManager_java_onInventoryClick__int_id_failed")) {
//                        Bukkit.getLogger().info("[WOKS][GUIManager.java#onInventoryClick][v6.12.2023]int id failed, " + e);
//                    }
//                }

            PersistentDataContainer dataContainer = player.getPersistentDataContainer();
            Integer currentPage4 = (Integer) storageManager.getValueWithNamespacedKey(dataContainer, _quest_gui_currentPage4_index);
            Integer currentPage3 = (Integer) storageManager.getValueWithNamespacedKey(dataContainer, _quest_gui_currentPage3_index);
            Integer currentPage2 = (Integer) storageManager.getValueWithNamespacedKey(dataContainer, _quest_gui_currentPage2_index);
            Integer currentPage = (Integer) storageManager.getValueWithNamespacedKey(dataContainer, _quest_gui_currentPage1_index);
            // Perform the action based on the GUI ID
            if (guiId == 1) {
                // Handle GUI with ID 1
                // this is the main menu  or "/quest" gui

//                    Bukkit.getLogger().info("GUI with ID 1 clicked by: " + player.getName());
                ItemStack itemStack = event.getCurrentItem();
                assert itemStack != null;

                // this is to make the player go to the current quest gui
                if (itemStack.getType() == Material.REDSTONE) {
                    // open the current quest ui , gui : 2
                    Integer currentQuestIntegerId = dataContainer.get(_quest_id_integer, PersistentDataType.INTEGER);

                    Quest quest = questManager.getQuestByIntegerId(currentQuestIntegerId);
                    if (quest == null) {
                        Msg.send(player, "Your current Quest does not exist.");
                        return;
                    }

                    player.openInventory(createCurrentQuestInventory(player, 1));

                } else if (itemStack.getType() == Material.TNT) {
                    // this is the ClaimQuest gui : 4
                    QuestGUI questGUI = new QuestGUI(player);

                    int[] numbers = dataContainer.get(_quest_can_array, INTEGER_ARRAY);

                    assert numbers != null;
                    currentPage2 = 0;
                    questGUI.openGUI(getActiveQuestFromListInteger(TypeConversionUtils.castIntArrayToList(numbers)), currentPage2, "Quests", 4, 1, "Completed Quests");
                } else if (itemStack.getType() == Material.BARREL) {
                    QuestGUI questGUI = new QuestGUI(player);
                    // this is the Completed Quests gui : 5
                    int[] numbers = dataContainer.get(_quest_completed_array, PersistentDataType.INTEGER_ARRAY);

                    assert numbers != null;
                    currentPage3 = 0;
                    questGUI.openGUI(getActiveQuestFromListInteger(TypeConversionUtils.castIntArrayToList(numbers)), currentPage3, "Completed Quests", 5, 1, "Quests");
                } else if (itemStack.getType() == Material.BOOK) {
                    // this is the Stats GUI

                }



            }
            else if (guiId == 2) {
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

                    RewardChestGUI.openGUIChest(player, "Current Quest Rewards", items, currentPage3, 2);
                    if (config.getBoolean("log__GUIManager_java_onInventoryClick__CHEST")) {
                        Bukkit.getLogger().info("[WOKS][GUIManager.java#onInventoryClick][v6.12.2023]CHEST");
                    }
                } else if (item.getType() == Material.FEATHER) {
                    // get nbt
                    NBTItem nbtItem = new NBTItem(item);
                    Integer prevGUI = nbtItem.getInteger("prevGUI");

                    // ignore this >> // if the gui is 1 or current quest cus its custom
                    player.openInventory(guiManager.getGUIInventoryByIntegerID(prevGUI));
                } else if (item.getType() == Material.PAPER) {

                } else if (item.getType() == Material.BOOK) {

                } else if (item.getType() == Material.LIME_STAINED_GLASS_PANE) {
                    // get nbt
                    NBTItem nbtItem = new NBTItem(item);
                    if (nbtItem.getBoolean("canClaim")) {
                        // now give the rewards and remove it from the can do array
                        storageManager.removeIdInIntegerArray(dataContainer, _quest_can_array, nbtItem.getInteger("questIntId"));
                        // give the items
                        dataContainer.set(_quest_claimed, PersistentDataType.INTEGER, 1);
                        dataContainer.set(_quest_completed, PersistentDataType.INTEGER, dataContainer.get(_quest_completed, PersistentDataType.INTEGER) + 1);

                        RewordQuest(player, dataContainer.get(_quest_id, PersistentDataType.STRING));
                        // DONE make this open the same thing, like a /reload
                        player.openInventory(createCurrentQuestInventory(player, nbtItem.getInteger("prevGUI")));
                    }
                }
//                        player.openInventory(createCurrentQuestInventory(player));
                if (config.getBoolean("log__GUIManager_java_onInventoryClick__Should_work")) {
                    Bukkit.getLogger().info("[WOKS][GUIManager.java#onInventoryClick][v6.12.2023]Should work.");
                }
            }
            else if (guiId == 3) {
                // this is the chest thing?
                // yes, it's the Current quest rewards.
                Quest currentQuest = questManager.getQuestById(dataContainer.get(_quest_id, PersistentDataType.STRING));
                List<ItemStack> items = new ArrayList<>(List.of(currentQuest.getRewardItems()));
                items.add(customExpBottle(currentQuest.getExpAmount()));

                // something, like, current rewards
                if (config.getBoolean("log__GUIManager_java_onInventoryClick__marko")) {
                    Bukkit.getLogger().info("[WOKS][GUIManager.java][v6.12.2023]marko");
                }
//                    ItemStack item = event.getCurrentItem().getType();
                // for next page

                if (item.getType() == Material.LIME_STAINED_GLASS_PANE) {
                    currentPage6++;
                    RewardChestGUI.openGUIChest(player, "Current Quest Rewards", items, currentPage6, 2);
                }
                // fro prevuis item
                else if (item.getType() == Material.RED_STAINED_GLASS_PANE) {
                    currentPage6--;
                    RewardChestGUI.openGUIChest(player, "Current Quest Rewards", items, currentPage6, 2);
                } else if (item.getType() == Material.FEATHER) {
                    player.openInventory(createCurrentQuestInventory(player, 1));
                }
            }
            else if (guiId == 4) {
                // this is Quests gui || ClaimQuest gui

//                        ItemStack clickedItem = event.getCurrentItem();
//                        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
//                            return;
//                        }

                int[] numbers = dataContainer.get(_quest_can_array, PersistentDataType.INTEGER_ARRAY);

                assert numbers != null;
                List<Quest> questList = getActiveQuestFromListInteger(TypeConversionUtils.castIntArrayToList(numbers));

                QuestGUI questGUI = new QuestGUI(player);
//                        Bukkit.getLogger().info("[WOKS][v2023.6.13] cp1 : " + currentPage);
                        Bukkit.getLogger().info("[WOKS][v2023.6.13] cp2 : " + currentPage2);
//                        Bukkit.getLogger().info("[WOKS][v2023.6.13] cp3 : " + currentPage3);
                NBTItem nbtItem = new NBTItem(item);
                if (item.getType() == Material.RED_STAINED_GLASS_PANE && currentPage2 > 0) {
                    currentPage2--;
                    Bukkit.getLogger().info(String.valueOf(currentPage2));
                    questGUI.openGUI(questList, currentPage2, "Quests", 4, 1, "Completed Quests");
                } else if (item.getType() == Material.GREEN_STAINED_GLASS_PANE && currentPage2 < (questList.size() - 1) / 45) {
                    currentPage2++;
                    questGUI.openGUI(questList, currentPage2, "Quests", 4, 1, "Completed Quests");
                }
                else if (nbtItem.getBoolean("IsCanQuest")) {
                    storageManager.setValueWithNamespacedKey(dataContainer, _quest_can_array_index, event.getRawSlot() + ((int) storageManager.getValueWithNamespacedKey(dataContainer, _quest_gui_currentPage2_index) * 45));
                    player.openInventory(ClaimNewQuestGUI.getGuiClaim(dataContainer,
                                                                      questManager.getQuestById(nbtItem.getString("stringId")), true));
                }
                else if (item.getType() == Material.PAPER) {
                    int[] canDo = dataContainer.get(_quest_completed_array, PersistentDataType.INTEGER_ARRAY);

                    assert canDo != null;
                    questGUI.openGUI(getActiveQuestFromListInteger(TypeConversionUtils.castIntArrayToList(canDo)),
                                     currentPage3, "Completed Quests", 5, 1, "Quests");
                }  else if (item.getType() == Material.FEATHER) {
                    // get nbt
                    Integer prevGUI = nbtItem.getInteger("prevGUI");

                    // ignore this >> // if the gui is 1 or current quest cus its custom
                    player.openInventory(guiManager.getGUIInventoryByIntegerID(prevGUI));
                }


            }
            else if (guiId == 5) {
                // this is the completed quest id gui/menu
                NBTItem nbtItem = new NBTItem(item);


                int[] numbers = dataContainer.get(_quest_completed_array, PersistentDataType.INTEGER_ARRAY);

                assert numbers != null;
                List<Quest> questList = getActiveQuestFromListInteger(TypeConversionUtils.castIntArrayToList(numbers));

                QuestGUI questGUI = new QuestGUI(player);


                if (item.getType() == Material.RED_STAINED_GLASS_PANE && currentPage3 > 0) {
//                            Bukkit.getLogger().info("[WOKS][v2023.6.13] cp2 : 1 : " + currentPage3);
                    currentPage3--;
//                            Bukkit.getLogger().info("[WOKS][v2023.6.13] cp2 : 2 : " + currentPage3);
                    questGUI.openGUI(questList, currentPage3, "Completed Quests", 5, 1, "Quests");
                } else if (item.getType() == Material.GREEN_STAINED_GLASS_PANE && currentPage3 < (questList.size() - 1) / 45) {
//                            Bukkit.getLogger().info("[WOKS][v2023.6.13] cp2 : 3 : " + currentPage3);
                    currentPage3++;
//                            Bukkit.getLogger().info("[WOKS][v2023.6.13] cp2 : 4 : " + currentPage3);
                    questGUI.openGUI(questList, currentPage3, "Completed Quests", 5, 1, "Quests");
                }
                else if (nbtItem.getBoolean("IsCanQuest")) {
                    storageManager.setValueWithNamespacedKey(dataContainer,
                                 _quest_done_array_index,
                                 event.getRawSlot() +
                                 ((int) storageManager.getValueWithNamespacedKey(dataContainer, _quest_gui_currentPage3_index) * 45));

                    player.openInventory(ClaimNewQuestGUI.getGuiClaim(
                            dataContainer,
                            questManager.getQuestById(nbtItem.getString("stringId")),
                            true,
                            7,
                            5
                    ));
                }
                else if (item.getType() == Material.PAPER) {
                    int[] canDo = dataContainer.get(_quest_can_array, PersistentDataType.INTEGER_ARRAY);

                    assert canDo != null;
                    questGUI.openGUI(getActiveQuestFromListInteger(TypeConversionUtils.castIntArrayToList(canDo)), currentPage2, "Quests", 4, 1, "Completed Quests");
                }  else if (item.getType() == Material.FEATHER) {
                    // get nbt
                    Integer prevGUI = nbtItem.getInteger("prevGUI");

                    // ignore this >> // if the gui is 1 or current quest cus its custom
                    player.openInventory(guiManager.getGUIInventoryByIntegerID(prevGUI));
                }

            }
            else if (guiId == 6) {
                // this is the quest for when claim use
                // so these are is the can quest
                NBTItem nbtItem = new NBTItem(item);
                String title = event.getView().getTitle();
                if (item.getType() == Material.RED_STAINED_GLASS_PANE) {
                    // "Quest Claimed"
                    Msg.send(player, "You have already done this quest.");
                }
                else if (item.getType() == Material.GREEN_STAINED_GLASS_PANE) {
                    // "Embark on quest"
                    // check to make sure that the current quest is done and claimed and percent done is 100%
                    // thats already checked
                    // setting the current quest

                    // made the quest not appear in the claim quest gui.
                    storageManager.setValueWithNamespacedKey(
                        dataContainer,
                        _quest_can_array,
                        removeValueFromArray(
                            (int[]) storageManager.getValueWithNamespacedKey(dataContainer, _quest_can_array),
                            nbtItem.getInteger("questIntId")
                        )
                    );
                    String stringId = nbtItem.getString("questId");
                    storageManager.setValueWithNamespacedKey(dataContainer, _quest_claimed, 0);
                    storageManager.setValueWithNamespacedKey(dataContainer, _quest_percent_done, 0.0d);
                    storageManager.setValueWithNamespacedKey(dataContainer, _quest_id, stringId);
                    storageManager.setValueWithNamespacedKey(dataContainer, _quest_id_integer, nbtItem.getInteger("questIntId"));

                    // now reload the thing
                    // be opening gui 4
                    QuestGUI questGUI = new QuestGUI(player);
                    int[] canDo = dataContainer.get(_quest_can_array, PersistentDataType.INTEGER_ARRAY);

                    assert canDo != null;
                    questGUI.openGUI(getActiveQuestFromListInteger(TypeConversionUtils.castIntArrayToList(canDo)), currentPage2, "Quests", 4, 1, "Completed Quests");
                }
                // this might be redundant
                else if (item.getType() == Material.LIME_STAINED_GLASS_PANE) {
                    // "Claim Quest"
                    dataContainer.set(_quest_claimed, PersistentDataType.INTEGER, 1);
                    dataContainer.set(_quest_completed, PersistentDataType.INTEGER,
                                      dataContainer.get(_quest_completed, PersistentDataType.INTEGER) + 1);

                    RewordQuest(player, dataContainer.get(_quest_id, PersistentDataType.STRING));
                    // now reload
                    player.openInventory(ClaimNewQuestGUI.getGuiClaim(dataContainer, questManager.getQuestById(title), true));
                }
                else if (item.getType() == Material.ORANGE_STAINED_GLASS_PANE) {
                    // "Can't do Quest"
                    Msg.send(player, "Do your current quest before you can do another.");
                }
                else if (item.getType() == Material.CHEST) {
                    Quest currentQuest = questManager.getQuestById(title);
                    List<ItemStack> items = new ArrayList<>(List.of(currentQuest.getRewardItems()));
                    items.add(customExpBottle(currentQuest.getExpAmount()));

                    RewardChestGUI.openGUIChest(player, title + " Rewards", items, currentPage5, 2, 6);
                }
                else if (item.getType() == Material.PAPER) {
                    Msg.send(player, Objects.requireNonNull(Objects.requireNonNull(item.getItemMeta()).getLore()).toString());
                }
                else if (item.getType() == Material.ECHO_SHARD) {
                    // prev page
                    int IndexItem = nbtItem.getInteger("ItemIndex");
                    int[] can_array = (int[]) storageManager.getValueWithNamespacedKey(dataContainer, _quest_can_array);
                    IndexItem--;
                    storageManager.setValueWithNamespacedKey(dataContainer, _quest_can_array_index, IndexItem);
                    Quest quest = questManager.getQuestByIntegerId(can_array[IndexItem]);

                    Inventory inv = ClaimNewQuestGUI.getGuiClaim(dataContainer, quest, true);

                    // reload
                    player.openInventory(inv);
                }
                else if (item.getType() == Material.AMETHYST_SHARD) {
                    // next page
                    int IndexItem = nbtItem.getInteger("ItemIndex");
                    int[] can_array = (int[]) storageManager.getValueWithNamespacedKey(dataContainer, _quest_can_array);
                    IndexItem++;
                    storageManager.setValueWithNamespacedKey(dataContainer, _quest_can_array_index, IndexItem);
                    Quest quest = questManager.getQuestByIntegerId(can_array[IndexItem]);

                    Inventory inv = ClaimNewQuestGUI.getGuiClaim(dataContainer, quest, true);

                    // reload
                    player.openInventory(inv);
                }
                else if (item.getType() == Material.FEATHER) {
                    QuestGUI questGUI = new QuestGUI(player);
                    int[] canDo = dataContainer.get(_quest_can_array, PersistentDataType.INTEGER_ARRAY);

                    assert canDo != null;
                    questGUI.openGUI(getActiveQuestFromListInteger(TypeConversionUtils.castIntArrayToList(canDo)), currentPage2, "Quests", 4, 1, "Completed Quests");

                }

            }
            else if (guiId == 7) {
                // this will be for the Completed

                String title = event.getView().getTitle();
                NBTItem nbtItem = new NBTItem(item);

                if (item.getType() == Material.CHEST) {
                    // TODO FIX
                    Quest currentQuest = questManager.getQuestById(title);
                    List<ItemStack> items = new ArrayList<>(List.of(currentQuest.getRewardItems()));
                    items.add(customExpBottle(currentQuest.getExpAmount()));

                    RewardChestGUI.openGUIChest(player, title + " Rewards", items, currentPage4, 7, 8);
                }
                else if (item.getType() == Material.PAPER) {
                    Msg.send(player, Objects.requireNonNull(Objects.requireNonNull(item.getItemMeta()).getLore()).toString());
                }
                else if (item.getType() == Material.ECHO_SHARD) {
                    // prev page

                    int IndexItem = nbtItem.getInteger("ItemIndex");
                    int[] can_array = (int[]) storageManager.getValueWithNamespacedKey(dataContainer, _quest_completed_array);
                    IndexItem--;
                    storageManager.setValueWithNamespacedKey(dataContainer, _quest_completed_array, IndexItem);
                    Quest quest = questManager.getQuestByIntegerId(can_array[IndexItem]);

                    Inventory inv = ClaimNewQuestGUI.getGuiClaim(dataContainer, quest, false, 7, 5);

                    // reload
                    player.openInventory(inv);
                }
                else if (item.getType() == Material.AMETHYST_SHARD) {
                    // next page
                    int IndexItem = nbtItem.getInteger("ItemIndex");
                    int[] can_array = (int[]) storageManager.getValueWithNamespacedKey(dataContainer, _quest_completed_array);
                    IndexItem++;
                    storageManager.setValueWithNamespacedKey(dataContainer, _quest_completed_array, IndexItem);
                    Quest quest = questManager.getQuestByIntegerId(can_array[IndexItem]);

                    Inventory inv = ClaimNewQuestGUI.getGuiClaim(dataContainer, quest, false, 7, 5);

                    // reload
                    player.openInventory(inv);
                }
                else if (item.getType() == Material.FEATHER) {
                    int[] numbers = dataContainer.get(_quest_completed_array, PersistentDataType.INTEGER_ARRAY);

                    assert numbers != null;
                    List<Quest> questList = getActiveQuestFromListInteger(TypeConversionUtils.castIntArrayToList(numbers));

                    QuestGUI questGUI = new QuestGUI(player);
                    questGUI.openGUI(questList, currentPage3, "Completed Quests", 5, 1, "Quests");

                }
            }
            else if (guiId == 8) {
                // this is the chest thing?
                // yes, it's the Current quest rewards.
                Quest currentQuest = questManager.getQuestById(dataContainer.get(_quest_id, PersistentDataType.STRING));
                List<ItemStack> items = new ArrayList<>(List.of(currentQuest.getRewardItems()));
                items.add(customExpBottle(currentQuest.getExpAmount()));

                // something, like, current rewards
                if (config.getBoolean("log__GUIManager_java_onInventoryClick__marko")) {
                    Bukkit.getLogger().info("[WOKS][GUIManager.java][v6.12.2023]marko");
                }
//                    ItemStack item = event.getCurrentItem().getType();
                // for next page

                if (item.getType() == Material.LIME_STAINED_GLASS_PANE) {
                    currentPage++;
                    RewardChestGUI.openGUIChest(player, "Quest Rewards", items, currentPage, 7);
                }
                // fro prevuis item
                else if (item.getType() == Material.RED_STAINED_GLASS_PANE) {
                    currentPage--;
                    RewardChestGUI.openGUIChest(player, "Quest Rewards", items, currentPage, 7);
                } else if (item.getType() == Material.FEATHER) {
                    player.openInventory(createCurrentQuestInventory(player, 7));
                }
            }
            else if (guiId == 0) {
                event.setCancelled(false);
            }

            storageManager.setValueWithNamespacedKey(dataContainer, _quest_gui_currentPage3_index, currentPage3);
            storageManager.setValueWithNamespacedKey(dataContainer, _quest_gui_currentPage2_index, currentPage2);
            storageManager.setValueWithNamespacedKey(dataContainer, _quest_gui_currentPage1_index, currentPage);
            //            } catch (Exception exception) {
//                Bukkit.getLogger().info("[WOKS][GUIManager.java#onInventoryClick][v6.12.2023]So the thing failed");
//                Bukkit.getLogger().severe("[WOKS][ERROR]" + exception);
//            }
        }
    }

    public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}

