package codechef;

import java.io.*;
import java.util.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class DDIMMST {
    private static class Point {
        private int i;
        private int[] x;
        private int w = 0;

        public Point(int i, int[] x) {
            this.i = i;
            this.x = x;
        }

        public int w() {
            return this.w;
        }

        public void set(int w) {
            this.w = w;
        }
    }

    private static int distance(Point p1, Point p2) {
        int D = p1.x.length;
        int distance = 0;
        for (int i = 0; i < D; i++) {
            distance += Math.abs(p1.x[i] - p2.x[i]);
        }
        return distance;
    }

    public static void main(String[] args) throws Exception {
        Field queue = PriorityQueue.class.getDeclaredField("queue");
        queue.setAccessible(true);
        Method siftUp = PriorityQueue.class.getDeclaredMethod("siftUp", int.class, Object.class);
        siftUp.setAccessible(true);
        Scanner stdin = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int N = stdin.nextInt();
        final int D = stdin.nextInt();
        final Point[] p = new Point[N];
        for (int n = 0; n < N; n++) {
            int[] x = new int[D];
            for (int d = 0; d < D; d++) {
                x[d] = stdin.nextInt();
            }
            p[n] = new Point(n, x);
        }
        int weight = 0;
        PriorityQueue<Point> pool = new PriorityQueue<>(N, Comparator.comparingInt(Point::w).reversed());
        pool.addAll(Arrays.asList(p));
        Object[] array = (Object[]) queue.get(pool);
        while (!pool.isEmpty()) {
            Point s = pool.remove();
            Point t = null;
            for (int n = 0; n < N && (t = (Point) array[n]) != null; n++) {
                int w = distance(s, t);
                if (w > t.w) {
                    t.set(w);
                    siftUp.invoke(pool, n, t);
                }
            }
            weight += s.w;
        }
        System.out.println(weight);
        stdin.close();
    }
}
