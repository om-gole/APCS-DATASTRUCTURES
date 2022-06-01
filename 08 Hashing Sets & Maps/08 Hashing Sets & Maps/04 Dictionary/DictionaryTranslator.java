import java.io.*;
import java.util.*;

public class DictionaryTranslator
{
   public static void main(String[] args)
   {
   
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
      }
      catch(Exception e)
      {
      }
      
      Map<String, Set<String>> eng2spn = Dictionary.makeDictionary(infile);
      Map<String, Set<String>> spn2eng = Dictionary.reverse(eng2spn);    
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter English for an English to Spanish or Spanish for Spanish to English: ");
      String eS = sc.nextLine(); 
      System.out.println("What word would you like to translate? ");
      String word = sc.nextLine();
      
      if(eS.equals("English"))
      {
         if(!eng2spn.containsKey(word))
            System.out.println("No translation for: " + word);
         else
         {
            String toReturn = ""; 
            String val = eng2spn.get(word).toString();
            toReturn += val.substring(1,val.length()-1);
            System.out.println(toReturn);
            String yN = ""; 
            while(!yN.equals("No"))
            {
               System.out.println("Do you have any translations you would like to add (Yes or No)?");
               yN = sc.nextLine(); 
               if(yN.equals("No"))
                  break; 
               System.out.println("What would you like to add?");
               String add = sc.nextLine(); 
               toReturn += ", " + add; 
               System.out.println(toReturn);  
            }
                    
         }
      } 
      
     else
      {
         if(!spn2eng.containsKey(word))
            System.out.println("Sorry we do not have " + word + "'s translations :(");
         else
         {
            String toReturn = ""; 
            String val = spn2eng.get(word).toString();
            toReturn += val.substring(1,val.length()-1);
            System.out.println(toReturn);
            String yN = ""; 
            while(!yN.equals("No"))
            {
               System.out.println("Do you have any translations you would like to add (Yes or No)?");
               yN = sc.nextLine(); 
               if(yN.equals("No"))
                  break; 
               System.out.println("What would you like to add?");
               String add = sc.nextLine(); 
               toReturn += ", " + add; 
               System.out.println(toReturn);  
            }
                    
         }
      } 
   
   
   
   
   }





















}
