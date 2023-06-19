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

import static woks.woks.WOKS.removeValueFromArray;

@Deprecated
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



    public void removeIdInIntegerArray(PersistentDataContainer dataContainer, NamespacedKey namespacedKey, Integer value) {
        // get array to modify
        try {
            int[] integers = new int[0];
            try {
                integers = (int[]) getValueWithNamespacedKey(dataContainer, namespacedKey); //dataContainer.get(_quest_can_array, PersistentDataType.INTEGER_ARRAY);//
            } catch (Exception e) {
                Bukkit.getLogger().severe("[WOKS][v2023.6.13][StorageManger#removeIdInIntegerArray] Must be int array : " + e);
            }
            assert integers != null;

            dataContainer.set(namespacedKey, getPersistentDataTypeWithNamespacedKey(namespacedKey), removeValueFromArray(integers, value));
        } catch (Exception e) {
            Bukkit.getLogger().severe("[WOKS][v2023.6.13][StorageManger#removeIdInIntegerArray] Value not found : " + e);
        }
    }
    public void removeIdInIntegerArray(Player player, NamespacedKey namespacedKey, Integer value) {
        removeIdInIntegerArray(player.getPersistentDataContainer(), namespacedKey, value);
    }



    public void givePlayerStorageIfNotHave(PersistentDataContainer dataContainer, NamespacedKey namespacedKey, Object value) {
//        PersistentDataContainer dataContainer = player.getPersistentDataContainer();
        PersistentDataType ok = getPersistentDataTypeWithNamespacedKey(namespacedKey);
        if (!dataContainer.has(namespacedKey, ok)) {
             dataContainer.set(namespacedKey, ok, value);
        }
    }
    public void givePlayerStorageIfNotHave(Player player, NamespacedKey namespacedKey, Object value) {
        givePlayerStorageIfNotHave(player.getPersistentDataContainer(), namespacedKey, value);
    }

    public Object getValueWithNamespacedKey(Player player, NamespacedKey namespacedKey) {
        return getValueWithNamespacedKey(player.getPersistentDataContainer(), namespacedKey);
    }
    public Object getValueWithNamespacedKey(PersistentDataContainer dataContainer, NamespacedKey namespacedKey) {
        return dataContainer.get(namespacedKey, getPersistentDataTypeWithNamespacedKey(namespacedKey));
    }

    public void setValueWithNamespacedKey(Player player, NamespacedKey namespacedKey, Object value) {
        setValueWithNamespacedKey(player.getPersistentDataContainer(), namespacedKey, value);
    }
    public void setValueWithNamespacedKey(PersistentDataContainer dataContainer, NamespacedKey namespacedKey, Object value) {
        dataContainer.set(namespacedKey, getPersistentDataTypeWithNamespacedKey(namespacedKey), value);
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
