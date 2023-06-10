package woks.woks.matthew.gui;

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

import java.util.HashMap;
import java.util.Map;

import static woks.woks.WOKS._quest_id_integer;
import static woks.woks.WOKS.questManager;

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

        if (clickedInventory != null && guiMap.containsValue(clickedInventory)) {
            event.setCancelled(true);
            // Handle the click event for the registered GUI
            int id = getKeyByValue(guiMap, new questGUIToolClass(clickedInventory, clickedInventory.getViewers().get(0).getOpenInventory().getTitle()));
            if (id != -1) {
                PersistentDataContainer dataContainer = player.getPersistentDataContainer();
                // Perform the action based on the GUI ID
                if (id == 1) {// Handle GUI with ID 1
//                    Bukkit.getLogger().info("GUI with ID 1 clicked by: " + player.getName());
                    ItemStack itemStack = event.getCurrentItem();
                    assert itemStack != null;
                    if (itemStack.getType() == Material.REDSTONE) {
                        // open the current quest ui
                        Integer currentQuestIntegerId = dataContainer.get(_quest_id_integer, PersistentDataType.INTEGER);

                        Quest quest = questManager.getQuestByIntegerId(currentQuestIntegerId);
                        if (quest == null) {
                            Msg.send(player, "Your current Quest does not exist.");
                            return;
                        }


                    }
                } else if (id == 2) {
                    // current quest menu/gui
                    // TODO your gettigng off focuse rn so im quitying for today
                    // TODO You were working on how to set up the guis for invonoyts you wanted to maek a gui maneger but couldn't make one
                    // TODO that worked to well for the id wouldn't be good my be just do it some other way for current quest cus of the change
                    // TODO -ing things so quests did work but now not or not as well have yet to test more
                    // TODO 6/9/2023
                }
            }
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

