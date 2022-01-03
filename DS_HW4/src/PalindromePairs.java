import java.util.*;

public class PalindromePairs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = scanner.nextLine();
        }
        System.out.println(getPairs(words));
    }

    private static long getPairs(String[] words) {
        long count = 0;
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (map.get(words[i]) != null)
                map.get(words[i]).add(i);
            else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(words[i], list);
            }
        }
        for (int i = 0; i < words.length; i++) {
            int l = 0, r = 0;
            while (l <= r) {
                String s = words[i].substring(l, r);
                ArrayList list = map.get(new StringBuilder(s).reverse().toString());
                if (list != null && isPalindrome(words[i].substring(l == 0 ? r : 0, l == 0 ? words[i].length() : l)))
                    count += list.size();
                if (list != null && list.contains(i))
                    count--;
                if (r < words[i].length()) r++;
                else l++;
            }
        }
        return count;
    }

    private static boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;
        while (left <= right) {
            if (word.charAt(left++) != word.charAt(right--)) return false;
        }
        return true;
    }
}