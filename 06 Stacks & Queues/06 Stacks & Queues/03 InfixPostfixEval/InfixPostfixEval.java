// Name:Om Gole
// Date:1/11/2022
//uses PostfixEval

import java.util.*;
public class InfixPostfixEval
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      List<String> infixExp = new ArrayList<>();
         
       infixExp.add("5 - 1 - 1");  
       infixExp.add("5 - 1 + 1");  
       infixExp.add("12 / 6 / 2");  
       infixExp.add("3 + 4 * 5");  
       infixExp.add("3 * 4 + 5");  
       infixExp.add("1.3 + 2.7 + -6 * 6");  
       infixExp.add("( 33 + -43 ) * ( -55 + 65 )");  
       infixExp.add("8 + 1 * 2 - 9 / 3");  
       infixExp.add("3 * ( 4 * 5 + 6 )");  
       infixExp.add("3 + ( 4 - 5 - 6 * 2 )");  
       infixExp.add("2 + 7 % 3");  
       infixExp.add("( 2 + 7 ) % 3");
       infixExp.add("5 + [ 2 - ( 1 + 3 ) + 4 % 3 ]");  
       infixExp.add("4 - 3 + 2 + 5 * 2 / 3 % 2");   
         
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         System.out.println(infix + "\t\t\t" + pf );  
       //  System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
     /*
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      Stack<String> equation = new Stack <>();
      String append = "";
            
      for(String x : nums)
      {
         if(!operators.contains(x))
         {
         append = append + x + " ";
         }
         else if(LEFT.contains(x))
         {
         equation.push(x);
         }    
         else if (RIGHT.contains(x))
         {
         while(!LEFT.contains(equation.peek()))
           {
           append = append + equation.pop() + " ";
           }
           equation.pop();
         }
         else
         {
            while(!equation.empty() && isStrictlyLower(x, equation.peek()))
            {
            append = append + equation.pop() + " ";
            }
            equation.push(x);
         }
      }
      while(!equation.empty())
      {
      append = append + equation.pop() + " ";
      }
      return append;
      */
 String postfix = "";
     char[] chars = infix.toCharArray();
     Stack<Character> post = new Stack<Character>();
     for(int x=0; x<chars.length; x++){
        if(!isO(chars[x]) && LEFT.indexOf(chars[x]) == -1 && RIGHT.indexOf(chars[x]) == -1)
           postfix+=chars[x];//
        else if(LEFT.indexOf(chars[x]) != -1)
           post.push(chars[x]);
        else if(RIGHT.indexOf(chars[x]) != -1){
           while(LEFT.indexOf(post.peek()) == -1)
              postfix += post.pop();
           post.pop();
        }
        else if(isO(chars[x])){
           while(!(post.isEmpty()) && LEFT.indexOf(post.peek()) == -1 && !isStrictlyLower(post.peek(), chars[x]))
              postfix+=post.pop();
           post.push(chars[x]);
        }  
     }
     while(!post.isEmpty()){
        char y = post.pop();
        if(LEFT.indexOf(y) == -1 && RIGHT.indexOf(y) == -1)
           postfix+=y;
     }
     postfix = postfix.replaceAll("\\s", "");
     postfix = postfix.replaceAll("", " ").trim();
     
     return postfix;
/*
 String postfix = "";
     char[] exp = infix.toCharArray();
     List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
     Stack<String> post = new Stack<String>();
     for(String x : nums){
        if(!operators.contains(x) && !LEFT.contains(x) && !RIGHT.contains(x))
           postfix+=x;//
        else if(LEFT.contains(x))
           post.push("(");
        else if(RIGHT.contains(x) && !post.empty()){
           while(!LEFT.contains(post.peek()))
              postfix += post.pop();
           post.pop();
        }
        else if(operators.contains(x)){
           while(!(post.isEmpty()) && !LEFT.contains(x) && !isStrictlyLower(post.peek(), x))
              postfix+=post.pop();
           post.push(x);
        }  
     }
     while(!post.isEmpty()){
        String curr = post.pop();
        if(!LEFT.contains(curr) && !RIGHT.contains(curr))
           postfix+=curr;
     }
     return postfix;
*/
   }
  
   //enter your precedence method below
   

     public static boolean isStrictlyLower(char c1, char c2)
  {
     boolean low = false;
     if((c1 == '+' || c1 == '-')&&(c2=='*' || c2=='%' || c2 == '/' || LEFT.indexOf(c2) != -1 || LEFT.indexOf(c2) != -1))
        low = true;
     else if((c1 == '*' || c1 == '%' || c1 == '/')&&(LEFT.indexOf(c2) != -1 || LEFT.indexOf(c2) != -1))
        low = true;
     return low;
  }
  
 /* 
public static boolean isStrictlyLower(char next, char top)
   {
      int nextnum = 0;
      int topnum = 0;
   
      if(top == '+')
         topnum = 1;
      else if(top == '-')
         topnum = 1;
      else if(top == '*')
         topnum = 2;
      else if(top == '/')
         topnum = 2;
      else if(top == '%')
         topnum = 3;
      else if(top == '^')
         topnum = 4;
      else if(top == '!')
         topnum = 5;
   
      if(next == '+')
         nextnum = 1;
      else if(next == '-')
         nextnum = 1;
      else if(next == '*')
         nextnum = 2;
      else if(next == '/')
         nextnum = 2;
      else if(next == '%')
         nextnum = 3;
      else if(next == '^')
         nextnum = 4;
      else if(next == '!')
         nextnum = 5;
   
      if(topnum < nextnum)
         return true;
      else
         return false;
   
   }
  */
  public static boolean isO(char ch)


  {
     if(ch=='+' || ch=='-' || ch=='*' || ch=='/' || ch == '%')
        return true;
     else
        return false;
  }


   // top = +  && next = * return true opposite returns false.
   
}


/********************************************

Infix  	-->	Postfix		-->	Evaluate
 5 - 1 - 1			5 1 - 1 -			3.0
 5 - 1 + 1			5 1 - 1 +			5.0
 12 / 6 / 2			12 6 / 2 /			1.0
 3 + 4 * 5			3 4 5 * +			23.0
 3 * 4 + 5			3 4 * 5 +			17.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * +			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + *			-100.0
 8 + 1 * 2 - 9 / 3			8 1 2 * + 9 3 / -			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - +			-10.0
 2 + 7 % 3			2 7 3 % +			3.0
 ( 2 + 7 ) % 3			2 7 + 3 %			0.0
 5 2 1 3 + - 4 3 % + +
  4 3 - 2 + 5 2 * 3 / 2 % +     
***********************************************/
