// Name:   Om Gole
// Date:   5/12/2022
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/**************** Graphs 3: EdgeList *****/
interface VertexInterface
{
   public String getName();
   public HashSet<Vertex> getAdjacencies();
   
   /*
     postcondition: if the set already contains a vertex with the same name, the vertex v is not added
                    this method should be O(1)
   */
   public void addAdjacent(Vertex v);
   /*
     postcondition:  returns as a string one vertex with its adjacencies, without commas.
                     for example, D [C A]
     */
   public String toString(); 
 
} 
 
/*************************************************************/
class Vertex implements VertexInterface, Comparable<Vertex> //2 vertexes are equal if and only if they have the same name
{
   private final String name;
   private HashSet<Vertex> adjacencies;
  /* enter your code here  */
   public Vertex (String str)
   {
      name = str;
   
      adjacencies = new HashSet<Vertex>();
   }
  
   public String getName()
   {
      return name;
   }
   public HashSet<Vertex> getAdjacencies()
   {
      return adjacencies;
   }
   public void addAdjacent(Vertex v)
   {
      adjacencies.add(v);
   }
   public boolean equals(Vertex v)
   {
      if(name.equals(v.getName()))
         return true;
      return false;
   }
   public int hashCode()
   {
      return name.hashCode();
   }
   public int compareTo(Vertex v)
   {
      if(name.equals(v.getName()))
         return 0;
      else
         return name.compareTo(v.getName());
   }
   public String toString()
   {
      String toRet = name + " [";
      int count = 0;
      for(Vertex v : adjacencies)
      {
         if(count == 0)
         {
            toRet += v.getName();
            count++;
         }
         else
            toRet += " " + v.getName();
      
      }
      toRet += "]";
      return toRet;
   }
  
}   

/*************************************************************/
interface AdjListInterface 
{
   public Set<Vertex> getVertices();
   public Vertex getVertex(String vName);
   public Map<String, Vertex> getVertexMap();  //this is just for codepost testing
   
   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(log n)
   */
   public void addVertex(String vName);
   
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(1)
   */
   public void addEdge(String source, String target); 
   
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString(); 

}

  
/********************** Graphs 4: DFS and BFS *****/
interface DFS_BFS
{
   public List<Vertex> depthFirstSearch(String name);
   public List<Vertex> breadthFirstSearch(String name);
   /*   extra credit methods */
   public List<Vertex> depthFirstRecur(String name);
   public List<Vertex> depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/****************** Graphs 5: Edgelist with Cities *****/
interface EdgeListWithCities
{
   public void readData(String cities, String edges) throws FileNotFoundException;
   public int edgeCount();
   public int vertexCount();
   public boolean isReachable(String source, String target);
   public boolean isStronglyConnected(); //return true if every vertex is reachable from every 
                                          //other vertex, otherwise false 
}


/*************  start the Adjacency-List graph  *********/
public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, Vertex> vertexMap = new TreeMap<String, Vertex>();
      
   /* constructor is not needed because of the instantiation above */
  
   /* enter your code here  */
   public void readData(String cities, String edges) throws FileNotFoundException
   {
   Scanner city = new Scanner(new File(cities));
   Scanner edge = new Scanner(new File(edges));
   while(city.hasNext())
   addVertex(city.next());
   
   while(edge.hasNext())
   addEdge(edge.next(), edge.next());
   
   
   
   }

   public int edgeCount()
   {
      int edge = 0;
      for(String str : vertexMap.keySet())
         for(Vertex v : vertexMap.get(str).getAdjacencies())
            edge++;
   
      return edge;
   }
   public int vertexCount()
   {
      return vertexMap.keySet().size();
   }
   public boolean isReachable(String source, String target)
   {
      List<Vertex> length = depthFirstSearch(source);
      return length.contains(getVertex(target));
   }
   public boolean isStronglyConnected()
   {
      for(String str : vertexMap.keySet())
         if(depthFirstSearch(vertexMap.get(str).getName()).size() < vertexMap.size() - 1)
            return false;
   
      return true;
   }
   
   
   
   public List<Vertex> depthFirstRecur(String name)
   {
   //CHEESE
      Vertex v = getVertex(name);
      List<Vertex> toRet = new ArrayList<>();
      Stack<Vertex> stk = new Stack<>();
   //Remove
      Set<Vertex> vl = v.getAdjacencies();
      for(Vertex vert : vl)
         if(!toRet.contains(vert))
            stk.push(vert);
   
   //stk.push(v);
   
      while(!stk.isEmpty())
      {
         Vertex temp = stk.pop();
         if(!toRet.contains(temp))
            toRet.add(temp);
         Set<Vertex> templ = temp.getAdjacencies();
         for(Vertex vert : templ)
            if(!toRet.contains(vert))
               stk.push(vert);
      
      }
   
      return toRet;
   //CHEESE
   }
 
   public List<Vertex> depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable)
   {
      List<Vertex> rand = new ArrayList<Vertex>();
      return rand;
   }
 
 
 
 
 
   
   public List<Vertex> depthFirstSearch(String name)
   {
      Vertex v = getVertex(name);
      List<Vertex> toRet = new ArrayList<>();
      Stack<Vertex> stk = new Stack<>();
   //Remove
      Set<Vertex> vl = v.getAdjacencies();
      for(Vertex vert : vl)
         if(!toRet.contains(vert))
            stk.push(vert);
   
   //stk.push(v);
   
      while(!stk.isEmpty())
      {
         Vertex temp = stk.pop();
         if(!toRet.contains(temp))
            toRet.add(temp);
         Set<Vertex> templ = temp.getAdjacencies();
         for(Vertex vert : templ)
            if(!toRet.contains(vert))
               stk.push(vert);
      
      }
   
      return toRet;
   }
   public List<Vertex> breadthFirstSearch(String name) 
   {
      Vertex v = getVertex(name);
      List<Vertex> toRet = new ArrayList<>();
      Queue<Vertex> q = new LinkedList<Vertex>();
   //Remove
      Set<Vertex> vl = v.getAdjacencies();
      for(Vertex vert : vl)
         if(!toRet.contains(vert))
            q.add(vert);
    
   
   
   //q.add(v);
      while(!q.isEmpty())
      {
         Vertex temp = q.remove();
         if(!toRet.contains(temp))
            toRet.add(temp);
      
         Set<Vertex> templ = temp.getAdjacencies();
         for(Vertex vert : templ)
            if(!toRet.contains(vert))
               q.add(vert);
      }
    
      return toRet;
    
   }  
   
   
   public Set<Vertex> getVertices()
   {
      Set<Vertex> toRet = new TreeSet<Vertex>();
      for(String k : vertexMap.keySet())
      {
         toRet.add(vertexMap.get(k));
      }
      return toRet;
   }
   public Vertex getVertex(String vName)
   {
      return vertexMap.get(vName);
   }
   public Map<String, Vertex> getVertexMap()  
   {
      return vertexMap;
   }
   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(log n)
   */
   public void addVertex(String vName)
   {
      if(vertexMap.containsKey(vName))
         return;
   
      vertexMap.put(vName, new Vertex(vName));
   }
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(1)
   */
   public void addEdge(String source, String target)
   {
      vertexMap.get(source).getAdjacencies().add(vertexMap.get(target));
   }
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString() 
   {
      String toRet = "";
      for(String key : vertexMap.keySet())
      {
         toRet += vertexMap.get(key).toString();
         toRet += "\n";
      }
      return toRet;
   }
 
 
 
}


