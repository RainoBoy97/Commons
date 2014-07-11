package me.raino.commons.utility;

import me.raino.commons.messaging.localization.Language;
import me.raino.commons.reflection.SafeField;
import me.raino.commons.reflection.SafeMethod;

import org.bukkit.entity.Player;

public class PlayerUtility {

    public static Language getLanguage(Player player) {
        SafeMethod<Object> handle = SafeMethod.of(player.getClass(), "getHandle");
        Object craftPlayer = handle.invoke(player);
        SafeField<String> locale = SafeField.of(craftPlayer.getClass(), "locale");
        return Language.fromLocale(locale.get(craftPlayer));
    }

    private PlayerUtility() {}

}
