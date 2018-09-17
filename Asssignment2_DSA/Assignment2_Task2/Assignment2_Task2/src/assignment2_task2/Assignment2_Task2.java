/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_task2;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author wrk2544
 */
public class Assignment2_Task2 {

    /**
     * @param args the command line arguments
     */
    public static QueueTerminal<Customer> terminal1= new QueueTerminal<Customer>();
    public static QueueTerminal<Customer> terminal2=new QueueTerminal<Customer>();
    public static QueueTerminal<Customer> terminal3= new QueueTerminal<Customer>();
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner keyboard= new Scanner(System.in);
        Customer customer1[] = new Customer[2];
        
        
        int option;
        do
        {
            printMenu();
            option= keyboard.nextInt();
            if (option==1)
            {
                System.out.println("====================================");
                enqueueSmallestTerminal(terminal1, terminal2, terminal3);
                System.out.println("====================================");
            }
            else if (option==2)
            {
                System.out.println("====================================");
                customerLeft();
                System.out.println("====================================");
            }
            else if (option==3)
            {
                System.out.println("====================================");
                System.out.println("SUMMARY OF 3 TERMINAL:");
                summaryTerminal();
                System.out.println("====================================");
            }
            else if (option==4)
            {
                System.out.println("====================================");
                System.out.println("SUMMARY OF 3 TERMINAL:");
                summaryTerminal();
                System.out.println("Thank for using Restaurant System. GOOD BYE");
                System.out.println("====================================");
            }
        }while (option!=4);
        
//        Customer aCustomer1= new Customer();
//        Customer aCustomer2= new Customer();
//        Customer aCustomer3= new Customer();
//        terminal1.enqueue(aCustomer1);
//        terminal1.enqueue(aCustomer2);
//        terminal1.enqueue(aCustomer3);
//        System.out.println(terminal1.sizeQueue);
//        System.out.println(terminal1.peek());
//        terminal1.printQueue(1);
//        terminal1.dequeue();
//        System.out.println(terminal1.sizeQueue);
//        terminal1.printQueue(1);
//        System.out.println(terminal1.peek());
//        System.out.println(terminal1.sizeQueue);
    }
    
    public static void printMenu()
    {
        System.out.println("Welcome to Restaurant System, There are 3 Terminals");
        System.out.println("1. New customer will enter a terminal");
        System.out.println("2. A customer leave a terminal");
        System.out.println("3. Summary all 3 terminal");
        System.out.println("4. Quit the system");
    }
        
    public static void customerLeft()
    {
        if ((terminal1.sizeQueue==0)&&(terminal2.sizeQueue==0)&&(terminal3.sizeQueue==0)) // if all three terminal empty then return
        {
            System.out.println("There are no customers at all 3 terminals.");
            return;
        }
        Random rd= new Random();
        int terminalID=0;
        boolean check=false;
        while (check==false)
        {
            terminalID= rd.nextInt(3)+1;
            if ((terminalID==1)&&(terminal1.sizeQueue>0))
            {
                Customer aCustomer=terminal1.dequeue();
                System.out.println(aCustomer+ " has left Terminal 1 at " + new Date());
                check=true;
            }
            else if ((terminalID==2)&&(terminal2.sizeQueue>0))
            {
                Customer aCustomer= terminal2.dequeue();
                System.out.println(aCustomer+ " has left Terminal 2 at "+ new Date());
                check=true;
            }
            else if ((terminalID==3)&&(terminal3.sizeQueue>0))
            {
                Customer aCustomer=terminal3.dequeue();
                System.out.println(aCustomer+ " has left Terminal 3 at "+ new Date());
                check=true;
            }
        }
        
    }
    
    public static QueueTerminal<Customer> enqueueSmallestTerminal( QueueTerminal<Customer> terminal1,  QueueTerminal<Customer> terminal2,  QueueTerminal<Customer> terminal3)//this method return terminal ID of the terminal has least customer queue
    {
        Customer aCustomer= new Customer();
        if ((terminal2.sizeQueue<=terminal1.sizeQueue)&&(terminal2.sizeQueue<=terminal3.sizeQueue))
        {
            terminal2.enqueue(aCustomer);
            System.out.println(aCustomer+ " has enter Terminal 2");
            return terminal2;  // if terminal 2 is smallest
        }
        else if ((terminal3.sizeQueue<=terminal1.sizeQueue)&&(terminal3.sizeQueue<=terminal2.sizeQueue))
        {
            terminal3.enqueue(aCustomer);
            System.out.println(aCustomer+ " has enter Terminal 3");
            return terminal3; // if terminal 3 is smallest
        }
        else
        {
            terminal1.enqueue(aCustomer);
            System.out.println(aCustomer+ " has enter Terminal 1");
        }
        return terminal1;
    }
    
    public static void summaryTerminal()
    {
        terminal1.printQueue(1);
        terminal2.printQueue(2);
        terminal3.printQueue(3);
    }
}
