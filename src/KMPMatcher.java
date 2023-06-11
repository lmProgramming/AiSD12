import java.util.ArrayList;
import java.util.List;

public class KMPMatcher implements IStringMatcher
{
    @Override
    public List<Integer> validShifts(String T, String P)
    {
        int n = T.length();
        int m = P.length();
        int[] PI = ComputePrefixFunction(P);
        int q = 0;

        List<Integer> results = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            while (q > 0 && P.charAt(q) != T.charAt(i)) {
                q = PI[q];
            }

            if (P.charAt(q) == T.charAt(i)) {
                q = q + 1;
            }

            if (q == m) {
                int index = i - m + 1;
                results.add(index);

                q = PI[q];
            }
        }

        return results;
    }

    public int[] ComputePrefixFunction(String P)
    {
        int m = P.length();
        int[] PI = new int[m + 1];
        PI[0] = 0;
        int k = 0;

        for (int q = 1; q < m; q++) {
            while (k > 0 && P.charAt(k) != P.charAt(q)) {
                k = PI[k];
            }

            if (P.charAt(k) == P.charAt(q)) {
                k = k + 1;
            }

            PI[q] = k;
        }

        return PI;
    }
}
