import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Trie trie = new Trie();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        ArrayList<String> input = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            input.add(scanner.nextLine());
        }
        sortByLength(input);
        int count = 0;
        for (int i = 0; i < n; i++) {
            count = trie.insert(input.get(i), count);
            if (count == m) {
                System.out.println(input.get(i).length());
                break;
            }
        }
        if (count != m)
            System.out.println(-1);
    }

    static class MyComparator implements java.util.Comparator<String> {
        MyComparator() {
            super();
        }

        public int compare(String s1, String s2) {
            int dist1 = Math.abs(s1.length());
            int dist2 = Math.abs(s2.length());
            return dist1 - dist2;
        }
    }

    private static void sortByLength(ArrayList<String> words) {
        words.sort(new MyComparator());
    }
}

class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    int insert(String key, int count) {
        int length = key.length();
        int index;
        TrieNode node = root;
        for (int i = 0; i < length; i++) {
            index = key.charAt(i) - 'a';
            if (node.children[index] == null)
                node.children[index] = new TrieNode();
            node = node.children[index];
            if (node.isEndOfWord) {
                node.isEndOfWord = false;
                count--;
            }
        }
        count++;
        node.isEndOfWord = true;
        return count;
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord;
}