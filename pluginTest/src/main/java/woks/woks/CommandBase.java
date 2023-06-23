package woks.woks;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO Make TabCompleter
public abstract class CommandBase extends BukkitCommand implements CommandExecutor, TabExecutor, TabCompleter, Listener {
    private final int minArguments;
    private final int maxArguments;
    private final boolean playerOnly;
    private final String names;
    private List<String> delayedPlayers = null;
    private int delay = 0;
    private final WOKS plugin;

    public CommandBase(String command) {
        this(command, 0);
    }

    public CommandBase(String command, boolean playerOnly) {
        this(command, 0, playerOnly);
    }

    public CommandBase(String command, int requiredArguments) {
        this(command, requiredArguments, requiredArguments);
    }

    public CommandBase(String command, int minArguments, int maxArguments) {
        this(command, minArguments, maxArguments, false);
    }

    public CommandBase(String command, int requiredArguments, boolean playerOnly) {
        this(command, requiredArguments, requiredArguments, playerOnly);
    }

    public CommandBase(String command, int minArguments, int maxArguments, boolean playerOnly) {
        super(command);

        this.minArguments = minArguments;
        this.maxArguments = maxArguments;
        this.playerOnly = playerOnly;
        this.names = command;

        CommandMap commandMap = getCommandMap();
        if (commandMap != null) {
            commandMap.register(command, this);
        }

        this.plugin = WOKS.getInstance();

        try {
            Objects.requireNonNull(plugin.getCommand(command)).setExecutor(this);
            Objects.requireNonNull(plugin.getCommand(command)).setTabCompleter(this);
            Bukkit.getLogger().info("[woks] " + command +  " Succeed.");
        } catch (Exception exception) {
            Bukkit.getLogger().warning("[woks] " + command +  " Failed.");
        }
        try {
            Bukkit.getPluginManager().registerEvents(this, plugin);
        } catch (Exception exception) {
            Bukkit.getLogger().info("[woks] THIS broke.");
        }
    }

    public Command getThis() {
        return this;
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

    public CommandBase enableDelay(int delay) {
        this.delay = delay;
        this.delayedPlayers = new ArrayList<>();
        return this;
    }

    public void removeDelay(Player player) {
        this.delayedPlayers.remove(player.getName());
    }

    public void sendUsage(CommandSender sender) {
        Msg.send(sender, getUsage());
    }

    public String getName() {
        return names;
    }

    public boolean execute(CommandSender sender, String alias, String[] arguments) {
        if (arguments.length < minArguments || (arguments.length > maxArguments && maxArguments != -1)) {
            sendUsage(sender);
            return true;
        }

        if (playerOnly && !(sender instanceof Player)) {
            Msg.send(sender, "&cOnly players can use this command.");
            return true;
        }

        String permission = getPermission();
        if (permission != null && !sender.hasPermission(permission)) {
            Msg.send(sender, "&cYou do not have permission to use this command.");
            return true;
        }

        if (delayedPlayers != null && sender instanceof Player) {
            Player player = (Player) sender;
            if (delayedPlayers.contains(player.getName())) {
                Msg.send(player, "&cPlease wait before using this command again.");
                return true;
            }

            delayedPlayers.add(player.getName());
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                delayedPlayers.remove(player.getName());
            }, 20L * delay);
        }

        if (!onCommand(sender, arguments)) {
            sendUsage(sender);
        }

        return true;
    }

    public boolean onCommand(CommandSender sender, Command command, String alias, String[] arguments) {
        return this.onCommand(sender, arguments);
    }

    public abstract boolean onCommand(CommandSender sender, String[] arguments);

    public abstract String getUsage();

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> out = new ArrayList<>();

            out.add("CommandBaseIgnore");

            return out;
        }
        return null; //this is a little confusing but just put it there
    }
}
