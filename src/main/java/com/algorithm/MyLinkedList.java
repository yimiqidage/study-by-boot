package com.algorithm;

/**
 * 手动实现单向链表
 * @author weishi8
 * @create 2019-05-27
 * @description
 */
public class MyLinkedList {

    /**
     * 内部类，定义链表节点
     */
    private static class Node {
        int data;
        Node next;

        public Node (int data){
            this.data=data;
        }
    }

    //链表大小
    private int size;
    //表头节点
    private Node head;
    //表尾节点
    private Node last;

    /**
     * 插入新元素
     * @param data
     * @param index
     */
    public void insert(int data,int index){
        if(index<0 || index>size){
            throw new ArrayIndexOutOfBoundsException("数组越界");
        }
        Node insertNode =  new Node(data);
        if(size==0){
            head = insertNode;
            last = insertNode;
        } else if(size == index){
            last.next = insertNode;
            last = insertNode;
        } else {
            Node preNode = getNode(index-1);
            Node nextNode = preNode.next;
            preNode.next = insertNode;
            insertNode.next = nextNode;
        }
        size ++;
    }

    /**
     * 删除元素
     * @param index
     * @return
     */
    public Node delete(int index){
        if(index<0 || index>size){
            throw new ArrayIndexOutOfBoundsException("数组越界");
        }
        Node deleteNode = null;
        if(index==0){
            deleteNode = head;
            Node newHead = head.next;
            head = newHead;
        }else if (size -1 ==index){
            Node preNode = getNode(index-1);
            deleteNode = preNode.next;
            preNode.next = null;
            last = preNode;
        }else {
            Node preNode= getNode(index-1);
            deleteNode = preNode.next;
            Node nextNode = deleteNode.next;
            preNode.next = nextNode;
        }
        size--;
        return deleteNode;
    }

    /**
     * 链表查找元素
     * @param index 查找的位置
     * @return
     */
    public Node getNode (int index){
        if(index<0 || index==size){
            throw new ArrayIndexOutOfBoundsException("数组越界");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 输出链表
     */
    public  void output(){
        Node temp = head;
        while(temp!=null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.insert(3,0);
        myLinkedList.insert(4,1);
        myLinkedList.insert(5,2);
        myLinkedList.insert(6,1);
        myLinkedList.insert(7,2);
        myLinkedList.insert(8,5);
        myLinkedList.output();
        System.out.println("size:"+myLinkedList.size);
    }
}
