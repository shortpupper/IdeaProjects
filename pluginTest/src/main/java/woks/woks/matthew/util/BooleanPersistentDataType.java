package woks.woks.matthew.util;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class BooleanPersistentDataType implements PersistentDataType<Byte, Boolean> {

    @Override
    public @NotNull Class<Byte> getPrimitiveType() {
        return Byte.class;
    }

    @Override
    public @NotNull Class<Boolean> getComplexType() {
        return Boolean.class;
    }

    @Override
    public @NotNull Byte toPrimitive(@NotNull Boolean bool, @NotNull PersistentDataAdapterContext context) {
        return bool ? (byte) 1 : (byte) 0;
    }

    @Override
    public @NotNull Boolean fromPrimitive(@NotNull Byte byteValue, @NotNull PersistentDataAdapterContext context) {
        return byteValue != 0;
    }
}