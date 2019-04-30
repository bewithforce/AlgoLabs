public class Queue<T> {
    private class Node{
        private Node next;
        private T anInt;
        private Node(){
            next = null;
        }
    }

    private Node head, tail;
    private int size = 0;

    private Queue(){
        head = tail = null;
    }

    private void push(T a){
        Node node = new Node();
        node.anInt = a;
        if (head == null){
            head = tail = node;
            size++;
            return;
        }
        tail.next = node;
        tail = node;
        if (head.next == null){
            head.next = tail;
        }
        size++;
    }

    private T pop(){
        if(size == 0){
            return null;
        }
        Node node = head;
        head = head.next;
        node.next = null;
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
            string.append(" <- ");
            node = node.next;
        }
        string.append(node.anInt);
        return string.toString();
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
    
    public static void main(String[]args){
        Queue<Integer> list = new Queue<>();
        list.push(42);
        list.push(17);
        list.push(43);
        System.out.println(list);
        System.out.println(list.pop());
        System.out.println(list);
    }
}
