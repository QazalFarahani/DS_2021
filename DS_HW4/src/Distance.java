import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Distance {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());
        ArrayList<Integer>[] adjacency = new ArrayList[n];
        ArrayList<Integer>[] adjacencyTwo = new ArrayList[n];
        HashSet<Integer> setOne = new HashSet<>();
        HashSet<Integer> setTwo = new HashSet<>();
        for (int i = 0; i < n; i++) {
            adjacency[i] = new ArrayList<>();
            adjacencyTwo[i] = new ArrayList<>();
            setOne.add(i);
            setTwo.add(i);
        }
        int p, q;
        int cost;

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            p = Integer.parseInt(tokenizer.nextToken()) - 1;
            q = Integer.parseInt(tokenizer.nextToken()) - 1;
            cost = Integer.parseInt(tokenizer.nextToken());
            if (cost <= k) {
                adjacency[p].add(q);
                adjacency[q].add(p);
            }
            if (cost < k) {
                adjacencyTwo[p].add(q);
                adjacencyTwo[q].add(p);
            }
        }

        long withK = countNodes(adjacency, setOne, n);
//        long withOutK = countNodes(adjacency, setOne);
        long withOutK = countNodes(adjacencyTwo, setTwo, n);
        System.out.println(withK - withOutK);
    }

    private static long countNodes(ArrayList<Integer>[] adjacency, HashSet<Integer> set, int n) {
        long nodes;
        long paths = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++i)
            if (!visited[i]) {
                nodes = DFS(adjacency, i, visited, 1);
                paths += (nodes) * (nodes - 1) / 2;
            }
        return paths;
    }

    private static long DFS(ArrayList<Integer>[] adjacency, int start, boolean[] visited, long count) {
        visited[start] = true;
        Iterator<Integer> i = adjacency[start].listIterator();
        while (i.hasNext()) {
            int node = i.next();
            if (!visited[node]) {
                count += DFS(adjacency, node, visited, 1);
            }
        }
        return count;
    }
}



