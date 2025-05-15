import java.util.*;

public class Main {
    static int[] array = new int[0];
    static Random rand = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("\nOperation:");
            System.out.println("1) Generate random sequence");
            System.out.println("2) Generate increasing sequence");
            System.out.println("3) Generate decreasing sequence");
            System.out.println("4) Print the current sequence");
            System.out.println("5) Check if array is sorted");
            System.out.println("6) Print sum of sequence");
            System.out.println("7) Sort with Quicksort");
            System.out.println("8) Sort with randomized Quicksort");
            System.out.println("9) Sort with median Quicksort");
            System.out.println("10) Sort with any other algorithm");
            System.out.println("0) End");
            System.out.print("Operation: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 :
                    generateRandomSequence(scanner);
                    break;
                case 2 :
                    generateIncreasingSequence(scanner);
                    break;
                case 3 :
                    generateDecreasingSequence(scanner);
                    break;
                case 4 :
                    printArray();
                    break;
                case 5 :
                    checkIfSorted();
                    break;
                case 6 :
                    printSum();
                    break;
                case 7 :
                    sortWithQuickSort();
                    break;
                case 8 :
                    sortWithRandomizedQuickSort();
                    break;
                case 9 :
                    sortWithMedianQuickSort();
                    break;
                case 10 :
                    sortWithBubbleSort();
                    break;
                case 0 :

                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }



    static void generateRandomSequence(Scanner scanner) {
        System.out.print("Enter number of elements (1 to 1000000): ");
        int n = scanner.nextInt();
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt(1_000_000);
        }
    }

    static void generateIncreasingSequence(Scanner scanner) {
        System.out.print("Enter number of elements (1 to 1000000): ");
        int n = scanner.nextInt();
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
    }

    static void generateDecreasingSequence(Scanner scanner) {
        System.out.print("Enter number of elements (1 to 1000000): ");
        int n = scanner.nextInt();
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = n - i;
        }
    }

    static void printArray() {
        System.out.println("Array: " + Arrays.toString(array));
    }

    static void checkIfSorted() {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                System.out.println("Array is NOT sorted.");
                return;
            }
        }
        System.out.println("Array is sorted.");
    }

    static void printSum() {
        long sum = 0;
        for (int j : array) sum += j;
        System.out.println("Sum: " + sum);
    }

    static void sortWithQuickSort() {
        int[] copy = array.clone();
        long start = System.currentTimeMillis();
        quickSort(copy, 0, copy.length - 1);
        long end = System.currentTimeMillis();
        array = copy; // sıralanan dizi geri aktarılıyor
        System.out.println("Sorted with standard QuickSort in " + (end - start) + " ms");
    }


    static void sortWithRandomizedQuickSort() {
        int[] copy = array.clone();
        long start = System.currentTimeMillis();
        randomizedQuickSort(copy, 0, copy.length - 1);
        long end = System.currentTimeMillis();
        array = copy;
        System.out.println("Sorted with randomized QuickSort in " + (end - start) + " ms");
    }


    static void sortWithMedianQuickSort() {
        int[] copy = array.clone();
        long start = System.currentTimeMillis();
        medianQuickSort(copy, 0, copy.length - 1);
        long end = System.currentTimeMillis();
        array = copy;
        System.out.println("Sorted with median QuickSort in " + (end - start) + " ms");
    }


    static void sortWithBubbleSort() {
        int[] copy = array.clone();
        long start = System.currentTimeMillis();
        for (int i = 0; i < copy.length - 1; i++) {
            for (int j = 0; j < copy.length - i - 1; j++) {
                if (copy[j] > copy[j + 1]) {
                    int temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;
                }
            }
        }
        long end = System.currentTimeMillis();
        array = copy; // sıralanan dizi geri aktarılıyor
        System.out.println("Sorted with Bubble Sort in " + (end - start) + " ms");
    }


    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = low + rand.nextInt(high - low + 1);
            swap(arr, pivotIndex, high);
            int p = partition(arr, low, high);
            randomizedQuickSort(arr, low, p - 1);
            randomizedQuickSort(arr, p + 1, high);
        }
    }

    static void medianQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            int pivotIndex = medianOfThree(arr, low, mid, high);
            swap(arr, pivotIndex, high);
            int p = partition(arr, low, high);
            medianQuickSort(arr, low, p - 1);
            medianQuickSort(arr, p + 1, high);
        }
    }

    static int medianOfThree(int[] arr, int a, int b, int c) {
        int x = arr[a];
        int y = arr[b];
        int z = arr[c];
        if ((x > y) != (x > z))
            return a;
        else if ((y > x) != (y > z))
            return b;
        else
            return c;
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];// pivot en sağdaki eleman
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
} 
