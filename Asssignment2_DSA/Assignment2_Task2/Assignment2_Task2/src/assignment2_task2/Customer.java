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
public class Customer {
    private static int customerNumber=1;
    private int customerID;
    
    public Customer()
    {
        this(customerNumber);
        customerNumber++;
    }
    
    public Customer(int customerID)
    {
        this.customerID=customerID;
    }
    
    //toString
    @Override    
    public String toString() {
        return "Customer{" + "customerID=" + customerID + '}';
    }

    //getter and setter
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    
}
