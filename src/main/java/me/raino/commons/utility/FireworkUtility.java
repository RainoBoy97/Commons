package me.raino.commons.utility;

import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkUtility {

    public static void playInstant(Location location, FireworkEffect effect) {
        Firework firework = (Firework) location.getWorld().spawn(location, Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.clearEffects();
        fireworkMeta.addEffect(effect);
        firework.setFireworkMeta(fireworkMeta);
        firework.detonate();
    }

    private FireworkUtility() {}

}
