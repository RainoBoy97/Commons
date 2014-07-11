package me.raino.commons.utility;

import me.raino.commons.messaging.localization.Language;
import me.raino.commons.reflection.SafeField;
import me.raino.commons.reflection.SafeMethod;

import org.bukkit.entity.Player;

public class PlayerUtility {

    public static Language getLanguage(Player player) {
        Object craftPlayer = SafeMethod.of(player, "getHandle").invoke();
        SafeField<String> locale = SafeField.of(craftPlayer, "locale");
        return Language.fromLocale(locale.get());
    }

    private PlayerUtility() {}

}
