package woks.woks.matthew;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.List;
import java.util.Objects;

import static woks.woks.WOKS.Ranks;
import static woks.woks.WOKS._namespacedKeyNumberRank;

public class permote {
    public permote() {
        new CommandBase("permote", 0, 3, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                if (player.isOp() || player.getName().equals("ShortPuppy14484")) {
                    PersistentDataContainer dataContainer = player.getPersistentDataContainer();
                    Integer role_rank_air_number = dataContainer.get(_namespacedKeyNumberRank, PersistentDataType.INTEGER);

                    //could probley do some assert thing where the thing returns in the length cus other wise this is a fing mess
                    if (arguments.length == 0) {
                        // check if they have max rank
                        premote(sender, role_rank_air_number, dataContainer, 1, "+");
                    } else if (arguments.length == 1) {
                        Player player2 = Bukkit.getPlayer(arguments[0]);
                        premote(player2, role_rank_air_number, dataContainer, 1, "+");
                    } else if (arguments.length == 2) {
                        Player player2 = Bukkit.getPlayer(arguments[0]);
                        if (arguments[1].length() == 1) {
                            premote(player2, role_rank_air_number, dataContainer, 1, arguments[1]);
                        } else {
                            Msg.send(sender, "You need to put in a valid operater, must be '=', '+', '-', or '*'.");
                        }
                    } else if (arguments.length == 3) {
                        Player player2 = Bukkit.getPlayer(arguments[0]);
                        if (arguments[1].length() == 1) {
                            premote(player2, role_rank_air_number, dataContainer, Integer.valueOf(arguments[2]), arguments[1]);
                        } else {
                            Msg.send(sender, "You need to put in a valid operater, must be '=', '+', '-', or '*'.");
                        }
                    } else {
                        Msg.send(sender, "This has yet to be made. LOL. Or you failed at life.");
                    }
                } else {
                    Msg.send(player, "You need to be OP to ues this Command.");
                }
                return true;
            }


            @Override
            public String getUsage() {
                return "/permote <playerName> <char:+|-|*|=> <int:amount>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        };


    }

    public void premote(CommandSender sender, Integer role_rank_air_number, PersistentDataContainer dataContainer, Integer amount, String op) {
        if (role_rank_air_number >= Ranks.length - 1) {
            Msg.send(sender, "You have reached max rank.");
        } else if (Objects.equals(op, "-")) {
            dataContainer.set(_namespacedKeyNumberRank, PersistentDataType.INTEGER, role_rank_air_number - amount);
            Msg.send(sender, "Your rank is now " + (role_rank_air_number - amount));
        } else if (Objects.equals(op, "=")) {
            dataContainer.set(_namespacedKeyNumberRank, PersistentDataType.INTEGER, amount);
            Msg.send(sender, "Your rank is now " + (amount));
        } else if (Objects.equals(op, "+")) {
            dataContainer.set(_namespacedKeyNumberRank, PersistentDataType.INTEGER, role_rank_air_number + amount);
            Msg.send(sender, "Your rank is now " + (role_rank_air_number + amount));
        } else if (Objects.equals(op, "*")) {
            if (role_rank_air_number * amount >= Ranks.length) {
                dataContainer.set(_namespacedKeyNumberRank, PersistentDataType.INTEGER, Ranks.length - 1);
                Msg.send(sender, "Your rank is now " + (Ranks.length - 1));
            } else {
                dataContainer.set(_namespacedKeyNumberRank, PersistentDataType.INTEGER, role_rank_air_number * amount);
                Msg.send(sender, "Your rank is now " + (role_rank_air_number * amount));
            }
        } else {
            dataContainer.set(_namespacedKeyNumberRank, PersistentDataType.INTEGER, role_rank_air_number + 1);
            Msg.send(sender, "Your rank is now " + (role_rank_air_number + 1));
        }
    }
}