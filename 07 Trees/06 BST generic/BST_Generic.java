// Name: Om Gole
// Date: 2/22/2022
import java.util.*;

interface BSTinterface<E>
{
   public int size();
   public boolean contains(E element);
   public E add(E element);
   //public E addBalanced(E element);
   public E remove(E element);
   public E min();
   public E max();
   public String display();
   public String toString();
   public List<E> toList();  //returns an in-order list of E
}

/*******************
  Copy your BST code.  Implement generics.
**********************/
public class BST_Generic<E extends Comparable<E>> implements BSTinterface<E>
{
 private TreeNode<E> root;
   private int size;
  private ArrayList<E> list=new ArrayList<E>();
   public BST_Generic()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
     return size;
   }
   public TreeNode<E> getRoot()   //for Grade-It
   {
   return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public E add(E s) 
   {  
      if(root == null)
     {
     root = new TreeNode(s); 
     size++;
     } 
     else 
      add(root, s);
      
      return null;
   }
   private TreeNode<E> add(TreeNode<E> t, E s) //recursive helper method
   {  
     
     if(s.compareTo(t.getValue()) < 1 && t.getLeft() != null)   
     add(t.getLeft(), s);     
     if(s.compareTo(t.getValue()) > 0  && t.getRight() != null)
     add(t.getRight(), s);
     if(s.compareTo(t.getValue()) < 1 && t.getLeft() == null)
     {
     t.setLeft(new TreeNode(s));
     size++;
     }
     if(s.compareTo(t.getValue()) > 0 && t.getRight() == null)
     {
     t.setRight(new TreeNode(s));
     size++;
     }
     return t;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode<E> t, int level) //recursive helper method
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
   
   public boolean contains( E obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode<E> t, E x) //recursive helper method
   {
     if(t == null)
     return false;
     if(t.getValue().equals(x))
     return true;
     else
     return contains(t.getLeft(), x) || contains(t.getRight(), x);
   }
   
   public E min()
   {
      if(root == null)
    return null;
    else
      return min(root);
   }
   private E min(TreeNode<E> t)  //use iteration
   {
    if(t == null)
    return null;
    while(t.getLeft() != null)
    t = t.getLeft();
    return t.getValue();
   }
   
   public E max()
   {
      if(root == null)
    return null;
    else
      return max(root);
   }
   private E max(TreeNode<E> t)  //recursive helper method
   {
        if(t.getRight() == null)
        return t.getValue();
        else
        return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode<E> t)  //an in-order traversal.  Use recursion.
   {
     String toReturn = "";
      if(t == null)
      return "";
      toReturn += toString(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += toString(t.getRight());
      return toReturn; 
   }
   
   public E remove(E target)
   {  
      root = remove(root, target);
      size--;
      return target;
   }
   private TreeNode<E> remove(TreeNode<E> current, E target)
   {  
      if(current == null)
      return root;
      if(target.compareTo(current.getValue()) < 0)
      current.setLeft(remove(current.getLeft(), target));
      else if(target.compareTo(current.getValue()) > 0)
      current.setRight(remove(current.getRight(), target));
      else{
      if(current.getLeft() == null)
      return current.getRight();
      else if(current.getRight() == null)
      return current.getLeft();
      
      current.setValue(minNode(current.getRight()));
      
      current.setRight(remove(current.getRight(), current.getValue()));
      
      }
      return current;

   }
   E minNode (TreeNode<E> root)
   {
    E minObj = root.getValue();
    while(root.getLeft() != null)
    {
    minObj = root.getLeft().getValue();
    root = root.getLeft();
    }
    return minObj;
    
   }
   public ArrayList<E> toList()
   {
    toList(root);
    
    return list;
   }
   private TreeNode<E> toList(TreeNode<E> t)  //an in-order traversal.  Use recursion.
   {
      if(t == null)
      return null;
      toList(t.getLeft());
      list.add(t.getValue());
      toList(t.getRight());
      return null; 
   }
}

/*******************
  Copy your TreeNode code.  Implement generics.
**********************/
class TreeNode<E>
{
 private E value; 
      private TreeNode left, right;
   
       public TreeNode(E initValue)
      { 
         value = initValue; 
         left = null; 
         right = null; 
      }
   
       public TreeNode(E initValue, TreeNode initLeft, TreeNode initRight)
      { 
         value = initValue; 
         left = initLeft; 
         right = initRight; 
      }
   
       public E getValue()
      { 
         return value; 
      }
   
       public TreeNode<E> getLeft() 
      { 
         return left; 
      }
   
       public TreeNode<E> getRight() 
      { 
         return right; 
      }
   
       public void setValue(E theNewValue) 
      { 
         value = theNewValue; 
      }
   
       public void setLeft(TreeNode<E> theNewLeft) 
      { 
         left = theNewLeft;
      }
   
       public void setRight(TreeNode<E> theNewRight)
      { 
         right = theNewRight;
      }

}