import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {

        int digits = 5;
        long start = System.currentTimeMillis();
        long[] polindrom = new Solution().findMaxPolindrom(digits);
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
        System.out.println(Arrays.toString(polindrom));

    }

    private long[] findMaxPolindrom(int x) {
        List<Integer> primeNumber = getPrimeNumber(x);
        return getPolindrom(primeNumber);
    }

    private long[] getPolindrom(List<Integer> list) {
        long[] polindrom = new long[3];
        int k;

        first:
        for (int i = k = list.size() - 1; i >= 0 && k < list.size(); i--) {
            for (int j = list.size() - 1; j >= k; j--) {
                long number = (long) list.get(i) * list.get(j);
                if (polindrom[0] >= number) {
                    k = j + 1;
                    continue first;
                }
                if (isPalindrome(number)) {
                    polindrom[0] = number;
                    polindrom[1] = list.get(i);
                    polindrom[2] = list.get(j);
                }
            }
            k--;
        }

        return polindrom;
    }


    private boolean isPalindrome(long x) {
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        long revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        return x == revertedNumber || x == revertedNumber/10;
    }

    private List<Integer> getPrimeNumber(int sing) {
        int startRange = sing == 1 ? 2 : (int) Math.pow(10, sing -1);
        int finishRange = (int) Math.pow(10, sing);

        boolean[] eratosphen = new boolean[finishRange];

        for (int i = 2; i*i < finishRange; i++) {
            if (!eratosphen[i]) {
                for (int j = 2 * i; j < finishRange; j += i) {
                    eratosphen[j] = true;
                }
            }

        }

        ArrayList<Integer> primes = new ArrayList<>();

        for (int i = startRange; i < finishRange; i++) {
            if (!eratosphen[i])
                primes.add(i);
        }

        return primes;
    }
}
