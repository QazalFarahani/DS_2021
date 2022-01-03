//public class Matrix {
//    private CellNode rows;
//    private CellNode cols;
//
//    public Matrix() {
//        this.rows = new CellNode();
//        this.cols = new CellNode();
//    }
//
//    public void addToCol(Node node) {
//        if (node.getCol_num() >= cols.getSize()) {
//            ColNode col_node = new ColNode(node.getCol_num());
//            col_node.setHead(node);
//            cols.add(col_node);
//            return;
//        }
//        LinkedList col = cols.getHead();
//        while (col != null) {
//            if (col.getNumber() == node.getCol_num()) {
//                if (col.getTail().getRow_num() < node.getRow_num())
//                    col.addToTail(node);
//                else if (node.getRow_num() < col.getHead().getRow_num()) {
//                    node.setNext_col(col.getHead());
//                    col.setHead(node);
//                } else {
//                    Node n = col.getHead();
//                    if (n.getRow_num() == node.getRow_num()) {
//                        n.setValue(n.getValue() + node.getValue());
//                        break;
//                    }
//                    while (n.getNext_col() != null) {
//                        if (node.getRow_num() > n.getRow_num() && node.getRow_num() < n.getNext_col().getRow_num()) {
//                            node.setNext_col(n.getNext_col());
//                            n.setNext_col(node);
//                            break;
//                        } else if (node.getRow_num() == n.getNext_col().getRow_num()) {
//                            n.getNext_col().setValue(n.getValue() + node.getValue());
//                            break;
//                        }
//                        n = n.getNext_col();
//                    }
//                }
//                break;
//            }
//            col = col.getNext();
//        }
//    }
//
//    public void addToRow(Node node, LinkedList row) {
//        if (row.getSize() == 0)
//            row.addToTail(node);
//        else if (row.getHead().getCol_num() >= node.getCol_num()) {
//            node.setNext_row(row.getHead());
//            row.setHead(node);
//        } else if (node.getCol_num() > row.getTail().getCol_num())
//            row.addToTail(node);
//        else {
//            Node head = row.getHead();
//            if (row.getHead().getCol_num() == node.getCol_num())
//                row.getHead().setValue(row.getHead().getValue() + node.getValue());
//            while (head.getNext_row() != null) {
//                if (node.getCol_num() > head.getCol_num() && node.getCol_num() < head.getNext_row().getCol_num()) {
//                    node.setNext_row(head.getNext_row());
//                    head.setNext_row(node);
//                    break;
//                } else if (node.getCol_num() == head.getNext_row().getCol_num()) {
//                    head.getNext_row().setValue(head.getNext_row().getValue() + node.getValue());
//                    break;
//                }
//                head = head.getNext_row();
//            }
//        }
//    }
//
//    public Matrix get_clone() {
//        Matrix matrix = new Matrix();
//        CellNode rows = matrix.getRows();
//        CellNode cols = matrix.getCols();
//        LinkedList row = this.getRows().getHead();
//        for (int i = 0; i < this.getRows().getSize(); i++) {
//            LinkedList m_row = new RowNode(row.getNumber());
//            Node r_node = row.getHead();
//            while (r_node != null) {
//                m_row.addToTail(new Node(r_node.getValue(), r_node.getRow_num(), r_node.getCol_num()));
//                r_node = r_node.getNext_row();
//            }
//            row = row.getNext();
//            rows.add(m_row);
//        }
//        LinkedList col = this.getCols().getHead();
//        for (int i = 0; i < this.getCols().getSize(); i++) {
//            LinkedList m_col = new ColNode(col.getNumber());
//            Node c_node = col.getHead();
//            while (c_node != null) {
//                m_col.addToTail(new Node(c_node.getValue(), c_node.getRow_num(), c_node.getCol_num()));
//                c_node = c_node.getNext_col();
//            }
//
//            col = col.getNext();
//            cols.add(m_col);
//        }
//        return matrix;
//    }
//
//    public Matrix multiply(Matrix matrix, List col_nums) {
//        Matrix result = new Matrix();
//        LinkedList cols = matrix.getCols().getHead();
//        Node row_head;
//        Node col_head;
//        LinkedList row = this.getRows().getHead();
//        float k;
//        ListNode col = col_nums.getHead();
//        for (int i = 0; i < this.getRows().getSize(); i++) {
//            LinkedList result_row = new RowNode(row.getNumber());
//            for (int j = 0; j < col_nums.getSize(); j++) {
//                k = 0;
//                row_head = row.getHead();
//                col_head = cols.getHead();
//                while (cols.getNumber() != (int) col.getData()) {
//                    cols = cols.getNext();
//                    if (cols == null)
//                        break;
//                    col_head = cols.getHead();
//                }
//                if (cols == null)
//                    break;
//                while (row_head != null && col_head != null) {
//                    if (row_head.getCol_num() > col_head.getRow_num())
//                        col_head = col_head.getNext_col();
//                    else if (row_head.getCol_num() < col_head.getRow_num())
//                        row_head = row_head.getNext_row();
//                    else if (col_head.getRow_num() == row_head.getCol_num()) {
//                        k += row_head.getValue() * col_head.getValue();
//                        row_head = row_head.getNext_row();
//                        col_head = col_head.getNext_col();
//                    }
//                }
//                if (k != 0) {
//                    Node node = new Node(k, row.getNumber(), cols.getNumber());
//                    result.addToRow(node, result_row);
//                    result.addToCol(node);
//                }
//                cols = matrix.getCols().getHead();
//                col = col.getNext();
//            }
//            if (result_row.getSize() != 0)
//                result.getRows().add(result_row);
//            row = row.getNext();
//            cols = matrix.getCols().getHead();
//            col = col_nums.getHead();
//        }
//        return result;
//    }
//
//    public CellNode getCols() {
//        return cols;
//    }
//
//    public CellNode getRows() {
//        return rows;
//    }
//
//    public void remove_row(int i) {
//        if (rows.getSize() == 0)
//            return;
//        if (rows.getHead().getNumber() == i) {
//            if (rows.getSize() == 1) {
//                rows.setHead(null);
//                rows.setTail(null);
//            } else
//                rows.setHead(rows.getHead().getNext());
//        } else {
//            LinkedList row = rows.getHead();
//            while (row.getNext().getNumber() != i) {
//                row = row.getNext();
//            }
//            row.setNext(row.getNext().getNext());
//        }
//        rows.setSize(rows.getSize() - 1);
//        remove_from_col(i);
//    }
//
//    public void remove_from_col(int i) {
//        LinkedList column = cols.getHead();
//        for (int j = 0; j < cols.getSize(); j++) {
//            if (column.getSize() == 0)
//                break;
//            Node head = column.getHead();
//            if (head.getRow_num() == i) {
//                if (column.getSize() == 1) {
//                    column.setHead(null);
//                    column.setTail(null);
//                    reset_col(column.getNumber());
//                } else {
//                    column.setHead(column.getHead().getNext_col());
//                }
//                column.setSize(column.getSize() - 1);
//                break;
//            } else {
//                while (head.getNext_col() != null) {
//                    if (head.getNext_col().getRow_num() != i)
//                        head = head.getNext_col();
//                    else {
//                        head.setNext_col(head.getNext_col().getNext_col());
//                        column.setSize(column.getSize() - 1);
//                    }
//                }
//            }
//            column = column.getNext();
//        }
//    }
//
//    public void reset_col(int i) {
//        if (cols.getSize() == 1) {
//            cols.setHead(null);
//            cols.setTail(null);
//            cols.setSize(0);
//            return;
//        }
//        if (cols.getHead().getNumber() == i) {
//            cols.setHead(cols.getHead().getNext());
//            cols.setSize(cols.getSize() - 1);
//            return;
//        }
//        LinkedList column = cols.getHead();
//        while (column.getNext().getNumber() != i) {
//            column = column.getNext();
//        }
//        column.setNext(column.getNext().getNext());
//        cols.setSize(cols.getSize() - 1);
//    }
//
//}
