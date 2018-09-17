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
public interface BinaryTreeInterface<E> {

    public boolean isEmpty(); //return 0 if tree is empty
    public void preOrderTraverse(NodeTree root);
    public void inOrderTraverse(NodeTree root);
    public void postOrderTraverse(NodeTree root);
}
