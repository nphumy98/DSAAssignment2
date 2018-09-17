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
public interface QueueInterface<E> {
    public void enqueue(E value);
    public E dequeue ();
    public E peek();
    public boolean isEmpty();
    public int size();
    public void printQueue(int queueID);
}
