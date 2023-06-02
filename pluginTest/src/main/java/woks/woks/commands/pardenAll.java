package woks.woks.commands;

import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import woks.woks.CommandBase;

import java.util.List;
import java.util.Objects;

public class pardenAll {
    public pardenAll() {
        new CommandBase("pardonall", false) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                BanList x = Bukkit.getBanList(BanList.Type.NAME);

                for (BanEntry entry : x.getBanEntries()) {
                    if (Boolean.parseBoolean(arguments[0].toLowerCase())) {
                        Bukkit.getBanList(BanList.Type.NAME).pardon(entry.getTarget());
                    } else if ( !(Boolean.parseBoolean(arguments[0].toLowerCase())) && !(Objects.requireNonNull(Bukkit.getPlayer(entry.getTarget())).getScoreboardTags().contains("fullBan")) ) {
                        Bukkit.getBanList(BanList.Type.NAME).pardon(entry.getTarget());
                    }
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/pardonall <opt:fullBan:Boolean>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        };
    }
}
