//public class List<T> {
//    private ListNode<T> head;
//    private ListNode<T> tail;
//    private int size;
//
//    public List() {
//        this.head = null;
//    }
//
//    public void add(T node) {
//        ListNode<T> new_node = new ListNode<>(node);
//        size++;
//        if (this.head == null) {
//            head = new_node;
//            tail = new_node;
//            return;
//        }
//        tail.setNext(new_node);
//        tail = new_node;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public ListNode<T> getHead() {
//        return head;
//    }
//
//    public int get_index(T field) {
//        ListNode head = this.head;
//        int i = 1;
//        if (this.size == 0)
//            return -1;
//        if (field.equals(head.getData()))
//            return 0;
//        while (head.getNext() != null) {
//            head = head.getNext();
//            if (head.getData().equals(field))
//                return i;
//            i++;
//        }
//        return -1;
//    }
//
//    public ListNode get_ith(int i) {
//        ListNode head = this.head;
//        int j = 0;
//        while (j < i) {
//            head = head.getNext();
//            j++;
//        }
//        return head;
//    }
//}
