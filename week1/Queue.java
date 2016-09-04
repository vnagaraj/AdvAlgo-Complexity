package week1;

/**
 * Queue data structure using link list
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 September 1st, 2016
 * @param <Item>
 */
class Queue<Item>{

    private Node first;
    private Node last;
    private int size;

    private class Node{
        Node next;
        Item item;

        Node(Item item){
            this.item = item;
        }
    }

    public void enqueue(Item item){
        if (item == null){
            throw new RuntimeException("Item cannot be null");
        }
        Node oldLast = last;
        last = new Node(item);
        if (oldLast != null) {
            oldLast.next = last;
        }
        if (first == null){
            first = last;
        }
        size += 1;
    }

    public Item dequeue(){
        if (first == null){
            throw new RuntimeException("Cannot dequeue from empty queue");
        }
        Item item = first.item;
        first = first.next;
        if (first== null){
            last = first;
        }
        size -= 1;
        return item;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}