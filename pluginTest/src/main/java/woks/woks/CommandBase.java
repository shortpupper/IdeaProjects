package woks.woks;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class CommandBase extends BukkitCommand implements CommandExecutor, TabExecutor, TabCompleter {
    private final int minAguments;
    private final int maxArguments;
    private final boolean playerOnly;
    private List<String> delayedPlayers = null;
    private int delay = 0;




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

        this.minAguments = minArguments;
        this.maxArguments = maxArguments;
        this.playerOnly = playerOnly;

        CommandMap commandMap = getCommandMap();
        if (commandMap != null) {
            commandMap.register(command, this);
        }
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

    public boolean execute(CommandSender sender, String alias, String[] arguments) {
        if (arguments.length < minAguments || (arguments.length > maxArguments && maxArguments != -1)) {
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
            Bukkit.getScheduler().scheduleSyncDelayedTask(WOKS.getInstance(), () -> {
                delayedPlayers.remove((player.getName()));
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args ) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            // Add tab completions for the first argument here
            completions.add("cats");
            return completions;
        } else if (args.length == 2) {
            List<String> completions = new ArrayList<>();
            // Add tab completions for the second argument here
            completions.add("cats");
            return completions;
        }
        return null;
    }
}
