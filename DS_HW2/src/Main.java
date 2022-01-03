import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> topics = new List<>();
        List<String> names = new List<>();
        Matrix relation = new Matrix();
        Matrix interests = new Matrix();
        List<String> relation_names = new List<>();
        int sm_numbers = Integer.parseInt(scanner.nextLine());
        int interest_num, topics_num, relation_num, l_index, element_num, index_relation;
        int f_index = 0;
        String line;
        String topic;
        String name;
        String element;
        int index;
        double value;
        RowNode rowNode;
        for (int i = 0; i < sm_numbers; i++) {
            List<Integer> indexes = new List<>();
            List<String> name_per_loop = new List<>();
            line = scanner.nextLine();
            f_index = 0;
            l_index = indexOf(' ', line, 0);
            if (l_index == -1)
                l_index = line.length();
            topics_num = Integer.parseInt(substring(f_index, l_index, line));
            f_index = l_index;
            l_index = indexOf(' ', line, f_index + 1);
            for (int j = 0; j < topics_num; j++) {
                if (l_index == -1)
                    l_index = line.length();
                topic = substring(f_index + 1, l_index, line);
                if (topics.get_index(topic) != -1)
                    indexes.add(topics.get_index(topic));
                else {
                    indexes.add(topics.getSize());
                    topics.add(topic);
                }
                f_index = l_index;
                l_index = indexOf(' ', line, f_index + 1);
            }
            interest_num = Integer.parseInt(scanner.nextLine());
            for (int j = 0; j < interest_num; j++) {
                line = scanner.nextLine();
                f_index = 0;
                l_index = indexOf(' ', line, 0);
                name = substring(f_index, l_index, line);
                name_per_loop.add(name);
                int p = names.get_index(name);
                if (p != -1)
                    rowNode = (RowNode) interests.getRows().get_ith_row(p);
                else {
                    names.add(name);
                    rowNode = new RowNode(names.get_index(name));
                    interests.getRows().add(rowNode);
                }
                f_index = l_index;
                l_index = indexOf(' ', line, f_index + 1);
                if (l_index == -1)
                    l_index = line.length();
                element_num = Integer.parseInt(substring(f_index + 1, l_index, line));
                f_index = l_index;
                l_index = indexOf(' ', line, f_index + 1);
                if (element_num != 0) {
                    for (int k = 0; k < element_num; k++) {
                        if (l_index == -1)
                            l_index = line.length();
                        element = substring(f_index + 1, l_index, line);
                        index = Integer.parseInt(substring(0, indexOf(':', element, 0), element));
                        value = Double.parseDouble(substring(indexOf(':', element, 0) + 1, element.length(), element));
                        interests.addToCol(new Node(value, rowNode.getNumber(), (Integer) indexes.get_ith(index).getData()));
                        interests.addToRow(new Node(value, rowNode.getNumber(), (Integer) indexes.get_ith(index).getData()), rowNode);
                        f_index = l_index;
                        l_index = indexOf(' ', line, f_index + 1);
                    }
                }
            }
            relation_num = Integer.parseInt(scanner.nextLine());
            for (int j = 0; j < relation_num; j++) {
                line = scanner.nextLine();
                f_index = 0;
                l_index = indexOf(' ', line, 0);
                index_relation = Integer.parseInt(substring(f_index, l_index, line));
                name = (String) name_per_loop.get_ith(index_relation).getData();
                int p = relation_names.get_index(name);
                if (p != -1)
                    rowNode = (RowNode) relation.getRows().get_ith_row(names.get_index(name));
                else {
                    rowNode = new RowNode(names.get_index(name));
                    relation.getRows().add(rowNode);
                    relation_names.add(name);
                }
                f_index = l_index;
                l_index = indexOf(' ', line, f_index + 1);
                if (l_index == -1)
                    l_index = line.length();
                element_num = Integer.parseInt(substring(f_index + 1, l_index, line));
                f_index = l_index;
                l_index = indexOf(' ', line, f_index + 1);
                if (element_num != 0) {
                    for (int k = 0; k < element_num; k++) {
                        if (l_index == -1)
                            l_index = line.length();
                        element = substring(f_index + 1, l_index, line);
                        index = Integer.parseInt(substring(0, indexOf(':', element, 0), element));
                        name = (String) name_per_loop.get_ith(index).getData();
                        value = Double.parseDouble(substring(indexOf(':', element, 0) + 1, element.length(), element));
                        relation.addToCol(new Node(value, rowNode.getNumber(), names.get_index(name)));
                        relation.addToRow(new Node(value, rowNode.getNumber(), names.get_index(name)), rowNode);
                        f_index = l_index;
                        l_index = indexOf(' ', line, f_index + 1);
                    }
                }
            }
        }
        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setTopics(topics);
        socialMedia.setNames(names);
        socialMedia.setRelation(relation);
        socialMedia.setInterests(interests);

        int count = Integer.parseInt(scanner.nextLine());
        String question;
        for (int i = 0; i < count; i++) {
            question = scanner.nextLine();
            socialMedia.answer_question(question);
        }


    }

    public static String substring(int begin, int end, String string) {
        String result = "";
        for (int i = begin; i < end; i++) {
            result += string.charAt(i);
        }
        return result;
    }

    public static int indexOf(char c, String string, int first) {
        char t;
        for (int i = first; i < string.length(); i++) {
            t = string.charAt(i);
            if (t == c)
                return i;
        }
        return -1;
    }
}

