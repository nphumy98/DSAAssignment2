/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_task2;

/**
 *
 * @author wrk2544
 */
public class QueueTerminal<E> extends QueueAbstractClass<E> implements QueueInterface<E>{
    private Node head; //head of the queue
    private Node tail; //tail of the queue
    
    //constructor
    public QueueTerminal()
    {
        this.sizeQueue=0;
        head=null;
        tail=null;
    }
    @Override
    public void enqueue(E value) {
        if (sizeQueue==0) //if list is empty
        {
            this.head= new Node(value,null,null);
            tail=head;
        }
        else //if list is not empty
        {
            Node newNode= new Node(value,tail,null); //set previousNode of newNode is tail
            tail.setNextNode(newNode); //set NextNode of tail is newNode
            this.tail= tail.getNextNode(); //set tail is newNode
        }
        sizeQueue++;
    }

    @Override
    public E dequeue() {
       Node dequeueNode=null;

       if(sizeQueue>1) //if queue has more than 1 element
       {
           dequeueNode= head;
           this.head= head.getNextNode(); //get headNode to nextNode
           head.setPreviousNode(null); // previousNode of headNode is null
            sizeQueue--;
            return (E)dequeueNode.getaCustomer();
       }
       else if (sizeQueue==1) // if Queue has only 1 element
       {
            dequeueNode= head;
            head=null;
            sizeQueue--;
            return (E)dequeueNode.getaCustomer();
       }
       return null;// return null if queue is empty
    }
    
      @Override
    public void printQueue(String queueID) {
        if (sizeQueue>0)
        {
            System.out.println("There are "+this.sizeQueue+" Customers at Terminal " +queueID+" :");
            Node currentNode= this.head;
            do
            {
                System.out.println(currentNode.getaCustomer());
                currentNode=currentNode.getNextNode();
            }while (currentNode!=null);
        }
        else
        {
            System.out.println("There are no customer at Terminal "+queueID);
        }
    }
    

    @Override
    public E peek() {
        if (sizeQueue<=0)
        {
            return null;
        }
        return (E)this.head.getaCustomer();
    }

    //getter and setter

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

  
}
