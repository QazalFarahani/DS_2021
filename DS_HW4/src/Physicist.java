import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

class Physicist {
    public static void main(String[] args) throws IOException {
        Reader scanner = new Reader();
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        HashMap<Integer, List<Integer>> adjDirected = new HashMap<>();
        HashMap<Integer, List<Integer>> adjNotDirected = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjDirected.put(i, new ArrayList<>());
            adjNotDirected.put(i, new ArrayList<>());
        }
        int p, q;
        for (int i = 0; i < m; i++) {
            p = scanner.nextInt() - 1;
            q = scanner.nextInt() - 1;

            adjDirected.get(p).add(q);
            adjNotDirected.get(p).add(q);
            adjNotDirected.get(q).add(p);
        }

        int DAGs = countDAGs(n, adjDirected, adjNotDirected);
        System.out.println(n - DAGs);
    }

    private static int countDAGs(int n, HashMap<Integer, List<Integer>> adjDirected, HashMap<Integer, List<Integer>> adjNotDirected) {
        boolean[] visited = new boolean[n];
        boolean[] discovered = new boolean[n];
        int[] departure = new int[n];
        Graph graph = new Graph(adjDirected);
        int count = 0;
        for (int i = 0; i < n; ++i)
            if (!visited[i]) {
                ArrayList<Integer> list = new ArrayList();
                DFS(adjNotDirected, i, visited, list);
                if (isDAG(graph, graph.getSize(), list, discovered, departure))
                    count++;
            }
        return count;
    }

    private static void DFS(HashMap<Integer, List<Integer>> adjNotDirected, int start, boolean[] visited, ArrayList<Integer> nodes) {
        visited[start] = true;
        List<Integer> adjacency = adjNotDirected.get(start);
        nodes.add(start);
        for (int node : adjacency) {
            if (!visited[node]) {
                DFS(adjNotDirected, node, visited, nodes);
            }
        }
    }

    private static int DAG_DFS(Graph graph, int v, boolean[] discovered, int[] departure, int time) {
        discovered[v] = true;
        for (int u : graph.adjList.get(v)) {
            if (!discovered[u]) {
                time = DAG_DFS(graph, u, discovered, departure, time);
            }
        }
        departure[v] = time++;
        return time;
    }

    private static boolean isDAG(Graph graph, int n, ArrayList<Integer> list, boolean[] discovered, int[] departure) {
        int time = 0;
        for (int i : list) {
            if (!discovered[i]) {
                time = DAG_DFS(graph, i, discovered, departure, time);
            }
        }

        for (int u : list) {
            for (int v : graph.adjList.get(u)) {
                if (departure[u] <= departure[v]) {
                    return false;
                }
            }
        }
        return true;
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }

}

class Node {
    int familyIndex;
    int value;

    Node(int value, int index) {
        this.value = value;
        this.familyIndex = index;
    }

}


class Graph {
    Map<Integer, List<Integer>> adjList;

    Graph(Map<Integer, List<Integer>> adjList) {
        this.adjList = adjList;
    }

    int getSize() {
        return adjList.size();
    }
}
