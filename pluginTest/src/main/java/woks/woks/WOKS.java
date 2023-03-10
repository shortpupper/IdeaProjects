package woks.woks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import woks.woks.commands.*;
import woks.woks.handlers.*;
import woks.woks.items.*;
//import woks.woks.items.PRQ.Obamanium.Obamanium_ChestPlate;
import woks.woks.items.PRQ.Obamanium.*;

import java.lang.reflect.Field;

import static woks.woks.items.EnchantedCrying_Obsidian.EnchantedCrying_Obsidian;
import static woks.woks.items.EnchantedDiamond.EnchantedDiamond;
import static woks.woks.items.EnchantedEmerald.EnchantedEmerald;
import static woks.woks.items.EnchantedEnder_Chest.EnchantedEnder_Chest;
import static woks.woks.items.EnchantedEnder_Pearl.EnchantedEnder_Pearl;
import static woks.woks.items.EnchantedLeather.EnchantedLeather;
import static woks.woks.items.PRQ.Obamanium.Obamanium_Ingot.Obamanium_Ingot;

public final class WOKS extends JavaPlugin implements Listener {
    private static WOKS instance;
    public static boolean AFC = false;
    FileConfiguration config = this.getConfig();

    public static WOKS getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        Bukkit.getLogger().info("Starting, ShortPuppy14484 plugin.");

        config.addDefault("PlayerWalkPath", true);
        config.addDefault("GoodDayMSG", true);
        config.addDefault("UnsafeEnchanting", true);
        config.addDefault("PaidRequests", true);

        config.options().copyDefaults(true);
        saveConfig();

//        new Feed();
        new SaveEXP();
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
        new EnchantedEnder_Pearl();
        new EnchantedDiamond();
        new EnchantedEmerald();
        new EnchantedCrying_Obsidian();
        new EnchantedEnder_Chest();
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

        Recipes();
        Enchants();
        getServer().getPluginManager().registerEvents(this, this);
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
        //leather
        ShapedRecipe EnchantedLeather = new ShapedRecipe(new NamespacedKey(this, "EnchantedLeather"), EnchantedLeather());
        EnchantedLeather.shape("***", "*B*", "***");
        EnchantedLeather.setIngredient('*', Material.LEATHER);
        EnchantedLeather.setIngredient('B', Material.LAPIS_BLOCK);

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

        // BackPack9
        ShapedRecipe BackPack9 = new ShapedRecipe(new NamespacedKey(this, "BackPack9"), BackPack.BackPack(9, false));
        BackPack9.shape("!@#", "$C$", "#@!");
        BackPack9.setIngredient('C', new RecipeChoice.ExactChoice(EnchantedEnder_Chest()));
        BackPack9.setIngredient('!', new RecipeChoice.ExactChoice(EnchantedEmerald()));
        BackPack9.setIngredient('$', new RecipeChoice.ExactChoice(EnchantedDiamond()));
        BackPack9.setIngredient('#', Material.DIAMOND_BLOCK);
        BackPack9.setIngredient('@', Material.CHEST);

        // BackPack18
        ShapedRecipe BackPack18 = new ShapedRecipe(new NamespacedKey(this, "BackPack18"), BackPack.BackPack(18, true));
        BackPack18.shape("!@#", "$C$", "#@!");
        BackPack18.setIngredient('C', new RecipeChoice.ExactChoice(BackPack.BackPack(9, true)));
        BackPack18.setIngredient('!', Material.LEATHER);
        BackPack18.setIngredient('$', Material.ENDER_CHEST);
        BackPack18.setIngredient('#', new RecipeChoice.ExactChoice(EnchantedLeather()));
        BackPack18.setIngredient('@', new RecipeChoice.ExactChoice(EnchantedCrying_Obsidian()));

        // BackPack27
        ShapedRecipe BackPack27 = new ShapedRecipe(new NamespacedKey(this, "BackPack27"), BackPack.BackPack(27, true));
        BackPack27.shape("###", "$C$", "###");
        BackPack27.setIngredient('C', new RecipeChoice.ExactChoice(BackPack.BackPack(18, true)));
        BackPack27.setIngredient('#', new RecipeChoice.ExactChoice(EnchantedLeather()));
        BackPack27.setIngredient('$', new RecipeChoice.ExactChoice(EnchantedCrying_Obsidian()));

