public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
        }
    }

    public DoublyLinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void getHead() {
        System.out.println("Head: " + head.value);
    }

    public void getTail() {
        System.out.println("Tail: " + tail.value);
    }

    public void getLength() {
        System.out.println("Length: " + length);
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if(length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        length++;
    }

    public Node removeLast() {
        if(length == 0) {
            return null;
        }

        Node temp = tail;

        if(length == 1) {
            head = null;
            tail = null;
        } else{
            tail = tail.prev;
            tail.next = null;
            temp.prev = null;
        }

        length--;
        return temp;

    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if(length == 0) {
            head = newNode;
            tail = newNode;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        length++;
    }

    public Node removeFirst() {
        if (length == 0) {
            return null;
        }

        Node temp = head;

        if (length == 1) {
            head = null;
            tail = null;
            length --;
            return temp;
        }

        head = head.next;
        head.prev = null;
        temp.next = null;
        length--;
        return temp;

    }

    public Node get(int index) {
        if(index < 0 || index >= length) {
            return null;
        }
        Node temp = new Node(0);

        if (index < (length/2)) {
            temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = length-1; i > index; i--) {
                temp = temp.prev;
            }
        }
        return temp;
    }

    public boolean set(int index, int value) {
        if(index < 0 || index > length || length < 1) {
            return false;
        }
        Node nodeToBeSet = get(index);
        nodeToBeSet.value = value;
        return true;
    }

    public boolean insert(int index, int value) {
        if(index < 0 || index > length) {
            return false;
        }
        Node newNode = new Node(value);

        if(index == 0) {
            prepend(value);
            return true;
        }
        if(index == length) {
            append(value);
            return true;
        }

        Node nodeBefore = get(index-1);
        Node nodeAfter = nodeBefore.next;

        newNode.prev = nodeBefore;
        nodeBefore.next = newNode;
        newNode.next = nodeAfter;
        nodeAfter.prev = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {

        if(index < 0 || index >= length) {
            return null;
        }
        if(index == 0) {
            return removeFirst();
        }
        if(index == length -1) {
            return removeLast();
        }

        Node toBeRemoved = get(index);
        Node after = toBeRemoved.next;
        Node before = toBeRemoved.prev;

        before.next = after;
        after.prev = before;
        toBeRemoved.next = null;
        toBeRemoved.prev = null;
        length --;
        return toBeRemoved;
    }

    // LEET CODE EXERCISES

    public void swapFirstLast() {
        // if length is less than 2, return
            // (if length is 1, head and tail are the same node)
            // (if length is 0, it's empty)
        // firstTemp and lastTemp:
            // each will hold a value
        // set firstTemp.value = head.value
        // set lastTemp.value = tail.value
        // set tail.value = firstTemp.value
        // set head.value = lastTemp.value
        if (length >= 2) {
            Node firstTemp = new Node(head.value);
            Node lastTemp = new Node(tail.value);
            tail.value = firstTemp.value;
            head.value = lastTemp.value;
        }
    }

    //    Implement a method called reverse() that reverses the order of the nodes in the list.
    public void reverse(){

        Node currentNode = head;
        Node temp = null;

        while(currentNode != null) {
            temp = currentNode.prev;
            currentNode.prev = currentNode.next;
            currentNode.next = temp;
            currentNode = currentNode.prev;
        }
        temp = head;
        head = tail;
        tail = temp;
    }

    //    Write a method to determine whether a given doubly linked list reads the same forwards and backwards.
    public boolean isPalindrome(){
        Node forwardPointer = head;
        Node backwardPointer = tail;

        while (forwardPointer != backwardPointer) {
            if (forwardPointer.value != backwardPointer.value) {
                return false;
            } else {
                backwardPointer = backwardPointer.prev;
                forwardPointer = forwardPointer.next;
            }
        }
        return true;
    }

    //    Implement a method called swapPairs within the class that swaps the values of adjacent nodes in the linked list. The method should not take any input parameters.
    public void swapPairs() {
        if (length < 2) {
            return;
        }

        // We need to swap pairs, so indexes 0&1, 2&3, 4&5, etc.
            // we need to set firstInPair.value = secondInPair.value
            // then set secondInPair.value = firstInPair.value
            // we need a temp variable to do this.
        // We could do this in a loop, and traverse 2 at a time with
            // .next.next
            // Is there another way?
            // If we use a for loop, we could do this only on even indexes

        Node dummy = new Node(0);
        dummy.next = head;
        Node previousNode = dummy;
        Node firstNode;
        Node secondNode;

        while(head != null && head.next != null) {
            firstNode = head;
            secondNode = head.next;

            previousNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            secondNode.prev = previousNode;
            firstNode.prev = secondNode;
            if(firstNode.next != null) {
                firstNode.next.prev = firstNode;
            }

            head = firstNode.next;
            previousNode = firstNode;
        }
        head = dummy.next;

        if(head != null) {
            head.prev = null;
        }

    }
}
