package me.raino.commons.utility;

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

    public static String join(String separator, List<?> items) {
        StringBuilder sb = new StringBuilder();
        for (Object item : items)
            sb.append(separator).append(item);
        return sb.substring(separator.length());
    }

    public static String toEnglish(Object prefix, Object suffix, List<?> items) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, size = items.size(); i < size; i++) {
            String item = items.get(i).toString();
            if (i != 0)
                sb.append(i == size - 1 ? " and " : ", ");
            sb.append(prefix.toString()).append(item).append(suffix.toString());
        }
        return sb.toString();
    }
    
    public static String toEnglish(List<?> items) {
        return StringUtility.toEnglish("", "", items);
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
