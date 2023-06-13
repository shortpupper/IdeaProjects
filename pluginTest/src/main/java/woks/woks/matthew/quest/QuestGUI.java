package woks.woks.matthew.quest;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.TypeConversionUtils;
import woks.woks.WOKS;
import woks.woks.matthew.gui.RewardChestGUI;

import java.util.ArrayList;
import java.util.List;

import static woks.woks.WOKS.*;
import static woks.woks.items.CustomExpBottle.customExpBottle;

public class QuestGUI implements Listener {
    private final Player player;
    private final List<String> quest;
    private int currentPage;
    private int currentPage2;
    public static int currentPage3;



    public QuestGUI(Player player) {
        Bukkit.getPluginManager().registerEvents(this, WOKS.getInstance());

        this.player = player;
        this.quest = getQuests();
        this.currentPage = 0;
        this.currentPage2 = 0;
        currentPage3 = 0;
    }



    public List<String> getQuests() {
        // Implement your logic to retrieve quests here
        // This is just a dummy implementation
        List<String> quests = new ArrayList<>();



        // Add more quests here if needed
        return quests;
    }

    public List<String> getCompletedQuests() {
        // Implement your logic to retrieve completed quests here
        // This is just a dummy implementation
        List<String> completedQuests = new ArrayList<>();


        return completedQuests;
    }

    public void openGUI(List<Quest> quests) {
        openGUI(quests, 0, "Quests", true, "Completed Quests");
    }

