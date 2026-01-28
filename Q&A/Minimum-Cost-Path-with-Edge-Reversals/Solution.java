import java.util.*;

public class Solution {

    public int minCost(int n, int[][] edges) {
        // Building augmented graph
        ArrayList<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1], w = edges[i][2];
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, 2 * w});
        }

        // Initialize distance array
        final int INF = 1_000_000_000;
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        // Dijkstra
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0], u = cur[1];

            if (u == n - 1)
                return d;

            if (d != dist[u])
                continue;

            for (int[] e : adj[u]) {
                int v = e[0], w = e[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new int[]{dist[v], v});
                }
            }
        }

        return -1;
    }

    // ✅ Main method for VS Code
    public static void main(String[] args) {
        Solution sol = new Solution();

        int n = 3;
        int[][] edges = {
            {0, 1, 2},
            {1, 2, 3}
        };

        int result = sol.minCost(n, edges);
        System.out.println("Minimum cost: " + result);
    }
}

