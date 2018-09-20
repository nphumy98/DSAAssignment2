/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;

import Stack.StackTask1;
import assignment2_task1.Assignment2_Task1;
import com.sun.tools.javac.util.Assert;

/**
 *
 * @author wrk2544
 */
public class BinaryTree<E> extends AbstractBinaryTree<E> implements BinaryTreeInterface<E>{
    
    private String infixExpression=""; //present infixExpression
    private String postfixExpressionWithValue=""; //infixExpression with value of variable
    private String traversePostOrderExpression="";//this string is supposed to be equal with postfixExpressionwithValue
    //constructor
    public BinaryTree(String infixExpression)
    {
        Assert.checkNonNull(infixExpression); //check the expression not null before initialise tree
        Assert.check(infixExpression.length()>0);// check the expression lenbth>0 before intialise tree
        this.setInfixExpression(infixExpression);
        this.setRoot(null);
    }
    
    public BinaryTree(NodeTree root)
    {
        Assert.checkNonNull(root); //check root not null before intialise tree
        this.setRoot(root);
    }

    @Override
    public void preOrderTraverse(NodeTree root) {
        if (root==null)
        {
            return;
        }
        System.out.print(root.getData()+" ");
        preOrderTraverse(root.getLeftNode());
        preOrderTraverse(root.getRightNode());
    }

    @Override
    public void inOrderTraverse(NodeTree root) {
        if (root==null)
        {
            return;
        }
        inOrderTraverse(root.getLeftNode());
        System.out.print(root.getData()+" ");
        inOrderTraverse(root.getRightNode());
    }
    
    @Override
    public void postOrderTraverse(NodeTree root) {
        if (root==null)
        {
            return;
        }
        postOrderTraverse(root.getLeftNode());
        postOrderTraverse(root.getRightNode());
        System.out.print(root.getData()+" ");
    }

    @Override
    public String postOrderTraverseString(NodeTree root) {
        if (root==null)
        {
            return "";
        }
        else
        {
            postOrderTraverseString(root.getLeftNode());
            postOrderTraverseString(root.getRightNode());
            postfixExpressionWithValue+=root.getData();
            return postfixExpressionWithValue;
        }
        
    }

    //this method convert infix Expression to postfix Expression
    public static String infixToPostFix(String infixExpression)
    {
        String postfixExpression="";
        //scan
        StackTask1<String> operatorStack= new StackTask1<String>();
        char[] charArray = infixExpression.toCharArray();
        //scan all infix token
        for(int i=0;i<charArray.length;i++)
        {
            int check=checkCharacter(charArray[i]);
            if ((check==1)||(check==0)) // if it is operand
            {
                 postfixExpression=postfixExpression+Character.toString(charArray[i]);
            }
            else if (check==2) //if it is operator
            {
                if (operatorStack.isEmpty()==true)
                {
                    operatorStack.push(Character.toString(charArray[i]));
                }
                else if (operatorStack.peek().equals("(")==false)
                {
                    while ((operatorStack.isEmpty()==false)&&(operatorStack.peek().equals("(")==false)&&(rankPrecendence(Character.toString(charArray[i]))<=rankPrecendence(operatorStack.peek())))
                    {
                        postfixExpression=postfixExpression+operatorStack.pop();
                    }
                    operatorStack.push(Character.toString(charArray[i]));
                }
                else
                {
                    operatorStack.push(Character.toString(charArray[i]));
                }
            }
            else if (check==3) // if it is (
            {      
               operatorStack.push(Character.toString(charArray[i]));
            }
            else if (check==4) // if it is )
            {
                while(operatorStack.peek().equals("(")==false)
                {
                    postfixExpression=postfixExpression+operatorStack.pop();
                }
                operatorStack.pop(); //pop '('
            }      
        }
       // pop the rest from operatorStack
       while (operatorStack.isEmpty()==false)
       {
           postfixExpression=postfixExpression+operatorStack.pop();
       }
        return postfixExpression;
    }
    
    //this method check character from expression string and identify  character
    public static int checkCharacter(char aCharacter)
    {
        //if character is operator
        if ((aCharacter=='+')||(aCharacter=='-')||(aCharacter=='*')||(aCharacter=='/'))
        {
            return 2;
        }
        else if (aCharacter=='(') // if it is open parathesis
        {
            return 3;
        }
        else if (aCharacter==')') // if it is close parathesis
        {
            return 4;
        }
        else if (((aCharacter>='a')&&(aCharacter<='z'))||((aCharacter>='A')&&(aCharacter<='Z'))) //if it is operands variable
        {
            return 1;
        }
        else if ((aCharacter>='0')&&(aCharacter<='9')) //if it is number
        {
            return 0;
        }
        //else it is operand
        return -1;
    }
    
    // this method rank precendece of operator character
    public static int rankPrecendence(String operator)
    {
        //if operator is + or -
        if ((operator.equals("+"))||(operator.equals("-")))
        {
            return 1;
        }
        //if operator is * or -
        else if ((operator.equals("*"))||(operator.equals("/")))
        {
            return 2;
        }
        return 3;
    }
    
