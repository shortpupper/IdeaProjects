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
        Bukkit.getLogger().info("[woks] Moving1");
        if (Objects.requireNonNull(event.getCurrentItem()).getType() == Material.CHEST && Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta()).hasLore()) {
            Bukkit.getLogger().info("[woks] Moving2");
            if (new NBTItem(event.getCurrentItem()).getBoolean("Using")) {
                event.setCancelled(true);
                Bukkit.getLogger().info("[woks] Moving3");
            }
        }
        Bukkit.getLogger().info("[woks] Moving4");
        ItemStack Item = event.getCursor();
        Bukkit.getLogger().info("[woks] Moving5");
        assert Item != null;
        Bukkit.getLogger().info("[woks] Moving6");
        assert Item.getType() != Material.AIR;
        Bukkit.getLogger().info("[woks] Moving7");
        assert Item.getAmount() > 0;
        Bukkit.getLogger().info("[woks] Moving8");
        NBTItem nbtItem = new NBTItem(Item);
        Bukkit.getLogger().info("[woks] Moving9");
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
            Bukkit.getLogger().info("[woks] Moving10");
            ItemStack[] ItemsE = event.getWhoClicked().getOpenInventory().getTopInventory().getStorageContents();
            Bukkit.getLogger().info("[woks] Moving11");
            nbtItem.setItemStackArray("ItemsE", ItemsE);
            Bukkit.getLogger().info("[woks] Moving12");
            nbtItem.setBoolean("Using", false);
            Bukkit.getLogger().info("[woks] Moving13");

            // might need fixing
            event.getWhoClicked().getInventory().setItemInMainHand(nbtItem.getItem());
            Bukkit.getLogger().info("[woks] Moving14");
            Objects.requireNonNull(event.getClickedInventory()).setItem(event.getSlot(), new ItemStack(Material.AIR));
            Bukkit.getLogger().info("[woks] Moving15");
            event.setCurrentItem(new ItemStack(Material.AIR));
            Bukkit.getLogger().info("[woks] Moving16");

        }
        Bukkit.getLogger().info("[woks] Moving17");
        if (event.getView().getTitle().equals("BackPack") && event.getWhoClicked().getInventory().getItemInMainHand().getType() == Material.CHEST) {
            Bukkit.getLogger().info("[woks] Moving18");
            NBTItem nbtItemMainHand = new NBTItem(event.getWhoClicked().getInventory().getItemInMainHand());
            Bukkit.getLogger().info("[woks] Moving19");
            ItemStack[] NBTSavedItems = nbtItemMainHand.getItemStackArray("ItemsE");
            Bukkit.getLogger().info("[woks] Moving20");
            if (!(Arrays.equals(NBTSavedItems, event.getView().getTopInventory().getStorageContents()))) {
                Bukkit.getLogger().info("[woks] Moving21");
                // check if in main hand
                nbtItemMainHand.setItemStackArray("ItemsE", event.getView().getTopInventory().getStorageContents());
                Bukkit.getLogger().info("[woks] Moving22");
                event.getWhoClicked().getInventory().setItemInMainHand(nbtItemMainHand.getItem());
                Bukkit.getLogger().info("[woks] Moving23");
            }
            Bukkit.getLogger().info("[woks] Moving24");
        }
        Bukkit.getLogger().info("[woks] Moving25");
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
