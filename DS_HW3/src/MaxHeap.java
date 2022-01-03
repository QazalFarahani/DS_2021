//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class MaxHeap {
//    private long[] Heap;
//    private int size;
//
//    private MaxHeap(int maxsize) {
//        this.size = 0;
//        Heap = new long[maxsize + 1];
//        Heap[0] = Integer.MAX_VALUE;
//    }
//
//    private int parent(int position) {
//        return position / 2;
//    }
//
//    private boolean isLeaf(int position) {
//        return position > (size / 2) && position <= size;
//    }
//
//    private void swap(int fPosition, int sPosition) {
//        long tmp;
//        tmp = Heap[fPosition];
//        Heap[fPosition] = Heap[sPosition];
//        Heap[sPosition] = tmp;
//    }
//
//    private long maxHeapify(int position, long count) {
//        if (isLeaf(position))
//            return count;
//
//        int l = 2 * position;
//        int r = (2 * position) + 1;
//        int bigChild;
//        if (l <= size && Heap[l] > Heap[position])
//            bigChild = l;
//        else
//            bigChild = position;
//        if (r <= size && Heap[r] > Heap[bigChild])
//            bigChild = r;
//        if (bigChild != position) {
//            swap(position, bigChild);
//            count++;
//            count = maxHeapify(bigChild, count);
//        }
//        return count;
//    }
//
//    private void insert(long element) {
//        Heap[++size] = element;
//        int current = size;
//        long count = 0;
//        while (Heap[current] > Heap[parent(current)]) {
//            swap(current, parent(current));
//            current = parent(current);
//            count++;
//        }
//        System.out.println(count);
//    }
//
//    private void extractMax() {
//        long max = Heap[1];
//        Heap[1] = Heap[size--];
//        long count = maxHeapify(1, 0);
//        System.out.println(max + " " + count);
//    }
//
//    public static void main(String[] arg) {
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            int n = Integer.parseInt(br.readLine());
//            MaxHeap maxHeap = new MaxHeap(n);
//            int f;
//            long input;
//            for (int i = 0; i < n; i++) {
//                StringTokenizer tokenizer = new StringTokenizer(br.readLine());
//                f = Integer.parseInt(tokenizer.nextToken());
//                if (f == 1) {
//                    maxHeap.extractMax();
//                } else if (f == 2) {
//                    input = Long.parseLong(tokenizer.nextToken());
//                    maxHeap.insert(input);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
