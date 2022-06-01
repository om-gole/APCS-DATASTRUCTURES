// name:   Om Gole     date: 3/31/2022 in turkish airlines lounge
import java.util.*;
import java.io.*;
public class Huffman
{
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   public static void huffmanize(String message, String middlePart) throws IOException
   {
   
        //Make a frequency table of the chs
         Map<String, Integer>map = new HashMap<String, Integer>();
         for(int x = 0; x < message.length(); x++) {
            String ch = message.substring(x, x+1);
            if(map.containsKey(ch))
              map.put(ch,map.get(ch)+1);
            else
              map.put(ch, 1);
         }
      	//Put each ch-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).  
         PriorityQueue<HuffmanTreeNode> q = new PriorityQueue<>();
         for(String k :map.keySet()) {
            q.add(new HuffmanTreeNode(k,map.get(k)));
         }
      	//Use the priority queue of nodes to build the Huffman tree
         while(q.size() > 1) {
            HuffmanTreeNode child1 = q.remove();
            HuffmanTreeNode child2 = q.remove();
            HuffmanTreeNode parent = new HuffmanTreeNode("*", child1.getFreq() + child2.getFreq());
            parent.setLeft(child1);
            parent.setRight(child2);
            q.add(parent);
         }
         HuffmanTreeNode root = q.remove();
      	//Process the string ch-by-ch and search the tree for the 
   		//       ch. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
         Map<String, String> schemes = new HashMap<String, String>();
         
         File messageFile = new File("message."+middlePart+".txt");
         messageFile.createNewFile();
         File schemeFile = new File("scheme."+middlePart+".txt");
         schemeFile.createNewFile();
         PrintStream mesSc = new PrintStream(messageFile);
         PrintStream schSc = new PrintStream(schemeFile);
         
         String messagePrint = "";
         String schemePrint = "";
         
         for(int x = 0; x < message.length(); x++) {
            String ch = message.substring(x, x+1);
            String scheme = scheme(ch, "", root);
            messagePrint += scheme;
            if(!schemePrint.contains(ch))
               schemePrint += (ch + scheme + "\n");
         }
         mesSc.println(messagePrint);
         schSc.println(schemePrint);
         //System.out.println the binary message 
      	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
         //System.out.println the scheme from the tree--needs a recursive helper method
      	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
         
            
   }
   
   private static String scheme(String ch, String pastScheme, HuffmanTreeNode t) {
      if(t == null)
         return "";
      if(t.getValue().equals(ch))
         return pastScheme;
      return scheme(ch, pastScheme+"0", t.getLeft()) + scheme(ch, pastScheme + "1", t.getRight());
   } 
         
         
            
  
   /*
         //Make a frequency table of the characters
      	//Put each character-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).     
      	//Use the priority queue of nodes to build the Huffman tree
      	//Process the string character-by-character and search the tree for the 
   		//       character. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
         //System.out.println the binary message 
      	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
         //System.out.println the scheme from the tree--needs a recursive helper method
      	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
        
      Map<Character, Integer> map = new HashMap<Character, Integer>();
   
      for(int x=0; x<message.length(); x++){
         if(map.containsKey(message.charAt(x)))
            map.put(message.charAt(x), map.get(message.charAt(x))+1);
         else
            map.put(message.charAt(x), 1);
      }
   
      PriorityQueue<HuffmanTreeNode> q = new PriorityQueue<HuffmanTreeNode>();
   
      for(char x: map.keySet())
         q.add(new HuffmanTreeNode(x, map.get(x)));
        
      while(q.size() != 1){
         HuffmanTreeNode child1 = q.remove();
         HuffmanTreeNode child2 = q.remove();
         HuffmanTreeNode p = new HuffmanTreeNode('*', child1.getFreq()+child2.getFreq());
         p.setLeft(child1);
         p.setRight(child2);
         q.add(p);
      }
      HuffmanTreeNode root = q.remove();
   
      Map<Character, String> code = new HashMap<Character, String>();
   
      for(int x=0; x<message.length(); x++){
         code.put(message.charAt(x), scheme(root, ""+message.charAt(x), map));
      }
   
      PrintStream pmes = new PrintStream("message."+ middlePart + ".txt");
      PrintStream psch = new PrintStream("scheme."+ middlePart + ".txt");
      
      for(int x=0; x<message.length(); x++){
         pmes.print(code.get(message.charAt(x)));    
      }
      for(char x: map.keySet())
         psch.println(x+": "+code.get(x));
 }
   private static String scheme(HuffmanTreeNode t, String str, Map map)
  {
     if(t == null)
     return "";
      char temp = str.charAt(0);
      int tocompare = t.getValue().toString().compareTo(str);
     if(tocompare == 0)
     return "";
     else{
     int divfreq = t.getFreq()/2;
     int freq = Integer.parseInt(""+map.get(temp));
        if(freq == divfreq){
           if(tocompare>0)
           return "0"+scheme(t.getLeft(), str, map);
           else
           return "1"+scheme(t.getRight(), str, map);
        }
        else if(freq < divfreq)
           return "0"+scheme(t.getLeft(), str, map);
        else
           return "1"+scheme(t.getRight(), str, map);
     }
  }
*/


}
	/*
	  * This tree node stores two values.  Look at TreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private Object value;
   private int lowfreq;
   private HuffmanTreeNode left, right;
   
   public HuffmanTreeNode(Object val, int integer)
   {
      value = val;
      lowfreq = integer;
      left = null;
      right = null;
    
   }
   
   public HuffmanTreeNode(Object val, int integer, HuffmanTreeNode lef, HuffmanTreeNode rig)
   {
      value = val;
      lowfreq = integer;
      left = lef;
      right = rig;
   }
   
   public Object getValue()
   {
      return value;
   }
   public HuffmanTreeNode getLeft()
   {
      return left;
   }
   public HuffmanTreeNode getRight()
   {
      return right;
   }
   public int getFreq()
   {
      return lowfreq;
   }
   public void setLeft(HuffmanTreeNode t)
   {
      left = t;
   }
   public void setRight(HuffmanTreeNode t)
   {
      right = t;
   }
   public int compareTo(HuffmanTreeNode t)
   {
      if(lowfreq < t.getFreq())
      {
         return -1;
      }
      else if(lowfreq > t.getFreq())
      {
         return 1;
      }
      return 0;
   }
}
