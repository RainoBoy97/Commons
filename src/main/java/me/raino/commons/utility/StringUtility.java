package me.raino.commons.utility;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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

    public static String join(String separator, Collection<?> items) {
        StringBuilder sb = new StringBuilder();
        for (Object item : items)
            if (item != null)
                sb.append(separator).append(item);
        return sb.substring(separator.length());
    }

    public static String toEnglish(Object prefix, Object suffix, List<?> items) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, size = items.size(); i < size; i++) {
            String item = items.get(i).toString();
            if (item == null)
                continue;
            if (i != 0)
                sb.append(i == size - 1 ? " and " : ", ");
            if (prefix != null)
                sb.append(prefix);
            sb.append(item);
            if (suffix != null)
                sb.append(suffix);
        }
        return sb.toString();
    }

    public static String toEnglish(List<?> items) {
        return toEnglish(null, null, items);
    }

    public static List<String> split(String text, String regex) {
        return Arrays.asList(text.split(regex));
    }

    public static <T> T match(String query, Collection<T> items, StringProvider<T> provider) {
        T match = null;
        double maxScore = 0.0;
        for (T item : items) {
            String toMatch = provider.get(item);
            if (toMatch.equalsIgnoreCase(query)) {
                match = item;
                break;
            }
            double score = LiquidMetal.score(toMatch, query);
            if (score > maxScore) {
                match = item;
                maxScore = score;
            } else if (score == maxScore) {
                match = null;
            }
        }
        return match;
    }

    public static <T> T match(String query, T[] items, StringProvider<T> provider) {
        return match(query, Arrays.asList(items), provider);
    }

    public static abstract class StringProvider<V> {

        public abstract String get(V value);

    }

    /**
     * Direct port of the JavaScript LiquidMetal.
     * https://github.com/rmm5t/liquidmetal
     *
     * @author Steve Anton
     */
    private static class LiquidMetal {
        public static final double SCORE_NO_MATCH = 0.0;
        public static final double SCORE_MATCH = 1.0;
        public static final double SCORE_TRAILING = 0.8;
        public static final double SCORE_TRAILING_BUT_STARTED = 0.9;
        public static final double SCORE_BUFFER = 0.85;

        public static double score(String string, String abbreviation) {
            if (abbreviation.length() == 0)
                return SCORE_TRAILING;
            if (abbreviation.length() > string.length())
                return SCORE_NO_MATCH;

            double[] scores = buildScoreArray(string, abbreviation);

            // complete miss:
            if (scores == null) {
                return 0;
            }

            double sum = 0.0;
            for (double score : scores) {
                sum += score;
            }

            return (sum / scores.length);
        }

        private static double[] buildScoreArray(String string, String abbreviation) {
            double[] scores = new double[string.length()];
            String lower = string.toLowerCase();
            String chars = abbreviation.toLowerCase();

            int lastIndex = -1;
            boolean started = false;
            for (int i = 0; i < chars.length(); i++) {
                char c = chars.charAt(i);
                int index = lower.indexOf(c, lastIndex + 1);

                if (index == -1)
                    return null; // signal no match
                if (index == 0)
                    started = true;

                if (isNewWord(string, index)) {
                    scores[index - 1] = 1.0;
                    fillArray(scores, SCORE_BUFFER, lastIndex + 1, index - 1);
                } else if (isUpperCase(string, index)) {
                    fillArray(scores, SCORE_BUFFER, lastIndex + 1, index);
                } else {
                    fillArray(scores, SCORE_NO_MATCH, lastIndex + 1, index);
                }

                scores[index] = SCORE_MATCH;
                lastIndex = index;
            }

            double trailingScore = started ? SCORE_TRAILING_BUT_STARTED : SCORE_TRAILING;
            fillArray(scores, trailingScore, lastIndex + 1, scores.length);
            return scores;
        }

        private static boolean isNewWord(String string, int index) {
            if (index == 0)
                return false;
            char c = string.charAt(index - 1);
            return (c == ' ' || c == '\t');
        }

        private static void fillArray(double[] array, double value, int from, int to) {
            for (int i = from; i < to; i++) {
                array[i] = value;
            }
        }

        private static boolean isUpperCase(String string, int index) {
            char c = string.charAt(index);
            return ('A' <= c && c <= 'Z');
        }
    }

    private StringUtility() {}

}
