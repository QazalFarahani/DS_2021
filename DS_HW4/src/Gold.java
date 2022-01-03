import java.util.Scanner;

public class Gold {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numberOne = scanner.nextLine();
        String numberTwo = scanner.nextLine();
        int n, k;
        long answer = 0;
        n = numberOne.length();
        k = numberTwo.length();
        if (k > n)
            System.out.println(0);
        else if (k == n) {
            if (numberOne.compareTo(numberTwo) >= 0) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        } else {
            if (2 * k < n) {
                answer = handleMiddleOnes(n - 2 * k) + handleCountUntil(numberOne, numberTwo, n, k) % 1000000007;
            } else if (2 * k == n) {
                answer = handleCountUntil(numberOne, numberTwo, n, k) % 1000000007;
            }
            answer = (answer + handleOverlaps(numberTwo, k)) % 1000000007;
            System.out.println(answer);
        }

    }

    public static long handleOverlaps(String numberTwo, int k) {
        int[] lps = LPS(numberTwo, k);
        int c = 0;
        while (lps[lps.length - 1] > 0) {
            c++;
            lps = LPS(numberTwo, lps[lps.length - 1]);
        }
        return c + 1;
    }

    private static long handleCountUntil(String numberOne, String numberTwo, int n, int k) {
        String firstK = numberOne.substring(0, k);
        String middle = numberOne.substring(k, n - k);
        String lastK = numberOne.substring(n - k, n);
        if (firstK.compareTo(numberTwo) > 0) {
            return getMod(countUntil(middle.length(), "1", "0"));
        } else if (firstK.compareTo(numberTwo) == 0) {
            if (lastK.compareTo(numberTwo) >= 0) {
                return (getMod(middle) + 1) % 1000000007;
            } else {
                return getMod(middle) % 1000000007;
            }
        } else
            return 0;
    }

    public static int[] LPS(String pattern, int n) {
        int[] lps = new int[n];
        int len = 0;
        int i = 1;
        lps[0] = 0;
        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }

    private static String countUntil(int count, String start, String addElement) {
        StringBuilder temp = new StringBuilder(start);
        for (int i = 0; i < count; i++) {
            temp.append(addElement);
        }
        return temp.toString();
    }

    private static long handleMiddleOnes(int count) {
        String temp = countUntil(count, "", "1");
        return getMod(temp) % 1000000007;
    }

    private static long getMod(String number) {
        long res = 0;
        for (int i = 0; i < number.length(); i++) {
            res = (res * 10L + (long) number.charAt(i) - '0') % 1000000007;
        }
        return res;
    }
}
