//Updated on 12.14.2020 v2

//Name:Om Gole   Date: 1/18/2022
import java.util.*;
import java.io.*;
public class McRonald
{
   public static final int TIME = 1080;     //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   //public static final int numberOfServiceWindows = 3;  //for McRonald 3
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(Queue<Customer> q, int min)
   { 
      //Billington's
      outfile.println(min + ": " + q);	
      //Jurj's
      //outfile.println("Customer#" + intServiceAreas[i] + 
      //                            " leaves and his total wait time is " + (intMinute - intServiceAreas[i]));                     	
      
   }
   
   public static int getCustomers()
   {
      return customers;
   }
   public static double calculateAverage()
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   public static int getLongestWaitTime()
   {
      return longestWaitTime;
   }
   public static int getLongestQueue()
   {
      return longestQueue;
   }
            
   public static void main(String[] args)
   {     
    //set up file      
      try
      {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      mcRonald(TIME, outfile);   //run the simulation
      
      outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of)
   {
      /***************************************
           Write your code for the simulation   
      **********************************/
      Queue<Customer> customerList = new LinkedList<>();
   for(int x = TIME; x > 0; x--){

        if(customerList.size() != 0){
           
           if(customerList.peek().getTotalWait()>longestWaitTime)
              longestWaitTime = customerList.peek().getTotalWait();
           customerList.peek().subOrderTime();
           if(customerList.peek().getOrderTime() == 0){
              totalMinutes += customerList.peek().getTotalWait();
              customerList.remove();
           }
        }
        double odds = (Math.random());
        if(odds > 0.8){
           if(customerList.size()>longestQueue)
              longestQueue = customerList.size();
           customers++;
           int wait = 0;
           for(Customer cust: customerList){
              wait+= cust.getOrderTime();
           }
           customerList.add(new Customer(wait, ""+customers));
         
        }
        of.println(customerList);
     }
     while(customerList.size() != 0){
        customerList.peek().subOrderTime();
        if(customerList.peek().getOrderTime() == 0)
           totalMinutes += customerList.peek().getTotalWait();
        of.println(customerList);     



     }
                
              
      /*   report the data to the screen    */  
      System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers()); //getCustomers()
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime); //longestWaitTime
      System.out.println("Longest queue = " + longestQueue); //longestQueue
       customers = 0;
       totalMinutes = 0;
       longestWaitTime = 0;
       longestQueue = 0;
   }
   
   static class Customer      
   {
   /*
      private int arrivedAt;
      private int orderAndBeServed;
      
    /**********************************
       Complete the Customer class with  
       constructor, accessor methods, toString.
    ***********************************/
  /*
   public Customer(int arrive, int order)
   {
   
   arrivedAt = arrive;
   orderAndBeServed = order;
   }
   
    public int getArrival()
    {
    return arrivedAt;
    }
    public int getOrder()
    {
    return orderAndBeServed;
    }
    public void orderAdd()
    {
    orderAndBeServed++;
    }
    public String toString()
    {
    return "[" + arrivedAt + ", " + orderAndBeServed + "]";
    }
    
    
   }
   
   */
   private int arrivedAt;
  private int orderAndBeServed;

  private String name;
  public Customer(int n, String m){
     orderAndBeServed = (int)(Math.random()*5+2);
     arrivedAt = orderAndBeServed + n;
     name = m;
  }
  public int getOrderTime(){
     return orderAndBeServed;
  }
  public void subOrderTime(){
     orderAndBeServed--;
  }
  public int getTotalWait(){
     return arrivedAt;
  }


  public void addTotalWait(int x){
     arrivedAt += x;
  }


  public String toString(){
     return name;
  }
}

}