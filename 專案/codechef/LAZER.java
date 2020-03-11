package codechef;

import java.util.stream.Collectors;

class LAZER {
    private static class Query {
        protected int id;
        protected int x1;
        protected int x2;
        protected int y;
        protected int n;

        Query(int id, int x1, int x2, int y) {
            this.id = id;
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        StringTokenizer st;
        int t = Integer.parseInt(reader.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            Integer[] A = new Integer[n];
            st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
            Query[] Q = new Query[q];
            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(reader.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                Q[i] = new Query(i, x1, x2, y);
            }
            Map<Integer, List<Integer>> minYs = new HashMap<>();
            Map<Integer, List<Integer>> maxYs = new HashMap<>();
            for (int i = 1; i < n; i++) {
                int y1 = A[i - 1];
                int y2 = A[i];
                int minY = Math.min(y1, y2);
                int maxY = Math.max(y1, y2);
                List<Integer> minYXs = minYs.getOrDefault(minY, new ArrayList<>());
                minYXs.add(i);
                minYs.put(minY, minYXs);
                List<Integer> maxYXs = maxYs.getOrDefault(maxY, new ArrayList<>());
                maxYXs.add(i);
                maxYs.put(maxY, maxYXs);
            }
            TreeMap<Integer, List<Query>> yEvents = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                yEvents.put(A[i], new ArrayList<>());
            }
            for (int i = 0; i < q; i++) {
                List<Query> queries = yEvents.getOrDefault(Q[i].y, new ArrayList<>());
                queries.add(Q[i]);
                yEvents.put(Q[i].y, queries);
            }
            TreeSet<Integer> xCandis = new TreeSet<>();
            for (Map.Entry<Integer, List<Query>> yEvent : yEvents.entrySet()) {
                int y = yEvent.getKey();
                if (minYs.containsKey(y)) {
                    xCandis.addAll(minYs.get(y));
                }
                for (Query query : yEvent.getValue()) {
                    query.n = xCandis.subSet(query.x1, query.x2).size();
                }
                if (maxYs.containsKey(y)) {
                    xCandis.removeAll(maxYs.get(y));
                }
            }
            for (int i = 0; i < q; i++) {
                writer.println(Q[i].n);
            }
        }
        reader.close();
        writer.flush();
        writer.close();
    }
}
