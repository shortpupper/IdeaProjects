package woks.woks.board.server.util.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import woks.woks.CommandBase;
import woks.woks.Msg;

import java.util.ArrayList;
import java.util.List;

import static woks.woks.board.server.util.EntityUtils.pauseCurrentEntities;
import static woks.woks.board.server.util.EntityUtils.unpauseCurrentEntities;

public class pause {
    public static boolean isPaused = false;
    public pause() {
        new CommandBase("pause", false) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                isPaused = !isPaused;
                Msg.send(player, String.valueOf(isPaused));
                if (isPaused) {
                    pauseCurrentEntities();
                } else {
                    unpauseCurrentEntities();
                }
                return true;
            }

            @Override
            public @NotNull String getUsage() {
                return "/pause";
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                if (args.length == 1) {
                    List<String> out = new ArrayList<>();

                    out.add("");

                    return out;
                }
                return null;
            }
        }.enableDelay(2);
    }
}
