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
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import woks.woks.commands.*;
import woks.woks.handlers.*;
import woks.woks.items.*;

import java.lang.reflect.Field;

import static woks.woks.items.EnchantedCrying_Obsidian.EnchantedCrying_Obsidian;
import static woks.woks.items.EnchantedDiamond.EnchantedDiamond;
import static woks.woks.items.EnchantedEmerald.EnchantedEmerald;
import static woks.woks.items.EnchantedEnder_Chest.EnchantedEnder_Chest;
import static woks.woks.items.EnchantedEnder_Pearl.EnchantedEnder_Pearl;
import static woks.woks.items.EnchantedLeather.EnchantedLeather;

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

        Recipes();
        Enchants();
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void Recipes() {
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
        Msg.send(player, "Hello " + player.getName() + ", to keep my plugin alive");
        Msg.send(player, "or to request me to add something, please visit my github repo");
        Msg.send(player, "https://github.com/shortpupper/IdeaProjects");
        if (config.getBoolean("GoodDayMSG")) {
            Msg.send(player, "Good day.");
        } else {
            Msg.send(player, "Hellos.");
        }
    }
}
