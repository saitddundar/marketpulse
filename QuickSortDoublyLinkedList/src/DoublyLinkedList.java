public class DoublyLinkedList {

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
                swapData(i, j);
            }
        }
        i = (i == null) ? low : i.next;
        swapData(i, high);

        return i;
    }

    private void swapData(Node a, Node b) {
        int temp = a.data;
        a.data = b.data;
        b.data = temp;
    }

}
