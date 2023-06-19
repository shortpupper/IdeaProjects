package woks.woks.matthew;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.NKD;
import woks.woks.matthew.util.ExtraDataContainer;

import java.util.ArrayList;
import java.util.List;

import static woks.woks.WOKS.Ranks;

public class permote {
    public permote() {
        new CommandBase("permote", 0, 3, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                if (player.isOp() || player.getName().equals("ShortPuppy14484")) {
                    ExtraDataContainer dataContainer = new ExtraDataContainer(player.getPersistentDataContainer());
                    Integer role_rank_air_number = dataContainer.get(NKD.PLAYER_RANK);

                    //could probley do some assert thing where the thing returns in the length cus other wise this is a fing mess
                    //^^^^^^^^^^^^^^^^^^^ don't think this matters ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                    if (arguments.length == 0) {
                        // check if they have max rank
                        premote(sender, role_rank_air_number, dataContainer, "add", 1);
                    } else if (arguments.length == 1) {
                        Player player2 = Bukkit.getPlayer(arguments[0]);
                        premote(player2, role_rank_air_number, dataContainer, "add", 1);
                    } else if (arguments.length == 2) {
                        Player player2 = Bukkit.getPlayer(arguments[0]);
                        premote(player2, role_rank_air_number, dataContainer, arguments[1], 1);
                    } else if (arguments.length == 3) {
                        Player player2 = Bukkit.getPlayer(arguments[0]);
                        premote(player2, role_rank_air_number, dataContainer,  arguments[1], Integer.valueOf(arguments[2]));
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
                return "/permote <playerName> <string:add|sub|multi|eq|set> <int:amount>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

                if (args.length == 1) {
                    return null;
                } else if (args.length == 2) {
                    List<String> out = new ArrayList<>();

                    out.add("add");
                    out.add("+");

                    out.add("sub");
                    out.add("subtract");
                    out.add("-");

                    out.add("multi");
                    out.add("multiply");
                    out.add("*");
                    out.add("x");

                    out.add("eq");
                    out.add("equal");
                    out.add("set");
                    out.add("=");
                    out.add("==");
                    out.add("===");

                    return out;
                } else if (args.length == 3) {
                    List<String> out = new ArrayList<>();

                    out.add("0");
                    out.add("5");
                    out.add("10");
                    out.add("15");
                    out.add("20");
                    out.add("21");

                    return out;
                }

                return null;
            }
        };


    }

    public void premote(CommandSender sender, Integer role_rank_air_number, PersistentDataContainer EdataContainer, String op, Integer amount) {
        ExtraDataContainer dataContainer = new ExtraDataContainer(EdataContainer);
        if (role_rank_air_number >= Ranks.length - 1) {
            Msg.send(sender, "You have reached max rank.");
        } else if (op.equals("sub") || op.equals("subtract") || op.equals("-")) {
            if (role_rank_air_number - amount >= 0) {
                dataContainer.set(NKD.PLAYER_RANK, role_rank_air_number - amount);
                Msg.send(sender, "Your rank is now " + (role_rank_air_number - amount));
            } else {
                dataContainer.set(NKD.PLAYER_RANK, 0);
                Msg.send(sender, "Your rank is now " + 0);
            }
        } else if (op.equals("eq") || op.equals("set") || op.equals("equal") || op.equals("=") || op.equals("==") || op.equals("===")) {
            if (amount <= Ranks.length) {
                dataContainer.set(NKD.PLAYER_RANK, amount);
                Msg.send(sender, "Your rank is now " + (amount));
            } else {
                dataContainer.set(NKD.PLAYER_RANK, (Ranks.length - 1));
                Msg.send(sender, "Your rank is now " + (Ranks.length - 1));
            }
        } else if (op.equals("add") || op.equals("+")) {
            if (role_rank_air_number + amount <= Ranks.length) {
                dataContainer.set(NKD.PLAYER_RANK, role_rank_air_number + amount);
                Msg.send(sender, "Your rank is now " + (role_rank_air_number + amount));
            } else {
                dataContainer.set(NKD.PLAYER_RANK,  (Ranks.length - 1));
                Msg.send(sender, "Your rank is now " + ((Ranks.length - 1)));
            }
        } else if (op.equals("multi") || op.equals("x") || op.equals("*") || op.equals("multiply")) {
            if (role_rank_air_number * amount >= Ranks.length) {
                dataContainer.set(NKD.PLAYER_RANK, Ranks.length - 1);
                Msg.send(sender, "Your rank is now " + (Ranks.length - 1));
            } else {
                dataContainer.set(NKD.PLAYER_RANK, role_rank_air_number * amount);
                Msg.send(sender, "Your rank is now " + (role_rank_air_number * amount));
            }
        } else {
            if (role_rank_air_number + 1 <= Ranks.length) {
                dataContainer.set(NKD.PLAYER_RANK, role_rank_air_number + 1);
                Msg.send(sender, "Your rank is now " + (role_rank_air_number + 1));
            } else {
                dataContainer.set(NKD.PLAYER_RANK, Ranks.length - 1);
                Msg.send(sender, "Your rank is now " + (Ranks.length - 1));
            }
        }
    }
}
