// Name: Om Gole
// Date: 2/3/2022
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   public static final String operators = "+ - * / % ^ !";
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Codepost
   {
      return root;
   }
    
   public void buildTree(String str)
   {
      TreeNode temp = new TreeNode(null);
     // TreeNode ParentNode = new TreeNode(null);
      //TreeNode CurrNode = new TreeNode(null);
     Stack<TreeNode> stk = new Stack<>();
     String[] split = new String[str.length()];
     split = str.split(" ");
     for(String x : split)
    {
     if(!isOperator(x))
     {
     stk.push(new TreeNode(x));
     }
     else
     {
     
     temp = new TreeNode(x);
     temp.setRight(stk.pop());
     temp.setLeft(stk.pop());
     stk.push(temp);
     }
     
     
    }
     root = stk.pop();

     }
     
     	
  
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
    if(t == null)
    return 0;
    if(!isOperator(t.getValue().toString()))
    return Double.parseDouble(t.getValue().toString());
   else
    return computeTerm(t.getValue().toString(), evaluateNode(t.getLeft()), evaluateNode(t.getRight()));
    
   
   }
   
   private double computeTerm(String s, double a, double b)
   {
     if(s.contains("+"))
     return a + b;
     if(s.contains("-"))
     return a - b;
     if(s.contains("*"))
     return a * b;
     if(s.contains("/"))
     return  a / b;
     if(s.contains("%"))
     return a % b;
     if(s.contains("^"))
     return Math.pow(a, b);
     else
     return a + b;
   }
  
   private boolean isOperator(String s)
   {
    if(operators.contains(s))
    return true;
    else
    return false;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
     String toReturn = "";
      if(t == null)
      return "";
      toReturn += inorderTraverse(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += inorderTraverse(t.getRight());
      return toReturn;  
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode t)
   {
    String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //process root
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
  /* extension */
   // public String inorderTraverseWithParentheses()
   // {
      // return inorderTraverseWithParentheses(root);
   // }
//    
   // private String inorderTraverseWithParentheses(TreeNode t)
   // {
      // return "";
   // }
}