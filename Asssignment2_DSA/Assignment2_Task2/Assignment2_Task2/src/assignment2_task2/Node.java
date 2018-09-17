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
public class Node<E> {
    private E aCustomer;
    private Node previousNode;
    private Node nextNode;
    
    //constructor
    public Node(E aCustomer, Node previousNode, Node nextNode)
    {
        this.aCustomer=aCustomer;
        this.previousNode=previousNode;
        this.nextNode=nextNode;
    }
    
    public Node(E aCustomer, Node previousNode)
    {
        this(aCustomer,previousNode,null);
        
    }
    
    public Node(E aCustomer)
    {
        this(aCustomer,null,null);
    }
    
    //getter and setter

    public E getaCustomer() {
        return aCustomer;
    }

    public void setaCustomer(E aCustomer) {
        this.aCustomer = aCustomer;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
    
}
