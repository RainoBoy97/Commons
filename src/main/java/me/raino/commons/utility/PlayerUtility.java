package me.raino.commons.utility;

import me.raino.commons.messaging.localization.Language;
import me.raino.commons.reflection.ReflectionClass;
import me.raino.commons.reflection.SafeField;

import org.bukkit.entity.Player;

public class PlayerUtility {

    public static Language getLanguage(Player player) {
        ReflectionClass<?> clazz = ReflectionClass.of(player.getClass());
        Object craftPlayer = clazz.getMethod("getHandle").invoke(player);
        SafeField<String> f = ReflectionClass.of(craftPlayer.getClass()).getField("locale");
        return Language.fromLocale(f.get(craftPlayer));
    }

    private PlayerUtility() {}

}
