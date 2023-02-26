package woks.woks.commands;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import woks.woks.BackPackGUI;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.UserCheckPaying;

import java.util.List;


// deprecated
public class AccessBackPack {
    public AccessBackPack() {
        new CommandBase("AccessBackPack", true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                Object Things = UserCheckPaying.CheckPaying();
                if (Things instanceof Boolean) {
                    Msg.send(player, "Can't get players.");
                    return true;
                } else if (Things instanceof List<?>) {
                    List<String> names = (List<String>) Things;
                    if (names.contains(player.getName())) {
                        if (player.getInventory().getItemInMainHand().getType() == Material.CHEST) {
                            NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
                            if (nbtItem.hasNBTData()) {
                                // if they leave this all goes to hell
                                new BackPackGUI(nbtItem, ((Player) sender).getPlayer());
                                nbtItem.setBoolean("Using", true);
                            } else {
                                Msg.send(sender, "This is just a BLACK_SHULKER_BOX");
                            }
                        } else {
                            Msg.send(sender, "You need to have a backpack/chest in you hand.");
                        }
                    } else {
                        Msg.send(player, "Sorry you need to pay ether 1.87 Euro, 36.78 Mexican Peso, 2.70 Canadian Dollar, 2 United States Dollar, 13.74 Chinese Yuan, 268.65 Japanese Yen, 2591.68 South Korean won, 1.85 Swiss Franc, 2.67 Singapore Dollar, 165.53 Indian Rupee, 148.00 Russian Ruble, 0.000082 BTC, 22.94 DOGE, 0.0012 ETH, or 13628.39 BCN to gain access to this command.");
                        Msg.send(player, "To contact me for payment email me at chartledev@gmail.com, or discord: 'Malicious Code#3614'.");
                    }
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/AccessBackPack";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        };
    }
}
