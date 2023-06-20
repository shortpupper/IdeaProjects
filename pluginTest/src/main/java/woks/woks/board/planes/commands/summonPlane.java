package woks.woks.board.planes.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import woks.woks.CommandBase;
import woks.woks.NKD;
import woks.woks.matthew.util.ExtraDataContainer;

import java.util.ArrayList;
import java.util.List;

public class summonPlane {
    public summonPlane() {
        new CommandBase("summonPlane", 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                Location playerLocation = player.getLocation();
                World    world          = playerLocation.getWorld();

                if (world != null) {
                    Location boatLocation = playerLocation.clone().add(0, 1, 0); // Adjust the boat's position as needed
                    if (arguments[0].equalsIgnoreCase("Aerora_Phantomjet")) {
                        makeBoat(arguments[0].replace("_", " "), world, player, boatLocation, 1.0d);
                    }
                    else if (arguments[0].equalsIgnoreCase("Azure_Skyrider")) {
                        makeBoat(arguments[0].replace("_", " "), world, player, boatLocation, 1.05d);
                    }
                    else if (arguments[0].equalsIgnoreCase("Gunfire_Spectacle")) {
                        makeMinecart(arguments[0].replace("_", " "), world, player, boatLocation, 1.35d);
                    }
                }
                return true;
            }

            @Override
            public @NotNull String getUsage() {
                return "/summonPlane <name>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                if (args.length == 1) {
                    List<String> out = new ArrayList<>();

                    out.add("Aerora_Phantomjet");
                    out.add("Azure_Skyrider");
                    out.add("Gunfire_Spectacle");

                    return out;
                }
                else if (args.length == 2) {
                    List<String> out = new ArrayList<>();

                    out.add("SwiftSky Voyager");
                    out.add("AeroFalcon Jetstream");
                    out.add("Horizon Hopper");
                    out.add("Nimbus Skyblade");
                    out.add("Starglide Specter");
                    out.add("Solaris Seraph");
                    out.add("ThunderBreeze Zephyr");
                    out.add("AirSprint Celestial");
                    out.add("Aurora Wingstrider");
                    out.add("StormSurge Tempest");
                    out.add("Vortex Swiftwind");
                    out.add("Dreamweaver Aviator");
                    out.add("Skywhisper Mirage");
                    out.add("Nebula Skylancer");
                    out.add("AeroFlare Stratos");
                    out.add("Horizon Firebrand");
                    out.add("SolarFlite Nimbus");
                    out.add("ThunderBolt Drifter");
                    out.add("AirSail Starlight");
                    out.add("Stargazer Falconer");
                    out.add("Jetstream Skydancer");
                    out.add("Radiant Dreamer");
                    out.add("SilverWing Serenade");

                    out.add("ZephyrGlide Soarer");
                    out.add("AeroNova Comet");
                    out.add("Celestial Blaze");
                    out.add("Whispering Windsong");
                    out.add("StormSail Skylark");
                    out.add("Moonstruck Voyager");
                    out.add("SolarEclipse Valkyrie");
                    out.add("SkySerenade Aurora");
                    out.add("Nightshade Nightingale");
                    out.add("ThunderGust Pegasus");
                    out.add("Starstrike Winged");
                    out.add("Mistral Moonrider");
                    out.add("Aurora Skydancer");
                    out.add("SolarFlare Streak");
                    out.add("ZephyrBreeze Twilight");
                    out.add("Thunderbolt Eclipse");

                    return out;
                }
                return null;
            }
        };
    }
    private void makeBoat(String Name, World world, Player player, Location boatLocation, Double speed) {
        Boat boat = world.spawn(boatLocation, Boat.class);
        boat.addPassenger(player); // Make the player ride the boat

        // other
        boat.setCustomName(Name);
        boat.setGravity(false);
        boat.setInvulnerable(true);
        boat.setGlowing(true);
        ExtraDataContainer dataContainer =  new ExtraDataContainer(boat.getPersistentDataContainer());
        dataContainer.set(NKD.SPEED, speed);

    }
    private void makeMinecart(String Name, World world, Player player, Location boatLocation, Double speed) {
        Minecart minecart = world.spawn(boatLocation, Minecart.class);
        minecart.addPassenger(player); // Make the player ride the boat

        // other
        minecart.setCustomName(Name);
        minecart.setGravity(false);
        minecart.setInvulnerable(true);
        minecart.setGlowing(true);
        ExtraDataContainer dataContainer =  new ExtraDataContainer(minecart.getPersistentDataContainer());
        dataContainer.set(NKD.SPEED, speed);
    }
}
