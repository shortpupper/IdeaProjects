package woks.woks;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import woks.woks.commands.*;
import woks.woks.dam.bannedWhat;
import woks.woks.handlers.*;
import woks.woks.items.BackPack;
import woks.woks.items.CustomExpBottle;
import woks.woks.items.EnchantedLeather;
import woks.woks.items.PRQ.Obamanium.*;
import woks.woks.matthew.ImAnAdmin;
import woks.woks.matthew.gui.GUIManager;
import woks.woks.matthew.permote;
import woks.woks.matthew.quest.*;
import woks.woks.matthew.quest.commands.giveQuestPlayer;
import woks.woks.matthew.roles;
import woks.woks.matthew.util.BooleanPersistentDataType;
import woks.woks.matthew.util.ExtraDataContainer;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static woks.woks.items.EnchantedCrying_Obsidian.EnchantedCrying_Obsidian;
import static woks.woks.items.EnchantedDiamond.EnchantedDiamond;
import static woks.woks.items.EnchantedEmerald.EnchantedEmerald;
import static woks.woks.items.EnchantedEnder_Chest.EnchantedEnder_Chest;
import static woks.woks.items.EnchantedEnder_Pearl.EnchantedEnder_Pearl;
import static woks.woks.items.EnchantedLeather.EnchantedLeather;
import static woks.woks.items.PRQ.Obamanium.Obamanium_Ingot.Obamanium_Ingot;
import static woks.woks.matthew.quest.giveQuest.GiveQuest;
import static woks.woks.matthew.quest.rewordQuest.DefaultExpAmounts;

public final class WOKS extends JavaPlugin implements Listener {
    private static WOKS instance;
    public static boolean AFC = false;
    public static FileConfiguration config;


    public static QuestManager questManager;
    public static GUIManager guiManager;

    // logging vars
    public static boolean LogDrag;
    public static boolean LogMovement;
    public static boolean LogQuestRegisterOnEnable;

    // dev stuff / testing things
    public static boolean devQuestForTesting;
//    public static BooleanPersistentDataType booleanDataType = new BooleanPersistentDataType();

    public static PersistentDataType<Byte, Boolean> booleanT = new BooleanPersistentDataType();




    public static String[] Ranks = {
            "Airman Basic§f",
            "Airman§f",

            "§2Airman First Class§f",
            "§2Senior Airman§f",
            "§2Staff Sergeant§f",
            "§2Technical Sergeant§f",

            "§2Master Sergeant§f",
            "§2Senior Master Sergeant§f",
            "§2Chief Master Sergeant§f",
            "§2Command Chief Master Sergeant§f",
            "§2Chief Master Sergeant of the Air Force§f",

            "§2Second Lieutenant§f",
            "§2First Lieutenant§f",
            "§2Captain§f",

            "§2Major§f",
            "§2Lieutenant Colonel§f",
            "§2Colonel§f",

            "§2Brigadier General§f",
            "§2Major General§f",

            "§2Lieutenant General§f",
            "§2General§f",

            "§cGeneral of the Air Force§f"
    };
    public static WOKS getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        config = this.getConfig();
        instance = this;
        // Plugin startup logic
        Bukkit.getLogger().info("Starting, ShortPuppy14484 plugin.");
        // Register the custom PersistentDataType

        booleanT = new BooleanPersistentDataType();





        // TODO make a gui for config
        // v2023.6.13

        config.addDefault("PlayerWalkPath", false);
        config.addDefault("GoodDayMSG", true);
        config.addDefault("UnsafeEnchanting", true);
        config.addDefault("PaidRequests", true);
        config.addDefault("roles", true);
        config.addDefault("Killer", true);
        config.addDefault("Quests", true);
        config.addDefault("Gui", true);
        config.addDefault("devQuestForTesting", true);
        config.addDefault("StorageManager_DONTCHANGE", true);

        config.addDefault("log__", true);
        boolean clog__;
        clog__ = config.getBoolean("log__");
        config.addDefault("LogDrag", clog__);
        config.addDefault("LogMovement", clog__);
        config.addDefault("LogQuestRegisterOnEnable", clog__);
        config.addDefault("log__GUIManager_java_onInventoryClick__int_guiId_failed", clog__);
        config.addDefault("log__GUIManager_java_onInventoryClick__int_id_failed", clog__);
        config.addDefault("log__GUIManager_java_onInventoryClick__uhmm_the_guiId_is", clog__);
        config.addDefault("log__GUIManager_java_onInventoryClick__CHEST", clog__);
        config.addDefault("log__GUIManager_java_onInventoryClick__Should_work", clog__);
        config.addDefault("log__GUIManager_java_onInventoryClick__marko", clog__);

        config.options().copyDefaults(true);
        saveConfig();



        // Logging stuff, really burning
        LogDrag = config.getBoolean("LogDrag");
        LogMovement = config.getBoolean("LogMovement");


        // commands

//        new Feed();
        new SaveEXP(this);
        new CustomExpBottle();
        new PlayerInteractEventHandler(this);
        new TestGetName();
        new BackPack();
        new ItemDropped(this);
        new TestingPlugin();
//        new AccessBackPack(); // deprecated
        new GiveBackPack();
        new InventoryEventHandler(this);

        if (config.getBoolean("PlayerWalkPath")) {
            new PlayerWalkPath(this);
        }

        new PlayerDeath(this);
        new PayingUSers();