        // BackPack36
        ShapedRecipe BackPack36 = new ShapedRecipe(new NamespacedKey(this, "BackPack36"), BackPack.BackPack(36, true));
        BackPack36.shape("##%", "$C$", "%##");
        BackPack36.setIngredient('C', new RecipeChoice.ExactChoice(BackPack.BackPack(27, true)));
        BackPack36.setIngredient('#', new RecipeChoice.ExactChoice(EnchantedLeather()));
        BackPack36.setIngredient('$', new RecipeChoice.ExactChoice(EnchantedCrying_Obsidian()));
        BackPack36.setIngredient('%', new RecipeChoice.ExactChoice(EnchantedDiamond()));

        // BackPack45
        ShapedRecipe BackPack45 = new ShapedRecipe(new NamespacedKey(this, "BackPack45"), BackPack.BackPack(45, true));
        BackPack45.shape("@&%", "#C#", "%&@");
        BackPack45.setIngredient('C', new RecipeChoice.ExactChoice(BackPack.BackPack(36, true)));
        BackPack45.setIngredient('#', new RecipeChoice.ExactChoice(EnchantedLeather()));
        BackPack45.setIngredient('@', new RecipeChoice.ExactChoice(EnchantedEmerald()));
        BackPack45.setIngredient('%', new RecipeChoice.ExactChoice(EnchantedDiamond()));
        BackPack45.setIngredient('&', new RecipeChoice.ExactChoice(EnchantedEnder_Pearl()));

        // BackPack54
        ShapedRecipe BackPack54 = new ShapedRecipe(new NamespacedKey(this, "BackPack54"), BackPack.BackPack(54, true));
        BackPack54.shape("#@%", "$C$", "%@#");
        BackPack54.setIngredient('C', new RecipeChoice.ExactChoice(BackPack.BackPack(45, true)));
        BackPack54.setIngredient('#', new RecipeChoice.ExactChoice(EnchantedLeather()));
        BackPack54.setIngredient('$', new RecipeChoice.ExactChoice(EnchantedEnder_Chest()));
        BackPack54.setIngredient('%', new RecipeChoice.ExactChoice(EnchantedEnder_Pearl()));
        BackPack54.setIngredient('@', new RecipeChoice.ExactChoice(EnchantedDiamond()));


        // add recipes
        getServer().addRecipe(EnchantedLeather);
        getServer().addRecipe(EnchantedDiamond);
        getServer().addRecipe(EnchantedCrying_Obsidian);
        getServer().addRecipe(EnchantedEnder_Pearl);
        getServer().addRecipe(EnchantedEmerald);
        getServer().addRecipe(EnchantedEnder_Chest);
        getServer().addRecipe(BackPack9);
        getServer().addRecipe(BackPack18);
        getServer().addRecipe(BackPack27);
        getServer().addRecipe(BackPack36);
        getServer().addRecipe(BackPack45);
        getServer().addRecipe(BackPack54);
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
        player.discoverRecipe(new NamespacedKey(this, "EnchantedDiamond"));
        player.discoverRecipe(new NamespacedKey(this, "EnchantedCrying_Obsidian"));
        player.discoverRecipe(new NamespacedKey(this, "EnchantedEnder_Pearl"));
        player.discoverRecipe(new NamespacedKey(this, "EnchantedEmerald"));
        player.discoverRecipe(new NamespacedKey(this, "EnchantedEnder_Chest"));
        player.discoverRecipe(new NamespacedKey(this, "BackPack9"));
        player.discoverRecipe(new NamespacedKey(this, "BackPack18"));
        player.discoverRecipe(new NamespacedKey(this, "BackPack27"));
        player.discoverRecipe(new NamespacedKey(this, "BackPack36"));
        player.discoverRecipe(new NamespacedKey(this, "BackPack45"));
        player.discoverRecipe(new NamespacedKey(this, "BackPack54"));

        if (config.getBoolean("PaidRequests")) {
            player.discoverRecipe(new NamespacedKey(this, "obamanium_ingot"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_sword"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_chestplate"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_leggings"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_boots"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_helmet"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_shovel"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_scrap"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_scrap_to"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_hoe"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_pickaxe"));
            player.discoverRecipe(new NamespacedKey(this, "obamanium_axe"));
        }

        Msg.send(player, "Hello " + player.getName() + ", to keep my plugin alive");
        Msg.send(player, "or to request me to add something, please visit my github repo");
        Msg.send(player, "https://github.com/shortpupper/IdeaProjects");
        Msg.send(player, "This plugin uses a resource pack for changes.");
        if (config.getBoolean("GoodDayMSG")) {
            Msg.send(player, "Good day.");
        } else {
            Msg.send(player, "Hellos.");
        }
    }
}
