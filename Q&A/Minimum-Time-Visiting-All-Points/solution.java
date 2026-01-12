public class solution {

    static int maxNorm(int[] P, int[] Q) {
        return Math.max(Math.abs(P[0] - Q[0]), Math.abs(P[1] - Q[1]));
    }

    public static int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;
        int[] prev = points[0];

        for (int[] P : points) {
            ans += maxNorm(P, prev);
            prev = P;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] points = {
                { 1, 1 },
                { 3, 4 },
                { -1, 0 }
        };

        int result = minTimeToVisitAllPoints(points);
        System.out.println("Minimum time to visit all points: " + result);
    }
}
