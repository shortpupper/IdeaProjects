package woks.woks.handlers;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import woks.woks.WOKS;

public class AutoAim implements Listener {
    private static final double MAX_RANGE = 64.0; // maximum range to target entities
    private static final String DOGMAN_NAME = "ShortPuppy14484";
    private static final double ARROW_SPEED = 2.5; // arrow speed in blocks per tick



    public AutoAim(WOKS plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof AbstractArrow arrow) {
            if (arrow.getShooter() instanceof Player player) {
                if (player.getName().equals(DOGMAN_NAME) && player.getGameMode().name().contains("SURVIVAL")) {
                    // If the player is DogMan and has auto-aim enabled, find the closest target and set the arrow's target
                    if (WOKS.AFC) {
                        Entity target = getClosestEntity(player, arrow.getLocation());
                        if(target != null) {
                            arrow.setGravity(false);
                            Location targetLoc = target.getLocation();
//                            targetLoc.setY(targetLoc.getY()+1);
                            Vector direction = targetLoc.subtract(arrow.getLocation()).toVector().normalize();
                            if(arrow.getLocation().getY() == player.getLocation().getY()) {
                                direction = direction.add(new Vector(0, 1, 0)).normalize();
                            }
                            arrow.setVelocity(direction.multiply(ARROW_SPEED));
                            Location arrowLoc = arrow.getLocation();
                            Vector difference = targetLoc.subtract(arrowLoc).toVector();
                            arrowLoc.setDirection(difference);
                            arrow.teleport(arrowLoc);
                            arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                            arrow.setBounce(false);
                        } else if(arrow.getLocation().getY() < player.getLocation().getY() + 25) {
                            Vector up = new Vector(0, 1, 0);
                            arrow.setVelocity(arrow.getVelocity().add(up.multiply(0.05)));
                        }
                    }
                }
            }
        }
    }

    private static Entity getClosestEntity(Player player, Location location) {
        Entity closestEntity = null;
        double closestDistanceSquared = Double.MAX_VALUE;
        for(Entity entity : player.getWorld().getEntities()) {
            if(entity instanceof Player otherPlayer) {
                if(otherPlayer.equals(player) || !otherPlayer.getGameMode().equals(player.getGameMode())) {
                    continue;
                }
            }
            if(entity instanceof Arrow otherArrow) {
                if(otherArrow.getShooter() != null && otherArrow.getShooter().equals(player)) {
                    continue;
                }
            }
            if ((entity.getType() == EntityType.EXPERIENCE_ORB || entity.getType() == EntityType.DROPPED_ITEM
                    || entity.getType() == EntityType.ITEM_FRAME || entity.getType() == EntityType.GLOW_ITEM_FRAME
                    || entity.getType() == EntityType.ARROW || entity.getType() == EntityType.AREA_EFFECT_CLOUD
                    || entity.getType() == EntityType.ARMOR_STAND || entity.getType() == EntityType.EGG
                    || entity.getType() == EntityType.BOAT || entity.getType() == EntityType.FALLING_BLOCK
                    || entity.getType() == EntityType.LEASH_HITCH || entity.getType() == EntityType.THROWN_EXP_BOTTLE
                    || entity.getType() == EntityType.WITHER_SKULL || entity.getType() == EntityType.SPLASH_POTION
                    || entity.getType() == EntityType.SPECTRAL_ARROW || entity.getType() == EntityType.SNOWBALL
                    || entity.getType() == EntityType.PAINTING
                    || entity.getType() == EntityType.MINECART)) {
                continue;
            }
            double distanceSquared = entity.getLocation().distanceSquared(location);
            if(distanceSquared < closestDistanceSquared) {
                closestDistanceSquared = distanceSquared;
                closestEntity = entity;
            }
        }
        return closestEntity;
    }

    private float getYawFromVector(Vector vector) {
        double x = vector.getX();
        double z = vector.getZ();
        double theta = Math.atan2(-x, z);
        float yaw = (float) Math.toDegrees(theta);
        if (yaw < 0) {
            yaw += 360.0f;
        }
        return yaw;
    }
}