package woks.woks.handlers;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import woks.woks.*;

import java.util.*;

public class PlayerInteractEventHandler implements Listener {
    public PlayerInteractEventHandler(WOKS plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    //    @EventHandler
//    public boolean onPlayerItemHeldEvent(PlayerSwapHandItemsEvent event) {
//        Player player = event.getPlayer();
////        NBTItem nbtItemOffHand = new NBTItem(Objects.requireNonNull(event.getOffHandItem()));
////        NBTItem nbtItemMainHand = new NBTItem(Objects.requireNonNull(event.getMainHandItem()));
////        if (nbtItemOffHand.getBoolean("Using") || nbtItemMainHand.getBoolean("Using")) {
////            event.setCancelled(true);
////            // maybe close inventory
////        }
//        if ((event.getMainHandItem() == null || event.getMainHandItem().getType() == Material.AIR)) {
//            if (player.getOpenInventory().getTitle().equals("BackPack")) {
//                player.getInventory().setItemInMainHand(player.getInventory().getItemInOffHand());
//                player.getInventory().setItemInOffHand(null);
//            }
//        }
//        return true;
//    }
    @EventHandler(priority = EventPriority.LOW)
    public boolean onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Bukkit.getLogger().info("in air");
            Material ItemType = (Objects.requireNonNull(event.getItem())).getType();
            if (ItemType == Material.EXPERIENCE_BOTTLE) {
                ItemStack item = event.getItem();
                NBTItem nbtItem = new NBTItem(item);
                if ((Objects.requireNonNull(item.getItemMeta())).hasLore()) {
                    int exps = Integer.parseInt((Objects.requireNonNull(item.getItemMeta().getLore())).get(0));
                    Bukkit.getLogger().info(String.valueOf(exps));
                    ExperienceManager.setTotalExperience(player, ExperienceManager.getTotalExperience(player) + exps);
                    event.setCancelled(true);
                    if (event.getItem().getAmount() > 1) {
                        event.getItem().setAmount(event.getItem().getAmount() - 1);
                    } else {
                        player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                    }
                } else if (nbtItem.hasNBTData()) {
                    ExperienceManager.setTotalExperience(player, ExperienceManager.getTotalExperience(player) + nbtItem.getInteger("Exp"));
                    event.setCancelled(true);
                    if (event.getItem().getAmount() > 1) {
                        event.getItem().setAmount(event.getItem().getAmount() - 1);
                    } else {
                        player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                    }
                } else {
                    return false;
                }
            }
            else if (ItemType == Material.CHEST) {
                ItemStack Item = new ItemStack(event.getItem());
                NBTItem nbtItem = new NBTItem(Item);
                if ((nbtItem.getBoolean("Using"))) {
                    Bukkit.getLogger().info("Bye bye say hello to air.3");
                    Item.setType(Material.AIR);
                } else if ((nbtItem.getBoolean("BackPack"))) {
                    Object Things = UserCheckPaying.CheckPaying();
                    if (Things instanceof Boolean) {
                        Msg.send(player, "Can't get players.");
                        return true;
                    } else if (Things instanceof List<?> && player.getInventory().getItemInOffHand().getType() != Material.CHEST) {
                        List<String> names = (List<String>) Things;
                        if (names.contains(player.getName())) {
                            new BackPackGUI(nbtItem, player);
                            nbtItem.setBoolean("Using", true);
                            event.getPlayer().getInventory().setItemInMainHand(nbtItem.getItem());
                            Bukkit.getLogger().info("use");

                            // START OF CHECKING
                            // Get the player's inventory
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
                                                    Bukkit.getLogger().info("Bye bye say hello to air.2");
                                                    player.getInventory().remove(item);
                                                    break;
                                                }
                                            }
                                        }
//                                        else {
//                                            Bukkit.getLogger().info("Bye bye say hello to air.1");
//                                            player.getInventory().remove(item);
//                                            break;
//                                        }
                                    }
                                }
                            }
                            // END OF CHECKING

                        } else {
                            Msg.send(player, "Sorry you need to pay to gain access to this command.");
                            Msg.send(player, "To contact me for payment my discord is 'Malicious Code#3614'.");
                        }
                    } else {
                        Msg.send(player, "You cant access backpack in off hand.");
                    }
                    // Don't want to place it, eh?
                    event.setCancelled(true);
                }
            }
            else if (ItemType == Material.ENDER_CHEST || ItemType == Material.ENDER_PEARL || ItemType == Material.CRYING_OBSIDIAN) {
                NBTItem nbtItem = new NBTItem(event.getItem());

                if (nbtItem.getBoolean("Disable")) {
                    event.setCancelled(true);
                }
            }
            else if (ItemType == Material.DIAMOND_SHOVEL) {

            }
            else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    // Helper method to find an item in the inventory with the same material as the specified item
    private ItemStack findSimilarItem(ItemStack[] inventory, ItemStack item) {
        for (ItemStack invItem : inventory) {
            // ME: questionable
            if (invItem != null && invItem.getType() == item.getType() &&
                    (invItem.getItemMeta() == null || invItem.getItemMeta().getLore() == null || invItem.getType() == Material.CHEST)) {
                return invItem;
            }
        }
        return null;
    }

}
