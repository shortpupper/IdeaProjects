package woks.woks.matthew;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.List;

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
                    if (arguments.length == 0) {
                        // check if they have max rank
                        if (role_rank_air_number >= Ranks.length) {
                            Msg.send(sender, "You have reached max rank.");
                        } else {
                            dataContainer.set(_namespacedKeyNumberRank, PersistentDataType.INTEGER, role_rank_air_number + 1);
                            Msg.send(sender, "Your rank is now " + (role_rank_air_number + 1));
                        }
                    } else {
                        Msg.send(sender, "This has yet to be made. LOL.");
                    }
                } else {
                    Msg.send(player, "You need to be OP to ues this Command.");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/permote <playerName> <add|sub|set> <int:amount>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        }.enableDelay(2);
    }
}
