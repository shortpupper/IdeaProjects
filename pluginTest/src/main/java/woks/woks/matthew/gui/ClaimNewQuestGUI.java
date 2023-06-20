package woks.woks.matthew.gui;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import woks.woks.NKD;
import woks.woks.matthew.quest.Quest;
import woks.woks.matthew.util.ExtraDataContainer;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class ClaimNewQuestGUI {
    public static ItemStack feather = makeGuiItem(Material.FEATHER, "Back", 6, 4);
    public static ItemStack chest = makeGuiItem(Material.CHEST, "Rewards", 6, 4, Collections.singletonList("Click Me"));
    public static ItemStack book = makeGuiItem(Material.BOOK, "Requirements", 6, 4);
    public static ItemStack paper = makeGuiItem(Material.PAPER, "Description", 6, 4);
    public static ItemStack glass_pane = makeGuiItem(Material.ORANGE_STAINED_GLASS_PANE, "Can't do Quest", 6, 4);

    public static Inventory getGuiClaim(PersistentDataContainer dataContainer, Quest quest, Boolean doGlass) {
        return getGuiClaim(dataContainer, quest, doGlass, 6, 4);
    }
    public static Inventory getGuiClaim(PersistentDataContainer EdataContainer, Quest quest, Boolean doGlass, Integer guiId, Integer prevGUI) {
        ExtraDataContainer dataContainer = new ExtraDataContainer(EdataContainer);
        ItemStack questItem      = makeGuiItem(quest.getMaterial(), quest.getName(), guiId, prevGUI);
        book  = setItemLore(book, Collections.singletonList(quest.getRequirements()));
        paper = setItemLore(paper, Collections.singletonList(quest.getDescription()));

        // this checks if the player can claim the
        boolean questClaimed     = dataContainer.get(NKD.HAS_BEEN_CLAIMED);
        double questPercentDone  = dataContainer.get(NKD.PERCENT_OF_DONE);
        NBTItem glass_paneNBT    = new NBTItem(glass_pane);

        glass_paneNBT.setInteger("questIntId", quest.getQuestIntegerId());
        glass_paneNBT.setString("questId",     quest.getQuestId());

        glass_pane = glass_paneNBT.getItem();


       if (questPercentDone >= 100.0d && questClaimed) {
           glass_pane = setItemMaterialAndDisplayName(glass_pane, Material.GREEN_STAINED_GLASS_PANE, "Embark on quest");
       } else {
           glass_pane = setItemMaterialAndDisplayName(glass_pane, Material.ORANGE_STAINED_GLASS_PANE, "Can't do Quest");
       }


        int lengthIntArray = ((int[]) dataContainer.get(NKD.CAN_DO_ARRAY)).length;
        int QuestIndex = dataContainer.get(NKD.CAN_PAGE_INDEX);
        ItemStack echo_shard = null;
        ItemStack amethyst_shard = null;
        if (lengthIntArray > 1) {
            if (QuestIndex + 1 < lengthIntArray) {
                amethyst_shard  = makeGuiItem(Material.AMETHYST_SHARD, "Next", guiId, prevGUI, QuestIndex);
            }
            if (QuestIndex != 0) {
                echo_shard = makeGuiItem(Material.ECHO_SHARD, "Previous", guiId, prevGUI, QuestIndex);
            }
        }

        Inventory inventory = Bukkit.createInventory(null, 54, quest.getQuestId());

        if (prevGUI != 4 && guiId != 6) {
            book = setItemPrevGUIandGuiLoc(book, guiId, prevGUI);
            chest = setItemPrevGUIandGuiLoc(chest, guiId, prevGUI);
            paper = setItemPrevGUIandGuiLoc(paper, guiId, prevGUI);
            feather = setItemPrevGUIandGuiLoc(feather, guiId, prevGUI);
        }

        inventory.setItem(4,  questItem);
        inventory.setItem(11, book);
        inventory.setItem(15, chest);
        inventory.setItem(22, paper);
        if (doGlass) {
            inventory.setItem(40, glass_pane);
        }
        inventory.setItem(48, echo_shard);
        inventory.setItem(49, feather);
        inventory.setItem(50, amethyst_shard);

        return inventory;
    }
    public static ItemStack makeGuiItem
        (
            Material           material,
            String             displayName,
            Integer            guiId,
            @Nullable Integer  prevGUI
        )
    {
        return makeGuiItem(material, displayName, 1, null, guiId, prevGUI, 0);
    }
    public static ItemStack makeGuiItem
            (
                    Material           material,
                    String             displayName,
                    Integer            guiId,
                    @Nullable Integer  prevGUI,
                    Integer            ItemIndex
            )
    {
        return makeGuiItem(material, displayName, 1, null, guiId, prevGUI, ItemIndex);
    }
    public static ItemStack makeGuiItem
            (
                    Material                material,
                    String                  displayName,
                    Integer                 guiId,
                    @Nullable Integer       prevGUI,
                    @Nullable List<String>  lore
            )
    {
        return makeGuiItem(material, displayName, 1, lore, guiId, prevGUI, 0);
    }
    public static ItemStack makeGuiItem(
                      Material      material,
                      String        displayName,
            @Nullable Integer       amount,
            @Nullable List<String>  lore,
                      Integer       guiId,
            @Nullable Integer       prevGUI,
                      Integer       ItemIndex) {
        ItemStack item = new ItemStack(material, amount == null ? 1 : amount);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(displayName);
        if (lore != null) {
            itemMeta.setLore(lore);
        }
        item.setItemMeta(itemMeta);

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setInteger("GuiLoc", guiId);

        nbtItem.setInteger("prevGUI", prevGUI != null ? prevGUI : 1);
        nbtItem.setInteger("ItemIndex", ItemIndex);

        return nbtItem.getItem();
    }

    public static ItemStack setItemLore(ItemStack item, List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
    public static ItemStack setItemMaterialAndDisplayName(ItemStack item, Material material, String displayName) {
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(displayName);

        item.setItemMeta(meta);
        item.setType(material);

        return item;
    }
    public static ItemStack setItemPrevGUIandGuiLoc(ItemStack item, Integer guiId, Integer prevGUI) {
        NBTItem nbtItem = new NBTItem(item);

        nbtItem.setInteger("GuiLoc", guiId);
        nbtItem.setInteger("prevGUI", prevGUI != null ? prevGUI : 1);

        return nbtItem.getItem();
    }
}
