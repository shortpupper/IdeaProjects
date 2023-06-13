package woks.woks.matthew.persistantStorageManager;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.WOKS;

import java.util.HashMap;
import java.util.Map;

public class StorageManager implements Listener {
    private Map<NamespacedKey, PersistentDataType> StorageMain;
    private Map<Integer, NamespacedKey> StorageSubMain;

    public StorageManager() {
        WOKS plugin = WOKS.getInstance();
        this.StorageMain = new HashMap<>();
        this.StorageSubMain = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void registerNewStorage(NamespacedKey namespacedKey, PersistentDataType dataType) {
        StorageMain.put(namespacedKey, dataType);
        StorageSubMain.put(StorageMain.size() + 1, namespacedKey);
    }

    public void givePlayerStorageIfNotHave(Player player, NamespacedKey namespacedKey, Object value) {
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();
        PersistentDataType ok = getPersistentDataTypeWithNamespacedKey(namespacedKey);
        if (!dataContainer.has(namespacedKey, ok)) {
             dataContainer.set(namespacedKey, ok, value);
        }
    }


    public NamespacedKey getNamespacedKeyFromId(Integer id) {
        return StorageSubMain.get(id);
    }


    public PersistentDataType getPersistentDataTypeWithNamespacedKey(NamespacedKey namespacedKey) {
        return StorageMain.get(namespacedKey);
    }

    public PersistentDataType<? extends Object, ? extends Object> getPersistentDataTypeWithId(Integer id) {
        return getPersistentDataTypeWithNamespacedKey(getNamespacedKeyFromId(id));
    }

}
