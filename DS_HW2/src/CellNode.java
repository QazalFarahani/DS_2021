//public class CellNode {
//    private LinkedList head;
//    private LinkedList tail;
//    private int size;
//
//    public CellNode() {
//        this.head = null;
//        size = 0;
//    }
//
//    public LinkedList getHead() {
//        return head;
//    }
//
//    public void setHead(LinkedList head) {
//        this.head = head;
//    }
//
//    public void add(LinkedList node) {
//        if (head == null) {
//            head = node;
//            tail = node;
//        }
//        else {
//            tail.setNext(node);
//            tail = node;
//        }
//        size++;
//    }
//
//    public LinkedList get_ith_row(int i) {
//        LinkedList head = this.head;
//        while (head != null) {
//            if (head.getNumber() == i)
//                return head;
//            head = head.next;
//        }
//        return null;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public void setTail(LinkedList tail) {
//        this.tail = tail;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//}
//
