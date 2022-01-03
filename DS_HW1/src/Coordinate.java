import java.util.Arrays;
import java.util.Scanner;

public class Coordinate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String string = scanner.nextLine();
        long[] left = new long[n];
        long[] right = new long[n];
        Integer[] array = Arrays.stream(string.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);


        Stack<Integer> q = new Stack();
        for (int i = 0; i < n; i++) {
            while (q.size() != 0 && array[q.top()] <= array[i]) {
                left[i] += left[q.top()] + 1;
                q.pop();
            }
            q.push(i);
        }
        while (q.size() != 0)
            q.pop();
        for (int i = n - 1; i >= 0; i--) {
            while (q.size() != 0 && array[q.top()] < array[i]) {
                right[i] += right[q.top()] + 1;
                q.pop();
            }
            q.push(i);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += ((left[i] + 1) * (right[i] + 1) * array[i]) % 105131359;
            if (ans < 0)
                ans += 105131359;
        }

        System.out.println(ans % 105131359);
    }


}
