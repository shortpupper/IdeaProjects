package woks.woks.matthew.quest;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.List;
import java.util.Objects;

import static woks.woks.WOKS.*;

public class ResetQuestCommand {
    public ResetQuestCommand() {
        new CommandBase("resetquest", 0, 5, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                if (player.isOp() || player.getName().equals("ShortPuppy14484")) {
                    PersistentDataContainer dataContainer = player.getPersistentDataContainer();
                    if (arguments.length == 0) {
//                    dataContainer.set();
                    dataContainer.set(_quest_completed, PersistentDataType.INTEGER, 1);
                    Msg.send(player, "Done.");
                    } else {
                        if (Objects.equals(arguments[0], "percent") || arguments[0].equals("0")) {
                            dataContainer.set(_quest_percent_done, PersistentDataType.DOUBLE, 100.0d);
                            Msg.send(player, "DOnse.");
                        } else if (arguments[0].equals("1")) {
                            dataContainer.set(_namespacedKeyNumberRank, PersistentDataType.INTEGER, 0);
                            dataContainer.set(_quest_percent_done, PersistentDataType.DOUBLE, 0.0d);
                            dataContainer.set(_quest_id, PersistentDataType.STRING, "0");
                            dataContainer.set(_quest_claimed, PersistentDataType.INTEGER, 0);
                            dataContainer.set(_quest_completed, PersistentDataType.INTEGER, 0);
                            dataContainer.set(_quest_completed_array, PersistentDataType.INTEGER_ARRAY, new int[]{});
                            dataContainer.set(_quest_id_integer, PersistentDataType.INTEGER, 0);
                            Msg.send(player, "DOnse. ok");
                        } else if (arguments[0].equals("2")) {
                            dataContainer.set(_quest_id, PersistentDataType.STRING, questManager.getQuestStringIdByIntegerId(1));
                            dataContainer.set(_quest_percent_done, PersistentDataType.DOUBLE, 100.0d);
                            dataContainer.set(_quest_claimed, PersistentDataType.INTEGER, 0);
                            Msg.send(player, "DOnse. dfsa");
                        } else if (arguments[0].equals("3")) {
                            Bukkit.getLogger().info(convertHashMapToString(questManager.activeQuests));
                            Msg.send(player, "Linda");
                        } else if (arguments[0].equals("4")) {
                            Bukkit.getLogger().info(String.valueOf((questManager.activeQuests.get(1))));
                            Msg.send(player, "David");
                        } else if (arguments[0].equals("5")) {
                            if (arguments.length >= 3) {
                                if (arguments[1].equals("0")) {
                                    Msg.send(player, String.valueOf(questManager.activeQuests.get(arguments[2])));
                                    Msg.send(player, String.valueOf(questManager.activeQuests.get(arguments[2]).getQuestId()));
                                    Msg.send(player, "Steve");
                                } else if (arguments[1].equals("1")) {
                                    Msg.send(player, String.valueOf(questManager.activeQuestsInteger.get(Integer.valueOf(arguments[2]))));
                                    Msg.send(player, "Alex");
                                } else if (arguments[1].equals("2")) {
                                    Msg.send(player, String.valueOf(questManager.IndexQuests.get(Integer.valueOf(arguments[2]))));
                                    Msg.send(player, "Gorden");
                                } else {
                                    Msg.send(player, "Failed");
                                }

                                Msg.send(player, "Carlos");
                            } else if (arguments.length == 2) {
                                Msg.send(player, "The third arg is key");
                            } else {
                                Msg.send(player, "The second arg is the HashMap out of three, [0:activeQuests|1:activeQuestsInteger|2:IndexQuests]");
                            }
                        }
                    }

                } else {
                    Msg.send(player, "You need OP to use this command.");
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/resetquest";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        };
    }
}
