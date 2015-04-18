package io.nicklong.utils;

public class StringUtils {
    public static String repeatChar(char chr, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++)
            sb.append(chr);
        return sb.toString();
    }
}
