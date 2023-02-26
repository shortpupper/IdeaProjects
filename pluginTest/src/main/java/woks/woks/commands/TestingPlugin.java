package woks.woks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.List;

public class TestingPlugin {
    public TestingPlugin() {
        new CommandBase("TestingPlugin", 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                if (player.getName().equals("ShortPuppy14484")) {
                    if ("effect".equals(arguments[0])) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 1000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 1000000, 255, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1000000, 10, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1000000, 10, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 255, true));
                    } else if ("no".equals(arguments[0])) {
                        ((Player) sender).setInvulnerable(true);
                    } else if ("yes".equals(arguments[0])) {
                        ((Player) sender).setInvulnerable(false);
                    }
                } else {
                    Msg.send(player, "You are not allowed to use this command, please contact you admin.");
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/TestingPlugin <str:thing> <thing> <thing> <thing>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        };
    }
}