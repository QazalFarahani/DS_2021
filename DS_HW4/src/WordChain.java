import java.util.*;

public class WordChain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<>();
        String start = scanner.nextLine();
        String target = scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++)
            words.add(scanner.nextLine());
        System.out.print(shortestChain(start, target, words, start.length()));
    }

    private static int shortestChain(String start, String target, ArrayList<String> words, int length) {
        if(start.equals(target) || !words.contains(target))
            return 0;
        int count = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                char[] word = queue.peek().toCharArray();
                queue.remove();
                for (int j = 0; j < length; j++) {
                    char character = word[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        word[j] = c;
                        if (String.valueOf(word).equals(target))
                            return count + 1;
                        if (!words.contains(String.valueOf(word)))
                            continue;
                        words.remove(String.valueOf(word));
                        queue.add(String.valueOf(word));

                    }
                    word[j] = character;
                }
            }
        }
        return 0;
    }
}