    public void openGUI(List<Quest> quests, Integer currentPage) {
        openGUI(quests, currentPage, "Quests", true, "Completed Quests");
    }
    public void openGUI(List<Quest> quests, Integer currentPage, String title) {
        openGUI(quests, currentPage, title, true, "Completed Quests");
    }
    public void openGUI(List<Quest> quests, Integer currentPage, Boolean feather) {
        openGUI(quests, currentPage, "Quests", feather, "Completed Quests");
    }
    public void openGUI(List<Quest> quests, Integer currentPage, String title, Boolean feather, String featherName) {
        Inventory gui = Bukkit.createInventory(null, 54, title);

        // Add quest items to the GUI
        for (int i = currentPage * 45; i < Math.min((currentPage + 1) * 45, quests.size()); i++) {
            Quest currentQuest = quests.get(i);
            String quest = currentQuest.getName();

            ItemStack questItem = new ItemStack(currentQuest.getMaterial());
            ItemMeta meta = questItem.getItemMeta();

            assert meta != null;


            List<String> lores = new ArrayList<>();
            lores.add("§f" + currentQuest.getDescription() + "§r");

            meta.setLore(lores);
            meta.setDisplayName(quest);
            questItem.setItemMeta(meta);


            NBTItem nbtItem = new NBTItem(questItem);
//            nbtItem.setObject("QuestObject", currentQuest);
            nbtItem.setInteger("intId", currentQuest.getQuestIntegerId());
            nbtItem.setString("stringId", currentQuest.getQuestId());

            // TODO make a thing so that user can click item to then open a gui with the info of the quest

            questItem = nbtItem.getItem();

            gui.setItem(i % 45, questItem);
        }

        // Add navigation buttons
        if (quests.size() > 45) {
            if (currentPage > 0) {
                ItemStack prevButton = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                ItemMeta prevMeta = prevButton.getItemMeta();
                assert prevMeta != null;
                prevMeta.setDisplayName("Prev");
                prevButton.setItemMeta(prevMeta);
                gui.setItem(48, prevButton);
            }
            if (currentPage < (quests.size() - 1) / 45) {
                ItemStack nextButton = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
                ItemMeta nextMeta = nextButton.getItemMeta();
                assert nextMeta != null;
                nextMeta.setDisplayName("Next");
                nextButton.setItemMeta(nextMeta);
                gui.setItem(50, nextButton);
            }
        }

        if (feather) {

            // Add completed quests feather
    //        List<String> completedQuests = getCompletedQuests();
            ItemStack completedFeather = new ItemStack(Material.FEATHER);
            ItemMeta featherMeta = completedFeather.getItemMeta();

            assert featherMeta != null;

            featherMeta.setDisplayName(featherName);
    //        featherMeta.setLore(completedQuests);

            completedFeather.setItemMeta(featherMeta);
            gui.setItem(49, completedFeather);
        }

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().equals(event.getView().getTopInventory())) {
            if (event.getView().getTitle().equals("Quests")) {
                event.setCancelled(true);

                ItemStack clickedItem = event.getCurrentItem();
                if (clickedItem == null || clickedItem.getType() == Material.AIR) {
                    return;
                }

                List<Quest> questList = questManager.getActiveQuestsAsListQuest();

                if (clickedItem.getType() == Material.RED_STAINED_GLASS_PANE && currentPage > 0) {
                    currentPage--;
                    openGUI(questList, currentPage, "Quests", true, "Quests");
                } else if (clickedItem.getType() == Material.GREEN_STAINED_GLASS_PANE && currentPage < (questList.size() - 1) / 45) {
                    currentPage++;
                    openGUI(questList, currentPage, "Quests", true, "Quests");
                } else if (clickedItem.getType() == Material.FEATHER) {
                    PersistentDataContainer dataContainer = player.getPersistentDataContainer();
                    int[] numbers = dataContainer.get(_quest_completed_array, PersistentDataType.INTEGER_ARRAY);

                    assert numbers != null;
                    openGUI(getActiveQuestFromListInteger(TypeConversionUtils.castIntArrayToList(numbers)), currentPage2, "Completed Quests", true, "Completed Quests");
                }
            } else if (event.getView().getTitle().equals("Completed Quests")) {
                event.setCancelled(true);

                ItemStack clickedItem = event.getCurrentItem();
                if (clickedItem == null || clickedItem.getType() == Material.AIR) {
                    return;
                }

                PersistentDataContainer dataContainer = player.getPersistentDataContainer();
                int[] numbers = dataContainer.get(_quest_completed_array, PersistentDataType.INTEGER_ARRAY);

                assert numbers != null;
                List<Quest> questList = getActiveQuestFromListInteger(TypeConversionUtils.castIntArrayToList(numbers));

                if (clickedItem.getType() == Material.RED_STAINED_GLASS_PANE && currentPage2 > 0) {
                    currentPage2--;
                    openGUI(questList, currentPage2, "Completed Quests", true, "Completed Quests");
                } else if (clickedItem.getType() == Material.GREEN_STAINED_GLASS_PANE && currentPage2 < (questList.size() - 1) / 45) {
                    currentPage2++;
                    openGUI(questList, currentPage2, "Completed Quests", true, "Completed Quests");
                } else if (clickedItem.getType() == Material.FEATHER) {
                    int[] canDo = dataContainer.get(_quest_can_array, PersistentDataType.INTEGER_ARRAY);

                    assert canDo != null;
                    openGUI(getActiveQuestFromListInteger(TypeConversionUtils.castIntArrayToList(canDo)), currentPage, "Quests", true, "Quests");
                }
            } else if (event.getView().getTitle().equals("Current Quest Rewards")) {
                event.setCancelled(true);

                Bukkit.getLogger().info("[WOKS][QuestGUI.java][v6.12.2023] here");

                ItemStack clickedItem = event.getCurrentItem();
                if (clickedItem == null || clickedItem.getType() == Material.AIR) {
                    return;
                }
                PersistentDataContainer dataContainer = player.getPersistentDataContainer();
//                List<Quest> questList = questManager.getActiveQuestsAsListQuest();
                Quest currentQuest = questManager.getQuestById(dataContainer.get(_quest_id, PersistentDataType.STRING));
                List<ItemStack> items = new ArrayList<>(List.of(currentQuest.getRewardItems()));
                items.add(customExpBottle(currentQuest.getExpAmount()));

                if (clickedItem.getType() == Material.RED_STAINED_GLASS_PANE && currentPage3 > 0) {
                    currentPage3--;
                    RewardChestGUI.openGUIChest(player, "Current Quest Rewards", items, currentPage3);
//                    openGUI(questList, currentPage, "Quests", true, "Quests");
                } else if (clickedItem.getType() == Material.GREEN_STAINED_GLASS_PANE && currentPage3 < (items.size() - 1) / 28) {
                    currentPage3++;
                    RewardChestGUI.openGUIChest(player, "Current Quest Rewards", items, currentPage3);

//                    openGUI(questList, currentPage, "Quests", true, "Quests");
                } else if (clickedItem.getType() == Material.BARRIER) {
                    // TODO: names for the items else they will be badded
                    player.openInventory(createCurrentQuestInventory(player));
                }
            }
        }
    }

    public static List<Quest> getActiveQuestFromListInteger(List<Integer> integerList) {
        List<Quest> questList = new ArrayList<>();

        for (Integer intId : integerList) {
            questList.add(questManager.getQuestByIntegerId(intId));
        }

        return questList;
    }
}



