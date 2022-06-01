// Name:
// Date:
import java.text.*;
public class HeapSort
{
   public static int N;  //9 or 100
	
   public static void main(String[] args)
   {
      //Part 1: Given a heap, sort it. Do this part first.
      
      N = 9;  
      double heap[] = {-1,99,80,85,17,30,84,2,16,1};  // size of array = N+1
      //N = 4;
      //double heap[] = {-1, 7.2, 3.4, 6.4, 9.9};
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
      
      /* Part 2:  Generate 100 random numbers, make a heap, sort it.  */
      /*
       N = 100;
       double[] heap = new double[N + 1];  // size of array = N+1
       heap = createRandom(heap);
       display(heap);
       makeHeap(heap, N);
       display(heap); 
       sort(heap);
       display(heap);
       System.out.println(isSorted(heap));
       */
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
      /* enter your code here */
      for(int x = array.length-1; x > 2; x--)
      {
         swap(array, 1, x);
         heapDown(array, 1, x-1);
      }
   
      if(array[1] > array[2])   //just an extra swap, if needed.
         swap(array, 1, 2);
         
      if(array[array.length - 1] < array[array.length - 2])
         swap(array, array.length -1 , array.length -2);
   }
  
   public static void swap(double[] array, int a, int b)
   {
      double temp = 0;
      temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
   
   public static void heapDown(double[] array, int k, int lastIndex) // children in 2k & 2k+1 Parent in k/2
   {
      int right = 2*k + 1;
      int left = 2 * k;
      if(k > lastIndex || (left > lastIndex && right > lastIndex))
         return;
      else
      {
         if(right <= lastIndex)
         {
            int max = (array[left]>array[right]) ? left:right;
            if(array[k] < array[max])
            {
               swap(array, k, max);
               heapDown(array, max, lastIndex);
            }
         }
      }    
   }
   
   public static boolean isSorted(double[] arr)
   {
   
      return !isSorted(arr, 1, arr.length);
   }
   
   public static boolean isSorted(double[] arr, int x, int n)
   {
      if( x >= (n-2)/2)
      {
         return true;
      }
   
      if(arr[x] >= arr[2*x+1] && arr[x] >= arr[2*x+2] && isSorted(arr, 2 * x + 1, n) && isSorted(arr, 2*x+2, n))
      {
         return true;
      }
   
      return false;
    
   }
   
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1;   //because it will become a heap
      DecimalFormat d = new DecimalFormat("0.00");
      for(int x = 1; x < N + 1; x ++)
         array[x] = Double.parseDouble(d.format(Math.random() * 100 + 1));
       
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int lastIndex)
   {
      for(int k = N/2; k >=1; k--)
         heapDown(array,k,N);
   }
}

