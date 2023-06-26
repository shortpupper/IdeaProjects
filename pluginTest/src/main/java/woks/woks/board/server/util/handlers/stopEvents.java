package woks.woks.board.server.util.handlers;

import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;
import woks.woks.WOKS;
import woks.woks.board.server.custom.config.ExtraConfig;

import static woks.woks.WOKS.IsIEffected;
import static woks.woks.WOKS.isPaused;


public class stopEvents implements Listener {
    private final ExtraConfig config;

    public stopEvents() {

        Bukkit.getPluginManager().registerEvents(this, WOKS.getInstance());

        this.config = new ExtraConfig(WOKS.config);

        WOKS.getInstance().getLogger().info(String.valueOf(config.getBoolean("GoodDayMSG")));


        //TODO make this more efficient and DRY it a bit
        if (config.getBoolean("pauseSettings.Player.MoveEvent")) {
            registerCustomEvent(PlayerMoveEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.InteractEvent")) {
            registerCustomEvent(PlayerInteractEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.MoveEvent.TeleportEvent")) {
            registerCustomEvent(PlayerTeleportEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.TakeLecternBookEvent")) {
            registerCustomEvent(PlayerTakeLecternBookEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.ToggleFlightEvent")) {
            registerCustomEvent(PlayerToggleFlightEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.ToggleSneakEvent")) {
            registerCustomEvent(PlayerToggleSneakEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.ToggleSprintEvent")) {
            registerCustomEvent(PlayerToggleSprintEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.AnimationEvent")) {
            registerCustomEvent(PlayerAnimationEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.InteractEntityEvent.ArmorStandManipulateEvent")) {
            registerCustomEvent(PlayerArmorStandManipulateEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.InteractEntityEvent.InteractAtEntityEvent")) {
            registerCustomEvent(PlayerInteractAtEntityEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.PickupItemEvent.PickupArrowEvent")) {
            registerCustomEvent(PlayerPickupArrowEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.PickupItemEvent")) {
            registerCustomEvent(PlayerPickupItemEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.BedEnterEvent")) {
            registerCustomEvent(PlayerBedEnterEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.BedLeaveEvent")) {
            registerCustomEvent(PlayerBedLeaveEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.BucketEvent.BucketEmptyEvent")) {
            registerCustomEvent(PlayerBucketEmptyEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.BucketEntityEvent")) {
            registerCustomEvent(PlayerBucketEntityEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.BucketEvent.BucketFillEvent")) {
            registerCustomEvent(PlayerBucketFillEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.HarvestBlockEvent")) {
            registerCustomEvent(PlayerHarvestBlockEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.CommandPreprocessEvent")) {
            registerCustomEvent(PlayerCommandPreprocessEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.ItemConsumeEvent")) {
            registerCustomEvent(PlayerItemConsumeEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.GameModeChangeEvent")) {
            registerCustomEvent(PlayerGameModeChangeEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.ChatEvent")) {
            registerCustomEvent(AsyncPlayerChatEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.ItemDamageEvent")) {
            registerCustomEvent(PlayerItemDamageEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.RecipeDiscoverEvent")) {
            registerCustomEvent(PlayerRecipeDiscoverEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.FishEvent")) {
            registerCustomEvent(PlayerFishEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.ItemHeldEvent")) {
            registerCustomEvent(PlayerItemHeldEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.ItemMendEvent")) {
            registerCustomEvent(PlayerItemMendEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.InteractEntityEvent")) {
            registerCustomEvent(PlayerInteractEntityEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.StatisticIncrementEvent")) {
            registerCustomEvent(PlayerStatisticIncrementEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.KickEvent")) {
            registerCustomEvent(PlayerKickEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.MoveEvent.TeleportEvent.PortalEvent")) {
            registerCustomEvent(PlayerPortalEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.ShearEntityEvent")) {
            registerCustomEvent(PlayerShearEntityEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.VelocityEvent")) {
            registerCustomEvent(PlayerUnleashEntityEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.VelocityEvent")) {
            registerCustomEvent(PlayerVelocityEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.LeashEntityEvent")) {
            registerCustomEvent(PlayerLeashEntityEvent.class, this::onEvent);
        }

        if (config.getBoolean("pauseSettings.Player.SwapHandItemsEvent")) {
            registerCustomEvent(PlayerSwapHandItemsEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.DropItemEvent")) {
            registerCustomEvent(PlayerDropItemEvent.class, this::onEvent);
        }
        if (config.getBoolean("pauseSettings.Player.EditBookEvent")) {
            registerCustomEvent(PlayerEditBookEvent.class, this::onEvent);
        }

        registerCustomEvent(EntityExplodeEvent.class, this::onEvent, "pauseSettings.Entity.ExplodeEvent");
        registerCustomEvent(EntityAirChangeEvent.class, this::onEvent, "pauseSettings.Entity.AirChangeEvent");
        registerCustomEvent(EntityBreedEvent.class, this::onEvent, "pauseSettings.Entity.BreedEvent");
        registerCustomEvent(EntityBlockFormEvent.class, this::onEvent, "pauseSettings.Entity.BlockFormEvent");
        registerCustomEvent(EntityChangeBlockEvent.class, this::onEvent, "pauseSettings.Entity.ChangeBlockEvent");
        registerCustomEvent(EntityBreakDoorEvent.class, this::onEvent, "pauseSettings.Entity.ChangeBlockEvent.BreakDoorEvent");
        registerCustomEvent(EntityCombustEvent.class, this::onEvent, "pauseSettings.Entity.CombustEvent");
        registerCustomEvent(EntityCombustByBlockEvent.class, this::onEvent, "pauseSettings.Entity.CombustEvent.CombustByBlockEvent");
        registerCustomEvent(EntityCombustByEntityEvent.class, this::onEvent, "pauseSettings.Entity.CombustEvent.CombustByEntityEvent");
        registerCustomEvent(EntitySpellCastEvent.class, this::onEvent, "pauseSettings.Entity.SpellCastEvent");
        registerCustomEvent(EntityDamageEvent.class, this::onEvent, "pauseSettings.Entity.DamageEvent");
        registerCustomEvent(EntityDamageByBlockEvent.class, this::onEvent, "pauseSettings.Entity.DamageEvent.DamageByBlockEvent");
        registerCustomEvent(EntityDamageByEntityEvent.class, this::onEvent, "pauseSettings.Entity.DamageEvent.DamageByEntityEvent");
        registerCustomEvent(EntityDismountEvent.class, this::onEvent, "pauseSettings.Entity.DismountEvent");
        registerCustomEvent(EntityDropItemEvent.class, this::onEvent, "pauseSettings.Entity.DropItemEvent");
        registerCustomEvent(EntityEnterBlockEvent.class, this::onEvent, "pauseSettings.Entity.EnterBlockEvent");
        registerCustomEvent(EntityEnterLoveModeEvent.class, this::onEvent, "pauseSettings.Entity.EnterLoveModeEvent");
        registerCustomEvent(EntityExhaustionEvent.class, this::onEvent, "pauseSettings.Entity.ExhaustionEvent");
        registerCustomEvent(EntityInteractEvent.class, this::onEvent, "pauseSettings.Entity.InteractEvent");
        registerCustomEvent(EntityPlaceEvent.class, this::onEvent, "pauseSettings.Entity.PlaceEvent");
        registerCustomEvent(EntityPotionEffectEvent.class, this::onEvent, "pauseSettings.Entity.PotionEffectEvent");
        registerCustomEvent(EntityResurrectEvent.class, this::onEvent, "pauseSettings.Entity.ResurrectEvent");
        registerCustomEvent(EntitySpawnEvent.class, this::onEvent, "pauseSettings.Entity.SpawnEvent");
        registerCustomEvent(EntityTameEvent.class, this::onEvent, "pauseSettings.Entity.TameEvent");
        registerCustomEvent(EntityTargetEvent.class, this::onEvent, "pauseSettings.Entity.TargetEvent");
        registerCustomEvent(EntityPortalEvent.class, this::onEvent, "pauseSettings.Entity.TeleportEvent.PortalEvent");
        registerCustomEvent(EntityPortalExitEvent.class, this::onEvent, "pauseSettings.Entity.TeleportEvent.PortalEvent.PortalExitEvent");
        registerCustomEvent(EntityTeleportEvent.class, this::onEvent, "pauseSettings.Entity.TeleportEvent");
        registerCustomEvent(EntityTransformEvent.class, this::onEvent, "pauseSettings.Entity.TransformEvent");
        registerCustomEvent(EntityMountEvent.class, this::onEvent, "pauseSettings.Entity.MountEvent");
        registerCustomEvent(EntityPickupItemEvent.class, this::onEvent, "pauseSettings.Entity.PickupItemEvent");
        registerCustomEvent(EntityRegainHealthEvent.class, this::onEvent, "pauseSettings.Entity.RegainHealthEvent");
        registerCustomEvent(EntityShootBowEvent.class, this::onEvent, "pauseSettings.Entity.ShootBowEvent");
        registerCustomEvent(EntityTargetLivingEntityEvent.class, this::onEvent, "pauseSettings.Entity.TargetEvent.TargetLivingEntityEvent");
        registerCustomEvent(EntityToggleGlideEvent.class, this::onEvent, "pauseSettings.Entity.ToggleGlideEvent");
        registerCustomEvent(EntityToggleSwimEvent.class, this::onEvent, "pauseSettings.Entity.ToggleSwimEvent");



        // this is now the block events
        registerCustomEvent(BlockExplodeEvent.class, this::onEvent, "pauseSettings.Block.ExplodeEvent");
//        registerCustomEvent(BlockExpEvent.class, this::onEvent, "pauseSettings.Block.ExpEvent");
        registerCustomEvent(BlockDamageAbortEvent.class, this::onEvent, "pauseSettings.Block.DamageAbortEvent");
        registerCustomEvent(BlockDispenseArmorEvent.class, this::onEvent, "pauseSettings.Block.DispenseEvent.DispenseArmorEvent");
        registerCustomEvent(BlockBreakEvent.class, this::onEvent, "pauseSettings.Block.ExpEvent.BreakEvent");
        registerCustomEvent(BlockBurnEvent.class, this::onEvent, "pauseSettings.Block.BurnEvent");
//        registerCustomEvent(BlockCanBuildEvent.class, this::onEvent, "pauseSettings.Block.CanBuildEvent");
        registerCustomEvent(BlockCookEvent.class, this::onEvent, "pauseSettings.Block.CookEvent");
        registerCustomEvent(BlockDamageEvent.class, this::onEvent, "pauseSettings.Block.DamageEvent");
        registerCustomEvent(BlockDispenseEvent.class, this::onEvent, "pauseSettings.Block.DispenseEvent");
        registerCustomEvent(BlockDropItemEvent.class, this::onEvent, "pauseSettings.Block.DropItemEvent");
        registerCustomEvent(BlockFadeEvent.class, this::onEvent, "pauseSettings.Block.FadeEvent");
        registerCustomEvent(BlockFertilizeEvent.class, this::onEvent, "pauseSettings.Block.FertilizeEvent");
        registerCustomEvent(BlockFormEvent.class, this::onEvent, "pauseSettings.Block.GrowEvent.FormEvent");
//        registerCustomEvent(EntityBlockFormEvent.class, this::onEvent, "pauseSettings.Block.GrowEvent.FormEvent.EntityBlockFormEvent");
        registerCustomEvent(BlockGrowEvent.class, this::onEvent, "pauseSettings.Block.GrowEvent");
        registerCustomEvent(BlockReceiveGameEvent.class, this::onEvent, "pauseSettings.Block.ReceiveGameEvent");
        registerCustomEvent(BlockIgniteEvent.class, this::onEvent, "pauseSettings.Block.IgniteEvent");
//        registerCustomEvent(BlockMultiPlaceEvent.class, this::onEvent, "pauseSettings.Block.PlaceEvent.MultiPlaceEvent");
        registerCustomEvent(BlockPlaceEvent.class, this::onEvent, "pauseSettings.Block.PlaceEvent");
        registerCustomEvent(BlockPhysicsEvent.class, this::onEvent, "pauseSettings.Block.PhysicsEvent");
        registerCustomEvent(BlockPistonEvent.class, this::onEvent, "pauseSettings.Block.PistonEvent");
        registerCustomEvent(BlockPistonExtendEvent.class, this::onEvent, "pauseSettings.Block.PistonEvent.PistonExtend");
//        registerCustomEvent(BlockPistonRetractEvent.class, this::onEvent, "pauseSettings.Block.PistonEvent.PistonRetract");
        registerCustomEvent(BlockShearEntityEvent.class, this::onEvent, "pauseSettings.Block.ShearEntityEvent");
        registerCustomEvent(BlockSpreadEvent.class, this::onEvent, "pauseSettings.Block.SpreadEvent");
        registerCustomEvent(BlockFromToEvent.class, this::onEvent, "pauseSettings.Block.FromToEvent");
        registerCustomEvent(CauldronLevelChangeEvent.class, this::onEvent, "pauseSettings.Block.CauldronLevelChangeEvent");
        registerCustomEvent(FluidLevelChangeEvent.class, this::onEvent, "pauseSettings.Block.FluidLevelChangeEvent");
        registerCustomEvent(LeavesDecayEvent.class, this::onEvent, "pauseSettings.Block.LeavesDecayEvent");
        registerCustomEvent(MoistureChangeEvent.class, this::onEvent, "pauseSettings.Block.MoistureChangeEvent");
        registerCustomEvent(NotePlayEvent.class, this::onEvent, "pauseSettings.Block.NotePlayEvent");
        registerCustomEvent(SignChangeEvent.class, this::onEvent, "pauseSettings.Block.SignChangeEvent");
        registerCustomEvent(SpongeAbsorbEvent.class, this::onEvent, "pauseSettings.Block.SpongeAbsorbEvent");

    }

    private <T extends Event> void registerCustomEvent(Class<T> eventClass, EventConsumer<T> eventConsumer) {
        WOKS.getInstance().getServer().getPluginManager().registerEvent(
                eventClass,
                this,
                EventPriority.NORMAL,
                (listener, event) -> {
                    if (eventClass.isInstance((event))) {
                        eventConsumer.accept(eventClass.cast(event));
                    }
                },
                WOKS.getInstance()
        );
    }
    private <T extends Event> void registerCustomEvent(Class<T> eventClass, EventConsumer<T> eventConsumer, String configPath) {
        if (config.getBoolean(configPath)) {
            registerCustomEvent(eventClass, eventConsumer);
        }
    }

    private void onEvent(Event event1) {
        if (event1 instanceof Cancellable event2) {
            if (event1 instanceof PlayerEvent event3) {
                event2.setCancelled(isPaused && IsIEffected.get(event3.getPlayer().getUniqueId()));
            }
            else if (event1 instanceof EntityEvent) {
                event2.setCancelled(isPaused);
            }
        }
    }

    private interface EventConsumer<T extends Event> {
        void accept(T event);
    }

    @EventHandler
    public void onEvent(PlayerJoinEvent event) {
        if ( isPaused && config.getBoolean("pauseSettings.Player.JoinEvent")) {
            event.getPlayer().kickPlayer("Game is paused.");
        }
    }

    @EventHandler
    public void onEvent(PlayerEggThrowEvent event) {
        if (isPaused && config.getBoolean("pauseSettings.Player.EggThrowEvent")) {
            event.getEgg().setBounce(true);
        }
    }
}
