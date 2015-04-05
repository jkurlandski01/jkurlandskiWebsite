package interviews.facetoface;

/**
 * Given a string containing only digits, restore it by returning all possible IP address combinations
 * A valid IP address has 4 parts separated by a period. Each part can contain 0-255. The first part can't 
 * start with 0.
 */
public class LongestPalindromicSubString {

    char[] sChar;
    int[] p;
    int id;

    private void findLongestPalindrome(int i) {
        int mx = id + p[id];

        // This is the key; init p[i].
        // bypass those that has already been compared.
        p[i] = (i <= mx) ? Math.min(p[2 * id - i], mx - i) : 0;

        for (;; p[i]++) {
            int left = i - p[i];
            int right = i + p[i];

            if (left < 0 || right >= sChar.length) break; // boundary check
            if (sChar[left] != sChar[right]) break;
        }

        p[i]--;
        if (p[id] < p[i]) id = i; // update global max if necessary.
    }

    public String longestPalindrome(String s) {
        int newLength = 2 * s.length() + 1;
        sChar = new char[newLength];
        p = new int[newLength];
        id = 0;

        // ---------generate s array
        sChar[0] = '#';
        for (int i = 0; i < s.length(); i++) {
            sChar[2 * i + 1] = s.charAt(i);
            sChar[2 * i + 2] = '#';
        }
        // ----------

        for (int i = 0; i < sChar.length; i++) {
            findLongestPalindrome(i);
            System.out.println(new String(sChar, id - p[id], 2 * p[id]).replaceAll("#", ""));
        }

        // String.class(charArray,initial_offset,subChar_len)
        String result = new String(sChar, id - p[id], 2 * p[id]).replaceAll("#", "");
        return result;

    }

    public static void main(String[] args) {
        String result = new LongestPalindromicSubString().longestPalindrome("abab");
        //String result = new LongestPalindromicSubString().longestPalindrome("ababa aba");
        System.out.println(result);
    }
}