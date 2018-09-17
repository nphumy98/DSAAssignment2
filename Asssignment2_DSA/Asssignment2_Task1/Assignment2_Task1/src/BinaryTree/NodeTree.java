/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;

/**
 *
 * @author wrk2544
 */
public class NodeTree<E>{
    
    private E data;
    private NodeTree<E> parent;
    private NodeTree<E> leftNode;
    private NodeTree<E> rightNode;
    
    //Constructor
    public NodeTree(E data, NodeTree parent, NodeTree leftNode, NodeTree rightNode)
    {
        this.data=data;
        this.parent=parent;
        this.leftNode=leftNode;
        this.rightNode=rightNode;
    }
    
    public NodeTree(E data, NodeTree parent)
    {
        this(data, parent, null,null);
    }
    
    public NodeTree(E data)
    {
        this(data,null,null,null);
    }
    
    public NodeTree()
    {
        this(null,null,null,null);
    }
    //methods
    public boolean isLeftChild() {
        // post: returns true if this is a left child of parent
        if ((this.parent!=null)&&(this== this.parent.leftNode))
        {
            return true;
        }
        return false;
    }

    public boolean isRightChild() {
        // post: returns true if this is a right child of parent
         if ((this.parent!=null)&&(this== this.parent.rightNode))
        {
            return true;
        }
        return false;
    }

    public boolean isDataNull() {
      //post: returns true if data is null for this node.
      if (this.data==null)
      {
          return true;
      }
      return false;
    }
    
    //getter and setter

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public NodeTree<E> getParent() {
        return parent;
    }

    public void setParent(NodeTree<E> parent) {
        this.parent = parent;
    }

    public NodeTree<E> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(NodeTree<E> leftNode) {
        this.leftNode = leftNode;
    }

    public NodeTree<E> getRightNode() {
        return rightNode;
    }

    public void setRightNode(NodeTree<E> rightNode) {
        this.rightNode = rightNode;
    }
    
    
    
    
}
