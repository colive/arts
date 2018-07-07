import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class get_long {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}

public class longest_substring_without_repeat {
    public static void main(String[] args) throws IOException {
        String s = "aabbcdsef";

        int ret = new get_long().lengthOfLongestSubstring(s);

        String out = String.valueOf(ret);

        System.out.print(out);
    }

}
