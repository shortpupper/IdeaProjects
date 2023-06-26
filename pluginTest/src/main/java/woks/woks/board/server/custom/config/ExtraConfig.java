package woks.woks.board.server.custom.config;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExtraConfig extends YamlConfiguration {
    private final FileConfiguration config;

    public ExtraConfig(FileConfiguration config) {
        this.config = config;
    }

    public ExtraConfig(File file) {
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }
    @Override
    public Set<String> getKeys(boolean deep) {
        return config.getKeys(deep);
    }

    @Override
    public ConfigurationSection getParent() {
        return config.getParent();
    }

    @Override
    public Configuration getRoot() {
        return config.getRoot();
    }
    @Override
    public @NotNull ExtraConfigOptions options() {
        return new ExtraConfigOptions(this);
    }

    public static class ExtraConfigOptions extends YamlConfigurationOptions {
        public ExtraConfigOptions(ExtraConfig config) {
            super(config);
        }
    }


    @Override
    public ConfigurationSection createSection(String path) {
        return config.createSection(path);
    }

    @Override
    public ConfigurationSection createSection(String path, Map<?, ?> map) {
        return config.createSection(path, map);
    }

    @Override
    public ConfigurationSection getConfigurationSection(String path) {
        return config.getConfigurationSection(path);
    }

    @Override
    public String getString(String path) {
        return config.getString(path);
    }

    @Override
    public String getString(String path, String def) {
        return config.getString(path, def);
    }
    @Override
    public double getDouble(String path) {
        return config.getDouble(path);
    }

    @Override
    public double getDouble(String path, double def) {
        return config.getDouble(path, def);
    }

    @Override
    public long getLong(String path) {
        return config.getLong(path);
    }

    @Override
    public long getLong(String path, long def) {
        return config.getLong(path, def);
    }

    @Override
    public List<?> getList(String path) {
        return config.getList(path);
    }


    @Override
    public List<?> getList(String path, List<?> def) {
        return config.getList(path, def);
    }

    @Override
    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }

    @Override
    public List<Integer> getIntegerList(String path) {
        return config.getIntegerList(path);
    }

    @Override
    public List<Boolean> getBooleanList(String path) {
        return config.getBooleanList(path);
    }
    @Override
    public int getInt(String path) {
        return config.getInt(path);
    }


    @Override
    public int getInt(String path, int def) {
        return config.getInt(path, def);
    }
    @Override
    public void save(@NotNull File file) throws IOException {
        config.save(file.getPath());
    }

    @Override
    public void load(@NotNull File file) throws IOException, InvalidConfigurationException {
        config.load(file);
    }

    @Override
    public @NotNull String saveToString() {
        return config.saveToString();
    }

    @Override
    public void loadFromString(@NotNull String contents) throws InvalidConfigurationException {
        config.loadFromString(contents);
    }


    @Override
    public boolean contains(@NotNull String path) {
        return config.contains(path);
    }

    @Override
    public boolean isSet(@NotNull String path) {
        return config.isSet(path);
    }

    @Override
    public void addDefault(@NotNull String path, Object value) {
        config.addDefault(path, value);
    }

    @Override
    public void set(@NotNull String path, Object value) {
        config.set(path, value);
    }

    @Override
    public Object get(@NotNull String path) {
        return config.get(path);
    }

    @Override
    public Object get(@NotNull String path, Object def) {
        return config.get(path, def);
    }

    private boolean getBoolean2(@NotNull String path) {
        Object def = getDefault(path);
        return getBoolean(path, (def instanceof Boolean) ? (Boolean) def : false);
    }
    public boolean getBoolean(@NotNull String path, boolean def) {
        Object val = get(path, def);
        return (val instanceof Boolean) ? (Boolean) val : def;
    }

    @Override
    public boolean getBoolean(@NotNull String path) {
        String[] paths = path.split("\\.");
        if (paths.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            boolean currentValue = getBoolean2(path) || getBoolean2(path + ".this");

            paths = Arrays.copyOf(paths, paths.length - 1);

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
