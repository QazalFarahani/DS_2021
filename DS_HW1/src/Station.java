import java.util.Scanner;
import java.util.Arrays;

public class Station {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Integer[] array = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        int m = 0;
        int j = n;
        boolean possible = true;
        StringBuilder operations = new StringBuilder();
        Stack<Integer> stack = new Stack<>();


        for (int i = n - 1; i >= 0; i--) {
            while (array[i] < j) {
                stack.push(j);
                operations.append("1");
                j--;
            }
            if (array[i] == j) {
                operations.append("2");
                j--;
            } else if (stack.top() != null && stack.top().equals(array[i])) {
                stack.pop();
                operations.append("3");
            } else {
                possible = false;
                break;
            }
        }


        if (possible) {
            System.out.println(operations.length());
            for (int i = 0; i < operations.length(); i++)
                System.out.print(operations.charAt(i) + " ");
        }
        else
            System.out.println(-1);
    }


}

