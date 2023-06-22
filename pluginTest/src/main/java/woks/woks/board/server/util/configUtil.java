package woks.woks.board.server.util;

import org.bukkit.configuration.file.FileConfiguration;

public class configUtil {
    public static boolean getBoolean(String Path, FileConfiguration config) {
        StringBuilder stringBuilder = new StringBuilder(Path);
        String[]      Paths         = Path.split("\\.");
        boolean       currentValue  = config.getBoolean(Path);
        int           numberOfPaths = Paths.length;
        /*
        see if Path starts with '.this' (-0) or if it's the full path `.isCool` (-1)
         ```yml
         dev:
           this: true
           isCool: true
         ```
         */
        int isThis = ( Path.endsWith(".this") ? 0 : 1);

        for (String path : Paths) {
            stringBuilder.append(path).append(".");
            currentValue &= config.getBoolean(stringBuilder + "this");
        }


        return currentValue;
    }
}