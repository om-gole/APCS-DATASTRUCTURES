//Name: Om Gole
//Date: 2/8/2022

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);
   //public boolean remove(String obj);
   public String min();
   public String max();
   public String toString();
}

/*******************
Represents a binary search tree holding Strings. 
Implements (most of) BSTinterface, above. 
The recursive methods all have a public method calling a private helper method. 
Copy the display() method from TreeLab. 
**********************/
class BST implements BSTinterface
{
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
     return size;
   }
   public TreeNode getRoot()   //for Grade-It
   {
   return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {  
      if(root == null)
     {
     root = new TreeNode(s); 
     size++;
     } 
     else 
      add(root, s);
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {  
     
     if(s.compareTo(t.getValue().toString()) < 1 && t.getLeft() != null)   
     add(t.getLeft(), s);     
     if(s.compareTo(t.getValue().toString()) > 0  && t.getRight() != null)
     add(t.getRight(), s);
     if(s.compareTo(t.getValue().toString()) < 1 && t.getLeft() == null)
     {
     t.setLeft(new TreeNode(s));
     size++;
     }
     if(s.compareTo(t.getValue().toString()) > 0 && t.getRight() == null)
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
   private String display(TreeNode t, int level) //recursive helper method
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
   
   public boolean contains( String obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
     if(t == null)
     return false;
     if(t.getValue().toString().equals(x))
     return true;
     else
     return contains(t.getLeft(), x) || contains(t.getRight(), x);
   }
   
   public String min()
   {
      if(root == null)
    return null;
    else
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
    if(t == null)
    return null;
    while(t.getLeft() != null)
    t = t.getLeft();
    return t.getValue().toString();
   }
   
   public String max()
   {
      if(root == null)
    return null;
    else
      return max(root);
   }
   private String max(TreeNode t)  //recursive helper method
   {
        if(t.getRight() == null)
        return t.getValue().toString();
        else
        return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
     String toReturn = "";
      if(t == null)
      return "";
      toReturn += toString(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += toString(t.getRight());
      return toReturn; 
   }
}
