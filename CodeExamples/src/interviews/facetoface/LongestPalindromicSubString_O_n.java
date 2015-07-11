package interviews.facetoface;

// This implementation has a complexity of O(n).
public class LongestPalindromicSubString_O_n {

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
        String result = new LongestPalindromicSubString().longestPalindromeSlow("abab");
        //String result = new LongestPalindromicSubString().longestPalindrome("ababa aba");
        System.out.println(result);
    }
}
