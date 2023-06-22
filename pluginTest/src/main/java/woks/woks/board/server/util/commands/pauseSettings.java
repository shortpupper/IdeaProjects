package woks.woks.board.server.util.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.ArrayList;
import java.util.List;

import static woks.woks.WOKS.*;

public class pauseSettings {
    public pauseSettings() {
        new CommandBase("pauseConfig", 1, 4, false) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                if (!player.isOp()) {
                    Msg.send(player, "You need to be op.");
                    return true;
                }

                if (arguments.length == 4) {
                    player = getWhoEver(arguments[3]);
                }

                if (arguments[0].equalsIgnoreCase("Player")) {
                    if (arguments[1].equalsIgnoreCase("dontPauseMe")) {
                        assert player != null;
                        DontEffectMe(player.getUniqueId());
                        Msg.send(player, "You will not be paused.");
                    }
                    else if (arguments[1].equalsIgnoreCase("doPauseMe")) {
                        assert player != null;
                        DoEffectMe(player.getUniqueId());
                        Msg.send(player, "You will not be paused.");
                    }
                    else if (arguments[1].equalsIgnoreCase("emIPaused")) {
                        assert player != null;
                        Msg.send(player, String.valueOf(EmIEffected(player.getUniqueId())));
                    }
                }
                return true;
            }

            @Override
            public @NotNull String getUsage() {
                return "/pauseConfig <Player|Entity> <dontPauseMe> <String|UUID> <player>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                if (args.length == 1) {
                    List<String> out = new ArrayList<>();

                    out.add("Player");
                    out.add("Entity");

                    return out;
                }
                else if (args.length == 2) {
                    List<String> out = new ArrayList<>();

                    if (args[0].equalsIgnoreCase("Player")) {
                        out.add("dontPauseMe");
                        out.add("doPauseMe");
                        out.add("emIPaused");
                    }
                    else if (args[0].equalsIgnoreCase("Entity")) {

                    }

                    return out;
                }
                else if (args.length == 3) {
                    List<String> out = new ArrayList<>();
                    if (args[0].equalsIgnoreCase("Player")) {
                        out.add("playerNames");
                        out.add("playerUUID");
                    }

                    return out;
                }
                else if (args.length == 4) {
                    List<String> out = new ArrayList<>();

                    if (args[0].equalsIgnoreCase("Player")) {
                        if (args[2].equalsIgnoreCase("playerNames")) {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                out.add(player.getName());
                            }
                            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                                out.add(offlinePlayer.getName());
                            }
                        }
                        else if (args[2].equalsIgnoreCase("playerUUID")) {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                out.add(player.getUniqueId().toString());
                            }
                            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                                out.add(offlinePlayer.getUniqueId().toString());
                            }
                        }
                    }

                    return out;
                }
                return null;
            }
        };
    }
}
