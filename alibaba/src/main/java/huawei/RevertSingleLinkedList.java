package huawei;

import java.util.Stack;

/**
 * 单链表翻转
 *
 * @author xiaoyu
 */
public class RevertSingleLinkedList {
    public static void main(String[] args) {
        Node node = buildList("1->2->3->4->NULL");
        Node temp = revert(node);
        printResult(temp);
    }

    private static void printResult(Node temp) {
        StringBuilder sb = new StringBuilder();
        while (temp != null) {
            sb.append(temp.getDate());
            temp = temp.getNext();
            sb.append("->");
            if (temp == null) {
                sb.append("NULL");
            }
        }
        System.out.println(sb.toString());
    }

    private static Node buildList(String s) {
        Node head = null;
        Node last = null;
        if (s.length() > 0) {
            String[] strings = s.split("->");
            if (strings.length > 0) {
                head = new Node(Integer.parseInt(strings[0]), null);
                last = head;
            }
            for (int i = 1; i < strings.length - 1; i++) {
                Node node = new Node(Integer.parseInt(strings[i]), null);
                last.setNext(node);
                last = last.getNext();
            }
        }
        return head;
    }

    private static Node revert(Node firstNode) {
        Stack<Integer> nodeStack = new Stack<>();
        Node temp = firstNode;
        if (temp != null) {
            nodeStack.push(temp.getDate());
        }
        while (temp != null) {
            temp = temp.getNext();
            if (temp != null) {
                nodeStack.push(temp.getDate());
            }
        }
        Node first = null;
        if (nodeStack.peek() != null) {
            first = new Node(nodeStack.pop(), null);
        }
        Node last = first;
        while (!nodeStack.empty()) {
            Integer pop = nodeStack.pop();
            last.setNext(new Node(pop, null));
            last = last.getNext();
        }

        return first;
    }
}

class Node {
    private Integer date;
    private Node next;

    public Node(int date, Node next) {
        this.date = date;
        this.next = next;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}