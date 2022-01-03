//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//class BinaryTree {
//    private Node root;
//
//    private BinaryTree() {
//        root = null;
//    }
//
//    private void insert(Node current, Node parent, long value) {
//        if (parent == null && current == null)
//            this.root = new Node(value, null);
//        else if (current == null) {
//            new Node(value, parent);
//            System.out.println(parent.key);
//        }
//        else if (value < current.key) {
//            insert(current.left, current, value);
//        } else if (value > current.key) {
//            insert(current.right, current, value);
//        } else {
//            System.out.println("_");
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            BinaryTree tree = new BinaryTree();
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            int n = Integer.parseInt(br.readLine());
//            long input;
//            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
//            for (int i = 0; i < n; i++) {
//                input = Long.parseLong(tokenizer.nextToken());
//                tree.insert(tree.root, null, input);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//class Node {
//    long key;
//    Node left, right;
//    Node parent;
//
//    Node(long value, Node parent) {
//        key = value;
//        left = right = null;
//        this.parent = parent;
//        if (parent != null) {
//            if (value < parent.key)
//                parent.left = this;
//            else
//                parent.right = this;
//        }
//    }
//}
