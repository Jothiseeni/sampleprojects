import java.util.*;

class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[101][101];
        dp[0][0] = poured;

        for (int r = 0; r <= query_row; r++) {
            for (int c = 0; c <= r; c++) {
                if (dp[r][c] > 1) {
                    double overflow = (dp[r][c] - 1) / 2.0;
                    dp[r][c] = 1;
                    dp[r + 1][c] += overflow;
                    dp[r + 1][c + 1] += overflow;
                }
            }
        }

        return Math.min(1, dp[query_row][query_glass]);
    }
}

// Main class for VS Code
public class Main {
    public static void main(String[] args) {

        Solution sol = new Solution();

        // Sample input
        int poured = 10;
        int query_row = 3;
        int query_glass = 1;

        double result = sol.champagneTower(poured, query_row, query_glass);

        System.out.println("Champagne in Glass = " + result);
    }
}