        new EnchantedLeather();
//        new EnchantedEnder_Pearl();
//        new EnchantedDiamond();
//        new EnchantedEmerald();
//        new EnchantedCrying_Obsidian();
//        new EnchantedEnder_Chest();
        new InventoryMoveItemEventHandler(this);
        new SetLore();
        new EnchantTest();

        /*
        deprecated
        new OpenPlayerEnderChest();
        new OpenPlayerInv();
         */
        new PlayerInv();
        if (config.getBoolean("UnsafeEnchanting")) {
            new EnchantTesting();
        }
        new AutoAim(this);

        if (config.getBoolean("PaidRequests")) {
            new Obamanium_Ingot();
            new Obamanium_Sword();
            new Obamanium_Helmet();
            new Obamanium_ChestPlate();
            new Obamanium_Leggings();
            new Obamanium_Boots();
        }

        if (config.getBoolean("roles")) {
            new roles(this);

            new ImAnAdmin();
            new permote();

            Bukkit.getLogger().info("[woks] Rolles are on.");
        }
        if (false) {
            new bannedWhat();

            Bukkit.getLogger().info("[woks] banned is on.");
        }

        if (config.getBoolean("Killer")) {
            new EntitysDeathKillCount(this);
            new pardenAll();

            Bukkit.getLogger().info("Killer stats are on.");
        }

        if (config.getBoolean("Quests")) {
            new QuestManager();

//            new QuestGUI(this);

//            new claimReward();
//            Objects.requireNonNull(Bukkit.getPluginCommand("claimreward")).setTabCompleter(new claimReward());

            new rewordQuest();
            new giveQuest();
//            new CommandNextQuest();
            new ResetQuestCommand();
            new CmdGetPerStorage();
            new giveQuestPlayer();

            new QuestHelpCommand();

            Bukkit.getLogger().info("[woks] Quests are on.");

            questManager = new QuestManager();


            ItemStack[] items;
            int expAmount;

            items = new ItemStack[]{new ItemStack(Material.SPRUCE_LOG, 16)};
            expAmount = 5;
            
            Material[] DEVSTACKm;
            DEVSTACKm = (Material.values());

            ItemStack[] DEVSTACK = new ItemStack[DEVSTACKm.length-1];
            for (int i = 0; i < DEVSTACKm.length-1; i++) {
                DEVSTACK[i] = new ItemStack(DEVSTACKm[i], 1);
            }

            questManager.registerQuest(
                    "Join_sever_first_time",
                    1,
                    items,
                    expAmount,
                    "Join the sever",
                    new Integer[]{},
                    Material.OAK_DOOR,
                    "Be a part of the sever.",
                    "Joining the sever for the first time",
                    new Integer[]{2}
            );
            questManager.registerQuest(
                    "Say_Hello",
                    2,
                    DEVSTACK,
                    DefaultExpAmounts,
                    "Talking In Chat",
                    new Integer[]{1},
                    Material.REDSTONE_TORCH,
                    "Say hello meat someone new.",
                    "Typing 'Hello' into chat just by its self.",
                    new Integer[]{3}
            );
            questManager.registerQuest(
                    "Quest_Help",
                    3,
                    new ItemStack[]{},
                    1,
                    "/questhelp",
                    new Integer[]{1,2},
                    Material.STRUCTURE_BLOCK,
                    "Getting used to Quests.",
                    "Executing the command '/questhelp'.",
                    new Integer[]{4,5,6, 7}
            );
            if (config.getBoolean("devQuestForTesting")) {
                questManager.registerQuest(
                        "DevQuestForTesting_a",
                        4,
                        new ItemStack[]{},
                        1,
                        "/next plz",
                        new Integer[]{},
                        Material.PAPER,
                        "Testing Quest.",
                        "Do /nextquest",
                        new Integer[]{}
                );
                questManager.registerQuest(
                        "DevQuestForTesting_b",
                        5,
                        new ItemStack[]{},
                        1,
                        "/next plz",
                        new Integer[]{},
                        Material.PAPER,
                        "Testing Quest.",
                        "Do /nextquest",
                        new Integer[]{}
                );
                questManager.registerQuest(
                        "DevQuestForTesting_c",
                        6,
                        new ItemStack[]{},
                        1,
                        "/next plz",
                        new Integer[]{},
                        Material.PAPER,
                        "Testing Quest.",
                        "Do /nextquest",
                        new Integer[]{}
                );
            }
        }

        if (config.getBoolean("Gui")) {
            new questCommand();

            guiManager = new GUIManager();

            guiManager.registerGUI(1, "Quests", getItemsRegisterGUIExample());
            guiManager.registerGUI(2, "Quests2", getItemsRegisterGUIExample());

//            guiManager.registerGUI(2, "Current Quest", getItemsForCurrentQuestGUI());

            Bukkit.getLogger().info("[WOKS][v6.9.2023] GUI added.");
        }





        new AccessLegacyBackPack();

        Recipes();
        Enchants();

