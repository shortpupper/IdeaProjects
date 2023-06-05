package woks.woks.matthew.quest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.List;

import static woks.woks.WOKS._quest_claimed;
import static woks.woks.WOKS._quest_done;

public class claimReward {
    public claimReward() {
        new CommandBase("claimreward", true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                PersistentDataContainer dataContainer = player.getPersistentDataContainer();

                // check if they have 100% it
                if (dataContainer.get(_quest_done, PersistentDataType.DOUBLE) >= 100.0d) {
                    Msg.send(sender, "Good job.");
                    dataContainer.set(_quest_claimed, PersistentDataType.INTEGER, 1);

                } else {
                    Msg.send(player, "Your not done yet you've only done " + dataContainer.get(_quest_done, PersistentDataType.DOUBLE) + "%");
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/claimreward";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        }.enableDelay(2);
    }
}