package woks.woks.matthew.quest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.List;

public class QuestHelpCommand {
    public QuestHelpCommand() {
        new CommandBase("QuestHelp", false) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                Msg.send(player, "To claim a quest you can do /claimquest or it aliases [qclaim, qreward].");
                Msg.send(player, "To get a new quest do /nextquest or it aliases [nq, nquest, nextq, qnext, questnext].");

                return true;
            }

            @Override
            public String getUsage() {
                return "/QuestHelp";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        };
    }
}
