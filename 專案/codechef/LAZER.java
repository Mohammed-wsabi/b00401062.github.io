package codechef;

import java.util.*;

class LAZER {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int t = reader.nextInt();
        while (t-- > 0) {
            int n = reader.nextInt();
            int q = reader.nextInt();
            int[] A = new int[n];
            for (int i = 0; i < n; i++) {
                A[i] = reader.nextInt();
            }
            int[][] Q = new int[q][3];
            int[] Y = new int[q];
            for (int i = 0; i < q; i++) {
                Q[i][0] = reader.nextInt();
                Q[i][1] = reader.nextInt();
                Q[i][2] = Y[i] = reader.nextInt();
            }
            Arrays.sort(Y);
            List<Integer>[] lists = new List[q];
            for (int i = 0; i < q; i++) {
                lists[i] = new ArrayList<>();
            }
            for (int i = 1; i < n; i++) {
                int y1 = A[i - 1];
                int y2 = A[i];
                int minY = Math.min(y1, y2);
                int maxY = Math.max(y1, y2);
                int minYIdx = Arrays.binarySearch(Y, minY);
                if (minYIdx < 0) minYIdx = -(minYIdx + 1);
                int maxYIdx = Arrays.binarySearch(Y, maxY);
                maxYIdx = (maxYIdx < 0) ? -(maxYIdx + 1) : (maxYIdx + 1);
                for (int j = minYIdx; j < maxYIdx; j++) {
                    lists[j].add(i);
                }
            }
            for (int i = 0; i < q; i++) {
                int x1 = Q[i][0];
                int x2 = Q[i][1];
                int y = Q[i][2];
                List<Integer> list = lists[Arrays.binarySearch(Y, y)];
                int x1Idx = Collections.binarySearch(list, x1);
                if (x1Idx < 0) x1Idx = -(x1Idx + 1);
                int x2Idx = Collections.binarySearch(list, x2);
                if (x2Idx < 0) x2Idx = -(x2Idx + 1);
                System.out.println(x2Idx - x1Idx);
            }
        }
        reader.close();
    }
}
