package woks.woks.matthew.util;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public class BooleanPersistentDataType implements PersistentDataType<Byte, Boolean> {

    @Override
    public Class<Byte> getPrimitiveType() {
        return Byte.class;
    }

    @Override
    public Class<Boolean> getComplexType() {
        return Boolean.class;
    }

    @Override
    public Boolean fromPrimitive(Byte byteValue, PersistentDataAdapterContext context) {
        return byteValue != 0;
    }

    @Override
    public Byte toPrimitive(Boolean bool, PersistentDataAdapterContext context) {
        return (byte) (bool ? 1 : 0);
    }
}
