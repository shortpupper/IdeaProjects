package woks.woks.handlers;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import woks.woks.WOKS;

import java.util.Arrays;
import java.util.Objects;

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
            }
        }
        ItemStack Item = event.getCursor();
        assert Item != null;
        assert Item.getType() != Material.AIR;
        assert Item.getAmount() > 0;
        NBTItem nbtItem = new NBTItem(Item);
        //        if (new NBTItem(event.getWhoClicked().getInventory().getItemInOffHand()).getBoolean("Using")) {
//            if (event.getView().getTitle().equals("BackPack")) {
//                NBTItem nbtItemOffHand = new NBTItem(event.getWhoClicked().getInventory().getItemInOffHand());
//                ItemStack[] NBTSavedItems = nbtItemOffHand.getItemStackArray("ItemsE");
//                if (!(Arrays.equals(NBTSavedItems, event.getView().getTopInventory().getStorageContents()))) {
//                    // check if in main hand
//                    nbtItemOffHand.setItemStackArray("ItemsE", event.getView().getTopInventory().getStorageContents());
//                    event.getWhoClicked().getInventory().setItemInOffHand(nbtItemOffHand.getItem());
//                }
//            }
//        }
        // check to make sure item in main hand is not air and then save var for item in main hand
        if (nbtItem.getBoolean("Using") && (event.getView().getTitle().equals("BackPack"))) {
            ItemStack[] ItemsE = event.getWhoClicked().getOpenInventory().getTopInventory().getStorageContents();

            nbtItem.setItemStackArray("ItemsE", ItemsE);
            nbtItem.setBoolean("Using", false);

            // might need fixing
            event.getWhoClicked().getInventory().setItemInMainHand(nbtItem.getItem());

            Objects.requireNonNull(event.getClickedInventory()).setItem(event.getSlot(), null);
            event.setCurrentItem(null);

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
        assert (Item != null ? Item.getAmount() : 0) > 0;
        NBTItem nbtItem = new NBTItem(Item);
        if (nbtItem.getBoolean("Using")) {
            event.setCancelled(true);
        }
    }
}
