package linkedlist;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

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

    public Node removeLast(){
        // check if list is empty.
            // if so, return null
        // check if list is length of 1
            // if so, return head (first node)
        // We want to set "tail" to "tail.previous" (which isn't a thing yet)
        // We want to return the last node, so we save it as a variable. Maybe "lastNode"
        // Once we set the tail.prev to tail, we make tail.next null
            // this will cut off the lastNode from the list
        // we then return lastNode

        // To do this, we want to run a loop.
        // We'll also need a temp var, let's call it current
        // when looping, if current.next == tail:
            // then lastNode = tail
        // tail = current
        // tail.next = null
        // return lastNode

        if(length == 0) {
            return null;
        }

        Node lastNode = tail;
        Node current = head;

        while (current != null) {
            if (current.next == tail) {
                tail = current;
                tail.next = null;
                length--;
                return lastNode;
            }
            current = current.next;
        }
        length--;
        if(length == 0) {
            head = null;
            tail = null;
            return lastNode;
        }
        return lastNode;
    }

    public void prepend(int value) {
        // We have a new node, we want it at the very front
        // We will set newNode.next = head
        // Then we will set head to newNode
        // Then return
        Node newNode = new Node(value);
        if(length == 0) {
            head = newNode;
            tail = newNode;
        }
        newNode.next = head;
        head = newNode;
        length++;
    }

    public Node removeFirst(){
        // check if length is 0. If it is, return null.
        if (length == 0) {
            return null;
        }
        // make a firstNode variable and set it to head.
        // head will move up one, but firstNode will remain
        Node firstNode = head;

        // if length is 1, we need to return head, then decrement length.
        // we set variable firstNode = head.
        // set head and tail = null.
        // decrement length by 1.
        // return firstNode
        if (length == 1) {
            head = null;
            tail = null;
            length--;
            return firstNode;
        }

        // if length is greater than 1, we move head to be firstNode.next
        // we then set firstNode.next to null, cutting it off from the list
        // we then decrement the length by 1
        // we then return firstNode
        head = firstNode.next;
        firstNode.next = null;
        length--;
        return firstNode;

    }

    public Node get(int index){
        if(index < 0 || index >= length) {
            return null;
        }

        Node temp = head;

        for(int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public boolean set(int index, int value) {
        // given an index, we find the node at that index and set its value to "value"
        // if index is less than 0 or greater than the length, we return false
        // if we are unable to set the value at that index for some other reason, we also return false
        // if we successfully change the value at the given index, we return true

        Node temp = get(index);
        if(temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value) {
        if(index < 0 || index > length) {
            return false;
        }

        if (index == 0) {
            prepend(value);
            return true;
        }

        if (index == length) {
            append(value);
            return true;
        }

        Node previous = get(index -1);
        Node nodeToBeInserted = new Node(value);

        nodeToBeInserted.next = previous.next;
        previous.next = nodeToBeInserted;
        length++;
        return true;
    }

    public Node remove(int index) {
        // return null if index < 0 or index >= length
        if(index < 0 || index >= length) {
            return null;
        }
        if(index == 0) {
            return removeFirst();
        }
        if(index == length - 1) {
            return removeLast();
        }
        Node temp = get(index - 1);
        Node nodeToBeRemoved = temp.next;

        temp.next = nodeToBeRemoved.next;
        nodeToBeRemoved.next = null;

        length --;
        return nodeToBeRemoved;
    }

    public void reverse() {
        // Need to swap head and tail
        // need to reverse pointers
        Node temp = head;
        head = tail;
        tail = temp;

        Node after = temp.next;
        Node before = null;

        for(int i = 0; i < length; i++){
            after = temp.next;
            temp.next = before;
            before = temp;
            temp = after;
        }

    }

    // I'll want to use two runners, "slow" and "fast".
    // fast will traverse twice as fast as slow.
    // Once fast has a .next (or a .next.next) of "null", then we return slow
    // If the length is 0 or 1... return null?
    public Node findMiddleNode() {
        Node fast = head;
        Node slow = head;

        if(head == null || head.next == null) {
            return null;
        }

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

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

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
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
        // we want to return the Kth from the end of the LL, or Kth from tail.
        // we know where tail is
        // we could have two runner variables and have them K nodes apart.
        // Do this by running a loop for the "fast" variable that goes until K.
        // Then we run a loop incrementing both vars until we reach the end, or until "fast == tail" or fast.next == null.
        // when fast == tail or fast.next == null, we return the "slow" variable, because that will be K nodes
            // behind fast, which will be on the last node
        // how do we check for a list shorter than K?
            // fast count?

        Node fast = head;
        Node slow = head;

        // if head is null (list is empty) return null
        if (head == null) {
            return null;
        }

        for (int i = 0; i < k; i++) {
            if(fast != null) {
                fast = fast.next;
            } else {
                return null;
            }
        }
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
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

        Node dummyNodeLess = new Node(0);
        Node dummyNodeGreaterThanOrEqual = new Node(0);
        Node current = head;
        Node prevLess = dummyNodeLess;
        Node prevGreaterThanOrEqual = dummyNodeGreaterThanOrEqual;

        while(current != null) {
            if(current.value < x) {
                prevLess.next = current;
                prevLess = current;
            } else {
                prevGreaterThanOrEqual.next = current;
                prevGreaterThanOrEqual = current;
            }
            current = current.next;
        }

        prevLess.next = dummyNodeGreaterThanOrEqual.next;
    }

    public void removeDuplicates(){
        // use a HashSet
        // iterate thru LL, check hashset to see if value is there
        // if value is not in hashset, add it.
        // if value IS in hashset, remove that node
            // need a "prev" variable so we can skip the dupe node
            // make prev.next = current.next

        // if head is null or length 1, return
        //

        Set<Integer> values = new HashSet<>();

        Node prev = null;
        Node current = head;

        while (current != null) {
            if(values.contains(current.value)) {
                prev.next = current.next;
                length--;
            }else {
                values.add(current.value);
                prev = current;
            }
            current = current.next;
        }

    }

    public void reverseBetween(int m, int n) {
        if (head == null) {
            return;
        }

        // set a dummy node, value 0.
        // set "prev" variable, set it to dummy
        // dummy.next should point to head
        // we'll move prev ahead m steps, so it will point to the node before m
            // this will track the node right before the start index
            // this should be a for loop: (i = 0; i <= m; i++)
        // set a current variable, have it point to prev.next
            // current will be the first to be reversed
        // we should do an amount of steps equal to n - m
            // a for loop from m to n should do this
        // set a nodeToMove var to point to current.next
        // set current.next = nodeToMove.next
        // set nodeToMove.next = prev.next
        // set prev.next = nodeToMove

        Node dummy = new Node(0);
        Node prev = dummy;
        dummy.next = head;

        for (int i = 0; i < m; i++) {
            prev = prev.next;
        }

        Node current = prev.next;
        Node nodeToMove = null;

        for (int i = 0; i < n - m; i++) {
            nodeToMove = current.next;
            current.next = nodeToMove.next;
            nodeToMove.next = prev.next;
            prev.next = nodeToMove;
        }
        head = dummy.next;
    }
}

