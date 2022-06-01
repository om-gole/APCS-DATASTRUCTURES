// Name:   Om Gole
// Date:   5/6/2022
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   public int[][] getGrid();
   public void addEdge(int source, int target);
   public void removeEdge(int source, int target);
   public boolean isEdge(int from, int to);
   public String toString();  //returns the grid as a String
   public int edgeCount();
   public List<Integer> getNeighbors(int source);
   //public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      //User-friendly functionality
{
   public boolean isEdge(String from, String to);
   public Map<String, Integer> getVertices();     
   public void readNames(String fileName) throws FileNotFoundException;
   public void readGrid(String fileName) throws FileNotFoundException;
   public void displayVertices();
   public void allPairsReachability();   // Warshall's Algorithm
   public List<String> getReachables(String from);  //Warshall extension
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix, Warshall, Floyd 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
   /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  write constructor, accessor method, and instance methods here  */  
   public AdjMat (int size)
   {
      grid = new int[size][size];
   
   }
   
   public int getCost(int from, int to)
   {
   return grid[from][to];
   }
   public int getCost(String from, String to)
   {
   return getCost(vertices.get(from), vertices.get(to));
   }
   public void allPairsWeighted()
   {
      for(int x = 0; x < grid.length; x++)
      for(int r = 0; r < grid.length; r++)
      for(int c = 0; c < grid.length; c++)
      if(grid[r][c] > grid[r][x] + grid[x][c])
      grid[r][c] = grid[r][x] + grid[x][c];
   }
   
   
   
   
   
  
   
   public boolean isEdge(String f, String t)
   {
      int from = vertices.get(f);
      int to = vertices.get(t);
      if(from > grid.length-1 || to > grid[0].length-1 || from < 0 || to < 0)
         return false; 
     
      if(grid[from][to] == 1)
         return true;
      else
         return false;
   
   }
   
   public Map<String, Integer> getVertices()  
   {
      return vertices;
   }
   public void readNames(String fileName) throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File(fileName));
      int size = sc.nextInt();
      
      vertices = new TreeMap<String, Integer>();
   
      for(int x = 0; x < size; x++)
      {
         vertices.put(sc.next(), x);
      }
   }
   public void readGrid(String fileName) throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File(fileName));
      int size = sc.nextInt();
      int check = 0;
      for(int r = 0; r < size; r++)
         for(int c = 0; c < size; c++)
         {
            check = sc.nextInt();
               grid[r][c] = check;
         }
   }
   public void displayVertices()
   {
      for(String str : vertices.keySet())
         System.out.println(vertices.get(str) + "-" + str);
   
   
   }
   public void allPairsReachability()   // Warshall's Algorithm
   {
   
      int[][]reach = new int[grid.length][grid.length];
      for(int i = 0; i < grid.length; i++)
         for(int r = 0; r < grid[0].length; r++)
            for(int c = 0; c < grid.length; c++)
            {
            
               if(isEdge(r, i) && isEdge(i, c))
                  addEdge(r, c);
            
            
            }
   
   
   }
   
   public List<String> getReachables(String from)  //Warshall extension
   {
      List<String> toRet = new ArrayList<>();
      int number = vertices.get(from);
      for(int x = 0; x < grid.length; x++)
      {
         if(isEdge(number, x))
            for(String str : vertices.keySet())
               if(vertices.get(str) == x)
                  toRet.add(str);
      }
   
      return toRet;
   }
   
   
   
   
   
   
   public int[][] getGrid()
   {
      return grid;
   }
   public void addEdge(int source, int target)
   {
      if(source > grid.length-1 || target > grid[0].length-1 || source < 0 || target < 0)
         return;
   
      grid[source][target] = 1;
   }
   public void removeEdge(int source, int target)
   {
      if(source > grid.length-1 || target > grid[0].length-1 || source < 0 || target < 0)
         return;
   
      grid[source][target] = 0;
   }
   public boolean isEdge(int from, int to)
   { 
      if(from > grid.length-1 || to > grid[0].length-1 || from < 0 || to < 0)
         return false; 
     
      if(grid[from][to] == 1)
         return true;
      else
         return false;
   }
   public String toString()  //returns the grid as a String
   {
      String toRet = "";
      for(int r = 0; r < grid.length; r++)
      {
         for(int c = 0; c < grid.length; c++)
         {
            toRet += grid[r][c] + " ";
         }
         toRet += "\n";
      }
    
      return toRet;
    
   }
   public int edgeCount()
   {
      int count = 0;
      for(int r = 0; r < grid.length; r++)
         for(int c = 0; c < grid.length; c++)
            if(grid[r][c] == 1)
               count++;
   
      return count;
   }
   public List<Integer> getNeighbors(int source)
   {
      List<Integer> toRet = new ArrayList<Integer>();
      for(int c = 0; c < grid[0].length; c++)
         if(grid[source][c] == 1)
            toRet.add(c);
   
      return toRet;
   }
   
}
