// Name:Om Gole
// Date:11/18/2021

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size = 0;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   //no constructor needed
   
   /* two accessor methods  */
   public int size()
   {
      return size;
   }
   public DLNode getHead()
   {
      return head;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index (the list is zero-indexed).  
      increments size. 
      no need for a special case when size == 0.
	   */
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode toAdd = new DLNode(obj, null, null);
      DLNode current = head;
      for(int x = 0; x < index; x++)
      {
      current = current.getNext();
      } 
      toAdd.setNext(current.getNext());
      current.getNext().setPrev(toAdd);
      current.setNext(toAdd);
      toAdd.setPrev(current);
      size++;
                    
   }
   
    /* return obj at position index (zero-indexed). 
    */
   public Object get(int index) throws IndexOutOfBoundsException
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode pointer = head;
      pointer = pointer.getNext();
      for(int x = 0; x < index; x++)
      {
         pointer = pointer.getNext();
      }
      return pointer.getValue();
   }
   
   /* replaces obj at position index (zero-indexed). 
        returns the obj that was replaced.
        */
   public Object set(int index, Object obj) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode pointer = head;
      pointer = pointer.getNext();
      for(int x = 0; x < index; x++)
      {
         pointer = pointer.getNext();
      }
      Object setter = pointer.getValue();
      pointer.setValue(obj);
      return setter;
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object in the node that was removed. 
        */
   public Object remove(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode pointer = head;
      while(index >= 0)
      {
         pointer = pointer.getNext();
         index--;
      }
      
      Object removed = pointer.getValue();
      pointer.getPrev().setNext(pointer.getNext()); // changes pointer of previous to the node after obj
      pointer.getNext().setPrev(pointer.getPrev()); //changes previous pointer of the node after obj to the node 
      size--;
      return removed;
   }
   
  	/* inserts obj to front of list, increases size.
	    */ 
   public void addFirst(Object obj)
   {
      DLNode temp = new DLNode(obj, head, head.getNext());
      head.getNext().setPrev(temp);
      head.setNext(temp);
      size++;
   }
   
   /* appends obj to end of list, increases size.
       */
   public void addLast(Object obj)
   {
      DLNode last = new DLNode(obj, head.getPrev(), head);
      head.setPrev(last);
      head.getPrev().getPrev().setNext(last);
      
      
      size++;
   }
   
   /* returns the first element in this list  
      */
   public Object getFirst()
   {
      return head.getNext().getValue();
   }
   
   /* returns the last element in this list  
     */
   public Object getLast()
   {
      return head.getPrev().getValue();
   }
   
   /* returns and removes the first element in this list, or
      returns null if the list is empty  
      */
   public Object removeFirst()
   {
      Object temp = head.getNext().getValue();
      remove(0);
      return temp;
    
   }
   
   /* returns and removes the last element in this list, or
      returns null if the list is empty  
      */
   public Object removeLast()
   {
      Object temp = head.getPrev().getValue();
      remove(size - 1);
      return temp;
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
      DLNode pointer = head.getNext();
      String Final = "";
      Final = Final + "[";
      for(int x = 0; x < size; x++)
      {
      
      Final = Final + pointer.getValue();
      pointer = pointer.getNext();
      if(x != size - 1)
      Final = Final + ", ";
      }
      Final = Final + "]";
      return Final;
   }
}