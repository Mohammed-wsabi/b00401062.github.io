package codechef;

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

    private static class Event {
        protected List<Integer> xis = new ArrayList<>();
        protected List<Integer> xos = new ArrayList<>();
        protected List<Query> queries = new ArrayList<>();
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
            Map<Integer, Event> events = new TreeMap<>();
            for (int i = 1; i < n; i++) {
                int y1 = A[i - 1];
                int y2 = A[i];
                int minY = Math.min(y1, y2);
                int maxY = Math.max(y1, y2);
                Event minYEvent = events.getOrDefault(minY, new Event());
                minYEvent.xis.add(i);
                events.put(minY, minYEvent);
                Event maxYEvent = events.getOrDefault(maxY, new Event());
                maxYEvent.xos.add(i);
                events.put(maxY, maxYEvent);
            }
            for (int i = 0; i < q; i++) {
                Event event = events.getOrDefault(Q[i].y, new Event());
                event.queries.add(Q[i]);
                events.put(Q[i].y, event);
            }
            BST<Integer> xCandis = new BST<>();
            for (Event event : events.values()) {
                for (Integer x : event.xis) {
                    xCandis.add(x);
                }
                for (Query query : event.queries) {
                    query.n = xCandis.size(query.x1, query.x2);
                }
                for (Integer x : event.xos) {
                    xCandis.remove(x);
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
