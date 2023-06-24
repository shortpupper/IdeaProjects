package woks.woks.board.server.util.handlers;

import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;
import woks.woks.WOKS;

import static woks.woks.WOKS.*;


public class stopEvents implements Listener {

    public stopEvents() {
        Bukkit.getPluginManager().registerEvents(this, WOKS.getInstance());


        //TODO make this more efficient and DRY it a bit
        WOKS.getInstance().getLogger().info("moveOnHope");
        if (config.getBoolean("pauseSettings.Player.MoveEvent")) {
            WOKS.getInstance().getLogger().info("moveOn");
            registerCustomEvent(PlayerMoveEvent.class, this::onEvent);
        }
        WOKS.getInstance().getLogger().info("moveAway");
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

    private void onEvent(Event event1) {
        if (event1 instanceof Cancellable event2) {
            if (event1 instanceof PlayerEvent event3) {
                event2.setCancelled(isPaused && IsIEffected.get(event3.getPlayer().getUniqueId()));
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



    @EventHandler
    public void onEvent(EntityExplodeEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityAirChangeEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityBreedEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityBreakDoorEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityBlockFormEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityChangeBlockEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityCombustEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityCombustByBlockEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityCombustByEntityEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntitySpellCastEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityDamageEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityDamageByBlockEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityDamageByEntityEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityDismountEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityDropItemEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityEnterBlockEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityEnterLoveModeEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityExhaustionEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityInteractEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityPlaceEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityPortalEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityPortalExitEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityPotionEffectEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityResurrectEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntitySpawnEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityTameEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityTargetEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityTeleportEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityTransformEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityMountEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityPickupItemEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityRegainHealthEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityShootBowEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityTargetLivingEntityEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityToggleGlideEvent event) {
        event.setCancelled(isPaused);
    }
    @EventHandler
    public void onEvent(EntityToggleSwimEvent event) {
        event.setCancelled(isPaused);
    }


}
