//public class SocialMedia {
//    private Matrix interests;
//    private Matrix relation;
//    private List<String> topics;
//    private List<String> names;
//
//    public SocialMedia() {
//    }
//
//    public void setNames(List<String> names) {
//        this.names = names;
//    }
//
//    public void setInterests(Matrix interests) {
//        this.interests = interests;
//    }
//
//    public void setRelation(Matrix relation) {
//        this.relation = relation;
//    }
//
//    public void setTopics(List<String> topics) {
//        this.topics = topics;
//    }
//
//    public List<String> getTopics() {
//        return topics;
//    }
//
//    public List<String> getNames() {
//        return names;
//    }
//
//    public void answer_question(String question) {
//        int f_index = question.indexOf(' ');
//        int l_index = question.indexOf(' ', f_index + 1);
//        int count = Integer.parseInt(question.substring(0, f_index));
//        List<Integer> columns = new List<>();
//        String field;
//        int index;
//        for (int i = 0; i < count; i++) {
//            field = question.substring(f_index + 1, l_index);
//            index = topics.get_index(field);
//            if (index != -1)
//                columns.add(index);
//            f_index = l_index;
//            l_index = question.indexOf(' ', f_index + 1);
//        }
//        Matrix result = this.interests.get_clone();
//        Matrix rel = this.relation.get_clone();
//        print_first_result(result, columns);
//        int depth = Integer.parseInt(question.substring(f_index + 1));
//        for (int i = 0; i < depth; i++) {
//            result = rel.multiply(result, columns);
//            if (result.getRows().getSize() == 0)
//                break;
//            else
//                print_result(result, rel, i);
//        }
//    }
//
//    public void print_first_result(Matrix result, List<Integer> columns) {
//        LinkedList row = result.getRows().getHead();
//        ListNode<String> name = this.getNames().getHead();
//        float interest;
//        for (int i = 0; i < this.getNames().getSize(); i++) {
//            if (row == null)
//                break;
//            if (row.getNumber() != i) {
//                name = name.getNext();
//                continue;
//            }
//            interest = get_sum_with_col(row.getHead(), columns);
//            if (interest == -1)
//                result.remove_row(row.getNumber());
//            else {
//                System.out.printf("%s %.6f\n", name.getData(), interest);
//            }
//            name = name.getNext();
//            row = row.getNext();
//        }
//    }
//
//    public void print_result(Matrix result, Matrix rel, int i) {
//        float interest;
//        ListNode<String> name;
//        String plus;
//        LinkedList row = result.getRows().getHead();
//        name = this.getNames().getHead();
//        for (int j = 0; j < this.names.getSize(); j++) {
//            plus = "";
//            if (row == null)
//                break;
//            if (row.getNumber() != j) {
//                name = name.getNext();
//                continue;
//            }
//            interest = get_sum(row.getHead());
//            for (int k = 0; k <= i; k++) {
//                plus += "+";
//            }
//            System.out.printf("%s %.6f %s\n", name.getData(), interest, plus);
//            rel.remove_row(row.getNumber());
//            row = row.getNext();
//            name = name.getNext();
//        }
//    }
//
//    public float get_sum_with_col(Node node, List<Integer> columns) {
//        float res = 0;
//        int count = 0;
//        while (node != null) {
//            if (columns.get_index(node.getCol_num()) != -1) {
//                res += node.getValue();
//                count++;
//            }
//            node = node.getNext_row();
//        }
//        if (count == columns.getSize())
//            return res;
//        else return -1;
//    }
//
//    public float get_sum(Node head) {
//        Node node = head;
//        if (head == null)
//            return 0;
//        float result = node.getValue();
//        while (node.getNext_row() != null) {
//            node = node.getNext_row();
//            result += node.getValue();
//        }
//        return result;
//    }
//}
