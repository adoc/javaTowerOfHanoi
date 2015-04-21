package io.nicklong.utils;

/**
 * Silly little String Utilities.
 */
public class StringUtils {
    /**
     * Repeat a character n times.
     *
     * @param chr Character to be repeated
     * @param n   Number of times
     * @return String of characters that were repeated.
     */
    public static String repeatChar(char chr, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++)
            sb.append(chr);
        return sb.toString();
    }
}
