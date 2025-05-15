import java.util.Random;
import java.util.Scanner;

public class Main {

    static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
        }
    }

    static class DoublyLinkedList {
        Node head;
        Node tail;

        public void addLastNode(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }

        public void clear() {
            head = tail = null;
        }

        public void printForward() {
            Node current = head;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }

        public boolean isSorted() {
            Node current = head;
            while (current != null && current.next != null) {
                if (current.data > current.next.data) {
                    return false;
                }
                current = current.next;
            }
            return true;
        }

        public void quickSort() {
            quickSort(head, tail);
        }

        private void quickSort(Node left, Node right) {
            if (left != null && right != null && left != right && left != right.next) {
                Node pivot = partition(left, right);
                quickSort(left, pivot.prev);
                quickSort(pivot.next, right);
            }
        }

        private Node partition(Node low, Node high) {
            int pivot = high.data;
            Node i = low.prev;

            for (Node j = low; j != high; j = j.next) {
                if (j.data <= pivot) {
                    i = (i == null) ? low : i.next;
                    int temp = i.data;
                    i.data = j.data;
                    j.data = temp;
                }
            }

            i = (i == null) ? low : i.next;
            int temp = i.data;
            i.data = high.data;
            high.data = temp;
            return i;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        DoublyLinkedList list = new DoublyLinkedList();

        while (true) {
            System.out.println("\nOperation:");
            System.out.println("1) Generate random sequence");
            System.out.println("2) Generate increasing sequence");
            System.out.println("3) Print the current sequence");
            System.out.println("4) Check if list is increasing (sorted)");
            System.out.println("5) Sort with QuickSort");
            System.out.println("6) End");
            System.out.print("Operation: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    list.clear();
                    System.out.print("Enter number of elements: ");
                    int n = scanner.nextInt();
                    for (int i = 0; i < n; i++) {
                        list.addLastNode(rand.nextInt(100));
                    }
                    break;

                case 2:
                    list.clear();
                    System.out.print("Enter number of elements: ");
                    int m = scanner.nextInt();
                    for (int i = 0; i < m; i++) {
                        list.addLastNode(i);
                    }
                    break;

                case 3:
                    list.printForward();
                    break;

                case 4:
                    if (list.isSorted()) {
                        System.out.println("List is sorted.");
                    } else {
                        System.out.println("List is not sorted.");
                    }
                    break;

                case 5:
                    long start = System.nanoTime();
                    list.quickSort();
                    long end = System.nanoTime();
                    System.out.println("List sorted with QuickSort.");
                    System.out.printf("Duration: %.3f ms%n", (end - start) / 1_000_000.0);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }
}
