package my.learning.dsa.p1510;

public class StoneGameIV {
    public static void main(String[] args) {
        StoneGameIV sg = new StoneGameIV();
        System.out.println(sg.winnerSquareGame(1)); //true
        System.out.println(sg.winnerSquareGame(2)); //false
        System.out.println(sg.winnerSquareGame(4)); //true
        System.out.println(sg.winnerSquareGame(7)); //false
    }

    /**
     * Backtracking and Dynamic Programming:
     * Top Dow approach
     * 1. Loop through all perfect square
     * <p>
     * dp(7) //false
     * i=1: dp(6) // true
     * i = 1: dp(5) // false
     * i= 1: dp(4) // true
     * i = 1 : dp(3) // true
     * i =1 : dp(2) //false
     * i=1 dp(1) // true
     * i=1 dp(0) // false
     * i = 2 : dp(0) //false
     * i = 2: dp(1) // true
     * i = 2: dp(3) //	true
     *
     * @param n
     * @return
     */
    public boolean winnerSquareGame_1(int n) {
        return dp_td(n, new Boolean[1000]);
    }

    private boolean dp_td(int n, Boolean[] memo) {
        if (n == 0) {
            return false;
        }
        if (memo[n] != null) {
            return memo[n];
        }
        boolean isValid = false;
        for (int i = 1; i * i <= n; i++) {
            if (!dp_td(n - i * i, memo)) {
                isValid = true;
                break;
            }
        }
        memo[n] = isValid;
        return isValid;
    }

    /**
     * Dynamic Programming : Bottom up
     *
     * @param n
     * @return
     */
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
