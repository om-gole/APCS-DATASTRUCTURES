// Name:Om Gole   
// Date:9/30/2021

import java.util.*;
import java.io.*;

public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename (no .txt): ");
      Maze m = new Maze(sc.next()+".txt");
      // Maze m = new Maze();    //extension
      m.display();      
      System.out.println("Options: ");
      System.out.println("1: Mark all dots.");
      System.out.println("2: Mark all dots and display the number of recursive calls.");
      System.out.println("3: Mark only the correct path.");
      System.out.println("4: Mark only the correct path. If no path exists, say so.");
      System.out.println("5: Mark only the correct path and display the number of steps.\n\tIf no path exists, say so.");
       System.out.println("6: Mark only the correct path and list steps.\n\tIf no path exists, say so.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
      m.display();      //display solved maze
   } 
}

class Maze
{
   //constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char TEMP = 'o';
   private final char PATH = '*';
   //instance fields
   private char[][] maze;
   private int startRow, startCol;
  
   //constructors
	
	/* 
	 * EXTENSION 
	 * This is a no-arg constructor that generates a random maze
	 */
   public Maze()
   {
   
   }
	
	/* 
	 * Copy Constructor  
	 */
   public Maze(char[][] m)  
   {
      maze = m;
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)      //identify start location
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   } 
	
	/* 
	 * Use a try-catch block
	 * Use next(), not nextLine()  
	 */
   public Maze(String filename)    
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(filename));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
      
      int row = infile.nextInt();
      int col = infile.nextInt();
      
      maze  = new char[row][col];
      String line = "";
      for(int x = 0; x < row; x++)
      {
      line = infile.nextLine();
      char[] array = line.toCharArray();
      for(int y = 0; y < array.length; y++)
       {
       maze[x][y] = array[y];
       }
      }
      
      for(int x = 0; x < row; x++)
      { 
      for(int y = 0; y < col; y++)
       {
       if(maze[x][y] == START)
        {
        startRow = x;
        startCol = y;
        }
       }
      }   
      
   }
   
   public char[][] getMaze()
   {
      return maze;
   }
   
   public void display()
   {
      if(maze==null) 
         return;
      for(int a = 0; a<maze.length; a++)
      {
         for(int b = 0; b<maze[0].length; b++)
         {
            System.out.print(maze[a][b]);
         }
         System.out.println();
      }
      System.out.println();
   }
   
   public void solve(int n)
   {
      switch(n)
      {
         case 1:
         {
            markAll(startRow, startCol);
            break;
         }
         case 2:
         {
            int count = markAllAndCountRecursions(startRow, startCol);
            System.out.println("Number of recursions = " + count);
            break;
         }
         case 3:
         {
            markTheCorrectPath(startRow, startCol);
            break;
         }
         case 4:         //use mazeNoPath.txt 
         {
            if( !markTheCorrectPath(startRow, startCol) )
               System.out.println("No path exists."); 
            break;
         }
         case 5:
         {
            if( !markCorrectPathAndCountSteps(startRow, startCol, 0) )
               System.out.println("No path exists."); 
            break;
         }
         case 6:
         {
         if( markCorrectPathAndListSteps(startRow, startCol, ""))
         {}
         else
         System.out.println("No path exists");
         break;
         }
         default:
            System.out.println("File not found");   
      }
   }
   
	/* 
	 * From handout, #1.
	 * Fill the maze, mark every step.
	 * This is a lot like AreaFill.
	 */ 
   public void markAll(int r, int c)
   {
    if(r < 0 || c < 0 || r >= maze.length || c >= maze[0].length)
     return;
    if(maze[r][c] == DOT || maze[r][c] == START)
    {
    maze[r][c] = '*';
    
    
    markAll(r + 1, c);
    
    markAll(r, c - 1);
    
    markAll(r - 1, c);
    
    markAll(r, c + 1);
    
    }
    
   }

	/* 
	 * From handout, #2.
	 * Fill the maze, mark and count every recursive call as you go.
	 * Like AreaFill's counting without a static variable.
	 */ 
   public int markAllAndCountRecursions(int r, int c)
   {
     
      if(r < 0 || c < 0 || r >= maze.length || c >= maze[0].length)
     return 0;
     if(maze[r][c] == DOT || maze[r][c] == START)
     {
     maze[r][c] = '*';
     return 1 + markAllAndCountRecursions(r-1,c) + markAllAndCountRecursions(r+1,c) + markAllAndCountRecursions(r,c-1) + markAllAndCountRecursions(r,c+1);
     
     }
   return 0;
   }

   /* 
	 * From handout, #3.
	 * Solve the maze, OR the booleans, and mark the path through it with an asterisk
	 * Recur until you find E, then mark the True path.
	 */ 	
   public boolean markTheCorrectPath(int r, int c)
   {
   
      if(r<0||c<0||r>=maze.length||c>=maze[0].length)
        return false;
        
        else if(maze[r][c]==EXIT)
        return true;
      else if(maze[r][c]== WALL)
       return false;
      else if(maze[r][c]== PATH)
        return false;
     else{
        maze[r][c]= PATH;
        if(markTheCorrectPath(r+1, c)==true)
        return true;
        if(markTheCorrectPath(r-1, c)==true)
        return true;
        if(markTheCorrectPath(r, c+1)==true)
        return true;
        if(markTheCorrectPath(r, c-1)==true)
        return true;
        else{
        maze[r][c]= DOT;
        return false;
        }
     }


      
     
   }
	
	
   /*  4   Mark only the correct path. If no path exists, say so.
           Hint:  the method above returns the boolean that you need. */
      

   /* 
	 * From handout, #5.
	 * Solve the maze, mark the path, count the steps. 	 
	 * Mark only the correct path and display the number of steps.
	 * If no path exists, say so.
	 */ 	
   public boolean markCorrectPathAndCountSteps(int r, int c, int count)
   {
      
      if(r<0||c<0||r>=maze.length||c>=maze[0].length)
        return false;
        
        else if(maze[r][c]==EXIT)
        return true;
      else if(maze[r][c]== WALL)
       return false;
      else if(maze[r][c]== PATH)
        return false;
     else{
        maze[r][c]= PATH;
        if(markCorrectPathAndCountSteps(r+1, c, count++)==true)
        return true;
        if(markCorrectPathAndCountSteps(r-1, c, count++)==true)
        return true;
        if(markCorrectPathAndCountSteps(r, c+1, count++)==true)
        return true;
        if(markCorrectPathAndCountSteps(r, c-1, count++)==true)
        return true;
        else{
        maze[r][c]= DOT;
        return false;
        }
 }
 }
    
   
   public boolean markCorrectPathAndListSteps(int r, int c, String s)
   {
      if(r<0||c<0||r>=maze.length||c>=maze[0].length)
        return false;
        
        
        else if(maze[r][c]==EXIT)
        {
        System.out.print(s);
        return true;
        }
      else if(maze[r][c]== WALL)
       return false;
      else if(maze[r][c]== PATH)
        return false;
        
     else{
        maze[r][c]= PATH;
        
        if(markCorrectPathAndListSteps(r+1, c, "(" + (r +1) + "," + c + ") ")==true)
        {
        System.out.print(s);
        return true;
        }
        if(markCorrectPathAndListSteps(r-1, c, "(" + (r-1) + "," + c + ") ")==true)
        {
        System.out.print(s);
        return true;
        }
        if(markCorrectPathAndListSteps(r, c+1, "(" + r + "," + (c+1) + ") ")==true)
        {
        System.out.print(s);
        return true;
        }
        if(markCorrectPathAndListSteps(r, c-1, "(" + r + "," + (c-1) + ") ")==true)
        {
        System.out.print(s);
        return true;
        }
       
        else{
        maze[r][c]= DOT;
        return false;
        }
        
        
       
   }

      }
}

/*****************************************
 
 ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 1
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 2
 Number of recursions = 105
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 3
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
     
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): mazeNoPath
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 4
 No path exists.
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 5
 Number of steps = 14
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
  ********************************************/