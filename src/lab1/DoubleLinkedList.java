package lab1;

public class DoubleLinkedList<T> {
    private class Node{
        private Node next;
        private Node prev;
        private T anInt;
        private Node(){
            next = null;
            prev = null;
        }
    }

    private Node head, tail;
    private int size = 0;

    private DoubleLinkedList(){
        head = tail = null;
    }

    private void add(T a){
        Node node = new Node();
        node.anInt = a;
        if (head == null){
            head = tail = node;
            size++;
            return;
        }
        node.prev = tail;
        tail.next = node;
        tail = node;
        if (tail.prev == null){
            tail.prev = head;
        }
        size++;
    }

    private boolean addAt(T a, int index){
       if(index > size   || index < 0){
           return false;
       }
       Node node = new Node();
       node.anInt = a;
       if(index == 0){
           node.next = head;
           head.prev = node;
           head = node;
           size++;
           return true;
       }
        if(index == size){
            node.prev = tail;
            tail.next = node;
            tail = node;
            size++;
            return true;
        }
        Node temp = head;
        for(int i = 0; i < index; i++){
            temp = temp.next;
        }
        node.prev = temp;
        node.next = temp.next;
        temp.next.prev = node;
        temp.next = node;
        size++;
        return true;
    }

    private boolean removeAt(int index){
        if(index > size - 1){
            return false;
        }
        if(index == 0){
            head.next.prev = null;
            head = head.next;
            size--;
            return true;
        }
        if(index == size - 1){
            tail.prev.next = null;
            tail = tail.prev;
            size--;
            return true;
        }
        Node node = head;
        for(int i = 0; i < index; i++){
            node = node.next;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        return true;
    }

    private boolean remove(T a){
        if(size == 0){
            return false;
        }
        if(head.anInt == a){
            head.next.prev = null;
            head = head.next;
            size--;
            return true;
        }
        Node node = head.next;
        while (node.next != null){
            if(node.anInt == a){
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
                return true;
            }
            node = node.next;
        }
        if(tail.anInt == a){
            tail.prev.next = null;
            tail = tail.prev;
            size--;
            return true;
        }
        return false;
    }

    private T get(int index){
        if(index > size - 1 || index < 0){
            return null;
        }
        if(index == 0){
            return head.anInt;
        }
        if(index == size - 1){
            return tail.anInt;
        }
        Node node = head;
        for(int i = 0; i < index; i++){
            node = node.next;
        }
        return node.anInt;
    }

    public String toString() {
        if(head == null){
            return "";
        }
        StringBuilder string = new StringBuilder();
        Node node = head;
        while(node.next != null){
            string.append(node.anInt);
            string.append(" <-> ");
            node = node.next;
        }
        string.append(node.anInt);
        return string.toString();
    }

    public static void main(String[]args){
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.add(42);
        list.add(42);
        list.remove(42);
        list.addAt(17, 1);
        list.removeAt(1);
        System.out.println(list);
    }
}
