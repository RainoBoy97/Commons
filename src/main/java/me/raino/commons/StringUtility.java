package me.raino.commons;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class StringUtility {

    public static String toEnumString(String text) {
        return text.toUpperCase().replace(' ', '_');
    }

    public static String ensureLength(String string, int length, boolean dotdotdot) {
        return string.length() > length ? string.substring(0, length - (dotdotdot ? 3 : 0)) + (dotdotdot ? "..." : "") : string;
    }

    public static String ensureLength(String string, int length) {
        return ensureLength(string, length, false);
    }

    public static <T> String join(String separator, Collection<T> items) {
        StringBuilder sb = new StringBuilder();
        for (T item : items)
            sb.append(separator).append(item);
        return sb.substring(separator.length());
    }

    public static List<String> split(String text, String regex) {
        return Arrays.asList(text.split(regex));
    }

    public static <T> T match(String query, Collection<T> items, StringProvider<T> provider) {
        T match = null;
        int minDist = Integer.MAX_VALUE;
        for (T item : items) {
            String toMatch = provider.get(item);
            if (toMatch.equalsIgnoreCase(query)) {
                match = item;
                break;
            }
            int dist = StringUtils.getLevenshteinDistance(toMatch, query);
            if (dist <= minDist) {
                match = item;
                minDist = dist;
            }
        }
        return match;
    }

    public static <T> T match(String query, T[] items, StringProvider<T> provider) {
        return StringUtility.match(query, Arrays.asList(items), provider);
    }

    public static abstract class StringProvider<V> {

        public abstract String get(V value);

    }

    private StringUtility() {}

}
