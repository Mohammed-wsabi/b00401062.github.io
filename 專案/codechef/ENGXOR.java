package codechef;

class ENGXOR {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        BufferedWriter stdout = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = stdin.nextInt();
        while (t-- > 0) {
            int n = stdin.nextInt();
            int q = stdin.nextInt();
            int lastA = 0;
            int nEvens = 0;
            for (int i = 0; i < n; i++) {
                lastA = stdin.nextInt();
                int cardinality = Integer.bitCount(lastA);
                nEvens += (cardinality % 2 == 0) ? 1 : 0;
            }
            boolean isLastACardinalityEven = Integer.bitCount(lastA) % 2 == 0;
            while (q-- > 0) {
                int p = stdin.nextInt();
                int cardinality = Integer.bitCount(p ^ lastA);
                boolean isCardinalitySame = (cardinality % 2 == 0) == isLastACardinalityEven;
                int resNEvens = isCardinalitySame ? nEvens : n - nEvens;
                stdout.write(String.format("%d %d\n", resNEvens, n - resNEvens));
            }
        }
        stdin.close();
        stdout.flush();
        stdout.close();
    }
}
