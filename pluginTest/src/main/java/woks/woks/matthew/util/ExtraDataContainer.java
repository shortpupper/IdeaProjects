package woks.woks.matthew.util;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import woks.woks.NKD;

import java.util.Set;

import static woks.woks.utils.ArrayUtils.removeFirstInstance;


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

        if (value instanceof Boolean) {
            dataContainer.set(key, type, value);
        }

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

    public void rmVAufAry(NKD nkd, Integer value) {
        int[] array = get(nkd); // Explicitly provide the type argument
        int[] ar = removeFirstInstance(array, value);
        set(nkd, ar);
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
        if (value instanceof Integer) {
            return (Z) Integer.valueOf(((Number) value2).intValue() + ((Number) value).intValue());
        } else if (value instanceof Double) {
            return (Z) Double.valueOf(((Number) value2).doubleValue() + ((Number) value).doubleValue());
        } else if (value instanceof Long) {
            return (Z) Long.valueOf(((Number) value2).longValue() + ((Number) value).longValue());
        } else if (value instanceof Boolean) {
            return (Z) Boolean.valueOf((Boolean) value2 || (Boolean) value);
        } else if (value instanceof String) {
            return (Z) ((String) value2).concat((String) value);
        } else {
            return null; // Or handle the unsupported type accordingly
        }
    }
}
