package woks.woks.matthew.quest;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static woks.woks.WOKS.parseStringNameSpaceKey;
import static woks.woks.WOKS.stringToPersistentDataType;

public class CmdGetPerStorage {
    public CmdGetPerStorage() {
        new CommandBase("CmdGetPerStorage", 2, 3, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                if (arguments.length == 3) {
                    player = Bukkit.getPlayer(arguments[2]);
                    if (player == null) {
                        try {
                            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                                if (Objects.equals(offlinePlayer.getName(), arguments[2])) {
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
                PersistentDataContainer dataContainer = player.getPersistentDataContainer();


                String dataTypeString = arguments[1].toLowerCase(); // Convert to lowercase
                PersistentDataType<?, ?> dataType = stringToPersistentDataType(dataTypeString);


                // Determine the data type based on the lowercase string value

                Object data = dataContainer.get(parseStringNameSpaceKey(arguments[0]), dataType);

                Msg.send(sender, String.valueOf(data));


                return true;
            }

            @Override
            public String getUsage() {
                return "/CmdGetPerStorage <VarName> <VarType> <opt:playerName>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                Player player = (Player) sender;
                PersistentDataContainer dataContainer = player.getPersistentDataContainer();
                if (args.length == 1) {
                    // what name
                    List<String> out = new ArrayList<>();

                    for (NamespacedKey key : dataContainer.getKeys()) {
                        out.add(key.getKey());
                    }

                    return out;
                } else if (args.length == 2) {
                    // PersistentDataType.T
                    List<String> out = new ArrayList<>();

                    out.add("Integer");
                    out.add("String");
                    out.add("Integer_Array");
                    out.add("Double");

                    out.add("Byte");
                    out.add("Byte_Array");
                    out.add("Float");
                    out.add("Long");
                    out.add("Long_Array");
                    out.add("Short");
                    out.add("Tag_Container");
                    out.add("Tag_Container_Array");

                    return out;
                } else if (args.length == 3) {
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

}