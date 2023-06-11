import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class AutomatonMatcher implements IStringMatcher
{
    private static final char[] alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    @Override
    public List<Integer> validShifts(String T, String P) {
        int n = T.length();
        int m = P.length();

        int q = 0;

        int[][] d = computeTransitionFunction(P, alphabet);

        List<Integer> results = new ArrayList<>();

        for (int i = 0; i < d.length; i++)
        {
            for (int j = 0; j < d[i].length; j++)
            {
                System.out.print(d[i][j]);
            }
            System.out.println();
        }

        for (int i = 0; i < n; i++)
        {
            q = d[q][indexOf(alphabet, T.charAt(i))];

            System.out.println("\n" + q + " " + m);
            if (q == m)
            {
                int s = i - m;

                results.add(s);
            }
        }

        return results;
    }

    public static int[][] computeTransitionFunction(String P, char[] alphabet) {
        int m = P.length();
        int[][] d = new int[m + 1][alphabet.length];

        for (int q = 0; q <= m; q++)
        {
            for (int a = 0; a < alphabet.length; a++)
            {
                int k = Math.min(m + 1, q + 1);

                do
                {
                    k--;
                }
                while (!prefixMatch(P, k, P, q, alphabet[a]));

                d[q][a] = k;
                a++;
            }
        }

        return d;
    }

    public static int indexOf(char[] arr, char val) {
        return IntStream.range(0, arr.length).filter(i -> arr[i] == val).findFirst().orElse(-1);
    }

    public static boolean prefixMatch(String P, int k, String Q, int q, char a) {
        while (k < P.length() && q < Q.length()) {
            if (P.charAt(k) != Q.charAt(q)) {
                return false;
            }

            k++;
            q++;
        }

        if (k < P.length()){
            return P.charAt(P.length() - 1) == a;
        }

        return k == P.length();
    }
}