class SocialMedia {
    private Matrix interests;
    private Matrix relation;
    private List<String> topics;
    private List<String> names;

    public SocialMedia() {
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public void setInterests(Matrix interests) {
        this.interests = interests;
    }

    public void setRelation(Matrix relation) {
        this.relation = relation;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public List<String> getNames() {
        return names;
    }

    public void answer_question(String question) {
        int f_index = question.indexOf(' ');
        int l_index = question.indexOf(' ', f_index + 1);
        int count = Integer.parseInt(question.substring(0, f_index));
        List<Integer> columns = new List<>();
        String field;
        int index;
        for (int i = 0; i < count; i++) {
            field = question.substring(f_index + 1, l_index);
            index = topics.get_index(field);
            if (index != -1)
                columns.add(index);
            f_index = l_index;
            l_index = question.indexOf(' ', f_index + 1);
        }
        Matrix result = this.interests.get_clone();
        Matrix rel = this.relation.get_clone();
        linked p = new linked();
        print_first_result(result, rel, columns, p);
        p.print();
        int depth = Integer.parseInt(question.substring(f_index + 1));
        for (int i = 0; i < depth; i++) {
            result = rel.multiply(result, columns);
            if (result.getRows().getSize() == 0)
                break;
            else {
                linked p1 = new linked();
                print_result(result, rel, i, p1);
                p1.print();
            }
        }
    }

    public void print_first_result(Matrix result, Matrix rel, List<Integer> columns, linked p) {
        LinkedList row = result.getRows().getHead();
        ListNode<String> name = this.getNames().getHead();
        double interest;
        for (int i = 0; i < this.getNames().getSize(); i++) {
            if (row == null)
                break;
            if (row.getNumber() != i) {
                name = name.getNext();
                continue;
            }
            interest = get_sum_with_col(row.getHead(), columns);
            if (interest == -1)
                result.remove_row(row.getNumber());
            else {
                linked.sortNode node = new linked.sortNode(interest, name.getData(), "");
                p.push(node);
                rel.remove_row(row.getNumber());
            }
            name = name.getNext();
            row = row.getNext();
        }
    }

    public void print_result(Matrix result, Matrix rel, int i, linked p) {
        double interest;
        ListNode<String> name;
        String plus;
        LinkedList row = result.getRows().getHead();
        name = this.getNames().getHead();
        for (int j = 0; j < this.names.getSize(); j++) {
            plus = "";
            if (row == null)
                break;
            if (row.getNumber() != j) {
                name = name.getNext();
                continue;
            }
            interest = get_sum(row.getHead());
            for (int k = 0; k <= i; k++) {
                plus += "+";
            }
            if (interest != -1) {
                linked.sortNode node = new linked.sortNode(interest, name.getData(), plus);
                p.push(node);
            }
            rel.remove_row(row.getNumber());
            row = row.getNext();
            name = name.getNext();
        }
    }

    public double get_sum_with_col(Node node, List<Integer> columns) {
        double res = 0;
        int count = 0;
        while (node != null) {
            if (columns.get_index(node.getCol_num()) != -1) {
                res += node.getValue();
                count++;
            }
            node = node.getNext_row();
        }
        if (count == columns.getSize())
            return res;
        else return -1;
    }

    public double get_sum(Node head) {
        Node node = head;
        if (head == null)
            return -1;
        double result = node.getValue();
        while (node.getNext_row() != null) {
            node = node.getNext_row();
            result += node.getValue();
        }
        return result;
    }
}

abstract class LinkedList {
    int number;
    Node head;
    Node tail;
    LinkedList next;
    int size;

    public LinkedList(int number) {
        this.number = number;
    }

    public abstract void addToTail(Node node);

    public void setNext(LinkedList next) {
        this.next = next;
    }

    public int getNumber() {
        return number;
    }

    public Node getHead() {
        return head;
    }

    public LinkedList getNext() {
        return next;
    }

    public void setHead(Node head) {
        this.head = head;
        if (size == 0)
            this.tail = head;
    }

    public int getSize() {
        return size;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

class List<T> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    public List() {
        this.head = null;
    }

    public void add(T node) {
        ListNode<T> new_node = new ListNode<>(node);
        if (this.head == null) {
            head = new_node;
            tail = new_node;
            size++;
            return;
        }
        tail.setNext(new_node);
        tail = new_node;
        size++;
    }

    public int getSize() {
        return size;
    }

    public ListNode<T> getHead() {
        return head;
    }

    public int get_index(T field) {
        ListNode head = this.head;
        int i = 1;
        if (this.size == 0)
            return -1;
        if (field.equals(head.getData()))
            return 0;
        while (head.getNext() != null) {
            head = head.getNext();
            if (head.getData().equals(field))
                return i;
            i++;
        }
        return -1;
    }

    public ListNode get_ith(int i) {
        ListNode head = this.head;
        int j = 0;
        while (j < i) {
            head = head.getNext();
            j++;
        }
        return head;
    }
}

class Matrix {
    private CellNode rows;
    private CellNode cols;

    public Matrix() {
        this.rows = new CellNode();
        this.cols = new CellNode();
    }

    public void addToCol(Node node) {
        if (cols.getSize() == 0) {
            ColNode col_node = new ColNode(node.getCol_num());
            col_node.setHead(node);
            col_node.setSize(col_node.getSize() + 1);
            cols.add(col_node);
            return;
        }
        if (cols.getHead().getNumber() > node.getCol_num()) {
            ColNode col_node = new ColNode(node.getCol_num());
            col_node.setHead(node);
            col_node.setNext(cols.getHead());
            cols.setHead(col_node);
            col_node.setSize(col_node.getSize() + 1);
            cols.setSize(cols.getSize() + 1);
            return;
        }
        if (node.getCol_num() > cols.getTail().getNumber()) {
            ColNode col_node = new ColNode(node.getCol_num());
            col_node.setHead(node);
            col_node.setSize(col_node.getSize() + 1);
            cols.add(col_node);
            return;
        }
        LinkedList col = cols.getHead();
        if (col.getNumber() == node.getCol_num()) {
            if (col.getSize() == 0)
                return;
            if (col.getTail().getRow_num() < node.getRow_num()) {
                col.addToTail(node);
                return;
            } else if (node.getRow_num() < col.getHead().getRow_num()) {
                node.setNext_col(col.getHead());
                col.setHead(node);
                col.setSize(col.getSize() + 1);
                return;
            } else {
                Node n = col.getHead();
                if (n.getRow_num() == node.getRow_num()) {
                    n.setValue(n.getValue() + node.getValue());
                    return;
                }
                while (n.getNext_col() != null) {
                    if (node.getRow_num() > n.getRow_num() && node.getRow_num() < n.getNext_col().getRow_num()) {
                        node.setNext_col(n.getNext_col());
                        n.setNext_col(node);
                        col.setSize(col.getSize() + 1);
                        break;
                    } else if (node.getRow_num() == n.getNext_col().getRow_num()) {
                        n.getNext_col().setValue(n.getNext_col().getValue() + node.getValue());
                        break;
                    }
                    n = n.getNext_col();
                }
            }
            return;
        }
        while (col.getNext() != null) {
            if (col.getNumber() < node.getCol_num() && col.getNext().getNumber() > node.getCol_num()) {
                ColNode col_node = new ColNode(node.getCol_num());
                col_node.setHead(node);
                col_node.setNext(col.getNext());
                col.setNext(col_node);
                col_node.setSize(col_node.getSize() + 1);
                cols.setSize(cols.getSize() + 1);
                break;
            } else if (col.getNext().getNumber() == node.getCol_num()) {
                if (col.getNext().getTail().getRow_num() < node.getRow_num()) {
                    col.getNext().addToTail(node);
                    break;
                } else if (node.getRow_num() < col.getNext().getHead().getRow_num()) {
                    node.setNext_col(col.getNext().getHead());
                    col.getNext().setHead(node);
                    col.getNext().setSize(col.getNext().getSize() + 1);
                    break;
                } else {
                    Node n = col.getNext().getHead();
                    if (n.getRow_num() == node.getRow_num()) {
                        n.setValue(n.getValue() + node.getValue());
                        break;
                    }
                    while (n.getNext_col() != null) {
                        if (node.getRow_num() > n.getRow_num() && node.getRow_num() < n.getNext_col().getRow_num()) {
                            node.setNext_col(n.getNext_col());
                            n.setNext_col(node);
                            col.getNext().setSize(col.getNext().getSize() + 1);
                            break;
                        } else if (node.getRow_num() == n.getNext_col().getRow_num()) {
                            n.getNext_col().setValue(n.getNext_col().getValue() + node.getValue());
                            break;
                        }
                        n = n.getNext_col();
                    }
                }
                break;
            }
            col = col.getNext();
        }
    }

    public void addToRow(Node node, LinkedList row) {
        if (row.getSize() == 0)
            row.addToTail(node);
        else if (row.getHead().getCol_num() > node.getCol_num()) {
            node.setNext_row(row.getHead());
            row.setHead(node);
            row.setSize(row.getSize() + 1);
        } else if (node.getCol_num() > row.getTail().getCol_num())
            row.addToTail(node);
        else {
            Node head = row.getHead();
            if (row.getHead().getCol_num() == node.getCol_num()) {
                row.getHead().setValue(row.getHead().getValue() + node.getValue());
                return;
            }
            while (head.getNext_row() != null) {
                if (node.getCol_num() > head.getCol_num() && node.getCol_num() < head.getNext_row().getCol_num()) {
                    node.setNext_row(head.getNext_row());
                    head.setNext_row(node);
                    row.setSize(row.getSize() + 1);
                    break;
                } else if (node.getCol_num() == head.getNext_row().getCol_num()) {
                    head.getNext_row().setValue(head.getNext_row().getValue() + node.getValue());
                    break;
                }
                head = head.getNext_row();
            }
        }
    }

    public Matrix get_clone() {
        Matrix matrix = new Matrix();
        CellNode rows = matrix.getRows();
        CellNode cols = matrix.getCols();
        LinkedList row = this.getRows().getHead();
        for (int i = 0; i < this.getRows().getSize(); i++) {
            LinkedList m_row = new RowNode(row.getNumber());
            Node r_node = row.getHead();
            while (r_node != null) {
                m_row.addToTail(new Node(r_node.getValue(), r_node.getRow_num(), r_node.getCol_num()));
                r_node = r_node.getNext_row();
            }
            row = row.getNext();
            if (m_row.getHead() != null)
                rows.add(m_row);
        }
        LinkedList col = this.getCols().getHead();
        for (int i = 0; i < this.getCols().getSize(); i++) {
            LinkedList m_col = new ColNode(col.getNumber());
            Node c_node = col.getHead();
            while (c_node != null) {
                m_col.addToTail(new Node(c_node.getValue(), c_node.getRow_num(), c_node.getCol_num()));
                c_node = c_node.getNext_col();
            }

            col = col.getNext();
            if (m_col.getHead() != null)
                cols.add(m_col);
        }
        return matrix;
    }

    public Matrix multiply(Matrix matrix, List col_nums) {
        Matrix result = new Matrix();
        if (col_nums.getSize() == 0 || this.getRows().getSize() == 0 || matrix.getCols().getSize() == 0)
            return result;
        LinkedList cols = matrix.getCols().getHead();
        LinkedList row = this.getRows().getHead();
        Node row_head;
        Node col_head;
        double k;
        ListNode col = col_nums.getHead();
        for (int i = 0; i < this.getRows().getSize(); i++) {
            LinkedList result_row = new RowNode(row.getNumber());
            if (cols == null)
                break;
            for (int j = 0; j < col_nums.getSize(); j++) {
                k = 0;
                row_head = row.getHead();
                col_head = cols.getHead();
                while (cols.getNumber() != (int) col.getData()) {
                    cols = cols.getNext();
                    if (cols == null)
                        break;
                    col_head = cols.getHead();
                }
                if (cols == null)
                    break;
                while (row_head != null && col_head != null) {
                    if (row_head.getCol_num() > col_head.getRow_num())
                        col_head = col_head.getNext_col();
                    else if (row_head.getCol_num() < col_head.getRow_num())
                        row_head = row_head.getNext_row();
                    else if (col_head.getRow_num() == row_head.getCol_num()) {
                        k += row_head.getValue() * col_head.getValue();
                        row_head = row_head.getNext_row();
                        col_head = col_head.getNext_col();
                    }
                }
                if (k != 0) {
                    Node node = new Node(k, row.getNumber(), cols.getNumber());
                    result.addToRow(node, result_row);
                    result.addToCol(node);
                }
                cols = matrix.getCols().getHead();
                col = col.getNext();
            }
            if (result_row.getSize() != 0)
                result.getRows().add(result_row);
            row = row.getNext();
            cols = matrix.getCols().getHead();
            col = col_nums.getHead();
        }
        return result;
    }

    public CellNode getCols() {
        return cols;
    }

    public CellNode getRows() {
        return rows;
    }

    public void remove_row(int i) {
        if (rows.getSize() == 0)
            return;
        if (rows.getSize() == 1) {
            if (rows.getHead().getNumber() == i) {
                rows.setHead(null);
                rows.setTail(null);
                remove_from_col(i);
                rows.setSize(rows.getSize() - 1);
                return;
            }
        }
        if (rows.getHead().getNumber() == i) {
            if (rows.getSize() == 1) {
                rows.setHead(null);
                rows.setTail(null);
            } else
                rows.setHead(rows.getHead().getNext());
            remove_from_col(i);
            rows.setSize(rows.getSize() - 1);
        } else {
            LinkedList row = rows.getHead();
            while (row.getNext() != null) {
                if (row.getNext().getNumber() == i) {
                    row.setNext(row.getNext().getNext());
                    if (rows.getSize() == 2)
                        rows.setTail(rows.getHead());
                    remove_from_col(i);
                    rows.setSize(rows.getSize() - 1);
                    break;
                }
                row = row.getNext();
            }
        }
    }

    public void remove_from_col(int i) {
        LinkedList column = cols.getHead();
        if (cols.getHead() == null)
            return;
        int size = cols.getSize();
        for (int j = 0; j < size; j++) {
            Node head = column.getHead();
            if (head.getRow_num() == i) {
                if (column.getSize() == 1) {
                    column.setHead(null);
                    column.setTail(null);
                    reset_col(column.getNumber());
                } else {
                    column.setHead(column.getHead().getNext_col());
                }
                column.setSize(column.getSize() - 1);
            } else {
                while (head.getNext_col() != null) {
                    if (head.getNext_col().getRow_num() != i)
                        head = head.getNext_col();
                    else {
                        head.setNext_col(head.getNext_col().getNext_col());
                        if (column.getSize() == 2)
                            column.setTail(column.getHead());
                        column.setSize(column.getSize() - 1);
                        break;
                    }
                }
            }
            column = column.getNext();
        }
    }

    public void reset_col(int i) {
        if (cols.getSize() == 1) {
            cols.setHead(null);
            cols.setTail(null);
            cols.setSize(0);
            return;
        }
        if (cols.getHead().getNumber() == i) {
            cols.setHead(cols.getHead().getNext());
            cols.setSize(cols.getSize() - 1);
            return;
        }
        LinkedList column = cols.getHead();
        while (column.getNext().getNumber() != i) {
            column = column.getNext();
        }
        if (column.getNext() != null) {
            column.setNext(column.getNext().getNext());
            if (cols.getSize() == 2)
                cols.setTail(cols.getHead());
            cols.setSize(cols.getSize() - 1);
        }
    }

}

class Node {
    private int row_num;
    private int col_num;
    private double value;
    private Node next_row;
    private Node next_col;

    public Node(double value, int r, int c) {
        this.value = value;
        this.next_col = null;
        this.next_row = null;
        this.row_num = r;
        this.col_num = c;
    }

    public void setNext_row(Node next_row) {
        this.next_row = next_row;
    }

    public void setNext_col(Node next_col) {
        this.next_col = next_col;
    }

    public int getCol_num() {
        return col_num;
    }

    public int getRow_num() {
        return row_num;
    }

    public Node getNext_col() {
        return next_col;
    }

    public Node getNext_row() {
        return next_row;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

class CellNode {
    private LinkedList head;
    private LinkedList tail;
    private int size;

    public CellNode() {
        this.head = null;
    }

    public LinkedList getHead() {
        return head;
    }

    public void setHead(LinkedList head) {
        this.head = head;
        if (head == null)
            this.tail = head;
    }

    public void add(LinkedList node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            if (node.getNumber() < this.getHead().getNumber()) {
                node.setNext(this.getHead());
                this.setHead(node);
            } else if (node.getNumber() > this.getTail().getNumber()) {
                tail.setNext(node);
                tail = node;
            } else {
                LinkedList head = this.getHead();
                while (head.getNext() != null) {
                    if (head.getNumber() < node.getNumber() && node.getNumber() < head.getNext().getNumber()) {
                        node.setNext(head.getNext());
                        head.setNext(node);
                        break;
                    }
                    head = head.getNext();
                }
            }
        }
        size++;
    }

    public LinkedList get_ith_row(int i) {
        LinkedList head = this.getHead();
        while (head != null) {
            if (head.getNumber() == i)
                return head;
            head = head.getNext();
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public void setTail(LinkedList tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LinkedList getTail() {
        return tail;
    }
}

class ListNode<T> {
    private T data;
    private ListNode<T> next;

    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public ListNode<T> getNext() {
        return next;
    }
}

class ColNode extends LinkedList {

    public ColNode(int number) {
        super(number);
        this.head = null;
        this.tail = null;
        this.next = null;
    }

    @Override
    public void addToTail(Node node) {
        if (this.head == null) {
            head = node;
            tail = node;
            size++;
            return;
        }
        this.tail.setNext_col(node);
        this.tail = node;
        size++;
    }
}

class RowNode extends LinkedList {

    public RowNode(int number) {
        super(number);
        this.head = null;
        this.tail = null;
        this.next = null;
    }

    @Override
    public void addToTail(Node node) {
        if (this.head == null) {
            head = node;
            tail = node;
            size++;
            return;
        }
        this.tail.setNext_row(node);
        this.tail = node;
        size++;
    }


}

class linked {
    sortNode head = null;

    static class sortNode {
        double val;
        String name;
        sortNode next;
        String plus;

        public sortNode(double val, String name, String plus) {
            this.val = val;
            this.name = name;
            this.plus = plus;
        }
    }

    private sortNode data_sortedMerge(sortNode a, sortNode b) {
        sortNode result;
        if (a == null)
            return b;
        if (b == null)
            return a;
        if (a.val < b.val) {
            result = b;
            result.next = data_sortedMerge(a, b.next);
        } else {
            result = a;
            result.next = data_sortedMerge(a.next, b);
        }
        return result;
    }

    private sortNode name_sortedMerge(sortNode a, sortNode b) {
        sortNode result;
        if (a == null)
            return b;
        if (b == null)
            return a;
        if (a.name.compareTo(b.name) <= 0) {
            result = a;
            result.next = name_sortedMerge(a.next, b);
        } else {
            result = b;
            result.next = name_sortedMerge(a, b.next);
        }
        return result;
    }

    private sortNode data_mergeSort(sortNode h) {
        if (h == null || h.next == null) {
            return h;
        }
        sortNode middle = getMiddle(h);
        sortNode nextofmiddle = middle.next;
        middle.next = null;
        sortNode left = data_mergeSort(h);
        sortNode right = data_mergeSort(nextofmiddle);
        return data_sortedMerge(left, right);
    }

    sortNode name_mergeSort(sortNode h) {
        if (h == null || h.next == null) {
            return h;
        }
        sortNode middle = getMiddle(h);
        sortNode nextofmiddle = middle.next;
        middle.next = null;
        sortNode left = name_mergeSort(h);
        sortNode right = name_mergeSort(nextofmiddle);
        return name_sortedMerge(left, right);
    }

    public static sortNode getMiddle(sortNode head) {
        if (head == null)
            return head;
        sortNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    void push(sortNode node) {
        sortNode new_node = new sortNode(node.val, node.name, node.plus);
        new_node.next = head;
        head = new_node;
    }

//    public void sortList() {
//        sortNode current = head, index = null;
//        double temp;
//
//        if (head == null) {
//            return;
//        } else {
//            while (current != null) {
//                index = current.next;
//                while (index != null) {
//                    if (current.val < index.val) {
//                        temp = current.val;
//                        current.val = index.val;
//                        index.val = temp;
//                        String st = current.name;
//                        current.name = index.name;
//                        index.name = st;
//                    }
//                    if (current.val == index.val) {
//                        if (current.name.compareTo(index.name) >= 0) {
//                            temp = current.val;
//                            current.val = index.val;
//                            index.val = temp;
//                            String st = current.name;
//                            current.name = index.name;
//                            index.name = st;
//                        }
//                    }
//                    index = index.next;
//                }
//                current = current.next;
//            }
//        }
//    }

    void print() {
//        this.sortList();
//        sortNode node = this.head;
        sortNode node = this.data_mergeSort(this.name_mergeSort(this.head));
        while (node != null) {
            System.out.println(node.name + " " + node.val);
//            System.out.printf("%s %.6f %s\n", node.name, node.val, node.plus);
            node = node.next;
        }
    }

}
