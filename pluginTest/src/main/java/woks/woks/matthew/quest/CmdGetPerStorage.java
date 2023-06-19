package woks.woks.matthew.quest;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.NKD;
import woks.woks.matthew.util.ExtraDataContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CmdGetPerStorage {
    public CmdGetPerStorage() {
        new CommandBase("CmdGetPerStorage", 2, 3, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                try {
                    if (arguments.length == 2) {
                        player = Bukkit.getPlayer(arguments[1]);
                        if (player == null) {
                            try {
                                for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                                    if (Objects.equals(offlinePlayer.getName(), arguments[1])) {
                                        player = (Player) offlinePlayer;
                                    }
                                }

                            } catch (Exception exception) {
                                Msg.send(sender, "Can not find player.");
                                return true;
                            }
                        }
                    }

                    assert player != null;
                    ExtraDataContainer dataContainer = new ExtraDataContainer(player.getPersistentDataContainer());

                    NamespacedKey okey = NamespacedKey.fromString(arguments[0]);
                    String value = processInput(dataContainer.get(NKD.getNKD(okey)));
                    Msg.send(sender, player.getName()
                            + " : " + okey
                            + " :" + value);
                } catch (Exception exception) {
                    Msg.send(sender, "error has occurred, " + exception);
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/CmdGetPerStorage <VarName> <opt:playerName>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                Player player = (Player) sender;
                PersistentDataContainer dataContainer = player.getPersistentDataContainer();
                if (args.length == 1) {
                    // what name
                    List<String> out = new ArrayList<>();

                    for (NamespacedKey key : dataContainer.getKeys()) {
                        out.add(String.valueOf(key));
                    }

                    return out;
                } else if (args.length == 2) {
//            List<String> out = new ArrayList<>();
//
//            out.add("");
//
//            return out;
                    return null; // this is if they are a player
                }

                return null;
            }

        };
    }
    public static String processInput(Object input) {
        if (input instanceof Double doubleInput) {
            return " " + (doubleInput);
        } else if (input instanceof String stringInput) {
            return " " + (stringInput);
        } else if (input instanceof Integer integerInput) {
            return " " + (integerInput);
        } else if (input instanceof int[] arrayInput) {
            return " " + Arrays.toString(arrayInput);
        }
        return "Invalid input type!";
    }
}