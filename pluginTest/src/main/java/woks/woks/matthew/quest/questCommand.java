package woks.woks.matthew.quest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;

import java.util.List;

import static woks.woks.WOKS.guiManager;

public class questCommand {
    public questCommand() {
        new CommandBase("quest", 0, 5, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                player.openInventory(guiManager.getGUIInventoryByIntegerID(1));

                return true;
            }

            @Override
            public String getUsage() {
                return "/quest";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        };
    }
}
