package woks.woks.handlers;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import woks.woks.WOKS;

import java.util.*;

//InventoryMoveItemEventHandler
public class InventoryMoveItemEventHandler implements Listener {
    public InventoryMoveItemEventHandler(WOKS plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMoveItem(final InventoryClickEvent event) {
        Bukkit.getLogger().info("Moving");
        if (Objects.requireNonNull(event.getCurrentItem()).getType() == Material.CHEST && Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta()).hasLore()) {
            if (new NBTItem(event.getCurrentItem()).getBoolean("Using")) {
                event.setCancelled(true);
//                event.getWhoClicked().setItemOnCursor(null);
//                event.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR));
//                event.setCurrentItem(new ItemStack(Material.CAVE_AIR)); // change to air after
            }
        }
        ItemStack Item = event.getCursor();
        assert Item != null;
        assert Item.getAmount() > 0;
        NBTItem nbtItem = new NBTItem(Item);
        if (new NBTItem(event.getWhoClicked().getInventory().getItemInOffHand()).getBoolean("Using")) {
            if (event.getView().getTitle().equals("BackPack")) {
                NBTItem nbtItemOffHand = new NBTItem(event.getWhoClicked().getInventory().getItemInOffHand());
                ItemStack[] NBTSavedItems = nbtItemOffHand.getItemStackArray("ItemsE");
                if (!(Arrays.equals(NBTSavedItems, event.getView().getTopInventory().getStorageContents()))) {
                    // check if in main hand
                    nbtItemOffHand.setItemStackArray("ItemsE", event.getView().getTopInventory().getStorageContents());
                    event.getWhoClicked().getInventory().setItemInOffHand(nbtItemOffHand.getItem());
                }
            }
        }
        // check to make sure item in main hand is not air and then save var for item in main hand
        if (nbtItem.getBoolean("Using") && (event.getView().getTitle().equals("BackPack"))) {

            Bukkit.getLogger().info("STOP2");
            ItemStack[] ItemsE = event.getWhoClicked().getOpenInventory().getTopInventory().getStorageContents();
            nbtItem.setItemStackArray("ItemsE", ItemsE);
            nbtItem.setBoolean("Using", false);

            // might need fixing
            Bukkit.getLogger().info("adeedd");
            event.getWhoClicked().getInventory().setItemInMainHand(nbtItem.getItem());
//            event.getClickedInventory().setItem(event.getRawSlot(), new ItemStack(Material.AIR));
            event.getClickedInventory().setItem(event.getSlot(), null);
            Bukkit.getLogger().info("Bye bye say hello to air.4");
            event.setCurrentItem(null);


            // START OF CHECKING
            // Get the player's inventory
            Player player = (Player) event.getWhoClicked();
            ItemStack[] inventory = player.getInventory().getContents();

            // Create a map to store ItemStacks with the same lore
            Map<String, List<ItemStack>> loreItems = new HashMap<>();

            // Iterate through each item in the inventory
            for (ItemStack item : inventory) {
                // Ignore null items and items without lore
                if (item == null || item.getItemMeta() == null || item.getItemMeta().getLore() == null || item.getType() != Material.CHEST) {
                    continue;
                }

                // Get the lore of the item
                List<String> lore = Collections.singletonList(nbtItem.getString("UUIDToPreventDuping"));//item.getItemMeta().getLore();

                // If an item with the same lore has already been found, add this item to the list
                // Otherwise, create a new list with this item and add it to the map
                String loreString = lore.toString();
                if (loreItems.containsKey(loreString)) {
                    loreItems.get(loreString).add(item);
                } else {
                    List<ItemStack> itemList = new ArrayList<>();
                    itemList.add(item);
                    loreItems.put(loreString, itemList);
                }
            }

            // Iterate through the map and remove duplicate items
            for (List<ItemStack> itemList : loreItems.values()) {
                // If there are more than 1 item in the list, remove all but one
                if (itemList.size() > 1) {
                    for (int i = 1; i < itemList.size(); i++) {
                        ItemStack item = itemList.get(i);
                        // Make sure not to remove an item with no lore but the same material
                        if (item.getItemMeta() == null || item.getItemMeta().getLore() == null || item.getType() == Material.CHEST) {
                            ItemStack similarItem = findSimilarItem(inventory, item);
                            if (similarItem != null) {
                                if (Objects.equals(new NBTItem(item).getString("UUIDToPreventDuping"), new NBTItem(similarItem).getString("UUIDToPreventDuping"))) {

                                    Bukkit.getLogger().info("Bye bye say hello to air.5");
                                    player.getInventory().remove(item);
                                    break;
                                }
                            }
                        }
//                        else {
//                            Bukkit.getLogger().info("Bye bye say hello to air.6");
//                            player.getInventory().remove(item);
//                            break;
//                        }
                    }
                }
            }
            // END OF CHECKING


//            event.getWhoClicked().closeInventory();
//            event.getWhoClicked().getInventory().setItemInMainHand(nbtItem.getItem());
//            Bukkit.getLogger().info(String.valueOf));
//            Objects.requireNonNull(event.getClickedInventory()).getItem(event.getSlot());
        }
        if (event.getView().getTitle().equals("BackPack") && event.getWhoClicked().getInventory().getItemInMainHand().getType() == Material.CHEST) {
            NBTItem nbtItemMainHand = new NBTItem(event.getWhoClicked().getInventory().getItemInMainHand());
            ItemStack[] NBTSavedItems = nbtItemMainHand.getItemStackArray("ItemsE");
            if (!(Arrays.equals(NBTSavedItems, event.getView().getTopInventory().getStorageContents()))) {
                // check if in main hand
                nbtItemMainHand.setItemStackArray("ItemsE", event.getView().getTopInventory().getStorageContents());
                event.getWhoClicked().getInventory().setItemInMainHand(nbtItemMainHand.getItem());
            }
        }
    }

    @EventHandler
    public void onDragItem(final InventoryDragEvent event) {
        ItemStack Item = event.getCursor();
        assert Item.getAmount() > 0;
        NBTItem nbtItem = new NBTItem(Item);
        if (nbtItem.getBoolean("Using")) {
            Bukkit.getLogger().info("STOP");
            event.setCancelled(true);
        }
    }

    // Helper method to find an item in the inventory with the same material as the specified item
    private ItemStack findSimilarItem(ItemStack[] inventory, ItemStack item) {
        for (ItemStack invItem : inventory) {
            if (invItem != null && invItem.getType() == item.getType() &&
                    (invItem.getItemMeta() == null || invItem.getItemMeta().getLore() == null)) {
                return invItem;
            }
        }
        return null;
    }

}
