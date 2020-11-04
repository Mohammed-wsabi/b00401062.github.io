package leetcode;

class MinDistance {
    public static int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i1 = 1; i1 <= word1.length(); i1++) {
            dp[i1][0] = i1;
        }
        for (int i2 = 1; i2 <= word2.length(); i2++) {
            dp[0][i2] = i2;
        }
        for (int i1 = 0; i1 < word1.length(); i1++) {
            for (int i2 = 0; i2 < word2.length(); i2++) {
                char c1 = word1.charAt(i1), c2 = word2.charAt(i2);
                dp[i1 + 1][i2 + 1] = Math.min(
                    dp[i1][i2] + (c1 == c2 ? 0 : 1),
                    Math.min(dp[i1][i2 + 1], dp[i1 + 1][i2]) + 1
                );
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
