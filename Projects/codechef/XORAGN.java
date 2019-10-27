package codechef;

import java.io.*;
import java.util.*;

class XORAGN {
    public static void main (String[] args) throws IOException {
        Scanner stdin = new Scanner(System.in);
        int t = Integer.parseInt(stdin.nextLine());
        while (t-- > 0) {
            Integer.parseInt(stdin.nextLine());
            System.out.println(
                Arrays.stream(stdin.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .map(x -> x * 2)
                .reduce((x1, x2) -> x1 ^ x2)
                .getAsInt()
            );
        }
        stdin.close();
    }
}
