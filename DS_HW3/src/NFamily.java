import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class NFamily {
    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Node>> iMap = new HashMap<>();
        HashMap<Integer, ArrayList<Node>> jMap = new HashMap<>();
        int n = 0;
        int m = 0;
        try {
            Reader reader = new Reader();
            n = reader.nextInt();
            m = reader.nextInt();
            int a, b, c, d;
            for (int i = 1; i <= m; i++) {
                iMap.put(i, new ArrayList<>());
                jMap.put(i, new ArrayList<>());
            }
            for (int i = 0; i < n; i++) {
                a = reader.nextInt();
                b = reader.nextInt();
                c = reader.nextInt();
                d = reader.nextInt();
                iMap.get(a).add(new Node(c, i + 1));
                jMap.get(b).add(new Node(d, i + 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] iArray = new int[n];
        int[] jArray = new int[n];
        boolean b;
        b = calc(iMap, iArray, m, n);
        if (b)
            b = calc(jMap, jArray, m, n);
        if (!b)
            System.out.println(-1);
        else {
            for (int i = 0; i < n; i++) {
                System.out.println(iArray[i] + " " + jArray[i]);
            }
        }

    }

    private static boolean calc(HashMap<Integer, ArrayList<Node>> map, int[] array, int m, int n) {
        MinHeap heap = new MinHeap(n);
        ArrayList<Node> list;
        Node node;
        for (int i = 1; i <= m; i++) {
            list = map.get(i);
            for (int k = 0; k < list.size(); k++) {
                heap.insert(list.get(k));
            }
            if (heap.size != 0) {
                node = heap.extractMin();
                if (i > node.value) {
                    return false;
                } else
                    array[node.familyIndex - 1] = i;
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

class MinHeap {
    private Node[] Heap;
    int size;

    MinHeap(int maxsize) {
        this.size = 0;
        Heap = new Node[maxsize + 1];
        Heap[0] = new Node(Integer.MIN_VALUE, -1);
    }

    private int parent(int position) {
        return position / 2;
    }

    private boolean isLeaf(int position) {
        return position > (size / 2) && position <= size;
    }

    private void swap(int fPosition, int sPosition) {
        Node tmp;
        tmp = Heap[fPosition];
        Heap[fPosition] = Heap[sPosition];
        Heap[sPosition] = tmp;
    }

    private void Heapify(int position) {
        if (isLeaf(position))
            return;
        int l = 2 * position;
        int r = (2 * position) + 1;
        int child;
        if (l <= size && Heap[l].value < Heap[position].value)
            child = l;
        else
            child = position;
        if (r <= size && Heap[r].value < Heap[child].value)
            child = r;
        if (child != position) {
            swap(position, child);
            Heapify(child);
        }
    }

    void insert(Node node) {
        Heap[++size] = node;
        int current = size;
        while (Heap[current].value < Heap[parent(current)].value) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    Node extractMin() {
        Node max = Heap[1];
        Heap[1] = Heap[size--];
        Heapify(1);
        return max;
    }
}


