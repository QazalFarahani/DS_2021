import java.util.Scanner;

public class TheOther {
    static String N;
    static String M;
    static long divisor = 1000000007L;

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextLine();
        M = scanner.nextLine();
        System.out.println(solve());
    }

    public static String solve() {
        if (N.length() < M.length()) {
            return "0";
        } else if (N.length() == M.length()) {
            if (AcomparetoB(N, M) >= 0) return "1";
            else return "0";
        } else {
            int dif = N.length() - 2 * M.length();
            if (dif < 0) {
                return String.valueOf(lowDigits());
            } else if (dif == 0) {
                long x = (lowDigits() + highDigits()) % divisor;
                return String.valueOf(x);
            } else {
                long x = (lowDigits() + highDigits() + midDigits()) % divisor;
                return String.valueOf(x);
            }
        }

    }

    public static long lowDigits() {
        int[] ints = LPSCalculator(M, M.length());
        if (ints[ints.length - 1] == 0) {
            return 1;
        } else {
            int c = 0;
            while (ints[ints.length - 1] > 0) {
                c++;
                ints = LPSCalculator(M, ints[ints.length - 1]);
            }
            return 1 + c;
        }
    }

    public static long midDigits() {
        int dif = N.length() - 2 * M.length();
        long res = 0;
        if (dif > 0) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < dif; i++) {
                s.append(1);
            }
            return mod(s.toString());
        }
        return res;
    }

    public static long highDigits() {
        if (AcomparetoB(N.substring(0, M.length()), M) < 0) {
            return 0;
        } else if (AcomparetoB(N.substring(0, M.length()), M) > 0) {
            StringBuilder s = new StringBuilder("1");
            long dif = N.length() - 2L * M.length();
            for (int i = 0; i < dif; i++) {
                s.append("0");
            }
            return mod(s.toString());
        } else {

            int i = AcomparetoB(N.substring(N.length() - M.length()), M);
            String substring = N.substring(M.length(), N.length() - M.length());
            if (i >= 0) {
                return mod(substring) + 1;
            } else {
                return mod(substring);
            }
        }
    }

    public static int AcomparetoB(String a, String b) {
        if (a.length() < b.length()) return -1;
        else if (a.length() == b.length()) {
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) > b.charAt(i)) return 1;
                else if (a.charAt(i) < b.charAt(i)) return -1;
            }
            return 0;
        } else {
            return 1;
        }
    }

    public static int[] LPSCalculator(String temp, int n) {
        int[] lps = new int[n];
        lps[0] = 0;
        for (int i = 1; i < n; i++) {
            int len = lps[i - 1];
            while (len > 0 && temp.charAt(len) !=
                    temp.charAt(i)) {
                len = lps[len - 1];
            }
            if (temp.charAt(i) == temp.charAt(len)) {
                len++;
            }
            lps[i] = len;
        }
        return lps;
    }

    static long mod(String num) {
        long res = 0;
        for (int i = 0; i < num.length(); i++) {
            res = (res * 10L + (long) num.charAt(i) - '0') % divisor;
        }
        return res;
    }
}