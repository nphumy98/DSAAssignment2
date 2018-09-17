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
public class Node<E> {
    private E data;
    private Node previousNode;
    private Node nextNode;
    
    //constructor
     public Node(E data, Node previousNode, Node nextNode)
    {
        this.data=data;
        this.previousNode=previousNode;
        this.nextNode=nextNode;
    }
    
    public Node(E data, Node previousNode)
    {
        this(data,previousNode,null);
        
    }
    
    public Node(E data)
    {
        this(data,null,null);
    }
    
    //getter and setter
    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
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
