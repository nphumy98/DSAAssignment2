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
public abstract class AbstractBinaryTree<E> implements BinaryTreeInterface<E> {
    private NodeTree root;
    @Override
    public boolean isEmpty() //return 0 if tree is empty
    {
        if (this.root==null)
        {
            return true;
        }
        return false;
    }
    //getter and setter
    public NodeTree getRoot() {
        return root;
    }

    public void setRoot(NodeTree root) {
        this.root = root;
    }
    
    
}
