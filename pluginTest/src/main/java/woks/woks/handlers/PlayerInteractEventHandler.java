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
    @EventHandler(priority = EventPriority.LOW)
    public boolean onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
//            Bukkit.getLogger().info("in air");
            if (event.getItem() == null) {return true;}
            Material ItemType = event.getItem().getType();
            if (ItemType == Material.EXPERIENCE_BOTTLE) {
                ItemStack item = event.getItem();
                NBTItem nbtItem = new NBTItem(item);
                if ((Objects.requireNonNull(item.getItemMeta())).hasLore()) {
                    int exps = Integer.parseInt((Objects.requireNonNull(item.getItemMeta().getLore())).get(0));
                    Bukkit.getLogger().info("[woks]"+String.valueOf(exps));
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
                if ((nbtItem.getBoolean("Using")) && !player.getName().equals("ShortPuppy14484")) {
//                    Bukkit.getLogger().info("Bye bye say hello to air.3");
                    /*Item.setType(Material.AIR);*/
                    Msg.send(player, "You messed up");
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
                            player.getInventory().setItem(player.getInventory().getHeldItemSlot(), nbtItem.getItem());
                        } else {
                            Msg.send(player, "Sorry you need to pay to gain access to this command.");
                            Msg.send(player, "To contact me for payment my discord is 'Malicious Code#3614'.");
                        }
                    } else {
                        Msg.send(player, "You cant access backpack in off hand.");
                    }
                    // Don't want to place it, eh?
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                    Msg.send(player, "This is a borked backpack, contact an admin to fix it.");
                }
            }
            else if (ItemType == Material.ENDER_CHEST || ItemType == Material.ENDER_PEARL || ItemType == Material.CRYING_OBSIDIAN || ItemType == Material.TRIPWIRE) {
                NBTItem nbtItem = new NBTItem(event.getItem());

                if (nbtItem.getBoolean("Disable")) {
                    event.setCancelled(true);
                }
            }
//            else if (ItemType == Material.DIAMOND_SHOVEL) {
//                if (event.getItem().containsEnchantment(WOKS.getInstance().THROWDOWN)) {
//
//                }
//            }
            else {
                return true;
            }
        } else {
            return true;
        }
        return true;
    }

    // Helper method to find an item in the inventory with the same material as the specified item
//    private ItemStack findSimilarItem(ItemStack[] inventory, ItemStack item) {
//        for (ItemStack invItem : inventory) {
//            // ME: questionable
//            if (invItem != null && invItem.getType() == item.getType() &&
//                    (invItem.getItemMeta() == null || invItem.getItemMeta().getLore() == null || invItem.getType() == Material.CHEST)) {
//                return invItem;
//            }
//        }
//        return null;
//    }

}
