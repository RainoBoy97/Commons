package me.raino.commons.utility;

import me.raino.commons.reflection.SafeMethod;

import org.bukkit.entity.Player;

public class PlayerUtility {

    public static Object getCraftPlayer(Player player) {
        return SafeMethod.of(player, "getHandle").invoke();
    }

    private PlayerUtility() {}

}
