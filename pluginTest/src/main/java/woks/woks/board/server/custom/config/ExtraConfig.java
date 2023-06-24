package woks.woks.board.server.custom.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

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

    protected boolean getBoolean2(@NotNull String path) {
        Object def = getDefault(path);
        return getBoolean(path, (def instanceof Boolean) ? (Boolean) def : false);
    }

    @Override
    public boolean getBoolean(@NotNull String path, boolean def) {
        Object val = get(path, def);
        return (val instanceof Boolean) ? (Boolean) val : def;
    }

    @Override
    public boolean getBoolean(@NotNull String path) {
        String[] paths = path.split("\\.");
        if (paths.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            // FIXME change that to something like true
            boolean currentValue = getBoolean2(path) || getBoolean2(path + ".this");

            if (path.endsWith(".this")) {
                paths = Arrays.copyOf(paths, paths.length - 1);
            }

            for (String p : paths) {
                stringBuilder.append(p).append(".");
                currentValue &= getBoolean2(stringBuilder + "this");
            }
            return currentValue;
        } else {
            return getBoolean2(path);
        }
    }

}