    //this method build a tree from infixExpression
    public BinaryTree buildTree()
    {
        //convert to postfix
        String postfixExpression= infixToPostFix(this.infixExpression);
        System.out.println(postfixExpression);
        char[] charArray = postfixExpression.toCharArray();
        //make a Stack
        StackTask1<NodeTree> nodeStack= new StackTask1<NodeTree>();
        // scan
        for(int i=0;i<charArray.length;i++)
        {
            int check=checkCharacter(charArray[i]); //check character 
            if ((check==1)||(check==0))// if it is operand
            {
                NodeTree newNode= new NodeTree(Character.toString(charArray[i]));
                nodeStack.push(newNode);
            }
            else //if it is operator
            {
                //pop 2 nodetree from stack to make new tree
                NodeTree rightNode= nodeStack.pop();
                NodeTree leftNode= nodeStack.pop();
                //make operator a temp root
                NodeTree tempRoot= new NodeTree(Character.toString(charArray[i]),null,leftNode,rightNode);
                nodeStack.push(tempRoot);
            }
        }
        this.setRoot(nodeStack.pop());// the last node in stack is the tree 
        return this;
    }
    
    //this method evaluate the expression from infixExpression.
    public double evaluateExpression()
    {
        takeVariable(); // read and return value for variable in string
        double result=0;
        int checkCharacter=0;
        char[] charArray = traversePostOrderExpression.toCharArray();
        StackTask1<Double> aStack= new StackTask1<Double>();
        //scan the postfix
        for(int i=0;i<charArray.length;i++)
        {
            checkCharacter=checkCharacter(charArray[i]); //check the character
            if (checkCharacter==0) // if it is operand
            {
                aStack.push(Double.parseDouble(Character.toString(charArray[i])));             
            }
            else //if it is operator
            {
                //pop 2 elements from stack then calculate the total
                Double total=new Double(0);
                Double operand1= aStack.pop();
                Double operand2= aStack.pop();
                if (charArray[i]=='+')
                {
                    total= operand1+operand2;
                }
                else if (charArray[i]=='-')
                {
                    total= operand2-operand1;
                }
                else if (charArray[i]=='*')
                {
                    total= operand1*operand2;
                }
                else if (charArray[i]=='/')
                {
                    if (operand1!=0)
                    {
                        total= operand2/operand1;
                    }
                    else // if divide by zero detected
                    {
                        System.out.println("Divide by zero detected while evaluating the expression! Your expression input is not good");
                        this.traversePostOrderExpression="";
                        return 0;
                    }
                    
                }
                aStack.push(total); //push that total into stack
            }
        }
        result=aStack.pop();// the last element is the final result
        return result;
    }
    
    public String takeVariable()
    {
        this.postfixExpressionWithValue="";
        String postfixExpression= postOrderTraverseString(this.getRoot()); //post order traverse to get the postfix
        this.traversePostOrderExpression="";
        for(int i=0;i<postfixExpression.length();i++)
        {
            if (checkCharacter(postfixExpression.charAt(i))==1) // if that character is variable
            {
                System.out.println("Variable ["+postfixExpression.charAt(i)+"] detected. Please give the value from 0...9");
                int intVariableValue= checkInput();
                String variableValue=Integer.toString(intVariableValue);
                traversePostOrderExpression+=variableValue;
            }
            else
            {
                traversePostOrderExpression+=Character.toString(postfixExpression.charAt(i)); //add character to postfix
            }
        }
        return this.traversePostOrderExpression;
    }
    
    //this method make sure the input of user is always from 0...9 for variable
    private static int checkInput () //should we try catch?
    {
        //initilise the variables
        int maxOption=9;
        boolean isValidSelection = true;
        String stringToCheck = "";
        int returnvalue = -1 ;

        do
       {
           do
           {
               //read in the selection
               stringToCheck = Assignment2_Task1.keyboard.nextLine();
               stringToCheck=stringToCheck.trim();//trim the String
               if (stringToCheck.length()==1)
               {
                   char charNumber= stringToCheck.charAt(0);
                   if (charNumber < '0' || charNumber > '9')
                   {
                       System.out.println("Wrong input, please enter a single digit number from 0...9");
                       isValidSelection=false;
                       break;
                   }
                   else
                   {
                       isValidSelection=true;
                   }

               }
               else
               {
                   stringToCheck="-1";
                   System.out.println("Wrong input, please enter a single digit number from 0...9");
                   isValidSelection=false;
                   break;
               }
           } while (!isValidSelection);

               try
               {
                   //handel input that is not letters or is a number that is to long
                   returnvalue= Integer.valueOf(stringToCheck);
               }
               catch (NumberFormatException e)
               {
                   returnvalue = -1;
               }         
       } while(returnvalue < 0 || returnvalue > maxOption);
        return returnvalue;
    }
    
    //getter and setter
    public String getInfixExpression() {
        return infixExpression;
    }

    public void setInfixExpression(String infixExpression) {
        this.infixExpression = infixExpression;
    }

    public String getPostfixExpressionWithValue() {
        return postfixExpressionWithValue;
    }

    public void setPostfixExpressionWithValue(String postfixExpressionWithValue) {
        this.postfixExpressionWithValue = postfixExpressionWithValue;
    }

    public String getTraversePostOrderExpression() {
        return traversePostOrderExpression;
    }

    public void setTraversePostOrderExpression(String traversePostOrderExpression) {
        this.traversePostOrderExpression = traversePostOrderExpression;
    }
}
