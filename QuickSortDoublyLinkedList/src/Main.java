import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random rand = new Random();
    static DoublyLinkedList list = new DoublyLinkedList();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOperation:");
            System.out.println("1) Generate random sequence");
            System.out.println("2) Generate increasing sequence");
            System.out.println("3) Print the current sequence");
            System.out.println("4) Check if list is increasing (sorted)");
            System.out.println("5) Sort with Quicksort");
            System.out.println("6) End");
            System.out.print("Operation:");

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
                    long start = System.currentTimeMillis();
                    list.quickSort();
                    long end = System.currentTimeMillis();
                    System.out.println("List sorted with QuickSort.");
                    System.out.println("Duration: " + (end - start) + " ms");
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
