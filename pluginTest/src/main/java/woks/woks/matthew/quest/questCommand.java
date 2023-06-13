package woks.woks.matthew.quest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import woks.woks.CommandBase;

import java.util.ArrayList;
import java.util.List;

import static woks.woks.WOKS.guiManager;

public class questCommand implements Listener {
    public questCommand() {
        new CommandBase("quest", 0, 5, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                if (arguments.length >= 2 && arguments[0].equalsIgnoreCase("testingGui")) {
                    player.openInventory(guiManager.getGUIInventoryByIntegerID(Integer.parseInt(arguments[1])));
                } else {

                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/quest <gui> <integer:number>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                if (args.length == 1) {
                    List<String> out = new ArrayList<>();

                    out.add("testingGui");

                    return out;
                }
                return null;
            }
        };
    }

    private void openGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, "Quest GUI");

        ItemStack blankItem = createItemStack(Material.BLACK_STAINED_GLASS_PANE, "");
        ItemStack redStone  = createItemStack(Material.REDSTONE, "Current Quest");
        ItemStack tnt       = createItemStack(Material.TNT, "Claim Quest");
        ItemStack barrel    = createItemStack(Material.BARREL, "Completed Quests");
        ItemStack book      = createItemStack(Material.BOOK, "Stats");

        ItemStack[] items = new ItemStack[54];
        for (int i = 0; i < 54; i++) {
            items[i] = blankItem;
        }

        items[13] = redStone;
        items[20] = tnt;
        items[24] = barrel;
        items[40] = book;

        inventory.setContents(items);
        player.openInventory(inventory);
    }

    public static ItemStack createItemStack(Material material, String displayName) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Quest GUI")) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() == Material.AIR) {
                return;
            }

            Player player = (Player) event.getWhoClicked();
            if (clickedItem.getType() == Material.REDSTONE) {
                // Current Quest clicked
                Bukkit.getLogger().info("Current Quest clicked by: " + player.getName());
            } else if (clickedItem.getType() == Material.TNT) {
                // Claim Quest clicked
                Bukkit.getLogger().info("Claim Quest clicked by: " + player.getName());
            } else if (clickedItem.getType() == Material.BARREL) {
                // Completed Quests clicked
                Bukkit.getLogger().info("Completed Quests clicked by: " + player.getName());
            } else if (clickedItem.getType() == Material.BOOK) {
                // Stats clicked
                Bukkit.getLogger().info("Stats clicked by: " + player.getName());
            }
        }
    }
}
