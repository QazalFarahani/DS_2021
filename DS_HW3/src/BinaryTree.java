//import java.util.Scanner;
//
//import static java.lang.Math.max;
//
//class BinaryTree {
//    public static void main(String[] args) {
//        AVLTree tree = new AVLTree();
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        long[] array = new long[n];
//        long input;
//        int ceiling;
//        int floor;
//        for (int i = 0; i < n; i++) {
//            input = scanner.nextLong();
//            array[i] = input;
//        }
//        tree.insert(tree.root, null, array[0], 0);
//        for (int i = 1; i < n; i++) {
//            if (tree.find(array[i]) != null) {
//                System.out.println("_");
//            } else {
//                ceiling = tree.next(array[i]);
//                floor = tree.previous(array[i]);
//                if (floor != -1 && ceiling != -1 && floor > ceiling)
//                    System.out.println(array[floor]);
//                else if (floor != -1 && ceiling != -1 && floor < ceiling)
//                    System.out.println(array[ceiling]);
//                else if (floor == -1 && ceiling != -1)
//                    System.out.println(array[ceiling]);
//                else if (floor != -1 && ceiling == -1)
//                    System.out.println(array[floor]);
//                tree.insert(tree.root, null, array[i], i);
//            }
//        }
//    }
//}
//
//class Node {
//    long value;
//    Node parent;
//    Node left;
//    Node right;
//    long h;
//    int index;
//
//    Node(long value, int i) {
//        this.value = value;
//        this.parent = null;
//        this.left = null;
//        this.right = null;
//        this.h = 1;
//        this.index = i;
//    }
//}
//
//class AVLTree {
//    Node root;
//    private int size;
//
//    AVLTree() {
//        root = null;
//    }
//
//    private long height(Node node) {
//        if (node == null)
//            return 0;
//        return node.h;
//    }
//
//    private long getBalance(Node node) {
//        if (node == null)
//            return 0;
//        return height(node.right) - height(node.left);
//    }
//
//    private void leftRotate(Node x) {
//        Node y = x.right;
//        x.right = y.left;
//        if (y.left != null)
//            y.left.parent = x;
//        y.parent = x.parent;
//        if (x.parent == null)
//            this.root = y;
//        else if (x == x.parent.left)
//            x.parent.left = y;
//        else
//            x.parent.right = y;
//        y.left = x;
//        x.parent = y;
//        x.h = max(height(x.left), height(x.right)) + 1;
//        y.h = max(height(y.left), height(y.right)) + 1;
//    }
//
//    private void rightRotate(Node x) {
//        Node y = x.left;
//        x.left = y.right;
//        if (y.right != null)
//            y.right.parent = x;
//        y.parent = x.parent;
//        if (x.parent == null)
//            this.root = y;
//        else if (x == x.parent.right)
//            x.parent.right = y;
//        else
//            x.parent.left = y;
//        y.right = x;
//        x.parent = y;
//        y.h = max(height(y.left), height(y.right)) + 1;
//        x.h = max(height(x.left), height(x.right)) + 1;
//    }
//
//
//    void insert(Node node, Node p, long key, int i) {
//        if (size == 0) {
//            root = new Node(key, i);
//            size++;
//            return;
//        }
//        if (node == null) {
//            Node n = new Node(key, i);
//            n.parent = p;
//            if (key < p.value)
//                p.left = n;
//            else
//                p.right = n;
//            size++;
//            return;
//        } else if (key < node.value)
//            insert(node.left, node, key, i);
//        else if (key > node.value)
//            insert(node.right, node, key, i);
//        node.h = 1 + max(height(node.left), height(node.right));
//        reBalance(node);
//    }
//
//    private void reBalance(Node node) {
//        long balance = getBalance(node);
//        if (balance > 1) {
//            if (getBalance(node.right) >= 0)
//                leftRotate(node);
//            else {
//                rightRotate(node.right);
//                leftRotate(node);
//            }
//        } else if (balance < -1) {
//            if (getBalance(node.left) <= 0)
//                rightRotate(node);
//            else {
//                leftRotate(node.left);
//                rightRotate(node);
//            }
//        }
//    }
//
//    int next(long value) {
//        Node r = root;
//        int number = -1;
//        long diff = Long.MAX_VALUE;
//        long d;
//        while (r != null) {
//            d = r.value - value;
//            if (d > 0 && d < diff) {
//                diff = d;
//                number = r.index;
//            }
//            if (value < r.value)
//                r = r.left;
//            else if (value > r.value)
//                r = r.right;
//            else
//                break;
//        }
//        return number;
//    }
//
//    int previous(long value) {
//        Node r = root;
//        int number = -1;
//        long diff = Long.MIN_VALUE;
//        long d;
//        while (r != null) {
//            d = r.value - value;
//            if (d < 0 && d > diff) {
//                diff = d;
//                number = r.index;
//            }
//            if (value < r.value)
//                r = r.left;
//            else if (value > r.value)
//                r = r.right;
//            else
//                break;
//        }
//        return number;
//    }
//
//    Node find(long key) {
//        Node current = root;
//        while (current != null) {
//            if (current.value == key) {
//                break;
//            }
//            current = current.value < key ? current.right : current.left;
//        }
//        return current;
//    }
//}
