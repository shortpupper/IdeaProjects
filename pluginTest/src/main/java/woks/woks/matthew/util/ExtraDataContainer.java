package woks.woks.matthew.util;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ExtraDataContainer implements PersistentDataContainer {

    private final PersistentDataContainer dataContainer;

    public ExtraDataContainer(PersistentDataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    @Override
    public <T, Z> void set(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
        dataContainer.set(key, type, value);
    }

    @Override
    public <T, Z> boolean has(NamespacedKey key, PersistentDataType<T, Z> type) {
        return dataContainer.has(key, type);
    }

    @Override
    public <T, Z> Z get(NamespacedKey key, PersistentDataType<T, Z> type) {
        return dataContainer.get(key, type);
    }

    @Override
    public <T, Z> Z getOrDefault(NamespacedKey key, PersistentDataType<T, Z> type, Z defaultValue) {
        return dataContainer.getOrDefault(key, type, defaultValue);
    }

    @NotNull
    @Override
    public Set<NamespacedKey> getKeys() {
        return dataContainer.getKeys();
    }

    @Override
    public void remove(@NotNull NamespacedKey key) {
        dataContainer.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return dataContainer.isEmpty();
    }

    @NotNull
    @Override
    public PersistentDataAdapterContext getAdapterContext() {
        return dataContainer.getAdapterContext();
    }

    public <T, Z> void give(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
        if (!has(key, type)) {
            set(key, type, value);
        }
    }
}
