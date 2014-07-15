package me.raino.commons.utility;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.projectiles.ProjectileSource;

public class EventUtility {

    @SuppressWarnings("deprecation")
    public static Player getPlayerDamager(EntityDamageEvent event) {
        Player player = null;
        if (event instanceof EntityDamageByEntityEvent) {
            Entity damager = ((EntityDamageByEntityEvent) event).getDamager();
            if (damager instanceof Player) {
                player = (Player) damager;
            } else if (damager instanceof Projectile) {
                ProjectileSource shooter = ((Projectile) damager).getShooter();
                if (shooter instanceof Player) {
                    player = (Player) shooter;
                }
            }
        }
        return player;
    }

    private EventUtility() {}

}
