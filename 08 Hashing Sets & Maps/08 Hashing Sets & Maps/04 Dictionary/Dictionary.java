// Name: Om Gole
// Date: 3/10/2022

import java.io.*;
import java.util.*;

public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
   
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
     Map<String, Set<String>> Map = new TreeMap<String, Set<String>>();
     while(infile.hasNext())
     {
     add(Map, infile.next(), infile.next());
     }
     return Map;
     
   }
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   { 
     if(dictionary.keySet().contains(word))
     {
      dictionary.get(word).add(translation);
     }
     else
     {
     Set<String> s = new HashSet<String>();
         TreeSet<String> t = new TreeSet<String>(s);
         t.add(translation);
     dictionary.put(word, t);
     }
   }
   
   public static void display(Map<String, Set<String>> m)
   {
    String toReturn = "";
      for(String str : m.keySet())
      {
         toReturn += str + " ";
      
         
      
        toReturn += m.get(str).toString();
      
         
      
         toReturn += "\r";
      }
  
      System.out.print(toReturn);

   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
   Map<String, Set<String>> Map = new TreeMap<String, Set<String>>();
     
     for(String str : dictionary.keySet())
      {
      
         Iterator <String> it = dictionary.get(str).iterator(); //iterator
      
         while(it.hasNext())
         {
            
           add(Map, it.next(), str); 
            
            }

       }
     return Map;
   }
}


   /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/