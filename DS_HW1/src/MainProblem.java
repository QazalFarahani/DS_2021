import java.util.Arrays;
import java.util.Scanner;

public class MainProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long k = scanner.nextLong();
        scanner.nextLine();
        Long[] array = Arrays.stream(scanner.nextLine().split(" ")).map(Long::parseLong).toArray(Long[]::new);
        int[] next = new int[n];
        Stack<Integer> stack = new Stack<>();
        MyLinkedList<Long> list = new MyLinkedList<>();

        for (int i = 0; i < n; i++) {
            if (stack.size() == 0 || array[i] < array[stack.top()])
                stack.push(i);
            else {
                while (stack.size() != 0 && array[i] >= array[stack.top()]) {
                    next[stack.top()] = i;
                    stack.pop();
                }
                stack.push(i);
            }
        }

        while (stack.size() != 0) {
            next[stack.top()] = n;
            stack.pop();
        }

        int start = 0;
        int end = 0;
        long sum = 0;
        long max = array[0];
        int max_index = 0;
        list.push(max, max_index);
        long count = 0;
        long diff = 0;
        int chert;
        while (start < n && end < n) {
            if (sum <= k) {
                end++;
                if (end >= start)
                    count += end - start;
                if (end < n) {
                    if (array[end] >= max) {
                        max = array[end];
                        max_index = end;
                        list = new MyLinkedList<>();
                    } else {
                        sum += max - array[end];
                        while (list.size != 0 && list.last != null && list.last.value < array[end]) {
                            list.pop();
                        }
                    }
                    list.push(array[end], end);
                }
            } else {
                if (start + 1 < n) {
                    if (array[start] - array[start + 1] > 0) {
                        chert = start + 1;
                        if (next[chert] > end) {
                            sum -= (end - chert + 1) * (array[start] - array[chert]);
                        } else if (next[chert] == end) {
                            diff = array[start] - array[next[chert]];
                            if (diff < 0)
                                diff = 0;
                            sum -= ((end - chert) * (array[start] - array[chert]) + diff);
                        } else {
                            while (array[start] - array[chert] > 0) {
                                if (next[chert] < end) {
                                    sum -= (next[chert] - chert) * (array[start] - array[chert]);
                                    chert = next[chert];
                                } else if (next[chert] == end) {
                                    diff = array[start] - array[next[chert]];
                                    if (diff < 0)
                                        diff = 0;
                                    sum -= ((end - chert) * (array[start] - array[chert]) + diff);
                                    break;
                                } else {
                                    sum -= (end - chert + 1) * (array[start] - array[chert]);
                                    break;
                                }
                            }
                        }
                    }
                }
                if (start == max_index) {
                    list.popFirst();
                    if (list.size != 0) {
                        max = list.first.value;
                        max_index = list.first.index;
                    }
                }
                start++;
            }
        }

        System.out.println(count);
    }
}

class LinkedList<T> {
    MyLinkedNode<T> first;
    MyLinkedNode<T> last;
    int size;

    void push(T value, int index) {
        MyLinkedNode<T> newNode = new MyLinkedNode<>(value, index);
        if (size == 0) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            newNode.setPrevious(last);
            last = newNode;
        }
        size++;
    }

    void popFirst() {
        if (size == 0)
            return;
        if (size == 1) {
            first = null;
            last = null;
        } else if (size == 2) {
            first = last;
            first.setNext(null);
            last.setPrevious(null);
        } else {
            first = first.getNext();
            first.setPrevious(null);
        }
        size--;
    }

    void pop() {
        if (size == 0)
            return;
        if (size == 1) {
            first = null;
            last = null;
        } else if (size == 2) {
            last = first;
            last.setPrevious(null);
            first.setNext(null);
        } else {
            last = last.getPrevious();
            last.setNext(null);
        }
        size--;
    }

}

class LinkedNode<T> {
    MyLinkedNode<T> next;
    MyLinkedNode<T> previous;
    int index;
    T value;

    LinkedNode(T value, int index) {
        this.value = value;
        this.index = index;
    }

    void setPrevious(MyLinkedNode<T> previous) {
        this.previous = previous;
    }

    MyLinkedNode<T> getPrevious() {
        return previous;
    }

    void setNext(MyLinkedNode<T> next) {
        this.next = next;
    }

    void setIndex(int index) {
        this.index = index;
    }

    MyLinkedNode<T> getNext() {
        return next;
    }

    int getIndex() {
        return index;
    }
}

class Stack<T> {
    private Node<T> top;
    private int size;

    int size() {
        return size;
    }

    T top() {
        if (top != null)
            return top.getValue();
        else return null;
    }

    void pop() {
        top = top.getPrevious();
        size--;
    }

    void push(T value) {
        Node<T> node = new Node<>(value);
        node.setPrevious(top);
        top = node;
        size++;
    }
}

class Node<T> {
    private T value;
    private Node<T> previous;

    Node(T value) {
        this.value = value;
    }

    void setPrevious(Node<T> node) {
        this.previous = node;
    }

    Node<T> getPrevious() {
        return previous;
    }

    T getValue() {
        return value;
    }
}
