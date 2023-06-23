package woks.woks.board.server.util.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.board.server.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class gravitys {
    public gravitys() {
        new CommandBase("gravitys", 2, false) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player  player;
                if (arguments[0].equalsIgnoreCase("has")) {
                    if (arguments[1].equalsIgnoreCase("@s")) {
                        player = (Player) sender;
                        Msg.send(sender, player.getName() + " has " + (player.hasGravity() ? "gravity" : "not gravity"));
                    }
                    else {
                        player = Bukkit.getPlayer(arguments[1]);
                        if (player != null) {
                            Msg.send(sender, player.getName() + " has " + (player.hasGravity() ? "gravity" : "not gravity"));
                        }
                        else {
                            Msg.send(sender, "There is not one with this name");
                            return  true;
                        }
                    }
                    return true;
                }
                boolean happens = arguments[0].equalsIgnoreCase("on");
                if (arguments[1].equalsIgnoreCase("@a")) {
                    for (Player playerOn:
                         Bukkit.getOnlinePlayers()) {
                        playerOn.setGravity(happens);
                    }
                    Msg.send(sender, "Gravity shifted");
                }
                else if (arguments[1].equalsIgnoreCase("@e")) {
                    for (Entity playerOn : EntityUtils.getAllEntities()) {
                        playerOn.setGravity(happens);
                    }
                }
                else if (arguments[1].equalsIgnoreCase("@r")) {
                    Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);
                    Random   random  = new Random();
                    int      x       = random.nextInt(players.length-1);
                    players[x].setGravity(happens);
                    Msg.send(sender, players[x].getName() + " had there gravity shifted");
                }
                else if (arguments[1].equalsIgnoreCase("@s")) {
                    ((Player) sender).setGravity(happens);
                    Msg.send(sender, sender.getName() + " had there gravity shifted");
                }
                else {
                    player = Bukkit.getPlayer(arguments[1]);
                    if (player != null) {
                        player.setGravity(happens);
                        Msg.send(sender, arguments[1] + " had there gravity shifted");
                    }
                    else {
                        Msg.send(sender, "That player can not be found.");
                        return true;
                    }
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/gravitys <on|off|has> <@a|@e|@r|@s|playerName> ";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                if (args.length == 2) {
                    List<String> out = new ArrayList<>();
                    if (!args[0].equalsIgnoreCase("has")) {
                        out.add("@a");
                        out.add("@e");
                        out.add("@r");
                    }
                    if (!(sender instanceof  Player)) {
                        out.add("@s");
                    }
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        out.add(player.getName());
                    }

                    return out;
                }
                else if (args.length == 1) {
                    List<String> out = new ArrayList<>();

                    out.add("on");
                    out.add("off");
                    out.add("has");

                    return out;
                }
                return null;
            }
        }.enableDelay(2);
    }
}
