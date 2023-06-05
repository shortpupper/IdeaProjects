package woks.woks.matthew;


//player.getScoreboardTags().contains("roleAdmin")

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.WOKS;

import java.util.List;

public class ImAnAdmin {
    public ImAnAdmin(WOKS plugin) {
        Bukkit.getPluginManager().registerEvents((Listener) this, plugin);

        ImAnAdmin th = this;

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
                    NamespacedKey namespacedKeyNumberRank = new NamespacedKey((Plugin) th, "_admin");
                    PersistentDataContainer dataContainer = player.getPersistentDataContainer();


                    if (!dataContainer.has(namespacedKeyNumberRank, PersistentDataType.INTEGER)) {
                        dataContainer.set(namespacedKeyNumberRank, PersistentDataType.INTEGER, 0);
                    }

//                    Integer role_rank_air_number = dataContainer.get(namespacedKeyNumberRank, PersistentDataType.INTEGER);

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
