 //Name:Om Gole   
 //Date:3/24/2022
 
import java.util.*;

/* implement the API for java.util.PriorityQueue
 *     a min-heap of objects in an ArrayList<E> in a resource class
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public ArrayList<E> getHeap()   //for Codepost
   {
      return myHeap;
   }
   
   public int lastIndex()
   {
      return myHeap.size() - 1;
   }
   
   public boolean isEmpty()
   {
      if(myHeap.size() < 2)
      return true;
      else
      return false;
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      heapUp(lastIndex());
      return true;
   }
   
   public E remove()
   {
     int size = myHeap.size();
     E temp = this.peek();
     if(this.isEmpty())
     return null;
     
     if(myHeap.size() <= 2)
     myHeap.remove(1);
     
     else
     {
     swap(1, myHeap.size() - 1);
     
     myHeap.remove(myHeap.size() - 1 );
     
     heapDown(1, myHeap.size());
     
     }
     return temp;
   }
   
   public E peek()
   {
    if(!this.isEmpty())
    return myHeap.get(1);
    else
    return null;
   }
   
   //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapUp(int k)
   {
     if(k/2 >= 1)
     {
     if(myHeap.get(k/2).compareTo(myHeap.get(k)) > 0)
     {
     swap(k, k/2);
     heapUp(k/2);
     }
     else
     return;
     }
     else
     return;
   }
   
   private void swap(int a, int b)
   {
     E temp = myHeap.get(a);
     myHeap.set(a, myHeap.get(b));
     myHeap.set(b, temp);
   }
   
  //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapDown(int k, int lastIndex)
   {
    int right = 2*k + 1;
      int left = 2 * k;
      if(k > lastIndex || (left > lastIndex && right > lastIndex))
         return;
      else
      {
         if(right < lastIndex)
         {
            int max = (myHeap.get(left).compareTo(myHeap.get(right)) < 0) ? left:right;
            if(myHeap.get(k).compareTo( myHeap.get(max)) > 0 )
            {
               swap(k, max);
               heapDown(max, lastIndex);
            }
         }
      }   
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
