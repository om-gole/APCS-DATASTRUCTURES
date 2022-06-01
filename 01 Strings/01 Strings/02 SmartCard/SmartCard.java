//name:  om gole  date:8/26/2021

import java.text.DecimalFormat;
public class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
  
   /* enter the private fields */
   private double myBalance = 0;
   private boolean onBoard = false;
   private Station myStation;
   private String firstStation;
   private int firstZone;
   
   /* the one-arg constructor  */
   public SmartCard(double initBalance)
   {
      myBalance = initBalance;
   }

   //these three getter methods only return your private data
   //they do not make any changes to your data
   public boolean getIsBoarded() 
   { 
      return onBoard;
   }
   
   public String getFormattedBalance()
   {
      String x = df.format(myBalance); 
      return x;
   }
   
   public double getBalance()
   { 
      return myBalance;
   }
         
   public Station getBoardedAt()
   {
      return myStation;
   }
    
   /* write the instance methods  */
   public void board(Station x)
   {
      if(myBalance < 0.5)
      {
         System.out.println("Insufficient funds to Board. Please add more money.");
        return; 
      }
      else if(onBoard == true)
      {
         System.out.println("Error: Already Boarded");
         return;
      }
      else
      {
         myStation = x;
         firstStation = x.getName();
         firstZone = x.getZone();
         onBoard = true;
         
         
      }
      
   }
  
   public void exit(Station x)
   {
      myBalance = myBalance - cost(x);
      if(myBalance < 0)
      {
         System.out.println("Insufficient funds to exit. Please add more money.");
         return;
      }
      else if(onBoard == false)
      {
         System.out.println("Error: Did not Board");
         return;
      }
      else
      {
      System.out.println("From " + firstStation +" to " + x.getName() +" costs: " + cost(x) + ".  ");
      System.out.println("Card Balance:" + getFormattedBalance());
      myStation = null;
      onBoard = false;
      return;
      }
   }
  
   public double cost(Station x)
   {
      int zone1 = firstZone;
      int zone2 = x.getZone();
      double c = 0;
      if(zone1 == zone2)
      {
         c = 0.5;
         
      }
      else if (zone1 != zone2)
      {
         c = (0.75 * Math.abs(zone1 - zone2)) + 0.5;
         
      }
      

      return c;
   }
   
   public void addMoney(double c)
   {
   myBalance = myBalance + c;
   }
      
    

}
   
// ***********  start a new class.  The new class does NOT have public or private.  ***/
class Station
{
   private int myZone;
   private String myName; 
  
   public Station()
   {
      myZone = 0;
      myName = null;
   }
   public Station(String name, int zone)
   {
      myZone = zone;
      myName = name;
   }
   
   public String getName()
   {
      return myName;
   }
   
   public int getZone()
   {
      return myZone;
   }
}

