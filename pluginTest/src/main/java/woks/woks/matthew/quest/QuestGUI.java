package woks.woks.matthew.quest;

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

import java.util.ArrayList;
import java.util.List;

import static woks.woks.WOKS._quest_completed_array;
import static woks.woks.WOKS.questManager;

public class QuestGUI implements Listener {
    private final Player player;
    private final List<String> quest;
    private int currentPage;
    private int currentPage2;



    public QuestGUI(Player player) {
        Bukkit.getPluginManager().registerEvents(this, WOKS.getInstance());

        this.player = player;
        this.quest = getQuests();
        this.currentPage = 0;
        this.currentPage2 = 0;
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

    public void openGUI(List<Quest> quests, Integer currentPage) {
        openGUI(quests, currentPage, "Quests");
    }
    public void openGUI(List<Quest> quests, Integer currentPage, String title) {
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
                gui.setItem(45, prevButton);
            }
            if (currentPage < (quests.size() - 1) / 45) {
                ItemStack nextButton = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
                ItemMeta nextMeta = nextButton.getItemMeta();
                assert nextMeta != null;
                nextMeta.setDisplayName("Next");
                nextButton.setItemMeta(nextMeta);
                gui.setItem(53, nextButton);
            }
        }

        // Add completed quests feather
//        List<String> completedQuests = getCompletedQuests();
        ItemStack completedFeather = new ItemStack(Material.FEATHER);
        ItemMeta featherMeta = completedFeather.getItemMeta();

        assert featherMeta != null;

        featherMeta.setDisplayName("Completed Quests");
//        featherMeta.setLore(completedQuests);

        completedFeather.setItemMeta(featherMeta);
        gui.setItem(52, completedFeather);

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
                    openGUI(questList, currentPage);
                } else if (clickedItem.getType() == Material.GREEN_STAINED_GLASS_PANE && currentPage < (questList.size() - 1) / 45) {
                    currentPage++;
                    openGUI(questList, currentPage);
                } else if (clickedItem.getType() == Material.FEATHER) {
                    PersistentDataContainer dataContainer = player.getPersistentDataContainer();
                    int[] numbers = dataContainer.get(_quest_completed_array, PersistentDataType.INTEGER_ARRAY);

                    assert numbers != null;
                    openGUI(getActiveQuestFromListInteger(TypeConversionUtils.castIntArrayToList(numbers)), currentPage2, "Completed Quests");
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
                    openGUI(questList, currentPage2);
                } else if (clickedItem.getType() == Material.GREEN_STAINED_GLASS_PANE && currentPage2 < (questList.size() - 1) / 45) {
                    currentPage2++;
                    openGUI(questList, currentPage2);
                } else if (clickedItem.getType() == Material.FEATHER) {
                    openGUI(questManager.getActiveQuestsAsListQuest(), currentPage, "Quests");
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



