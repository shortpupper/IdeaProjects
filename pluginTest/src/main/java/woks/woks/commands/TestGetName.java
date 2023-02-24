package woks.woks.commands;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import woks.woks.CommandBase;
import woks.woks.Msg;

public class TestGetName {
    public TestGetName() {
        new CommandBase("GetExp", true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                ItemStack item = player.getInventory().getItemInMainHand();
                NBTItem nbtItem = new NBTItem(item);
                Msg.send(sender, String.valueOf(nbtItem.getInteger("Exp")));
                return true;
            }

            @Override
            public String getUsage() {
                return "/GetExp";
            }
        };
    }
}
