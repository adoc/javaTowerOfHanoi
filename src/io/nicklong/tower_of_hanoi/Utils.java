package io.nicklong.tower_puzzle;

public class Utils {
    public static String repeatString(String str, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++)
            sb.append(str);
        return sb.toString();
    }
}
