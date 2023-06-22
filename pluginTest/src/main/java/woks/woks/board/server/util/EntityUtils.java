package woks.woks.board.server.util;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import woks.woks.NKD;
import woks.woks.matthew.util.ExtraDataContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityUtils {
    public static List<Entity> getAllEntities() {
        List<Entity> entities = new ArrayList<>();

        for (World world : Bukkit.getWorlds()) {
            entities.addAll(world.getEntities());
        }

        return entities;
    }

    public static void pauseCurrentEntities() {
        List<Entity> entities = getAllEntities();

        // save there previous stats
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity) {
                if (!entity.isDead() && entity.isValid()) {
                    ExtraDataContainer dataContainer = new ExtraDataContainer(entity.getPersistentDataContainer());
                    dataContainer.set(NKD.ENTITY_GRAVITY, entity.hasGravity());
                    Map<String, Integer> speed = getEffectInfo((LivingEntity) entity, PotionEffectType.SLOW);
                    dataContainer.set(NKD.ENTITY_SPEED_TIME, speed.get("TimeLeft") != null ? speed.get("TimeLeft") : 0);
                    dataContainer.set(NKD.ENTITY_SPEED_AMP, speed.get("Amplitude") != null ? speed.get("Amplitude") : 0);
                    dataContainer.set(NKD.ENTITY_PAUSED, true);
                    dataContainer.set(NKD.ENTITY_AI, ((LivingEntity) entity).hasAI());
                    dataContainer.set(NKD.ENTITY_pause_Collidable, ((LivingEntity) entity).isCollidable());

                    PotionEffect potionEffect = new PotionEffect(PotionEffectType.SLOW, -1, 255, true);
                    ((LivingEntity) entity).addPotionEffect(potionEffect);

                    ((LivingEntity) entity).setAI(false);
                    entity.setGravity(false);
                    ((LivingEntity) entity).setCollidable(false);
                }
            }
        }
    }
    public static void unpauseCurrentEntities() {
        List<Entity> entities = getAllEntities();

        // save there previous stats
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity) {
                if (!entity.isDead()) {
                    ExtraDataContainer dataContainer = new ExtraDataContainer(entity.getPersistentDataContainer());
                    Boolean            value         = dataContainer.get(NKD.ENTITY_PAUSED);
                    assert value != null;
                    if (value) {
                        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SLOW,
                                                                     dataContainer.get(NKD.ENTITY_SPEED_TIME),
                                                                     dataContainer.get(NKD.ENTITY_SPEED_AMP),
                                                                     false);
                        ((LivingEntity) entity).addPotionEffect(potionEffect);

                        ((LivingEntity) entity).setAI(dataContainer.get(NKD.ENTITY_AI));
                        ((LivingEntity) entity).setGravity(dataContainer.get(NKD.ENTITY_GRAVITY));
                        ((LivingEntity) entity).setCollidable(dataContainer.get(NKD.ENTITY_pause_Collidable));


                        dataContainer.set(NKD.ENTITY_PAUSED, false);
                        dataContainer.set(NKD.ENTITY_GRAVITY, dataContainer.get(NKD.ENTITY_GRAVITY));
                        dataContainer.set(NKD.ENTITY_SPEED_TIME, 0);
                        dataContainer.set(NKD.ENTITY_SPEED_AMP, 0);
                        dataContainer.set(NKD.ENTITY_AI, dataContainer.get(NKD.ENTITY_AI));
                        dataContainer.set(NKD.ENTITY_pause_Collidable, dataContainer.get(NKD.ENTITY_pause_Collidable));
                    }
                }
            }
        }
    }

    public static double getEntitySpeed(Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            AttributeInstance attribute    = livingEntity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
            if (attribute != null) {
                return attribute.getValue();
            }
        }
        return 0.0;
    }

    public static Map<String, Integer> getEffectInfo(LivingEntity entity, PotionEffectType effectType) {
        Map<String, Integer> effectInfo = new HashMap<>();

        PotionEffect effect = entity.getPotionEffect(effectType);
        if (effect != null) {
            int duration = effect.getDuration();
            int amplifier = effect.getAmplifier();

            effectInfo.put("TimeLeft", duration);
            effectInfo.put("Amplitude", amplifier);
        }

        return effectInfo;
    }
}