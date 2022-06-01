// Name:Om Gole
// Date:1/10/2022

import java.util.*;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
    postfixExp.add("3 3 +");
    postfixExp.add("3 3 -");
    postfixExp.add("3 3 *");
    postfixExp.add("3 3 /");
    postfixExp.add("3 3 %");
    postfixExp.add("3 3 ^");
    postfixExp.add("3 3 !");
    postfixExp.add("2 7 3 % +");
    postfixExp.add("2 3 ^");
    postfixExp.add("2 -2 ^");
    postfixExp.add("2 3 ^ 3 2 +");
    postfixExp.add("2 7 + 3 %");
    postfixExp.add("5 !");
    postfixExp.add("1 1 1 1 1 + + + + !");
    
      
   
      
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      /*  enter your code here  */
      Stack<Double> equation = new Stack <>();
      int count = 0;
      
      while(count < postfixParts.size())
      {
      if(!isOperator(postfixParts.get(count)))
      equation.push(Double.parseDouble(postfixParts.get(count))); //Pushes numbers until it sees an operand, then once it sees an operand, pops twice, then calculate, then pushes the number back in, it then continues down.
      else
         {
         double f = 0;
         double s = 0;
         f = equation.pop();
         if(!equation.isEmpty())
         {
         s = equation.pop();
         }
         equation.push(eval(f, s, postfixParts.get(count)));
         }
         count++;
      }
      
      return equation.peek();
      
      //push numbers, pop operators
   }
   
   public static double eval(double a, double b, String ch)
   {
   
   if(ch.contains("+"))
   return a + b;
   else if(ch.contains("-"))
   return b-a;
   else if(ch.contains("*"))
   return b * a;
   else if(ch.contains("/"))
   return b/a;
   else if(ch.contains("%"))
   return b%a;
   else if(ch.contains("^"))
   return (double)Math.pow(b, a);
   else if(ch.contains("!")) //factorial
   {
      if(a == 0)
      return 1;
      else
      return (a * eval(a-1, b, ch));
   }
   else
   return a;
   
   }
   
   public static boolean isOperator(String op)
   {
   return operators.contains(op);
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/