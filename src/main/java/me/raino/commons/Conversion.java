package me.raino.commons;

import java.util.List;

import me.raino.commons.utility.StringUtility;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import com.google.common.collect.Lists;

public class Conversion {

    public static String toString(Object object) {
        return object.toString();
    }

    public static Integer parseInteger(String text, Integer def) {
        try {
            return Integer.valueOf(text);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static Integer parseInteger(String text) {
        return Conversion.parseInteger(text, null);
    }

    public static List<Integer> parseIntegerList(String text) {
        List<Integer> result = Lists.newArrayList();
        for (String part : StringUtility.split(text, ","))
            result.add(Conversion.parseInteger(part));
        return result;
    }

    public static Double parseDouble(String text, Double def) {
        try {
            return Double.valueOf(text);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static Double parseDouble(String text) {
        return Conversion.parseDouble(text, null);
    }

    public static List<Double> parseDoubleList(String text) {
        List<Double> result = Lists.newArrayList();
        for (String part : StringUtility.split(text, ","))
            result.add(Conversion.parseDouble(part));
        return result;
    }

    public static Long parseLong(String text, Long def) {
        try {
            return Long.valueOf(text);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static Long parseLong(String text) {
        return Conversion.parseLong(text, null);
    }

    public static List<Long> parseLongList(String text) {
        List<Long> result = Lists.newArrayList();
        for (String part : StringUtility.split(text, ","))
            result.add(Conversion.parseLong(part));
        return result;
    }

    public static Short parseShort(String text, Short def) {
        try {
            return Short.valueOf(text);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static Short parseShort(String text) {
        return Conversion.parseShort(text, null);
    }

    public static List<Short> parseShortList(String text) {
        List<Short> result = Lists.newArrayList();
        for (String part : StringUtility.split(text, ","))
            result.add(Conversion.parseShort(part));
        return result;
    }

    public static Byte parseByte(String text, Byte def) {
        try {
            return Byte.valueOf(text);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static Byte parseByte(String text) {
        return Conversion.parseByte(text, null);
    }

    public static List<Byte> parseByteList(String text) {
        List<Byte> result = Lists.newArrayList();
        for (String part : StringUtility.split(text, ","))
            result.add(Conversion.parseByte(part));
        return result;
    }

    public static Material parseMaterial(String text) {
        return Material.matchMaterial(text);
    }

    public static ChatColor parseChatColor(String text, ChatColor def) {
        return Conversion.parseEnumValue(ChatColor.class, text, def);
    }

    public static ChatColor parseChatColor(String text) {
        return Conversion.parseChatColor(text, null);
    }

    public static DyeColor parseDyeColor(String text) {
        return Conversion.parseEnumValue(DyeColor.class, text);
    }

    public static Enchantment parseEnchantment(String text) {
        return Enchantment.getByName(StringUtility.toEnumString(text));
    }

    public static <T extends Enum<T>> T parseEnumValue(Class<T> type, String text, T def) {
        try {
            return Enum.valueOf(type, StringUtility.toEnumString(text));
        } catch (Exception e) {
            return def;
        }
    }

    public static <T extends Enum<T>> T parseEnumValue(Class<T> type, String text) {
        return Conversion.parseEnumValue(type, text, null);
    }

    private Conversion() {}

}
