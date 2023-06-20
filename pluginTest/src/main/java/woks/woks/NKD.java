package woks.woks;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

import static woks.woks.WOKS.booleanT;

//NamespacedKeyDefine.java
public enum NKD {
    STRING_ID("_quest_id", PersistentDataType.STRING),
    PLAYER_RANK("_namespacedKeyNumberRank", PersistentDataType.INTEGER),
    INTEGER_ID("_quest_id_integer", PersistentDataType.INTEGER),
    PERCENT_OF_DONE("_quest_percent_done", PersistentDataType.DOUBLE),
    HAS_BEEN_CLAIMED("_quest_claimed", booleanT),
    HOW_MANY_COMPLETED("_quest_completed", PersistentDataType.INTEGER),
    COMPLETED_ARRAY("_quest_completed_array", PersistentDataType.INTEGER_ARRAY),
    CAN_DO_ARRAY("_quest_can_array", PersistentDataType.INTEGER_ARRAY),
    CAN_PAGE_INDEX("_quest_can_array_index", PersistentDataType.INTEGER),
    GUI_3_CURRENT_PAGE_INDEX_CHEST_REWARDS("_quest_gui_currentPage6_index", PersistentDataType.INTEGER),
    GUI_4_CURRENT_PAGE_INDEX("_quest_gui_currentPage2_index", PersistentDataType.INTEGER),
    GUI_5_CURRENT_PAGE_INDEX("_quest_gui_currentPage3_index", PersistentDataType.INTEGER),
    GUI_6_CURRENT_PAGE_INDEX("_quest_gui_currentPage5_index", PersistentDataType.INTEGER),
    GUI_7_CURRENT_PAGE_INDEX_CHEST_REWARDS("_quest_gui_currentPage4_index", PersistentDataType.INTEGER),
    GUI_8_CURRENT_PAGE_INDEX("_quest_gui_currentPage1_index", PersistentDataType.INTEGER),
    DONE_PAGE_INDEX("_quest_done_array_index", PersistentDataType.INTEGER),
    JSON_SMELLS("_jsonSmells", PersistentDataType.INTEGER),
    SPEED("_speed_at", PersistentDataType.DOUBLE),
    IS_ADMIN("_admin", booleanT);
    // Add more keys here as needed


    private final NamespacedKey namespacedKey;
    private final PersistentDataType<?, ?> type;

    NKD(String key, PersistentDataType<?, ?> type) {
        this.namespacedKey = new NamespacedKey(WOKS.getInstance(), key);
        this.type = type;
    }

    public NamespacedKey getK() {
        return namespacedKey;
    }

    public PersistentDataType<?, ?> getT() {
        return type;
    }

    private static final Map<NamespacedKey, NKD> ENUM_MAP = new HashMap<>();

    static {
        for (NKD nkd : values()) {
            ENUM_MAP.put(nkd.getK(), nkd);
        }
    }


    // Rest of the enum definition

    public static NKD getNKD(NamespacedKey key) {
        return ENUM_MAP.get(key);
    }
}