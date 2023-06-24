package woks.woks.board.server.custom.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import woks.woks.utils.ArrayUtils;

public class ExtraConfig extends FileConfiguration {
    private final FileConfiguration config;

    public ExtraConfig(FileConfiguration config) {
        this.config = config;
    }
    @NotNull
    @Override
    public String saveToString() {
        return config.saveToString();
    }
    @Override
    public void loadFromString(@NotNull String contents) throws InvalidConfigurationException {
        config.loadFromString(contents);
    }

    public boolean getBoolean2(@NotNull String path) {
        Object def = getDefault(path);
        return getBoolean(path, (def instanceof Boolean) ? (Boolean) def : false);
    }

    @Override
    public boolean getBoolean(@NotNull String path, boolean def) {
        Object val = get(path, def);
        return (val instanceof Boolean) ? (Boolean) val : def;
    }

    @Override
    public boolean getBoolean(@NotNull String Path) {
        String[] Paths = Path.split("\\.");
        if (Paths.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            // FIXME change that to like true
            boolean       currentValue  = getBoolean2(Path) || getBoolean2(Path + ".this");

            if (Path.endsWith(".this")) {
                Paths = ArrayUtils.removeLastElement(Paths);
            }

            for (String path : Paths) {
                stringBuilder.append(path).append(".");
                currentValue &= config.getBoolean(stringBuilder + "this");
            }
            return currentValue;
        } else {
            return getBoolean2(Path);
        }
    }
}
