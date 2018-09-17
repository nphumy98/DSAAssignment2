/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_task1;

import BinaryTree.BinaryTree;
import BinaryTree.NodeTree;
import Stack.StackTask1;
import java.util.Scanner;

/**
 *
 * @author wrk2544
 */
public class Assignment2_Task1 {

    /**
     * @param args the command line arguments
     */
    private static String originalString="";
    private static String inorderExpression="";
    public static Scanner keyboard= new Scanner(System.in);
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Please enter your expression string:");
        originalString= keyboard.nextLine();
        inorderExpression=originalString;
        boolean validExpression=validateExpression();
        if (validExpression==true)
        {
            System.out.println("This is valid expression");
            
            //build a tree
            BinaryTree aTree= new BinaryTree(inorderExpression);
            aTree.buildTree();// build a Tree with that inOrderExpression
            System.out.println("The tree has been built");
            System.out.println("In-order traverse: ");
            aTree.inOrderTraverse(aTree.getRoot());
            System.out.println("");
            System.out.println("Post-order traverse: ");
            aTree.postOrderTraverse(aTree.getRoot());
            System.out.println("");
            double result=aTree.evaluateExpression();
            if (aTree.getPostfixExpressionWithValue().equals("")==false) // if no divide by zero detected
            {
                System.out.println("The result: "+result);  
            }
        }
    }
    
    //this method trim the 
    private static void trimInorderExpression()
    {
        Assignment2_Task1.inorderExpression= inorderExpression.trim();
        String trimString ="";
        for(int i=0;i<Assignment2_Task1.inorderExpression.length();i++)
        {
            if (inorderExpression.charAt(i)!=' ')
            {
                trimString+= Character.toString(inorderExpression.charAt(i));
            }
        }
        Assignment2_Task1.inorderExpression=trimString;
    }
    
    //validate the Expression
    private static boolean validateExpression()
    {
        //trim all the spaces of inorderExpression
        trimInorderExpression();
        //check character
        boolean character=checkCharacter();
        if (character==true)
        {
            //check parenthesis
            boolean parenthesis=checkParenthesis();
            if (parenthesis==true)
            {
                //check if 2 operands or 2 operators together
                boolean OperandAndOperator= checkOperandAndOperator();
                if (OperandAndOperator==true)
                {
                    return true;
                }
                else
                {
                    System.out.println("Invalid operand and operator!!!");
                    return false;
                }
            }
            else
            {
                System.out.println("Invalid parenthesis!!!");
                return false;
            }
        }
        else
        {
            System.out.println("Invalid character!!!");
            return false;
        }
        
    }
    
    private static boolean checkCharacter()
    {
        boolean check=true;
        if (inorderExpression.length()>0) //if the inorderExpression length>0
        {
            for(int i=0;i<inorderExpression.length();i++)
            {
                int characterType= BinaryTree.checkCharacter(inorderExpression.charAt(i));
                if (characterType==-1) // if character not in character allowed
                {
                    return false;
                }
            }
        }
        else // if the inorderExpression has nothing
        {
            check=false;
        }
        return check;
    }
    
    private static boolean checkParenthesis()
    {
        boolean check=false;
        StackTask1 parenthesisStack= new StackTask1();
        if (inorderExpression.length()>0) //if the inorderExpression length>0
        {
            for(int i=0;i<inorderExpression.length();i++)
            {
                if (BinaryTree.checkCharacter(inorderExpression.charAt(i))==3) // if the character is (
                {
                    if (i==inorderExpression.length()-1) // if ( at the end
                    {
                        System.out.println("Symbol ( at the end . Invalid Expression");
                        return false; //return false
                    }
                    
                    if (i>0)
                    {
                        //if the previous character is  an operator or (. and the after character is  an operand or ( then it is ok
                        if (((BinaryTree.checkCharacter(inorderExpression.charAt(i-1))==2)||(BinaryTree.checkCharacter(inorderExpression.charAt(i-1))==3))
                            &&((BinaryTree.checkCharacter(inorderExpression.charAt(i+1))==0)||(BinaryTree.checkCharacter(inorderExpression.charAt(i+1))==1)||(BinaryTree.checkCharacter(inorderExpression.charAt(i+1))==3)))
                        {}
                        else // if not then expression invalid
                        {
                            System.out.println("Invalid character before and after ( parethesis. Invalid Expression");
                            return false;
                        }
                    }
                    parenthesisStack.push(inorderExpression.charAt(i));
                }
                else if (BinaryTree.checkCharacter(inorderExpression.charAt(i))==4) // if the character is ) 
                {
                    if (parenthesisStack.isEmpty()==true) // if stack is empty then return false. expression invalid
                    {
                        System.out.println("Parenthesis () error. Invalid Expression");
                        return false;
                    }
                    
                    if (i<inorderExpression.length()-1) //if i not the last character
                    {
                        //if the previous character is  an operand or ). and the after character is  an operator or ) then it is ok
                        if (((BinaryTree.checkCharacter(inorderExpression.charAt(i+1))==2)||(BinaryTree.checkCharacter(inorderExpression.charAt(i+1))==4))
                            &&((BinaryTree.checkCharacter(inorderExpression.charAt(i-1))==0)||(BinaryTree.checkCharacter(inorderExpression.charAt(i-1))==1)||(BinaryTree.checkCharacter(inorderExpression.charAt(i-1))==4)))
                        {}
                        else // if not then expression invalid
                        {
                            System.out.println("Invalid character before and after ) parethesis. Invalid Expression");
                            return false;
                        }
                    }
                    parenthesisStack.pop(); //pop ( from stack
                       
                }
            }
            if (parenthesisStack.isEmpty()==true) //if stack is empty mean number of ( equal number of )
            {
                check=true;
            }
            else
            {
                System.out.println("Parenthesis () error. Invalid Expression");
            }
        }
        return check;
    }
    
    //check operand
    private static boolean checkOperandAndOperator()
    {
        boolean check=false;
        String removeParenthesis="";
        removeParenthesis=removeParenthesis();
        if (removeParenthesis.length()>=3) //valid it need to have at least 3 elememt.
        {
            for(int i=0;i<removeParenthesis.length();i++)
            {
                int currentcheckCharacter= BinaryTree.checkCharacter(removeParenthesis.charAt(i));
                int previousCheckCharacter=-1;
                int nextCheckCharacter=-1;
                //if beginning and at the end not an operand
                if (((i==0)||(i==removeParenthesis.length()-1))&&((currentcheckCharacter!=1)&&(currentcheckCharacter!=0)))
                {
                    System.out.println("Beginning or the end is not operand- Invalid Expression");
                    return false;
                }
                if (i>0)
                {
                    previousCheckCharacter=BinaryTree.checkCharacter(removeParenthesis.charAt(i-1));
                    //check divide by 0
                    if((removeParenthesis.charAt(i)=='0')&&(removeParenthesis.charAt(i-1)=='/'))
                    {
                        System.out.println("Divide by 0- Invalid Expression");
                        return false;
                    }
                    
                    //if 2 operand next to each other
                    if (((previousCheckCharacter==0)||(previousCheckCharacter==1))&&((currentcheckCharacter==0)||(currentcheckCharacter==1)))
                    {
                        System.out.println("2 operands next to each other without operator- Invalid Expression");
                        return false;
                    }
                    else if ((previousCheckCharacter==2)&&(currentcheckCharacter==2)) // if 2 operator next to each other
                    {
                        System.out.println("2 operators next to each other without operand- Invalid Expression");
                        return false;
                    }
                }
            }
            //if pass all the test
            check=true;
        }
        return check;
    }
    
    //remove the parenthesis from string
    public static String removeParenthesis()
    {
        String returnString="";
        if (inorderExpression.length()>0)
        {
            for(int i=0;i<inorderExpression.length();i++)
            {
                if ((BinaryTree.checkCharacter(inorderExpression.charAt(i))!=3)&&(BinaryTree.checkCharacter(inorderExpression.charAt(i))!=4)&&(BinaryTree.checkCharacter(inorderExpression.charAt(i))!=-1))
                {
                    returnString+=Character.toString(inorderExpression.charAt(i));
                }
            }
        }
        return returnString;
    }
}
