// Name: Om Gole              Date: 3/29/2022
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines) //0 is Left 1 is Right
   {
      TreeNode root = new TreeNode("");
      Stack<String> toAdd = new Stack<>();
      while(huffLines.hasNext())
      {
         String temp = huffLines.nextLine();
         for(int x = temp.length(); x > 0; x--)
            toAdd.push(temp.substring(x-1, x));
      
         TreeNode position = root;
         String Letter = toAdd.pop();
         while(!toAdd.isEmpty())
         {
         
            String next = toAdd.pop();
         
            if(next.equals("0"))
            {
            
               if(position.getLeft() == null)
               {
                  if(toAdd.isEmpty())
                  {
                     position.setLeft(new TreeNode(Letter));
                  }
                  else
                     position.setLeft(new TreeNode(""));    
               }
               position = position.getLeft();
            }
            else if(next.equals("1"))
            {
            
               if(position.getRight() == null)
               {
                  if(toAdd.isEmpty())
                  {
                     position.setRight(new TreeNode(Letter));
                  }
                  else
                     position.setRight(new TreeNode(""));  
               }
               position = position.getRight();
            
            }
         
         }
      }
     return root;
   }
   public static String dehuff(String text, TreeNode root)
   {
   String toReturn = "";
   Stack<String> toParse = new Stack<>();
   for(int x = text.length(); x > 0; x--)
   {
   toParse.push(text.substring(x-1,x));
   }
   TreeNode position = root;
   
   while(!toParse.isEmpty())
    {
     String next = toParse.pop();
     if(position != null)
     {
     /*
     if(position.getLeft() == null && position.getRight() == null)
     {
     toReturn = toReturn + position.getValue();
     position = root;
     }
     */
     if(position.getLeft() != null)
     if(next.equals("0"))
     position = position.getLeft();
     
     if(position.getRight() != null)
     if(next.equals("1"))
     position = position.getRight();
     
     if(position.getLeft() == null && position.getRight() == null)
     {
     toReturn = toReturn + position.getValue();
     position = root;
     }
     

     }
    }
   
   
   return toReturn;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