        getServer().getPluginManager().registerEvents(this, this);
    }

    private ItemStack[] getItemsRegisterGUIExample() {
        // Create the ItemStacks for the GUI
        ItemStack blankItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack redStone = new ItemStack(Material.REDSTONE);
        ItemStack tnt = new ItemStack(Material.TNT);
        ItemStack barrel = new ItemStack(Material.BARREL);
        ItemStack book = new ItemStack(Material.BOOK);

        // Set display names for the items
        ItemMeta redStoneMeta = Objects.requireNonNull(redStone.getItemMeta());
        redStoneMeta.setDisplayName("Current Quest");
        redStone.setItemMeta(redStoneMeta);
        NBTItem nbtItem = new NBTItem(redStone);
        nbtItem.setInteger("GuiLoc", 1);
        redStone = nbtItem.getItem();


        ItemMeta tntMeta = Objects.requireNonNull(tnt.getItemMeta());
        tntMeta.setDisplayName("Claim Quest");
        tnt.setItemMeta(tntMeta);
        NBTItem nbtItem2 = new NBTItem(tnt);
        nbtItem2.setInteger("GuiLoc", 1);
        tnt = nbtItem2.getItem();

        ItemMeta barrelMeta = Objects.requireNonNull(barrel.getItemMeta());
        barrelMeta.setDisplayName("Completed Quests");
        barrel.setItemMeta(barrelMeta);
        NBTItem nbtItem3 = new NBTItem(barrel);
        nbtItem3.setInteger("GuiLoc", 1);
        barrel = nbtItem3.getItem();

        ItemMeta bookMeta = Objects.requireNonNull(book.getItemMeta());
        bookMeta.setDisplayName("Stats");
        book.setItemMeta(bookMeta);
        NBTItem nbtItem4 = new NBTItem(book);
        nbtItem4.setInteger("GuiLoc", 1);
        book = nbtItem4.getItem();

        // Create the item array for the GUI
        ItemStack[] items = new ItemStack[54];
//        for (int i = 0; i < 54; i++) {
//            items[i] = blankItem;
//        }
        items[13] = redStone;
        items[20] = tnt;
        items[24] = barrel;
        items[40] = book;

        // Register the GUI with ID 1
        return items;
    }

    private ItemStack[] getItemsForCurrentQuestGUI() {
        Integer prevGUI = 1;
        // Create the ItemStacks for the GUI
        ItemStack blankItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

        ItemStack redStone  = new ItemStack(Material.REDSTONE);
        ItemStack tnt       = new ItemStack(Material.TNT);
        ItemStack barrel    = new ItemStack(Material.BARREL);
        ItemStack book      = new ItemStack(Material.BOOK);

        // idk
        NBTItem nbtItemPainting;

        // Set display names for the items
        ItemMeta redStoneMeta = Objects.requireNonNull(redStone.getItemMeta());
        redStoneMeta.setDisplayName("Current Quest");
        redStone.setItemMeta(redStoneMeta);

         nbtItemPainting = new NBTItem(redStone);
        nbtItemPainting.setInteger("GuiLoc", 1);
        nbtItemPainting.setInteger("prevGUI", prevGUI);
        redStone = nbtItemPainting.getItem();


        ItemMeta tntMeta = Objects.requireNonNull(tnt.getItemMeta());
        tntMeta.setDisplayName("Claim Quest");
        tnt.setItemMeta(tntMeta);

         nbtItemPainting = new NBTItem(tnt);
        nbtItemPainting.setInteger("GuiLoc", 2);
        nbtItemPainting.setInteger("prevGUI", prevGUI);
        tnt = nbtItemPainting.getItem();


        ItemMeta barrelMeta = Objects.requireNonNull(barrel.getItemMeta());
        barrelMeta.setDisplayName("Completed Quests");
        barrel.setItemMeta(barrelMeta);

         nbtItemPainting = new NBTItem(barrel);
        nbtItemPainting.setInteger("GuiLoc", 2);
        nbtItemPainting.setInteger("prevGUI", prevGUI);
        barrel = nbtItemPainting.getItem();


        ItemMeta bookMeta = Objects.requireNonNull(book.getItemMeta());
        bookMeta.setDisplayName("Stats");
        book.setItemMeta(bookMeta);

         nbtItemPainting = new NBTItem(book);
        nbtItemPainting.setInteger("GuiLoc", 2);
        nbtItemPainting.setInteger("prevGUI", prevGUI);
        book = nbtItemPainting.getItem();


        // Create the item array for the GUI
        ItemStack[] items = new ItemStack[54];
//        for (int i = 0; i < 54; i++) {
//            items[i] = blankItem;
//        }
        items[13] = redStone;
        items[20] = tnt;
        items[24] = barrel;
        items[40] = book;

        // Register the GUI with ID 1
        return items;
    }

    public static boolean codeLinear(int input) {
        if ((input >= 10 && input <= 16) ||
                (input >= 19 && input <= 25) ||
                (input >= 28 && input <= 34) ||
                (input >= 37 && input <= 43)) {
            return true;
        }
        return false;
    }

    public static int mapNumber(int number) {
        if (number >= 0 && number <= 6) {
            return number + 10;  // Range: 10-16
        } else if (number >= 7 && number <= 13) {
            return number + 12;  // Range: 19-25
        } else if (number >= 14 && number <= 20) {
            return number + 14;  // Range: 28-34
        } else if (number >= 21 && number <= 27) {
            return number + 16;  // Range: 37-43
        } else {
            throw new IllegalArgumentException("Invalid number. Must be between 0 and 27.");
        }
    }

    public static int[] removeValueFromArray(int[] array, int valueToRemove) {
        int count = 0; // Counter to track the number of occurrences of the value to remove

        // Count the number of occurrences of the value to remove
        for (int i = 0; i < array.length; i++) {
            if (array[i] == valueToRemove) {
                count++;
            }
        }

        // If no occurrences found, return the original array
        if (count == 0) {
            return array;
        }

        int[] newArray = new int[array.length - count];
        int newIndex = 0; // Index for the new array

        // Copy the elements from the original array to the new array, excluding the value to remove
        for (int i = 0; i < array.length; i++) {
            if (array[i] != valueToRemove) {
                newArray[newIndex++] = array[i];
            }
        }

        return newArray;
    }


    // GUI OF 2
    public static Inventory createCurrentQuestInventory(Player player) {
        return createCurrentQuestInventory(player, null);
    }

    public static Inventory createCurrentQuestInventory(Player player, Integer prevGUI) {
        // GUI OF 2
        ExtraDataContainer dataContainer = new ExtraDataContainer(player.getPersistentDataContainer());

        // Create the inventory
        Inventory inventory = Bukkit.createInventory(null, 54, "Current Quest");


        //TODO change some of the items so they do the stuffs


        //v6.12.2023 im mad cus just I have to put stuff here
        // public static double x = ( -b + || - sqrt(4ac)) / 2a
        Integer questIdInteger = dataContainer.get(NKD.INTEGER_ID);

        Quest currentQuest = questManager.getQuestByIntegerId(questIdInteger);


        // Set items in the inventory
        ItemStack painting = new ItemStack(currentQuest.getMaterial());
        ItemMeta paintingMeta = painting.getItemMeta();

        assert paintingMeta != null;

        paintingMeta.setDisplayName(currentQuest.getName());
        painting.setItemMeta(paintingMeta);

        NBTItem nbtItemPainting = new NBTItem(painting);
        nbtItemPainting.setInteger("GuiLoc", 2);
        nbtItemPainting.setInteger("prevGUI", prevGUI);
        painting = nbtItemPainting.getItem();

        inventory.setItem(4, painting);

        //book
        ItemStack requirementsBook = new ItemStack(Material.BOOK);
        ItemMeta requirementsBookMeta =  requirementsBook.getItemMeta();
        assert requirementsBookMeta != null;
        requirementsBookMeta.setDisplayName("Requirements");
        requirementsBookMeta.setLore(Collections.singletonList(currentQuest.getRequirements()));
        requirementsBook.setItemMeta(requirementsBookMeta);

        NBTItem nbtItemrequirementsBook = new NBTItem(requirementsBook);
        nbtItemrequirementsBook.setInteger("GuiLoc", 2);
        nbtItemrequirementsBook.setInteger("prevGUI", prevGUI);
        requirementsBook = nbtItemrequirementsBook.getItem();

        inventory.setItem(11, requirementsBook);

        //chrest
        ItemStack rewardsChest = new ItemStack(Material.CHEST);
        ItemMeta rewardsChestMeta = rewardsChest.getItemMeta();
        assert rewardsChestMeta != null;
        rewardsChestMeta.setDisplayName("Rewards");
        rewardsChest.setItemMeta(rewardsChestMeta);

        NBTItem nbtItemrewardsChest = new NBTItem(rewardsChest);
        nbtItemrewardsChest.setInteger("GuiLoc", 2);
        nbtItemrewardsChest.setInteger("prevGUI", prevGUI);
        rewardsChest = nbtItemrewardsChest.getItem();

        inventory.setItem(15, rewardsChest);

        //paper
        ItemStack descriptionPaper = new ItemStack(Material.PAPER);
        ItemMeta descriptionPaperMeta = descriptionPaper.getItemMeta();
        assert descriptionPaperMeta != null;
        descriptionPaperMeta.setDisplayName("Description");
        descriptionPaper.setItemMeta(descriptionPaperMeta);

        NBTItem nbtItemdescriptionPaper = new NBTItem(descriptionPaper);
        nbtItemdescriptionPaper.setInteger("GuiLoc", 2);
        nbtItemdescriptionPaper.setInteger("prevGUI", prevGUI);
        descriptionPaper = nbtItemdescriptionPaper.getItem();

        inventory.setItem(22, descriptionPaper);


        double questPercentDone = dataContainer.get(NKD.PERCENT_OF_DONE); // Replace with your quest percent done value
        boolean questClaimed = dataContainer.get(NKD.HAS_BEEN_CLAIMED); // Replace with your quest claimed value

        ItemStack claimGlassPane = new ItemStack(getClaimGlassPaneMaterial(questPercentDone, questClaimed));
        ItemMeta claimGlassPaneMeta = claimGlassPane.getItemMeta();
        boolean hasClaim = false;

        assert claimGlassPaneMeta != null;
        if (questClaimed) {
            claimGlassPaneMeta.setDisplayName("Claimed Quest");
        } else if (questPercentDone >= 100.0 && !questClaimed) {
            claimGlassPaneMeta.setDisplayName("Claim Quest");
            hasClaim = true;
            // Set green glass pane
            claimGlassPane.setType(Material.LIME_STAINED_GLASS_PANE);
//            setGlassPaneColor(claimGlassPaneMeta, Material.GREEN_STAINED_GLASS_PANE);
        } else if (questPercentDone < 100.0 && !questClaimed) {
            claimGlassPaneMeta.setDisplayName("Claim Quest " + questPercentDone + "%");
            // Set red glass pane
            claimGlassPane.setType(Material.RED_STAINED_GLASS_PANE);
//            setGlassPaneColor(claimGlassPaneMeta, Material.RED_STAINED_GLASS_PANE);
            claimGlassPaneMeta.setLore(List.of("You need to have 100% of the quest done."));
        }

        claimGlassPane.setItemMeta(claimGlassPaneMeta);

        NBTItem nbtItemclaimGlassPane = new NBTItem(claimGlassPane);
        nbtItemclaimGlassPane.setInteger("GuiLoc", 2);
        nbtItemclaimGlassPane.setInteger("prevGUI", prevGUI);
        nbtItemclaimGlassPane.setBoolean("canClaim", hasClaim);
        nbtItemclaimGlassPane.setInteger("questIntId", currentQuest.getQuestIntegerId());

        claimGlassPane = nbtItemclaimGlassPane.getItem();

        inventory.setItem(31, claimGlassPane);

        // Create glass panes with different colors based on quest percent done
        int[] percentIndexes = {28, 37, 46, 47, 48, 40, 50, 51, 52, 43, 34};
        double[] percentValues = {0.0, 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0};

        for (int i = 0; i < percentIndexes.length; i++) {
            int index = percentIndexes[i];
            double percentValue = percentValues[i];

            ItemStack glassPane = new ItemStack(getGlassPaneMaterial(percentValue, questPercentDone));
            ItemMeta glassPaneMeta = glassPane.getItemMeta();

            // Set display name based on percent value
            assert glassPaneMeta != null;
            glassPaneMeta.setDisplayName(percentValue + "%");

            glassPane.setItemMeta(glassPaneMeta);

            NBTItem nbtItemglassPane = new NBTItem(glassPane);
            nbtItemglassPane.setInteger("GuiLoc", 2);
            nbtItemglassPane.setInteger("prevGUI", prevGUI);
            nbtItemglassPane.setBoolean("Done", true);
            glassPane = nbtItemglassPane.getItem();

            inventory.setItem(index, glassPane);
        }

        ItemStack item = new ItemStack(Material.FEATHER);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("Back");
        item.setItemMeta(itemMeta);

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setInteger("GuiLoc", 2);
        nbtItem.setInteger("prevGUI", prevGUI);

        item = nbtItem.getItem();
        inventory.setItem(49, item);

        return inventory;
    }

    private static Material getClaimGlassPaneMaterial(double questPercentDone, boolean questClaimed) {
        if (questClaimed) {
            return Material.RED_STAINED_GLASS_PANE;
        } else if (questPercentDone >= 100.0 && !questClaimed) {
            return Material.LIME_STAINED_GLASS_PANE;
        } else if (questPercentDone < 100.0 && !questClaimed) {
            return Material.RED_STAINED_GLASS_PANE;
        }

        return Material.RED_STAINED_GLASS_PANE; // Default to red if none of the conditions match
    }

    private static Inventory createGlassPanes(Inventory inventory, double questPercentDone) {
        int[] percentIndexes = {28, 37, 46, 47, 48, 49, 50, 51, 52, 43, 34};
        double[] percentValues = {0.0, 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0};

        for (int i = 0; i < percentIndexes.length; i++) {
            int index = percentIndexes[i];
            double percentValue = percentValues[i];

            ItemStack glassPane = new ItemStack(getGlassPaneMaterial(percentValue, questPercentDone));
            ItemMeta glassPaneMeta = glassPane.getItemMeta();

            // Set display name based on percent value
            assert glassPaneMeta != null;
            if (percentValue == 100.0 && questPercentDone >= 100.0) {
                glassPaneMeta.setDisplayName("Claim Quest");
            } else {
                glassPaneMeta.setDisplayName(percentValue + "%");
            }

            glassPane.setItemMeta(glassPaneMeta);
            inventory.setItem(index, glassPane);
        }
        return inventory;
    }

    private static Material getGlassPaneMaterial(double percentValue, double questPercentDone) {
//        double questPercentDone = questPercentDone; // Replace with your quest percent done value

        if (percentValue == 100.0 && questPercentDone >= 100.0) {
            return Material.LIME_STAINED_GLASS_PANE;
        } else if (questPercentDone >= percentValue) {
            return Material.LIME_STAINED_GLASS_PANE;
        } else {
            return Material.RED_STAINED_GLASS_PANE;
        }
    }


    public static PersistentDataType<?, ?> stringToPersistentDataType(String string) {
        String dataTypeString = string.toLowerCase(); // Convert to lowercase


        return switch (dataTypeString) {
            case "integer" -> PersistentDataType.INTEGER;
            case "integer_array" -> PersistentDataType.INTEGER_ARRAY;
            case "double" -> PersistentDataType.DOUBLE;
            case "byte" -> PersistentDataType.BYTE;
            case "byte_array" -> PersistentDataType.BYTE_ARRAY;
            case "float" -> PersistentDataType.FLOAT;
            case "long" -> PersistentDataType.LONG;
            case "long_array" -> PersistentDataType.LONG_ARRAY;
            case "short" -> PersistentDataType.SHORT;
            case "tag_container" -> PersistentDataType.TAG_CONTAINER;
            case "tag_container_array" -> PersistentDataType.TAG_CONTAINER_ARRAY;
            default -> PersistentDataType.STRING;
        };
    }

    public static NamespacedKey parseStringNameSpaceKey(String input) {
        String[] parts = input.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid input: " + input);
        }
        String namespace = parts[0];
        String key = parts[1];
        return new NamespacedKey(namespace, key);
    }

    public CommandMap getCommandMap() {
        try {
            if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
                Field field = SimplePluginManager.class.getDeclaredField("commandMap");
                field.setAccessible(true);

                return (CommandMap) field.get(Bukkit.getPluginManager());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static int[] addArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];

        System.arraycopy(arr1, 0, result, 0, arr1.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);

        return result;
    }
    public static UUID uuid3Generator(String inputString) {
        byte[] bytes = inputString.getBytes(StandardCharsets.UTF_8);
        return UUID.nameUUIDFromBytes(bytes);
    }
    public static String convertHashMapToString(Map<?, ?> hashMap) {
        StringBuilder sb = new StringBuilder();

        // Iterate over the key-value pairs in the HashMap
        for (Map.Entry<?, ?> entry : hashMap.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

            // Append key-value pair to the StringBuilder
            sb.append(key).append(": ").append(value).append(", ");
        }

        // Remove the trailing comma and space
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    public void applyPlayerPermissions(Player player) {
        try {
            String playerName = player.getName();

            ConfigurationSection playerPermissions = config.getConfigurationSection("permissions.players." + playerName);
            if (playerPermissions != null) {
                PermissionAttachment attachment = player.addAttachment(this);
                List<String> permissions = playerPermissions.getStringList("permissions");
                for (String permission : permissions) {
                    attachment.setPermission(permission, true);
                }
            }
        } catch (Exception exception) {
            Bukkit.getLogger().info("[WOKS] player cant dev");
        }
    }


    public void Recipes() {
        if (config.getBoolean("PaidRequests")) {
            final RecipeChoice ingot = new RecipeChoice.ExactChoice(Obamanium_Ingot(1));
            // 50 pesos ~ 2.78 United States Dollar
            ShapedRecipe obamanium_ingot = new ShapedRecipe(new NamespacedKey(this, "obamanium_ingot"), Obamanium_Ingot(1));
            obamanium_ingot.shape("***", "***", "***");
            obamanium_ingot.setIngredient('*', new RecipeChoice.ExactChoice(Obamanium_Scrap.Obamanium_Scrap(1)));

            ShapedRecipe obamanium_chestplate = new ShapedRecipe(new NamespacedKey(this, "obamanium_chestplate"), Obamanium_ChestPlate.Obamanium_ChestPlate());
            obamanium_chestplate.shape("* *", "***", "***");
            obamanium_chestplate.setIngredient('*', ingot);
//
            ShapedRecipe obamanium_helmet = new ShapedRecipe(new NamespacedKey(this, "obamanium_helmet"), Obamanium_Helmet.Obamanium_Helmet());
            obamanium_helmet.shape("***", "* *", "   ");
            obamanium_helmet.setIngredient('*', ingot);
//
            ShapedRecipe obamanium_leggings = new ShapedRecipe(new NamespacedKey(this, "obamanium_leggings"), Obamanium_Leggings.Obamanium_Leggings());
            obamanium_leggings.shape("***", "* *", "* *");
            obamanium_leggings.setIngredient('*', ingot);

            ShapedRecipe obamanium_boots = new ShapedRecipe(new NamespacedKey(this, "obamanium_boots"), Obamanium_Boots.Obamanium_Boots());
            obamanium_boots.shape("   ", "* *", "* *");
            obamanium_boots.setIngredient('*', ingot);

            ShapedRecipe obamanium_sword = new ShapedRecipe(new NamespacedKey(this, "obamanium_sword"), Obamanium_Sword.Obamanium_Sword());
            obamanium_sword.shape("*", "*", "1");
            obamanium_sword.setIngredient('*', ingot);
            obamanium_sword.setIngredient('1', Material.STICK);

            ShapedRecipe obamanium_shovel = new ShapedRecipe(new NamespacedKey(this, "obamanium_shovel"), Obamanium_Shovel.Obamanium_Shovel());
            obamanium_shovel.shape("*", "1", "1");
            obamanium_shovel.setIngredient('*', ingot);
            obamanium_shovel.setIngredient('1', Material.STICK);
//
            ShapedRecipe obamanium_hoe = new ShapedRecipe(new NamespacedKey(this, "obamanium_hoe"), Obamanium_Hoe.Obamanium_Hoe());
            obamanium_hoe.shape("** ", " 1 ", " 1 ");
            obamanium_hoe.setIngredient('*', ingot);
            obamanium_hoe.setIngredient('1', Material.STICK);

            ShapedRecipe obamanium_axe = new ShapedRecipe(new NamespacedKey(this, "obamanium_axe"), Obamanium_Axe.Obamanium_Axe());
            obamanium_axe.shape("** ", "*1 ", " 1 ");
            obamanium_axe.setIngredient('*', ingot);
            obamanium_axe.setIngredient('1', Material.STICK);
//
            ShapedRecipe obamanium_pickaxe = new ShapedRecipe(new NamespacedKey(this, "obamanium_pickaxe"), Obamanium_Pickaxe.Obamanium_Pickaxe());
            obamanium_pickaxe.shape("***", " 1 ", " 1 ");
            obamanium_pickaxe.setIngredient('*', ingot);
            obamanium_pickaxe.setIngredient('1', Material.STICK);

            ShapedRecipe obamanium_scrap = new ShapedRecipe(new NamespacedKey(this, "obamanium_scrap"), Obamanium_Scrap.Obamanium_Scrap(1));
            obamanium_scrap.shape("***", "***", "***");
            obamanium_scrap.setIngredient('*', Material.DIAMOND_BLOCK);
//            obamanium_scrap.setIngredient('/', Material.STICK);


            getServer().addRecipe(obamanium_scrap);
            getServer().addRecipe(obamanium_pickaxe);
            getServer().addRecipe(obamanium_axe);
            getServer().addRecipe(obamanium_hoe);
            getServer().addRecipe(obamanium_shovel);
            getServer().addRecipe(obamanium_sword);
            getServer().addRecipe(obamanium_boots);
            getServer().addRecipe(obamanium_leggings);
            getServer().addRecipe(obamanium_helmet);
            getServer().addRecipe(obamanium_chestplate);
            getServer().addRecipe(obamanium_ingot);
        }
        // recipe

        // diamond
        ShapedRecipe EnchantedDiamond = new ShapedRecipe(new NamespacedKey(this, "EnchantedDiamond"), EnchantedDiamond());
        EnchantedDiamond.shape("*%*", "BBB", "*%*");
        EnchantedDiamond.setIngredient('B', Material.DIAMOND);
        EnchantedDiamond.setIngredient('*', Material.COPPER_BLOCK);
        EnchantedDiamond.setIngredient('%', Material.DIAMOND);

        // EnchantedCrying_Obsidian
        ShapedRecipe EnchantedCrying_Obsidian = new ShapedRecipe(new NamespacedKey(this, "EnchantedCrying_Obsidian"), EnchantedCrying_Obsidian());
        EnchantedCrying_Obsidian.shape("*%*", "%*%", "*%*");
        EnchantedCrying_Obsidian.setIngredient('*', Material.OBSIDIAN);
        EnchantedCrying_Obsidian.setIngredient('%', Material.CRYING_OBSIDIAN);

        // EnchantedEnder_Pearl
        ShapedRecipe EnchantedEnder_Pearl = new ShapedRecipe(new NamespacedKey(this, "EnchantedEnder_Pearl"), EnchantedEnder_Pearl());
        EnchantedEnder_Pearl.shape("*%*", "%B%", "*%*");
        EnchantedEnder_Pearl.setIngredient('B', Material.ENDER_PEARL);
        EnchantedEnder_Pearl.setIngredient('*', Material.ENDER_EYE);
        EnchantedEnder_Pearl.setIngredient('%', Material.DIAMOND);

        // EnchantedEmerald
        ShapedRecipe EnchantedEmerald = new ShapedRecipe(new NamespacedKey(this, "EnchantedEmerald"), EnchantedEmerald());
        EnchantedEmerald.shape("%%%", "%C%", "%%%");
        EnchantedEmerald.setIngredient('%', new RecipeChoice.ExactChoice(EnchantedLeather()));
        EnchantedEmerald.setIngredient('C', new RecipeChoice.ExactChoice(EnchantedDiamond()));

        // EnchantedEnder_Chest
        ShapedRecipe EnchantedEnder_Chest = new ShapedRecipe(new NamespacedKey(this, "EnchantedEnder_Chest"), EnchantedEnder_Chest());
        EnchantedEnder_Chest.shape("#$#", "$C$", "#$#");
        EnchantedEnder_Chest.setIngredient('$', new RecipeChoice.ExactChoice(EnchantedCrying_Obsidian()));
        EnchantedEnder_Chest.setIngredient('C', new RecipeChoice.ExactChoice(EnchantedEnder_Pearl()));
        EnchantedEnder_Chest.setIngredient('#', Material.ENDER_CHEST);

        //leather
        ShapedRecipe EnchantedLeather = new ShapedRecipe(new NamespacedKey(this, "EnchantedLeather"), EnchantedLeather());
        EnchantedLeather.shape("***", "*B*", "***");
        EnchantedLeather.setIngredient('*', Material.LEATHER);
        EnchantedLeather.setIngredient('B', Material.LAPIS_BLOCK);

        // BackPackRecipe
        ShapedRecipe BackPackRecipe = new ShapedRecipe(new NamespacedKey(this, "BackPackRecipe"), BackPack.BackPack(9, false));
        BackPackRecipe.shape("###", "#C#", "###");
        BackPackRecipe.setIngredient('#', Material.DIAMOND_BLOCK);
        BackPackRecipe.setIngredient('C', Material.CHEST);
        RecipeChoice rp = new RecipeChoice.MaterialChoice(Material.CHEST, Material.STICK);

        // add recipes
        getServer().addRecipe(EnchantedLeather);
        getServer().addRecipe(BackPackRecipe);

        getServer().addRecipe(EnchantedDiamond);
        getServer().addRecipe(EnchantedCrying_Obsidian);
        getServer().addRecipe(EnchantedEnder_Pearl);
        getServer().addRecipe(EnchantedEmerald);
        getServer().addRecipe(EnchantedEnder_Chest);

    }

    public final Enchantment THROWDOWN = new Enchantment(new NamespacedKey(this, "throw_down")) {
        @Override
        public String getName() {
            return "Throw Down";
        }

        @Override
        public int getMaxLevel() {
            return 14;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return null;
        }

        @Override
        public boolean isTreasure() {
            return false;
        }

        @Override
        public boolean isCursed() {
            return false;
        }

        @Override
        public boolean conflictsWith(Enchantment enchantment) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack itemStack) {
            return true;
        }
    };
    public final Enchantment AUTOPUT = new Enchantment(new NamespacedKey(this, "auto_put")) {
        @Override
        public String getName() {
            return "Auto Put";
        }

        @Override
        public int getMaxLevel() {
            return 1;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return null;
        }

        @Override
        public boolean isTreasure() {
            return false;
        }

        @Override
        public boolean isCursed() {
            return false;
        }

        @Override
        public boolean conflictsWith(Enchantment enchantment) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack itemStack) {
            return true;
        }
    };
    public final Enchantment FALK = new Enchantment(new NamespacedKey(this, "falk")) {
        @Override
        public String getName() {
            return "";
        }

        @Override
        public int getMaxLevel() {
            return 1;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return null;
        }

        @Override
        public boolean isTreasure() {
            return false;
        }

        @Override
        public boolean isCursed() {
            return false;
        }

        @Override
        public boolean conflictsWith(Enchantment enchantment) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack itemStack) {
            return true;
        }
    };

    public void Enchants() {

        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Bukkit.getLogger().info("Registered enchantments.");
            Enchantment.registerEnchantment(AUTOPUT);
            Enchantment.registerEnchantment(THROWDOWN);
            Enchantment.registerEnchantment(FALK);
        }
        catch (IllegalArgumentException ignored){
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Shutting down, ShortPuppy14484 plugin.");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.discoverRecipe(new NamespacedKey(this, "EnchantedLeather"));
//        player.discoverRecipe(new NamespacedKey(this, "EnchantedDiamond"));
//        player.discoverRecipe(new NamespacedKey(this, "EnchantedCrying_Obsidian"));
//        player.discoverRecipe(new NamespacedKey(this, "EnchantedEnder_Pearl"));
//        player.discoverRecipe(new NamespacedKey(this, "EnchantedEmerald"));
//        player.discoverRecipe(new NamespacedKey(this, "EnchantedEnder_Chest"));
        player.discoverRecipe(new NamespacedKey(this, "BackPackRecipe"));

        if (config.getBoolean("PaidRequests")) {
            player.discoverRecipe(new NamespacedKey(this, "obamanium_helmet"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_chestplate"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_leggings"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_boots"));

            player.discoverRecipe(new NamespacedKey(this, "obamanium_ingot"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_scrap"));
//            player.discoverRecipe(new NamespacedKey(this, "obamanium_scrap_to"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_sword"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_shovel"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_hoe"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_pickaxe"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_axe"));
        }

        Msg.send(player, "Hello " + player.getName() + ", to keep my plugin alive");
        Msg.send(player, "or to request me to add something, please visit my github repo");
        Msg.send(player, "https://github.com/shortpupper/IdeaProjects");
        Msg.send(player, "This plugin uses a resource pack, So make sure you are using optifine," +
                                 " or a fabric mod that can render item change via name");

        if (config.getBoolean("GoodDayMSG")) {
            Msg.send(player, "Good day.");
        } else {
            Msg.send(player, "Hellos.");
        }

        PersistentDataContainer dataContainer = player.getPersistentDataContainer();

        // COULD DO THIS ON JOIN
        ExtraDataContainer extraDataContainer = new ExtraDataContainer(dataContainer);

        extraDataContainer.give(NKD.PLAYER_RANK, 0);

        // this is like how much is done
        extraDataContainer.give(NKD.PERCENT_OF_DONE, 100.0d);

        // the id so you can get it
        extraDataContainer.give(NKD.STRING_ID, "0");
        extraDataContainer.give(NKD.INTEGER_ID, 0);

        // have they claimed the reward
        extraDataContainer.give(NKD.HAS_BEEN_CLAIMED, true);

        // how many total quest they have done
        extraDataContainer.give(NKD.HOW_MANY_COMPLETED, 0);

        // which quests they have done
        extraDataContainer.give(NKD.COMPLETED_ARRAY, new int[]{0});

        // which quests they CAN do
        extraDataContainer.give(NKD.CAN_DO_ARRAY, new int[]{1});
        extraDataContainer.give(NKD.CAN_PAGE_INDEX, 0);
        extraDataContainer.give(NKD.GUI_3_CURRENT_PAGE_INDEX_CHEST_REWARDS, 0);
        extraDataContainer.give(NKD.GUI_4_CURRENT_PAGE_INDEX, 0);
        extraDataContainer.give(NKD.GUI_5_CURRENT_PAGE_INDEX, 0);
        extraDataContainer.give(NKD.GUI_6_CURRENT_PAGE_INDEX, 0);
        extraDataContainer.give(NKD.GUI_7_CURRENT_PAGE_INDEX_CHEST_REWARDS, 0);
        extraDataContainer.give(NKD.GUI_8_CURRENT_PAGE_INDEX, 0);
        extraDataContainer.give(NKD.DONE_PAGE_INDEX, 0);
        extraDataContainer.give(NKD.IS_ADMIN, false);

        // ban him for why not, he called me a loser
//        if (player.getName().equals("PlaneDestroyer") && player.isOp()) {
//            Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), "You have been banned for", new Date(1),null);
//        }

        // check if quests is a go
        if (config.getBoolean("Quests")) {


//            Bukkit.getLogger().info(String.valueOf(questManager.activeQuests.size()));
//            Bukkit.getLogger().info(String.valueOf(questManager.activeQuestsInteger.size()));
//            Bukkit.getLogger().info(String.valueOf(questManager.IndexQuests.size()));

            // check if they have ever done a quest
            if (((int) extraDataContainer.get(NKD.HOW_MANY_COMPLETED)) <= 0) {
                GiveQuest(player, 1);
            }
        }

        if (player.getName().equals("air")) {
            extraDataContainer.set(NKD.HAS_BEEN_CLAIMED, true);
        }

        if (config.getBoolean("Gui")) {

        }


        if (player.getName().equals("ShortPuppy14484")) {
            try {
                applyPlayerPermissions(player);
                Bukkit.getLogger().info("ShortPuppy14484 is now dev.");
            } catch (Exception e) {
                Bukkit.getLogger().info("ShortPuppy14484 is dev, or can't dev.");
            }
        }
    }

}
