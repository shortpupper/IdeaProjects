package woks.woks.commands;

import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import woks.woks.CommandBase;
import woks.woks.Msg;
import woks.woks.WOKS;

import java.util.ArrayList;
import java.util.List;

public class TestingPlugin {
    public TestingPlugin() {
        new CommandBase("TestingPlugin", 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                if (player.getName().equals("ShortPuppy14484")) {
                    if ("effect".equals(arguments[0])) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2100000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2100000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 2100000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 2100000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 2100000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 2100000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 2100000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 2100000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 2100000000, 10, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2100000000, 10, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2100000000, 255, true));
                    } else if ("no".equals(arguments[0])) {
                        ((Player) sender).setInvulnerable(true);
                    } else if ("yes".equals(arguments[0])) {
                        ((Player) sender).setInvulnerable(false);
                    } else if ("arrow".equals(arguments[0])) {
                        WOKS.AFC = !WOKS.AFC;
                        Msg.send(player, String.valueOf(WOKS.AFC));
                    } else if ("allies".equals(arguments[0])) {
                        if (arguments.length >= 2) {
                            NBTEntity playerNBT = new NBTEntity(player);
                            List<String> allies = playerNBT.getStringList("allies");
                            if (allies != null) {
                                if ((arguments.length == 2 && (arguments[1].equalsIgnoreCase("get")))) {
                                    Msg.send(player, allies.toString());
                                } else if (arguments.length >= 3) {
                                    if (arguments[1].equalsIgnoreCase("add")) {
                                        if (!(allies.contains(arguments[2]))) {
                                            allies.add(arguments[2]);
                                            Msg.send(player, "Added player " + arguments[2] + " to your list.");
                                        } else {
                                            Msg.send(player, "You all ready have this person on the list.");
                                            return true;
                                        }
                                    } else if (arguments[1].equalsIgnoreCase("remove")) {
                                        allies.remove(arguments[2]);
                                        Msg.send(player, "Removed player " + arguments[2] + " from your list.");
                                    } else {
                                        Msg.send(player, "Are you sure you typed it right.");
                                        return true;
                                    }
                                    playerNBT.setObject("allies", allies);
                                } else {
                                    Msg.send(player, "Fail.");
                                }
                            }
                        } else {
                            Msg.send(player, "You need to have 3 args.");
                            Msg.send(player, "/TestingPlugin <command> <add|remove|get> <playerName>");
                        }
                    }
                } else {
                    Msg.send(player, "You are not allowed to use this command, please contact you admin.");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/TestingPlugin <str:thing> <str:thing> <thing> <thing>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        };
    }
}