// Name:   Om Gole   
// Date: 8/31/2021
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
      
      /*  extension only    */
      String pigLatin = pig("What!?");
      System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      pigLatin = pig("{(Hello!)}");
      System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      pigLatin = pig("\"McDonald???\"");
      System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         s = s.trim();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static final String y = "Yy";
   public static final String qu = "quQuQUqU";
   public static String pig(String pig)
   {
      if(pig.length() == 0)
         return "";
        int firstLetter = -1;
        int lastLetter = -1;
      //remove and store the beginning punctuation 
      for(int i = 0; i < pig.length(); i++)
      {
      if(letters.contains(""+pig.charAt(i)))
      {
      firstLetter = i;
      break;
      }      
      }
      for(int i = 0; i < pig.length(); i++)
      {
      if(letters.contains(""+pig.charAt(i)))
      {
      lastLetter = i;
      }      
      }
      String firstPunct = pig.substring(0,firstLetter);
      String lastPunct = pig.substring(lastLetter + 1);
      //remove and store the ending punctuation           
      //START HERE with the basic case:
      String s = pig.substring(firstLetter, lastLetter + 1);
      //     find the index of the first vowel
     
      int vowelIndex = -1;
      int secondVowelIndex = -1;
      int vowelCount = 0;
      int yIndex = -1;
      
      char firstCharacter = s.charAt(0);
      boolean firstCap;
      firstCap = (Character.isUpperCase(firstCharacter));
     
      for(int i = 0; i < s.length(); i++)
      {
      if(vowels.contains(""+s.charAt(i)))
      {
      vowelIndex = i;
      break;
      }      
      }
      
      for(int i = 0; i < s.length(); i++)
      {
      if(vowels.contains(""+s.charAt(i)))
      {
      vowelCount++;
      secondVowelIndex = i;
      if(vowelCount == 2)
      break;
      }      
      }
      if(vowelCount == 1)
      secondVowelIndex = -1;
      
      for(int i = 1; i < s.length(); i++)
      {
      if(y.contains(""+s.charAt(i)))
      {
      yIndex = i;
      break;
      }      
      }
      
      int quIndex = -1;
      
      for(int i = 0; i < s.length(); i++)
      {
      String temp = s.substring(i, i + 1);
      if(qu.contains("" + temp))
      {
      quIndex = i;
      break;
      }      
      }
      if(quIndex < vowelIndex && quIndex > -1)
      vowelIndex = secondVowelIndex;
      if(quIndex > vowelIndex && secondVowelIndex == -1)
      vowelIndex = -1;
      int index = -1;
      if(yIndex > -1 && yIndex < vowelIndex)
      {
      index = yIndex;
      }
      else if(yIndex > -1 && vowelIndex == -1)
      index = yIndex;
      else
      index = vowelIndex;
      

      //     y is a vowel if it is not the first letter
      //     qu
      
      
      //if no vowel has been found
      if(index == -1)
      {
      return "**** NO VOWEL ****";
      }
      
      //is the first letter capitalized?
     
   
      
      //return the piglatinized word 
      String way = "way";
      String ay = "ay";
      String start = s.substring(index);
      
      String end = s.substring(0, index);
      String piglatin = null;
      
      if(firstCap)
      {
      String temp = end;
      if(end.length() >= 1)
      {
      temp =  end.substring(0, 1).toLowerCase() + end.substring(1);
      }
      if(end.length() == 0)
      {
      temp = temp + end.substring(0);
      }
      end = temp;
      }
      
      piglatin = start + end;
      
      if(firstCap)
      {
      piglatin = piglatin.substring(0,1).toUpperCase() + piglatin.substring(1);
      }
      
      if(vowelIndex == 0)
      piglatin = firstPunct + piglatin + way + lastPunct;
      else
      piglatin = firstPunct + piglatin + ay + lastPunct;
      
      
     
     
      return piglatin;
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      while(infile.hasNextLine())
      {
      String line = infile.nextLine();
      String[] words = line.split(" ");
      for(int x = 0; x < words.length; x++)
      {
      outfile.print(pig(words[x]) + " ");
      }
      outfile.println();
      }
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String pig)
   {
      if(pig.length() == 0)
         return "";
         
         int firstLetter = -1;
        int lastLetter = -1;
      //remove and store the beginning punctuation 
      for(int i = 0; i < pig.length(); i++)
      {
      if(letters.contains(""+pig.charAt(i)))
      {
      firstLetter = i;
      break;
      }      
      }
      for(int i = 0; i < pig.length(); i++)
      {
      if(letters.contains(""+pig.charAt(i)))
      {
      lastLetter = i;
      }      
      }
      String firstPunct = pig.substring(0,firstLetter);
      String lastPunct = pig.substring(lastLetter + 1);
      //remove and store the ending punctuation           
      //START HERE with the basic case:
      String s = pig.substring(firstLetter, lastLetter + 1);
      //     find the index of the first vowel
      
      char firstCharacter = s.charAt(0);
      boolean firstCap;
      firstCap = (Character.isUpperCase(firstCharacter));
         
      
      //return the piglatinized word 
      
      
      String piglatin = "";
      
      if(firstCap)
      {
      String temp = s;
      if(s.length() >= 1)
      {
      temp =  s.substring(0, 1).toLowerCase() + s.substring(1);
      }
      if(s.length() == 0)
      {
      temp = temp + s.substring(0);
      }
      s = temp;
      }
      String word = s;
      String reverse = "";
      
      
      
      for(int x = word.length()-1; x > -1; x--)
      {
      reverse = reverse + word.charAt(x);
      }
      piglatin = reverse;
      
      if(firstCap)
      {
      piglatin = piglatin.substring(0,1).toUpperCase() + piglatin.substring(1);
      }
      
      piglatin = firstPunct + piglatin + lastPunct;
      
     
     
      return piglatin;

   }
}
