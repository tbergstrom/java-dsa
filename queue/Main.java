package queue;

public class Main {

    public static void main(String[] args) {

        Queue myQueue = new Queue(7);

        myQueue.getFirst();
        myQueue.getLast();
        myQueue.getLength();

        myQueue.printQueue();
    }
}
