// Name: Om Gole
// Date: 11/30/2021

/**
 * Implements the cheat sheet's List interface.  Implements generics.
 * The backing array is an array of (E[]) new Object[10];
 * @override toString()
 */ 
public class TJArrayList<E>
{
   private int size;							//stores the number of objects
   private E[] myArray;
   public TJArrayList()                
   {
      myArray = (E[]) new Object[10]; //default constructor instantiates a raw array with 10 cells
   
      size = 0;
   }
   public int size()
   {
    return size;  
   }
 	/* appends obj to end of list; increases size;
      must be an O(1) operation when size < array.length, 
         and O(n) when it doubles the length of the array.
	  @return true  */
   public boolean add(E obj)
   {
   if(size < myArray.length)
        myArray[size] = obj;
     else{
        E[] newArray = (E[]) new Object[myArray.length*2];
        for(int x = 0; x < myArray.length; x++)
           newArray[x] = myArray[x];
        newArray[size] = obj;
        myArray = newArray;
     }
     size++;
     return true;  
   }
   /* inserts obj at position index.  increments size. 
		*/
   public void add(int index, E obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

     if(size + 1 > myArray.length){
        E[] next = (E[]) new Object[myArray.length*2];
        for(int x = 0; x < myArray.length; x++)
           next[x] = myArray[x];
        myArray = next;
     }


     for(int x = size - 1; x >= index; x--)
        myArray[x+1] = myArray[x];
     size++;
     myArray[index] = obj;
   }

   /* return obj at position index.  
		*/
   public E get(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
     
       return myArray[index];
   }
   /**
    * Replaces obj at position index. 
    * @return the object is being replaced.
    */  
   public E set(int index, E obj) throws IndexOutOfBoundsException  
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
         
         E temp = get(index);
         
     myArray[index] = obj;
     
     return temp;
   }
 /*  removes the node from position index. shifts elements 
     to the left.   Decrements size.
	  @return the object that used to be at position index.
	 */
   public E remove(int index) throws IndexOutOfBoundsException  
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
         
     E temp = myArray[index];
     
     for(int x = index; x < myArray.length-1; x++)
     
        myArray[x] = myArray[x+1];
        
     size--;
     return temp;
   }
	   /*
		   This method compares objects.
         Must use .equals(), not ==
     	*/
   public boolean contains(E obj)
   {
   for(Object o: myArray)
   if(obj.equals(o))
   return true;
   
   return false;
   }
	 /*returns a String of E objects in the array with 
       square brackets and commas.
     	*/
   public String toString()
   {
   String f = "[";

     for(int x=0; x < size-1; x++)
       f = f + "" + myArray[x] + ", ";
     f = f + "" + myArray[size-1] + "]";
     return f;
   }
}