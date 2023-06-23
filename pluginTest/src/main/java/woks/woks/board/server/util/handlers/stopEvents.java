package woks.woks.board.server.util.handlers;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;
import woks.woks.WOKS;
import woks.woks.board.server.util.configUtil;

import static woks.woks.WOKS.*;
import static woks.woks.board.server.util.commands.pause.isPaused;


public class stopEvents implements Listener {

    public stopEvents() {
        Bukkit.getPluginManager().registerEvents(this, WOKS.getInstance());
    }


    @EventHandler
    public void onEvent(PlayerMoveEvent event) {
        WOKS.getInstance().getLogger().info("[v23.6.23][Move][!this] " + config.getBoolean("pauseSettings.Player.MoveEvent"));
        WOKS.getInstance().getLogger().info("[v23.6.23][Move][this] " + config.getBoolean("pauseSettings.Player.MoveEvent.this"));
        event.setCancelled(isPaused &&
                           config.getBoolean("pauseSettings.Player.MoveEvent") &&
                           config.getBoolean("pauseSettings.Player.MoveEvent.this") &&
                           IsIEffected.get(event.getPlayer().getUniqueId()));
    }
    @EventHandler
    public void onEvent(PlayerInteractEvent event) {//pauseSettings.Player.InteractEvent
        WOKS.getInstance().getLogger().info("[v23.6.23][InteractEvent][isPaused] " + isPaused);

        WOKS.getInstance().getLogger().info("[v23.6.23][InteractEvent][configUtil] " +
                                            configUtil.getBoolean("pauseSettings.Player.InteractEvent", config));

        WOKS.getInstance().getLogger().info("[v23.6.23][InteractEvent][IsIEffected] " +
                                            IsIEffected.get(event.getPlayer().getUniqueId()));
        event.setCancelled(isPaused &&
                           configUtil.getBoolean("pauseSettings.Player.InteractEvent", config) &&
                           IsIEffected.get(event.getPlayer().getUniqueId()));
    }
    @EventHandler
    public void onEvent(PlayerSwapHandItemsEvent event) {
        event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()) &&
                           pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.SwapHandItemsEvent"));
    }
    @EventHandler
    public void onEvent(PlayerDropItemEvent event) {
        event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()) &&
                           pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.DropItemEvent"));
    }
    @EventHandler
    public void onEvent(PlayerEditBookEvent event) {
        event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()) &&
                           pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.EditBookEvent"));
    }
    @EventHandler
    public void onEvent(PlayerTeleportEvent event) {
        if (pausePlayer && isPaused
            && config.getBoolean("pauseSettings.Player.MoveEvent.this")
            && config.getBoolean("pauseSettings.Player.MoveEvent.TeleportEvent.this")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerTakeLecternBookEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.TakeLecternBookEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerToggleFlightEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.ToggleFlightEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerToggleSneakEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.ToggleSneakEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerToggleSprintEvent event) {
        if (isPaused && configUtil.getBoolean("pauseSettings.Player.ToggleSprintEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerAnimationEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.AnimationEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerArmorStandManipulateEvent event) {
        if (pausePlayer && isPaused
            && config.getBoolean("pauseSettings.Player.InteractEntityEvent.this")
            && config.getBoolean("pauseSettings.Player.InteractEntityEvent.ArmorStandManipulateEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerInteractAtEntityEvent event) {
        if (pausePlayer && isPaused
            && config.getBoolean("pauseSettings.Player.InteractEntityEvent.this")
            && config.getBoolean("pauseSettings.Player.InteractEntityEvent.InteractAtEntityEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerPickupArrowEvent event) {
        if (pausePlayer && isPaused
            && config.getBoolean("pauseSettings.Player.PickupItemEvent.this")
            && config.getBoolean("pauseSettings.Player.PickupItemEvent.PickupArrowEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerPickupItemEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.PickupItemEvent.this")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerBedEnterEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.BedEnterEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerBedLeaveEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.BedLeaveEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    // this made me mad cus it's not real
    // https://bukkit.org/threads/listeners-not-allways-getting-a-event.71905/
//    @EventHandler
//    public void onEvent(PlayerBucketEvent event) {
//        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.BucketEvent")) {
//            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
//        }
//    }
    @EventHandler
    public void onEvent(PlayerBucketEmptyEvent event) {
        if (pausePlayer && isPaused
            && config.getBoolean("pauseSettings.Player.BucketEvent.this")
            && config.getBoolean("pauseSettings.Player.BucketEvent.BucketEmptyEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerBucketEntityEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.BucketEntityEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerBucketFillEvent event) {
        if (pausePlayer && isPaused
            && config.getBoolean("pauseSettings.Player.BucketEvent.this")
            && config.getBoolean("pauseSettings.Player.BucketEvent.BucketFillEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerHarvestBlockEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.HarvestBlockEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerCommandPreprocessEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.CommandPreprocessEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerItemConsumeEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.ItemConsumeEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerGameModeChangeEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.GameModeChangeEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(AsyncPlayerChatEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.ChatEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerItemDamageEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.ItemDamageEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerRecipeDiscoverEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.RecipeDiscoverEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerFishEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.FishEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerItemHeldEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.ItemHeldEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerItemMendEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.ItemMendEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerInteractEntityEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.InteractEntityEvent.this")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerStatisticIncrementEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.StatisticIncrementEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }


    // might change this to be in the settings
    @EventHandler
    public void onEvent(PlayerKickEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.KickEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerJoinEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.JoinEvent")) {
            event.getPlayer().kickPlayer("Game is paused.");
        }
    }



    @EventHandler
    public void onEvent(PlayerPortalEvent event) {
        if (pausePlayer && isPaused
            && config.getBoolean("pauseSettings.Player.MoveEvent.this")
            && config.getBoolean("pauseSettings.Player.MoveEvent.TeleportEvent.this")
            && config.getBoolean("pauseSettings.Player.MoveEvent.TeleportEvent.PortalEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerShearEntityEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.ShearEntityEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerUnleashEntityEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.VelocityEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerVelocityEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.VelocityEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerLeashEntityEvent event) {
        if (pausePlayer && isPaused && config.getBoolean("pauseSettings.Player.LeashEntityEvent")) {
            event.setCancelled(IsIEffected.get(event.getPlayer().getUniqueId()));
        }
    }
    @EventHandler
    public void onEvent(PlayerEggThrowEvent event) {
        if (pausePlayer && config.getBoolean("pauseSettings.Player.EggThrowEvent") && isPaused) {
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
