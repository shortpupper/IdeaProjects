package woks.woks.board.server.custom.config;

import woks.woks.WOKS;

public enum ConfigKey {
    GUI("Gui", true),
    ROLES("roles", true),
    KILLER("Killer", true),
    QUESTS("Quests", true),
    PLANES("planes", true),
    DEV_THIS("dev.this", true),
    LOG_THIS("log.this", true),
    LOG_DRAG("log.Drag", true),
    UTIL_THIS("util.this", true),
    GOOD_DAY_MSG("GoodDayMSG", true),
    PAID_REQUESTS("PaidRequests", true),
    LOG_MOVEMENT("log.Movement", false),
    PLAYER_WALK_PATH("PlayerWalkPath", false),
    UNSAFE_ENCHANTING("UnsafeEnchanting", true),
    UTIL_COMMANDS_THIS("util.commands.this", true),
    PAUSE_SETTINGS_THIS("pauseSettings.this", true),
    LOG_GUI_MANAGER_THIS("log.GUIManager.this", false),
    LOG_GUI_MANAGER_CHEST("log.GUIManager.CHEST", false),
    LOG_GUI_MANAGER_MARKO("log.GUIManager.marko", false),
    UTIL_COMMANDS_GRAVITYS("util.commands.gravitys", true),
    DEV_DEV_QUEST_FOR_TESTING("dev.devQuestForTesting", true),
    DEV_STORAGE_MANAGER("dev.StorageManager_DONTCHANGE", true),
    PAUSE_SETTINGS_PLAYER_THIS("pauseSettings.Player.this", true),
    PAUSE_SETTINGS_COMMAND_THIS("pauseSettings.command.this", true),
    LOG_QUEST_REGISTER_ON_ENABLE("log.QuestRegisterOnEnable", false),
    LOG_GUI_MANAGER_SHOULD_WORK("log.GUIManager.Should_work", false),
    LOG_GUI_MANAGER_INT_ID_FAILED("log.GUIManager.int_id_failed", false),
    PAUSE_SETTINGS_PLAYER_CHAT_EVENT("pauseSettings.Player.ChatEvent", true),
    PAUSE_SETTINGS_PLAYER_FISH_EVENT("pauseSettings.Player.FishEvent", true),
    PAUSE_SETTINGS_PLAYER_JOIN_EVENT("pauseSettings.Player.JoinEvent", false),
    PAUSE_SETTINGS_PLAYER_KICK_EVENT("pauseSettings.Player.KickEvent", false),
    LOG_GUI_MANAGER_INT_GUI_ID_FAILED("log.GUIManager.int_guiId_failed", false),
    LOG_GUI_MANAGER_UHMM_THE_GUI_ID_IS("log.GUIManager.uhmm_the_guiId_is", false),
    PAUSE_SETTINGS_COMMAND_PAUSE_COMMAND("pauseSettings.command.pauseCommand", true),
    PAUSE_SETTINGS_PLAYER_INTERACT_EVENT("pauseSettings.Player.InteractEvent", true),
    PAUSE_SETTINGS_PLAYER_BED_ENTER_EVENT("pauseSettings.Player.BedEnterEvent", true),
    PAUSE_SETTINGS_PLAYER_BED_LEAVE_EVENT("pauseSettings.Player.BedLeaveEvent", true),
    PAUSE_SETTINGS_PLAYER_DROP_ITEM_EVENT("pauseSettings.Player.DropItemEvent", true),
    PAUSE_SETTINGS_PLAYER_EDIT_BOOK_EVENT("pauseSettings.Player.EditBookEvent", true),
    PAUSE_SETTINGS_PLAYER_EGG_THROW_EVENT("pauseSettings.Player.EggThrowEvent", true),
    PAUSE_SETTINGS_PLAYER_ITEM_HELD_EVENT("pauseSettings.Player.ItemHeldEvent", true),
    PAUSE_SETTINGS_PLAYER_ITEM_MEND_EVENT("pauseSettings.Player.ItemMendEvent", true),
    PAUSE_SETTINGS_PLAYER_VELOCITY_EVENT("pauseSettings.Player.VelocityEvent", true),
    PAUSE_SETTINGS_PLAYER_ANIMATION_EVENT("pauseSettings.Player.AnimationEvent", true),
    PAUSE_SETTINGS_PLAYER_MOVE_EVENT_THIS("pauseSettings.Player.MoveEvent.this", true),
    PAUSE_SETTINGS_PLAYER_ITEM_DAMAGE_EVENT("pauseSettings.Player.ItemDamageEvent", true),
    PAUSE_SETTINGS_PLAYER_BUCKET_EVENT_THIS("pauseSettings.Player.BucketEvent.this", true),
    PAUSE_SETTINGS_PLAYER_ITEM_CONSUME_EVENT("pauseSettings.Player.ItemConsumeEvent", true),
    PAUSE_SETTINGS_PLAYER_LEASH_ENTITY_EVENT("pauseSettings.Player.LeashEntityEvent", true),
    PAUSE_SETTINGS_PLAYER_SHEAR_ENTITY_EVENT("pauseSettings.Player.ShearEntityEvent", true),
    PAUSE_SETTINGS_PLAYER_TOGGLE_SNEAK_EVENT("pauseSettings.Player.ToggleSneakEvent", true),
    PAUSE_SETTINGS_PLAYER_BUCKET_ENTITY_EVENT("pauseSettings.Player.BucketEntityEvent", true),
    PAUSE_SETTINGS_PLAYER_HARVEST_BLOCK_EVENT("pauseSettings.Player.HarvestBlockEvent", true),
    PAUSE_SETTINGS_PLAYER_TOGGLE_FLIGHT_EVENT("pauseSettings.Player.ToggleFlightEvent", true),
    PAUSE_SETTINGS_PLAYER_TOGGLE_SPRINT_EVENT("pauseSettings.Player.ToggleSprintEvent", true),
    PAUSE_SETTINGS_PLAYER_UNLEASH_ENTITY_EVENT("pauseSettings.Player.UnleashEntityEvent", true),
    PAUSE_SETTINGS_PLAYER_SWAP_HAND_ITEMS_EVENT("pauseSettings.Player.SwapHandItemsEvent", true),
    PAUSE_SETTINGS_PLAYER_RECIPE_DISCOVER_EVENT("pauseSettings.Player.RecipeDiscoverEvent", true),
    PAUSE_SETTINGS_PLAYER_GAME_MODE_CHANGE_EVENT("pauseSettings.Player.GameModeChangeEvent", true),
    PAUSE_SETTINGS_PLAYER_PICKUP_ITEM_EVENT_THIS("pauseSettings.Player.PickupItemEvent.this", true),
    PAUSE_SETTINGS_PLAYER_TAKE_LECTERN_BOOK_EVENT("pauseSettings.Player.TakeLecternBookEvent", true),
    PAUSE_SETTINGS_PLAYER_COMMAND_PREPROCESS_EVENT("pauseSettings.Player.CommandPreprocessEvent", true),
    PAUSE_SETTINGS_PLAYER_STATISTIC_INCREMENT_EVENT("pauseSettings.Player.StatisticIncrementEvent", true),
    PAUSE_SETTINGS_PLAYER_INTERACT_ENTITY_EVENT_THIS("pauseSettings.Player.InteractEntityEvent.this", true),
    PAUSE_SETTINGS_PLAYER_BUCKET_EVENT_BUCKET_FILL_EVENT("pauseSettings.Player.BucketEvent.BucketFillEvent", true),
    PAUSE_SETTINGS_PLAYER_MOVE_EVENT_TELEPORT_EVENT_THIS("pauseSettings.Player.MoveEvent.TeleportEvent.this", true),
    PAUSE_SETTINGS_PLAYER_BUCKET_EVENT_BUCKET_EMPTY_EVENT("pauseSettings.Player.BucketEvent.BucketEmptyEvent", true),
    PAUSE_SETTINGS_PLAYER_PICKUP_ITEM_EVENT_PICKUP_ARROW_EVENT("pauseSettings.Player.PickupItemEvent.PickupArrowEvent", true),
    PAUSE_SETTINGS_PLAYER_MOVE_EVENT_TELEPORT_EVENT_PORTAL_EVENT("pauseSettings.Player.MoveEvent.TeleportEvent.PortalEvent", true),
    PAUSE_SETTINGS_PLAYER_INTERACT_ENTITY_EVENT_INTERACT_AT_ENTITY_EVENT("pauseSettings.Player.InteractEntityEvent.InteractAtEntityEvent", true),
    PAUSE_SETTINGS_PLAYER_INTERACT_ENTITY_EVENT_ARMOR_STAND_MANIPULATE_EVENT("pauseSettings.Player.InteractEntityEvent.ArmorStandManipulateEvent", true);

    private final String path;
    private final boolean defaultValue;

    ConfigKey(String path, boolean defaultValue) {
        this.path = path;
        this.defaultValue = defaultValue;
    }

    static {
        for (ConfigKey configKey : ConfigKey.values()) {
            WOKS.config.addDefault(configKey.getPath(), configKey.getDefaultValue());
        }
    }

    public String getPath() {
        return path;
    }

    public boolean getDefaultValue() {
        return defaultValue;
    }
}
