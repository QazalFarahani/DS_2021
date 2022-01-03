import java.util.Scanner;

public class PrePalindrome {
    private static int longestPalSubstr(String str) {
        int maxLength = 1;
        int n = str.length();
        int left, right;
        for (int i = 1; i < n; ++i) {
            right = i;
            left = i - 1;
            while (left >= 0 && right < n && str.charAt(left) == str.charAt(right)) {
                if (right - left + 1 > maxLength && left == 0) {
                    maxLength = right - left + 1;
                }
                --left;
                ++right;
            }
            left = i - 1;
            right = i + 1;
            while (left >= 0 && right < n && str.charAt(left) == str.charAt(right)) {
                if (right - left + 1 > maxLength && left == 0) {
                    maxLength = right - left + 1;
                }
                --left;
                ++right;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
        StringBuilder input = new StringBuilder();
        for (int i = 0; i < 100000; i++)
            input.append('a');
        long time = System.currentTimeMillis();
        System.out.println(longestPalSubstr(input.toString()));
        System.out.println(System.currentTimeMillis() - time);
    }
}