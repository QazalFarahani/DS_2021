//import java.util.Scanner;
//
//import static java.lang.Math.max;
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
//public class AVLTree {
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
//    private void deleteNode(Node node, long key) {
//        Node n = node;
//        if (node == null) {
//            return;
//        }
//        if (key < node.value) {
//            deleteNode(node.left, key);
//            node.h = max(height(node.left), height(node.right)) + 1;
//            reBalance(node);
//        } else if (key > node.value) {
//            deleteNode(node.right, key);
//            node.h = max(height(node.left), height(node.right)) + 1;
//            reBalance(node);
//        } else {
//            if (node.left == null) {
//                n = node.right;
//                if (n != null)
//                    n.parent = node.parent;
//                if (node.parent != null) {
//                    if (node.parent.left == node)
//                        node.parent.left = n;
//                    else node.parent.right = n;
//                } else
//                    root = n;
//                size--;
//            } else if (node.right == null) {
//                n = node.left;
//                n.parent = node.parent;
//                if (node.parent != null) {
//                    if (node.parent.left == node)
//                        node.parent.left = n;
//                    else node.parent.right = n;
//                } else
//                    root = n;
//                size--;
//            } else {
//                node.value = minimum(node.right).value;
//                deleteNode(node.right, node.value);
//            }
//            if (n != null)
//                n.h = max(height(n.left), height(n.right)) + 1;
//            reBalance(n);
//        }
//    }
//
//    private long getBalance(Node node) {
//        if (node == null)
//            return 0;
//        return height(node.right) - height(node.left);
//    }
//
//    private Node minimum(Node node) {
//        while (node.left != null) {
//            node = node.left;
//        }
//        return node;
//    }
//
//    private Node maximum(Node node) {
//        while (node.right != null) {
//            node = node.right;
//        }
//        return node;
//    }
//
//    private Node successor(Node x) {
//        if (x == null)
//            return null;
//        if (x.right != null) {
//            return minimum(x.right);
//        }
//        Node y = x.parent;
//        while (y != null && x == y.right) {
//            x = y;
//            y = y.parent;
//        }
//        return y;
//    }
//
//    private Node predecessor(Node x) {
//        if (x == null)
//            return null;
//        if (x.left != null) {
//            return maximum(x.left);
//        }
//        Node y = x.parent;
//        while (y != null && x == y.left) {
//            x = y;
//            y = y.parent;
//        }
//        return y;
//    }
//
//    Node searchTree(Node node, long key) {
//        if (node == null || key == node.value) {
//            return node;
//        }
//        if (key < node.value)
//            return searchTree(node.left, key);
//        else
//            return searchTree(node.right, key);
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
//        int number = 0;
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
//            else break;
//        }
//        return number;
//    }
//
//    int previous(long value) {
//        Node r = root;
//        int number = 0;
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
//            else break;
//        }
//        return number;
//    }
//
//    Node contains(Node node, long value) {
//        if (node == null || node.value == value)
//            return node;
//        if (value < node.value)
//            return contains(node.left, value);
//        else
//            return contains(node.right, value);
//    }
//
//    public static void main(String[] args) {
//        AVLTree bst = new AVLTree();
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        String line;
//        String function;
//        long input;
//        Node node;
////        for (int i = 0; i < n; i++) {
////            line = scanner.nextLine();
////            if (line.contains(" ")) {
////                function = line.substring(0, line.indexOf(" "));
////                input = Long.parseLong(line.substring(line.indexOf(" ") + 1));
////                switch (function) {
////                    case "add":
////                        bst.insert(bst.root, null, input);
////                        break;
////                    case "remove":
////                        bst.deleteNode(bst.root, input);
////                        break;
////                    case "ceiling":
////                        bst.next(input);
////                        break;
////                    case "floor":
////                        bst.previous(input);
////                        break;
////                    case "contains":
////                        bst.contains(bst.root, input);
////                        break;
////                }
////            } else {
////                System.out.println(bst.size);
////            }
////        }
//    }
//}