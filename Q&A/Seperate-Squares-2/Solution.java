class Solution {
    public double separateSquares(int[][] squares) {

        List<int[]> blocks = new ArrayList<>();
        for (int square[] : squares) {
            int x = square[0];
            int y = square[1];
            int len = square[2];
            blocks.add(new int[] { y, 1, x, x + len }); // Start from bottom
            blocks.add(new int[] { y + len, -1, x, x + len }); // Stop at top
        }

        // Sort by y values ascending because we start our line sweep from bottom
        Collections.sort(blocks, (a, b) -> {
            return Integer.compare(a[0], b[0]);
        });

        // LINE SWEEP 1 : To calculate the total area occupied
        double totalArea = 0;
        List<int[]> active = new ArrayList<>(); // Active x - intervals
        for (int i = 0; i < blocks.size() - 1; i++) {
            int e[] = blocks.get(i);

            if (e[1] == 1) {
                active.add(new int[] { e[2], e[3] });
            } else {
                removeInterval(active, e[2], e[3]);
            }

            double currY = blocks.get(i)[0];
            double nextY = blocks.get(i + 1)[0];
            double height = nextY - currY;

            if (height > 0) {
                double union = helperWidth(active);
                totalArea += union * height;
            }
        }

        // LINE SWEEP 2 : To calulate the middle splitting y
        double target = totalArea / 2.0;
        double areaBelow = 0;
        active.clear();

        for (int i = 0; i < blocks.size() - 1; i++) {
            int e[] = blocks.get(i);

            if (e[1] == 1) {
                active.add(new int[] { e[2], e[3] });
            } else {
                removeInterval(active, e[2], e[3]);
            }

            double currY = blocks.get(i)[0];
            double nextY = blocks.get(i + 1)[0];
            double height = nextY - currY;

            if (height > 0) {
                double union = helperWidth(active);
                double currArea = union * height;

                if (areaBelow + currArea >= target) {
                    return currY + (target - areaBelow) / union;
                }

                areaBelow += currArea;
            }
        }
        return 0;
    }

    public double helperWidth(List<int[]> active) {
        if (active.isEmpty())
            return 0;

        Collections.sort(active, (a, b) -> {
            return a[0] - b[0];
        });

        double width = 0;
        int start = active.get(0)[0];
        int end = active.get(0)[1];
        for (int i = 1; i < active.size(); i++) {
            int curr[] = active.get(i);
            if (curr[0] > end) {
                width += end - start;
                start = curr[0];
                end = curr[1];
            } else {
                end = Math.max(end, curr[1]);
            }
        }
        width += end - start;
        return width;
    }

    public void removeInterval(List<int[]> active, int a, int b) {
        for (int i = 0; i < active.size(); i++) {
            if (active.get(i)[0] == a && active.get(i)[1] == b) {
                active.remove(i);
                return;
            }
        }
    }
}
