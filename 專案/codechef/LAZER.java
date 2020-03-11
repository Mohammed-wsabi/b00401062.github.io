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

        protected int getID() { return this.id; }
        protected int getY() { return this.y; }
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
            Map<Integer, List<Integer>> xAtMinY = new HashMap<>();
            Map<Integer, List<Integer>> xAtMaxY = new HashMap<>();
            for (int i = 1; i < n; i++) {
                int y1 = A[i - 1];
                int y2 = A[i];
                int minY = Math.min(y1, y2);
                int maxY = Math.max(y1, y2);
                List<Integer> minYXs = xAtMinY.getOrDefault(minY, new ArrayList<>());
                minYXs.add(i);
                xAtMinY.put(minY, minYXs);
                List<Integer> maxYXs = xAtMaxY.getOrDefault(maxY, new ArrayList<>());
                maxYXs.add(i);
                xAtMaxY.put(maxY, maxYXs);
            }
            TreeSet<Integer> yEvents = new TreeSet<>();
            yEvents.addAll(Arrays.asList(A));
            yEvents.addAll(Arrays.stream(Q).map(Query::getY).collect(Collectors.toList()));
            Arrays.sort(Q, Comparator.comparing(Query::getY));
            TreeSet<Integer> xCandis = new TreeSet<>();
            int nProcessedQueries = 0;
            for (int y : yEvents) {
                if (xAtMinY.containsKey(y)) {
                    xCandis.addAll(xAtMinY.get(y));
                }
                while (nProcessedQueries < q && y == Q[nProcessedQueries].y) {
                    Query query = Q[nProcessedQueries];
                    int x1 = query.x1;
                    int x2 = query.x2;
                    query.n = xCandis.subSet(x1, x2).size();
                    nProcessedQueries++;
                }
                if (xAtMaxY.containsKey(y)) {
                    xCandis.removeAll(xAtMaxY.get(y));
                }
            }
            Arrays.sort(Q, Comparator.comparing(Query::getID));
            for (int i = 0; i < q; i++) {
                writer.println(Q[i].n);
            }
        }
        reader.close();
        writer.flush();
        writer.close();
    }
}
