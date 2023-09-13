public class LinkedList {

    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (head == null) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("\nLinked List:");
        if (head == null) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        length++;
    }

    // I'll want to use two runners, "slow" and "fast".
    // fast will traverse twice as fast as slow.
    // Once fast has a .next (or a .next.next) of "null", then we return slow
    // If the length is 0 or 1... return null?
    public Node findMiddleNode() {
        Node slow = head;
        Node fast = head;

        if(head == null) {
            return null;
        }

        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
            if(fast.next != null) {
                fast = fast.next;
            }
        }
        return slow;
    }

    // Implement a method called hasLoop that checks whether the list contains a loop or not.
    // If the list contains a loop, the method should return true; otherwise, it should return false

    // Use fast/ slow pointers
    // if fast or fast.next == slow, return true
    // if fast.next == null, return false
    public boolean hasLoop(){
        Node slow = head;
        Node fast = head;

        if (head == null) {
            return false;
        }

        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
            if(fast.next != null) {
                fast = fast.next;
                if(fast.next == slow) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    // Implement a method called findKthFromEnd that returns the k-th node from the end of the list.
    // If the list has fewer than k nodes, the method should return null.

    // Use fast/ slow pointers.
    // both pointers start at head.
    // fast will move out K spaces. Then both pointers will move at same pace.
    // Keep a counter? Use while loop.
    // Fast.next will eventually == null. At this point, return slow.

    public Node findKthFromEnd(int k) {

        Node fast = head;
        Node slow = head;

        if(head == null) {
            return null;
        }

        for (int i = 0; i < k; i++) {
            if (fast != null) {
                fast = fast.next;
            } else {
                return null;
            }
        }

        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    // Given a value x you need to rearrange the linked list such that all nodes with a value less than x
    // come before all nodes with a value greater than or equal to x.

    // While traversing the linked list, maintain two separate chains:
    // one for values less than x and one for values greater than or equal to x.
    // Use dummy nodes to simplify the handling of the heads of these chains.
    // After processing the entire list, connect the two chains to get the desired arrangement.
    public void partitionList(int x){
        if(head == null) {
            return;
        }

        // dummy nodes (values are irrelevant)
        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);

        // pointers
        Node prev1 = dummy1;
        Node prev2 = dummy2;
        Node current = head;

        while(current != null) {
            if (current.value < x) {
                prev1.next = current;
                prev1 = current;
            } else {
                prev2.next = current;
                prev2 = current;
            }
            current = current.next;
        }

        prev2.next = null;
        prev1.next = dummy2.next;
        head = dummy1.next;

    }
}

