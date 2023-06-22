package woks.woks.board.planes.handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import woks.woks.NKD;
import woks.woks.WOKS;
import woks.woks.matthew.util.ExtraDataContainer;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class flying implements Listener {

    private final Map<UUID, BukkitRunnable> actionBarTasks;
    public flying(WOKS plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        actionBarTasks = new HashMap<>();
    }


    @EventHandler
    public void onVehicleMove(VehicleMoveEvent event) {
        Entity vehicle = event.getVehicle();

        if (vehicle instanceof Boat boat) {
            if (boat.getCustomName() != null && boat.getCustomName().equalsIgnoreCase("Aerora Phantomjet")) {
                vehicleHandler(boat);
            }
            else if (boat.getCustomName() != null && boat.getCustomName().equalsIgnoreCase("Azure Skyrider")) {
                vehicleHandler(boat);
            }
        }
        else if (vehicle instanceof Minecart boat) {
            if (boat.getCustomName() != null && boat.getCustomName().equalsIgnoreCase("Gunfire Spectacle")) {
                vehicleHandler(boat);
            }
        }
    }

    private void vehicleHandler(Vehicle boat) {
        for (Entity passenger : boat.getPassengers()) {
            if (passenger instanceof Player player) {
                float yaw = player.getLocation().getYaw();
                float pitch = player.getLocation().getPitch();
                ExtraDataContainer dataContainer = new ExtraDataContainer(boat.getPersistentDataContainer());
                Double speed = dataContainer.get(NKD.SPEED);
                speed = speed != null ? speed : 0.5d;
                Vector velocity = player.getLocation().getDirection().multiply(speed); // Adjust the velocity as needed
                velocity.setY(Math.sin(Math.toRadians(-pitch))); // Adjust the boat's height based on player's pitch
                boat.setVelocity(velocity);

                // Set boat's rotation using Quaternion
                boat.setRotation(yaw-90, pitch);

                updateTitle(player, velocity.length());
            }
        }
    }

    private void updateTitle(Player player, double speed) {
        DecimalFormat decimalFormat = new DecimalFormat("000.000000000");
        String speedFormatted = decimalFormat.format(speed);

        BukkitRunnable actionBarTask = actionBarTasks.get(player.getUniqueId());
        if (actionBarTask != null) {
            actionBarTask.cancel();
        }

        actionBarTask = new BukkitRunnable() {
            @Override
            public void run() {
                player.sendTitle("", "", 0, 20, 0);
                player.sendTitle(ChatColor.GRAY + "Speed:", ChatColor.YELLOW + speedFormatted, 0, 40, 0);
            }
        };
        actionBarTask.runTaskLater(WOKS.getInstance(), 1L);
        actionBarTasks.put(player.getUniqueId(), actionBarTask);
    }
}