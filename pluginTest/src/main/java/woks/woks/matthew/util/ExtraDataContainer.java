package woks.woks.matthew.util;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.NKD;
import woks.woks.utils.ArrayUtils;

import java.util.Set;

public class ExtraDataContainer implements PersistentDataContainer {

    private final PersistentDataContainer dataContainer;

    public ExtraDataContainer(PersistentDataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    @Override
    public <T, Z> void set(NamespacedKey key,  PersistentDataType<T, Z> type,  Z value) {
        dataContainer.set(key, type, value);
    }

    public <Z> void set(NKD nkd, Z value) {
        NamespacedKey key = nkd.getK();
        PersistentDataType<NamespacedKey, Z> type = (PersistentDataType<NamespacedKey, Z>) nkd.getT();
        dataContainer.set(key, type, value);
    }

    @Override
    public <T, Z> boolean has( NamespacedKey key,  PersistentDataType<T, Z> type) {
        return dataContainer.has(key, type);
    }
    public <Z> boolean has( NKD nkd) {
        NamespacedKey key = nkd.getK();
        PersistentDataType<NamespacedKey, Z> type = (PersistentDataType<NamespacedKey, Z>) nkd.getT();
        return dataContainer.has(key, type);
    }

    @Override
    public <T, Z> Z get( NamespacedKey key,  PersistentDataType<T, Z> type) {
        return dataContainer.get(key, type);
    }
    public <Z> Z get( NKD nkd) {
        NamespacedKey key = nkd.getK();
        PersistentDataType<NamespacedKey, Z> type = (PersistentDataType<NamespacedKey, Z>) nkd.getT();
        return dataContainer.get(key, type);
    }

    @Override
    public <T, Z>  Z getOrDefault(NamespacedKey key,  PersistentDataType<T, Z> type,  Z defaultValue) {
        return dataContainer.getOrDefault(key, type, defaultValue);
    }


    @Override
    public Set<NamespacedKey> getKeys() {
        return dataContainer.getKeys();
    }

    @Override
    public void remove(NamespacedKey key) {
        dataContainer.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return dataContainer.isEmpty();
    }


    @Override
    public PersistentDataAdapterContext getAdapterContext() {
        return dataContainer.getAdapterContext();
    }

    public <Z> void give(NKD nkd, Z value) {
        if (!has(nkd)) {
            set(nkd, value);
        }
    }

    public <Z> void rmVAufAry(NKD nkd, Z value) {
        Z[] array = get(nkd);
        set(nkd, ArrayUtils.removeFirstInstance(array, value));
    }

    public <Z> Z add(NKD nkd, Z value) {
        Z value2 = get(nkd);

        if (value2 instanceof Number && value instanceof Number) {
            switch (value2.getClass().getSimpleName()) {
                case "Integer", "Double", "Long" -> value2 = castValue(value2, value);
                default -> {
                }
                // Do nothing
            }
        } else if (value2 instanceof Boolean && value instanceof Boolean) {
            value2 = castValue(value2, value);
        } else if (value2 instanceof String && value instanceof String) {
            value2 = castValue(value2, value);
        } else if (value2 instanceof int[] arr1 && value instanceof int[] arr2) {

            int[] mergedArray = new int[arr1.length + arr2.length];
            System.arraycopy(arr1, 0, mergedArray, 0, arr1.length);
            System.arraycopy(arr2, 0, mergedArray, arr1.length, arr2.length);

            value2 = (Z) mergedArray;
        }


        set(nkd, value2);
        return value2;
    }
    @SuppressWarnings("unchecked")
    private <Z> Z castValue(Z value2, Z value) {
        return switch (value) {
            case Integer ignored -> (Z) Integer.valueOf(((Number) value2).intValue() + ((Number) value).intValue());
            case Double ignored1 -> (Z) Double.valueOf(((Number) value2).doubleValue() + ((Number) value).doubleValue());
            case Long ignored2 -> (Z) Long.valueOf(((Number) value2).longValue() + ((Number) value).longValue());
            case Boolean aBoolean -> (Z) Boolean.valueOf((Boolean) value2 || aBoolean);
            case String s -> (Z) ((String) value2).concat(s);
            case null, default -> null;  // Or handle the unsupported type accordingly
        };
    }
}
