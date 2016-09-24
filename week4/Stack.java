package week4;

/**
 * Stack class used for the iterative version of DFS
 *
 * @version 1.0 September 23rd, 2016
 *
 */
class Stack<Item>{

    private Node first;
    private int size;


    private class Node{
        Node next;
        Item item;

        Node(Item item){
            this.item = item;
        }
    }

    public void push(Item item){
        if (item == null){
            throw new RuntimeException("Item cannot be null");
        }
        Node oldFirst = first;
        first = new Node(item);
        first.next = oldFirst;
        size += 1;
    }

    public Item pop(){
        if (first == null){
            throw new RuntimeException("Cannot pop from empty queue");
        }
        Item item = first.item;
        first = first.next;
        size -= 1;
        return item;
    }

    public Item peek(){
        if (first == null){
            return null;
        }
        return first.item;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}
