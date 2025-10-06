package org.example;

public class LinkedList {

    private Node head;

    private Node tail;

    private Integer length;

    public LinkedList(Integer n){
        Node node = new Node(n);
        this.head = node;
        this.tail = node;
        this.length = 1;
    }

    public void printList(){
        Node node = head;
        while(node != null){
            System.out.println(node.value);
            node = node.next;
        }
    }

    public void append(Integer value){
        Node newNode = new Node(value);

        if(length==0){
            head = newNode;
            tail = newNode;
        }else {
            tail.next = new Node(value);
            tail = tail.next;
            this.length += 1;
        }
    }

    public Node removeLast(){
        if(length == 0) return null;

        Node temp = head;

        Node pre = head;

        while(temp.next != null){
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;
        length--;
        if (length==0){
            head = null;
            tail = null;
        }

        return temp;
    }

    public Node removeFirst(){
            if(length==0){
                return null;
            }

            Node temp = head;

            head = temp.next;
            temp.next = null;
            length--;
            if(length==0){
                tail = null;
            }
            return temp;
    }

    public void prepend(Integer value){
        Node node = new Node(value);
        if(length==0){
            head = node;
            tail = node;
        }else {
            node.next = head;
            head = node;
        }
        length++;
    }

    public Node get(int index){
        if(index<0 || index >= length){
            return null;
        }

        Node temp = head;

        for(int i=0;i<index;i++){
            temp = temp.next;
        }

        return temp;
    }

    public boolean set(int index,int value){
        Node temp = get(index);
        if(temp !=null){
            temp.value = value;
            return true;
        }
        return  false;
    }

    public boolean insert(int index, int value){
        if(index<0 && index > this.length){
            return false;
        }

        if(index==0){
            prepend(value);
            return true;
        }

        if(index==length){
            append(value);
            return true;
        }

        Node node = new Node(value);

        Node temp = get(index-1);

        node.next = temp.next;
        temp.next = node;
        length++;

        return true;
    }

    public Node remove(int index){
        if(index<0 || index>= length) return null;

        if(index==0) return removeFirst();

        if(index==length-1) removeLast();

        Node prev = get(index-1);
        Node temp = prev.next;

        prev.next = temp.next;

        temp.next = null;

        length--;

        return temp;
    }

    public void reverse() {
        Node temp = head;
        head = tail;
        tail = temp;
        Node after;
        Node before = null;
        for (int i = 0; i < length; i++) {
            after = temp.next;
            temp.next = before;
            before = temp;
            temp = after;

        }
    }


    public Node findMiddleNode(){
        Node slow = head;
        Node fast = head;
        while(slow.next!=null || fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    
    public Node findKthFromEnd(int k){
        if(k<=0) return null;

        Node slow = head;
        Node fast = head;

        for(int i = 0;i<k;i++){
            if(fast==null) return null;
            fast = fast.next;
        }

        while(null !=fast){
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }


    public void partitionList(int x){

        if(head == null){
            return;
        }
        Node dummy1 = new Node(0);

        Node dummy2 = new Node(0);

        Node prev1 = dummy1;

        Node prev2 = dummy2;

        Node current = head;

        while(current!=null){

            if(current.value<x){
                prev1.next = current;

                prev1 = current;

            }else{
                prev2.next = current;
                prev2 = current;
            }

            current = current.next;
        }

        prev2.next = null;
        prev1.next = dummy2.next;

        head = dummy1.next;
    }

    class Node {
        Integer value;
        Node next;
        public Node(Integer value){
            this.value = value;
        }
    }
}
