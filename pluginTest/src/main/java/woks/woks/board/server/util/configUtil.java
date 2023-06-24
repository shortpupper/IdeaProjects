package woks.woks.board.server.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import woks.woks.WOKS;
import woks.woks.utils.ArrayUtils;

public class configUtil {
    public static boolean getBoolean(String Path) {
        return getBoolean(Path, WOKS.config);
    }
    public static boolean getBoolean(String Path, @NotNull FileConfiguration config) {
        StringBuilder stringBuilder = new StringBuilder();
        String[]      Paths         = Path.split("\\.");
        boolean       currentValue  = config.getBoolean(Path);

        if (Path.endsWith(".this")) {
            Paths = ArrayUtils.removeLastElement(Paths);
        }

        for (String path : Paths) {
            stringBuilder.append(path).append(".");
            currentValue &= config.getBoolean(stringBuilder + "this");
        }

        return currentValue;
    }
}