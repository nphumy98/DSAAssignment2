/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stack;

/**
 *
 * @author wrk2544
 */
public class StackTask1<E> extends AbstractStack<E> implements StackInterface<E>{
    
    private Node head; //top of stack
    private Node tail; //tail of stack
    
    //constructor
    public StackTask1()
    {
        this.sizeStack=0;
        head=null;
        tail=head;
    }
    @Override
    public void push(E value) {
        Node newNode= new Node(value); //make new node
        if (sizeStack==0) //if the stack is empty
        {
            head=newNode;  //head to newnode
            tail=head; //tail to head
        }
        else //if the stack is not empty
        {
            head.setPreviousNode(newNode);// set newNode on top of head Node
            newNode.setNextNode(head); //set head as NextNode of new Node
            head= head.getPreviousNode(); //set new head as previous node or new node
        }
        this.sizeStack++;
    }

    @Override
    public E pop() {
        if (sizeStack>1)
        {
            Node returnNode= head;
            head=head.getNextNode();// set new head to nextNode
            head.setPreviousNode(null); //set previousNode of head to null so this is new head node
            this.sizeStack--;
            return (E)returnNode.getData();
        }
        else if (sizeStack==1)// if the list only has 1 element
        {
            Node returnNode=head;
            head=null;
            this.sizeStack--;
            return (E)returnNode.getData();
        }
        return null; //else return null
    }

    @Override
    public E peek() {
        return (E)this.head.getData();
    }

    @Override
    public void traverse() {
        Node currentNode= head;
        System.out.println("Stack has "+ this.sizeStack+" elements");
        if (head!=null) //if stack is not empty
        {
            while(currentNode!=null)
            {
                System.out.println(currentNode.getData()); //print out Node
                currentNode=currentNode.getNextNode();//get to nextNode
            }
        }
    }
    
}
