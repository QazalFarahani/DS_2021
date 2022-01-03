import java.util.Scanner;

public class Authentication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        char input;
        char[] chars = new char[n];
        MyLinkedList list = new MyLinkedList();
        MyLinkedNode cursor = null;
        MyLinkedNode new_cur;
        for (int i = 0; i < n; i++)
            chars[i] = scanner.nextLine().charAt(0);
        for (char c : chars) {
            input = c;
            if (list.size == 0) {
                if (97 <= input && input <= 122) {
                    MyLinkedNode newNode = new MyLinkedNode(input);
                    list.addNode(newNode, null);
                    cursor = newNode;
                }
            } else {
                if (cursor == null) {
                    if (97 <= input && input <= 122) {
                        MyLinkedNode newNode = new MyLinkedNode(input);
                        list.addNode(newNode, null);
                        cursor = newNode;
                    } else if (input == '>') {
                        cursor = list.head;
                    }
                } else if (cursor.getNext() == null) {
                    if (input == '<')
                        cursor = cursor.getPrevious();
                    else if (97 <= input && input <= 122) {
                        MyLinkedNode newNode = new MyLinkedNode(input);
                        list.addNode(newNode, cursor);
                        cursor = newNode;
                    } else if (input == '-') {
                        new_cur = cursor.getPrevious();
                        list.deleteNode(cursor);
                        cursor = new_cur;
                    }
                } else {
                    if (input == '<') {
                        cursor = cursor.getPrevious();
                    } else if (input == '>') {
                        cursor = cursor.getNext();
                    } else if (97 <= input && input <= 122) {
                        MyLinkedNode newNode = new MyLinkedNode(input);
                        list.addNode(newNode, cursor);
                        cursor = newNode;
                    } else if (input == '-') {
                        new_cur = cursor.getPrevious();
                        list.deleteNode(cursor);
                        cursor = new_cur;
                    }
                }

            }
        }
        cursor = list.head;
        while (cursor != null) {
            System.out.print(cursor.value);
            cursor = cursor.getNext();
        }
    }


}

class MyLinkedList {
    MyLinkedNode head;
    int size;

    void addNode(MyLinkedNode newNode, MyLinkedNode i) {
        if (size == 0)
            head = newNode;
        else if (i == null) {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        } else if (i.getNext() == null) {
            newNode.setPrevious(i);
            i.setNext(newNode);
        } else {
            i.getNext().setPrevious(newNode);
            newNode.setNext(i.getNext());
            i.setNext(newNode);
            newNode.setPrevious(i);
        }
        size++;
    }

    void deleteNode(MyLinkedNode i) {
        if (size == 1)
            head = null;
        else if (head == i) {
            head = head.getNext();
            head.setPrevious(null);
        } else if (i.getNext() == null) {
            i.getPrevious().setNext(null);
        } else {
            i.getPrevious().setNext(i.getNext());
            i.getNext().setPrevious(i.getPrevious());
        }
        size--;
    }

}

class MyLinkedNode {
    MyLinkedNode next;
    MyLinkedNode previous;
    char value;

    MyLinkedNode(char value) {
        this.value = value;
    }

    void setNext(MyLinkedNode next) {
        this.next = next;
    }

    void setPrevious(MyLinkedNode previous) {
        this.previous = previous;
    }

    MyLinkedNode getNext() {
        return next;
    }

    MyLinkedNode getPrevious() {
        return previous;
    }
}