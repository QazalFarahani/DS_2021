import java.util.*;
import java.io.*;

public class MST {

    public static void main(String[] args) throws IOException {
        int maxW = 1000000;
        int n;
        int m;
        ArrayList<Edge>[] edges = new ArrayList[maxW];

        Reader scanner = new Reader();
        n = scanner.nextInt();
        m = scanner.nextInt();
        edges = createEdges(edges, maxW);
        int a, b, w;
        for (int i = 0; i < m; i++) {
            a = scanner.nextInt() - 1;
            b = scanner.nextInt() - 1;
            w = scanner.nextInt();
            edges[w].add(new Edge(a, b, w, i));
        }

        solve(n, m, maxW, edges);
    }

    private static ArrayList<Edge>[] createEdges(ArrayList<Edge>[] list, int max) {
        for (int i = 0; i < max; i++)
            list[i] = new ArrayList<>();
        return list;
    }

    private static void solve(int n, int m, int maxW, ArrayList<Edge>[] edges) {
        int[] answers = new int[m];
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        DisjointSet disjointSet = new DisjointSet(n);
        for (int i = 0; i < maxW; i++) {
            if (edges[i].size() != 0)
                evalStage(edges[i], disjointSet, answers);
        }

        for (int ans : answers) {
            if (ans == 0) out.println("any");
            else if (ans == 1) out.println("at least one");
            else out.println("none");
        }
        out.close();
    }

    private static void evalStage(ArrayList<Edge> edges, DisjointSet disjointSet, int[] answers) {
        int tempN = 0;
        HashMap<Integer, Integer> TempGraph = new HashMap<>();
        for (Edge e : edges) {
            int setA = disjointSet.find(e.a);
            int setB = disjointSet.find(e.b);
            e.a = setA;
            e.b = setB;
            if (!TempGraph.containsKey(setA))
                TempGraph.put(setA, tempN++);
            if (!TempGraph.containsKey(setB))
                TempGraph.put(setB, tempN++);
        }

        ArrayList<Edge>[] adjacencyList = new ArrayList[tempN];
        createEdges(adjacencyList, tempN);
        int numberOfEdges = createTempGraph(edges, disjointSet, TempGraph, answers, adjacencyList);
        handleBridges(tempN, numberOfEdges, answers, adjacencyList);
    }

    private static void handleBridges(int tempN, int numberOfEdges, int[] answers, ArrayList<Edge>[] adjacencyList) {
        boolean[] usedEdge = new boolean[numberOfEdges];
        int[] depth = new int[tempN];
        int[] dfs_low = new int[tempN];
        int currDisc = 1;
        for (int i = 0; i < tempN; i++)
            findBridges(i, answers, depth, dfs_low, usedEdge, adjacencyList, currDisc);
    }

    private static void findBridges(int n, int[] answers, int[] disc, int[] ll, boolean[] usedEdge, ArrayList<Edge>[] adjacencyList, int currDisc) {
        disc[n] = currDisc++;
        ll[n] = disc[n];
        for (Edge e : adjacencyList[n]) {
            if (usedEdge[e.i]) continue;
            usedEdge[e.i] = true;
            if (disc[e.n] == 0) {
                findBridges(e.n, answers, disc, ll, usedEdge, adjacencyList, currDisc);
                if (ll[e.n] > disc[n])
                    answers[e.origI] = 0;
                else
                    answers[e.origI] = 1;
                ll[n] = Math.min(ll[e.n], ll[n]);
            } else {
                answers[e.origI] = 1;
                ll[n] = Math.min(disc[e.n], ll[n]);
            }
        }
    }

    private static int createTempGraph(ArrayList<Edge> edges, DisjointSet disjointSet, HashMap<Integer,
            Integer> TempGraph, int[] answers, ArrayList<Edge>[] adjacencyList) {

        int numberOfEdges = 0;
        for (Edge e : edges) {
            disjointSet.union(e.a, e.b);
            e.a = TempGraph.get(e.a);
            e.b = TempGraph.get(e.b);
            if (e.a == e.b)
                answers[e.origI] = 2;
            else {
                adjacencyList[e.a].add(new Edge(e.b, numberOfEdges, e.origI));
                adjacencyList[e.b].add(new Edge(e.a, numberOfEdges, e.origI));
                numberOfEdges++;
            }
        }
        return numberOfEdges;
    }

    static class Edge {
        private int a;
        private int b;
        private int w;
        private int origI;
        private int n;
        private int i;

        Edge(int a, int b, int w, int origI) {
            this.a = a;
            this.b = b;
            this.w = w;
            this.origI = origI;
        }

        Edge(int n, int i, int origI) {
            this.n = n;
            this.i = i;
            this.origI = origI;
        }
    }

    static class DisjointSet {
        int size;
        int[] vals;

        DisjointSet(int size) {
            this.size = size;
            vals = new int[size];
            Arrays.fill(vals, -1);
        }

        void union(int a, int b) {
            int setA = find(a);
            int setB = find(b);
            if (setA == setB) return;
            if (vals[setA] < vals[setB]) {
                vals[setA] += vals[setB];
                vals[setB] = setA;
            } else {
                vals[setB] += vals[setA];
                vals[setA] = setB;
            }
        }

        int find(int i) {
            if (vals[i] < 0) return i;
            else {
                int res = find(vals[i]);
                vals[i] = res;
                return res;
            }
        }
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
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
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

		 						   	  	 	  	 	  	 	 	