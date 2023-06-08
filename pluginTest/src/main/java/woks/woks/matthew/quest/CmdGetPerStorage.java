package woks.woks.matthew.quest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import woks.woks.CommandBase;

import java.util.ArrayList;
import java.util.List;

public class CmdGetPerStorage implements TabCompleter {
    public CmdGetPerStorage() {
        new CommandBase("CmdGetPerStorage", 1, 4, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                PersistentDataContainer dataContainer = player.getPersistentDataContainer();



                return true;
            }

            @Override
            public String getUsage() {
                return "/CmdGetPerStorage";
            }


        };
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            // what name
            List<String> out = new ArrayList<>();

            out.add("a");

            return out;
        } else if (args.length == 2) {
            // PersistentDataType.T
            List<String> out = new ArrayList<>();

            out.add("INTEGER");
            out.add("STRING");
            out.add("INTEGER_ARRAY");
            out.add("DOUBLE");

            out.add("BYTE");
            out.add("BYTE_ARRAY");
            out.add("FLOAT");
            out.add("LONG");
            out.add("LONG_ARRAY");
            out.add("SHORT");
            out.add("TAG_CONTAINER");
            out.add("TAG_CONTAINER_ARRAY");

            return out;
        } else if (args.length == 3) {
            List<String> out = new ArrayList<>();

            out.add("");

            return out;
        } else if (args.length == 4) {
            return null; // this is if they are a player
        }

        return null;
    }
}