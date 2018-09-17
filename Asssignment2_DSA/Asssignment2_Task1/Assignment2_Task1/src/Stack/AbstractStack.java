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
public abstract class AbstractStack<E> implements StackInterface<E> {
    public int sizeStack;// size of the stack
    @Override
    public boolean isEmpty()
    {
        if (sizeStack>0)
        {
            return false;
        }
        return true;
    }
    
    @Override
    public int size()
    {
        return sizeStack;
    }
}
