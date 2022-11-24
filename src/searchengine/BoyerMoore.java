package searchengine;

public class BoyerMoore {
	 private final int R;     // radix
	 private int[] right;  // skip array

    private String pat;

    public BoyerMoore(String pat) {
        this.R = 100000;
        this.pat = pat;

        // rightmost occurrence
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pat.length(); j++)
            right[Character.toLowerCase(pat.charAt(j))] = j;
    }

    // index of first match; N if no match is found
    public int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (Character.toLowerCase(pat.charAt(j)) != Character.toLowerCase(txt.charAt(i+j))) {
                    skip = Math.max(1, j - right[Character.toLowerCase(txt.charAt(i+j))]);
                    break;
                }
            }
            if (skip == 0) return i;
        }
        return N;
    }
}
