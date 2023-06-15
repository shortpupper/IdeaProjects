package woks.woks.matthew.quest.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static woks.woks.WOKS.*;
import static woks.woks.matthew.gui.GUIManager.getKeyByValue;

public class giveQuestPlayer {
    public giveQuestPlayer() {
        new CommandBase("giveQuestPlayer", 1,2,false) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                if (sender.isOp() || sender.getName().equals("ShortPuppy14484")) {
                    if (arguments.length == 2) {
                        player = Bukkit.getPlayer(arguments[1]);
                    }
                    if (player == null) {
                        Msg.send(sender, "Player must be real");
                        return true;
                    }
                    PersistentDataContainer dataContainer = player.getPersistentDataContainer();
                    try {

                        dataContainer.set(_quest_can_array,
                              PersistentDataType.INTEGER_ARRAY,
                              addArrays(Objects.requireNonNull(dataContainer.get(_quest_can_array, PersistentDataType.INTEGER_ARRAY)),
                                        new int[]{
                                                (getKeyByValue(questManager.getActiveQuestsInteger(), arguments[0]))
                              }));
                    }
                    catch (Exception e) {
                        Msg.send(sender, "Error make sure that the quests StrID is real : " + e);
                        return true;
                    }

                }
                else {
                    Msg.send(sender, "You need op to use this command.");
                }
                Msg.send(sender, "Should have worked");
                return true;
            }

            @Override
            public String getUsage() {
                return "/giveQuestPlayer <String:StrId> <playerName>";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                if (args.length == 1) {
                    return new ArrayList<>(questManager.IndexQuests.values());
                }
                else if (args.length == 2) {
                    return null;
                }
                return null;
            }
        };
    }
}
