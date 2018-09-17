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
public abstract class QueueAbstractClass<E> implements QueueInterface<E> {
    int sizeQueue;
    
    @Override
    public boolean isEmpty()
    {
        if (sizeQueue==0)
        {
            return true;
        }
        return false;
    }
    
    public int size()
    {
        return sizeQueue;
    }
    
}
