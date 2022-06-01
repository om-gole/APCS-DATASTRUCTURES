// Name:Om Gole
// Date:1/7/2022

import java.util.*;

public class ParenMatch
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter test cases here */
     parenExp.add("5 + 7");
     parenExp.add("( 15 + -7 )");
     parenExp.add(") 5 + 7 (");
     parenExp.add("( ( 5.0 - 7.3 ) * 3.5 )");
     parenExp.add("< { 5 + 7 } * 3 >");
     parenExp.add("[ ( 5 + 7 ) * ] 3");
     parenExp.add("( 5 + 7 ) * 3");
     parenExp.add("5 + ( 7 * 3 )");
     parenExp.add("( ( 5 + 7 ) * 3");
     parenExp.add("[ ( 5 + 7 ) * 3 ] )");
     parenExp.add("( [ ( 5 + 7 ) * 3 ]");
     parenExp.add("( ( ( ) $ ) )");
     parenExp.add("( ) [ ]");



   
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
     
   //returns the index of the left parentheses or -1 if it is not there
   public static int isLeftParen(String p)
   {
      return LEFT.indexOf(p);
   }
  
   //returns the index of the right parentheses or -1 if it is not there
   public static int isRightParen(String p)
   {
      return RIGHT.indexOf(p);
   }
   
   public static boolean checkParen(String exp)
   {
     /* enter your code here */
         Stack<String> numbers = new Stack<String>();
         for(char ch : exp.toCharArray())
         {
            if(RIGHT.contains(""+ch) && numbers.empty())
            return false;
            else if(LEFT.contains(""+ch))
            numbers.push("" + ch);
            else if(RIGHT.contains(""+ch) && isLeftParen((numbers.peek())) == isRightParen("" + ch))
            numbers.pop();
         }
     return numbers.empty();  //so it compiles    
   }
}

/*****************************************

Parentheses Match
5 + 7		 good!
( 15 + -7 )		 good!
) 5 + 7 (		 BAD
( ( 5.0 - 7.3 ) * 3.5 )		 good!
< { 5 + 7 } * 3 >		 good!
[ ( 5 + 7 ) * ] 3		 good!
( 5 + 7 ) * 3		 good!
5 + ( 7 * 3 )		 good!
( ( 5 + 7 ) * 3		 BAD
[ ( 5 + 7 ] * 3 )		 BAD
[ ( 5 + 7 ) * 3 ] )		 BAD
( [ ( 5 + 7 ) * 3 ]		 BAD
( ( ( ) $ ) )		 good!
( ) [ ]		 good!
 
 *******************************************/
