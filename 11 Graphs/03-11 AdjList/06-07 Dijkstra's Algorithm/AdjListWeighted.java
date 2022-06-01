// Name:   Om Gole
// Date:   5/23/2022
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces for 
 *              Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */

class Neighbor implements Comparable<Neighbor>
{
   //2 Neighbors are equal if and only if they have the same name
   //implement all methods needed for a HashSet and TreeSet to work with Neighbor objects
   private final wVertex target;
   private final double edgeDistance;
   
   public Neighbor(wVertex t, double d) {
      target = t;
      edgeDistance = d;
   }
   public wVertex getTarget()
   {
      return target;
   }
   public double getDistance()
   {
      return edgeDistance;
   }
   public int compareTo(Neighbor n)
   {
      return target.getName().compareTo(n.getTarget().getName());
   }
   public boolean equals(Neighbor n)
   {
      return target.getName().equals(n.getTarget().getName());
   }
   
   public int hashCode()
   {
      return target.getName().hashCode();
   }
   //add all methods needed for a HashSet and TreeSet to function with Neighbor objects
   //use only target, not distances, since a vertex can't have 2 neighbors that have the same target
   //.........
   
   public String toString()
   {
      return target.getName() + " " + edgeDistance;  
   }
}

 /**************************************************************/
class PQelement implements Comparable<PQelement> { 
//used just for a PQ, contains a wVertex and a distance, also previous that is used for Dijksra 7
//compareTo is using the distanceToVertex to order them such that the PriorityQueue works
//will be used by the priority queue to order by distance
 
   private wVertex vertex;
   private Double distanceToVertex; 
   private wVertex previous; //for Dijkstra 7
      
   public PQelement(wVertex v, double d) {
      vertex = v;
      distanceToVertex = d;
      previous = null;
   }
   
    
   
   //getter and setter methods provided
   public wVertex getVertex() {
      return this.vertex;
   }
   
   public Double getDistanceToVertex() {
      return this.distanceToVertex;
   }
   
   public void setVertex(wVertex v) {
      this.vertex = v;
   }
   
   public void setDistanceToVertex(Double d) {
      this.distanceToVertex = d;
   }   
   
   public int compareTo(PQelement other) {
      //we assume no overflow will happen since distances will not go over the range of int
      return (int)(distanceToVertex - other.distanceToVertex);
   }
   
   public wVertex getPrevious()  //Dijkstra 7
   {
      return this.previous;
   }
   public void setPrevious(wVertex v) //Dijkstra 7
   {
      this.previous = v;
   } 
   
   //implement toString to match the sample output   
   public String toString()
   { 
      String toReturn = "";
      String prev = "";
      if(previous == null)
         prev = "null";
      else
         prev = previous.getName();
      //your code here...
      toReturn += vertex.getName() + " " + distanceToVertex +" Previous: " + prev;
      return toReturn;
   }
}

/********************* wVertexInterface ************************/
interface wVertexInterface 
{
   public String getName();
   
   public Set<Neighbor> getNeighbors();
   
   /*  adds to the neighbors set  
       called at the beginning of the lab*/
   public void addAdjacent(wVertex v, double d); 
     
    /*returns an arraylist of PQelements that store distanceToVertex to another wVertex  */
   public ArrayList<PQelement> getAlDistanceToVertex();
   
   //returns the PQelement that has the vertex equal to v
   public PQelement getPQelement(wVertex v);
   
   /*
   postcondition: returns null if wVertex v is not in the alDistanceToVertex
                  or the distance associated with that wVertex in case there is a PQelement that has v as wVertex
   */
   public Double getDistanceToVertex(wVertex v);
   
   /*
   precondition:  v is not null
   postcondition: - if the alDistanceToVertex has a PQelement that has the wVertex component equal to v
                  it updates the distanceToVertex component to d
                  - if the alDistanceToVertex has no PQelement that has the wVertex component equal to v,
                  then a new PQelement is added to the alDistanceToVertex using v and d   
   */
   public void setDistanceToVertex(wVertex v, double d); 
 
   public String toString();  
 
}

class wVertex implements Comparable<wVertex>, wVertexInterface 
{ 
   public static final double NODISTANCE = Double.POSITIVE_INFINITY; //constant to be used in class
   private final String name;
   private Set<Neighbor> neighbors;  
   private ArrayList<PQelement> alDistanceToVertex; //should have no duplicates, enforced by the getter/setter methods
  
   /* constructor, accessors, modifiers  */ 
   public wVertex(String s)
   {
      name = s;
      neighbors = new TreeSet<Neighbor>();
      alDistanceToVertex = new ArrayList<PQelement>();
   }
   
   /* 2 vertexes are equal if and only if they have the same name
      add all methods needed for a HashSet and TreeSet to function with Neighbor objects
      use only target, not distances, since a vertex can't have 2 neighbors that have the same target   
   */
   
   public String getName()
   {
      return name;
   }
   public Set<Neighbor> getNeighbors()
   {
      return neighbors;
   }
   /*  adds to the neighbors set  
       called at the beginning of the lab*/
   public void addAdjacent(wVertex v, double d) 
   {
      neighbors.add(new Neighbor(v, d));
   }
    /*returns an arraylist of PQelements that store distanceToVertex to another wVertex  */
   public ArrayList<PQelement> getAlDistanceToVertex()
   {
      return alDistanceToVertex;
   }
   //returns the PQelement that has the vertex equal to v
   public PQelement getPQelement(wVertex v)
   {
      for(PQelement pq : alDistanceToVertex)
         if(pq.getVertex().compareTo(v) == 0)
            return pq;
   
      return null;
   }
   /*
   postcondition: returns null if wVertex v is not in the alDistanceToVertex
                  or the distance associated with that wVertex in case there is a PQelement that has v as wVertex
   */
   public Double getDistanceToVertex(wVertex v)
   {
      for(PQelement pq : alDistanceToVertex)
         if(pq.getVertex().compareTo(v) == 0)
            return pq.getDistanceToVertex();
   
      return null;
   }
   /*
   precondition:  v is not null
   postcondition: - if the alDistanceToVertex has a PQelement that has the wVertex component equal to v
                  it updates the distanceToVertex component to d
                  - if the alDistanceToVertex has no PQelement that has the wVertex component equal to v,
                  then a new PQelement is added to the alDistanceToVertex using v and d   
   */
   public void setDistanceToVertex(wVertex v, double d)
   {
      for(PQelement pq : alDistanceToVertex)
         if(pq.getVertex().compareTo(v) == 0)
         {
            pq.setDistanceToVertex(d);
            return;
         }
   
      alDistanceToVertex.add(new PQelement(v,d));
   
   }
   
   public int compareTo(wVertex v)
   {
      return name.compareTo(v.getName());
   }
   
   public String toString()
   { 
      String toReturn = name;
      toReturn += " "+ neighbors;
      toReturn += " List: " + alDistanceToVertex; 
      return toReturn;
   }
}

/*********************   Interface for Graphs 6:  Dijkstra ****************/
interface AdjListWeightedInterface 
{
   public Set<wVertex> getVertices();  
   public Map<String, wVertex> getVertexMap();  //this is just for codepost testing
   public wVertex getVertex(String vName);
   /* 
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(logn)
   */
   public void addVertex(String vName);
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
                     addEdge should work in O(1)
   */   
   public void addEdge(String source, String target, double d);
   public void minimumWeightPath(String vertexName); // Dijstra's algorithm
   public String toString();  
}  

 /***********************  Interface for Graphs 7:  Dijkstra with Cities   */
interface AdjListWeightedInterfaceWithCities 
{       
   public List<String> getShortestPathTo(wVertex vSource, wVertex target);
   public void readData(String vertexNames, String edgeListData) ;
}
 
/****************************************************************/ 
/**************** this is the graph  ****************************/
public class AdjListWeighted implements AdjListWeightedInterface,AdjListWeightedInterfaceWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, wVertex> vertexMap = new TreeMap<String, wVertex>();
   
   /* default constructor -- not needed!  */
  
   /* similar to AdjList, but handles distances (weights) and wVertex*/ 
   
   
   public String toString()
   {
      String strResult = "";
      for(String vName: vertexMap.keySet())
      {
         strResult += vertexMap.get(vName) + "\n"; 
      }
      return strResult;
   }
   
   /*  Graphs 7 has two more methods */
   public List<String> getShortestPathTo(wVertex vSource, wVertex target) //Graphs 7
   {
   List<String> toRet = new ArrayList<>();
   Stack<wVertex> stk = new Stack<wVertex>();
   
   wVertex temp = target;
   while(vSource.getPQelement(temp).getDistanceToVertex() != 0.0 && vSource.getPQelement(temp).getPrevious() != null)
   {
   temp = vSource.getPQelement(temp).getPrevious();
   stk.push(temp);
   }
   
   while(!stk.isEmpty())
   {
   toRet.add(stk.pop().getName());
   
   }
   toRet.add(target.getName());
   
   return toRet;
   
   }
   
  
    
     
   public void readData(String vertexNames, String edgeListData) // Graphs 7 
   {
     /* use a try-catch  */
      try {
         Scanner names = new Scanner(new File(vertexNames));
         Scanner edge = new Scanner(new File(edgeListData));
         int bruh = names.nextInt();
         while(names.hasNext())
            addVertex(names.next());
      
         while(edge.hasNext())
            addEdge(edge.next(), edge.next(), edge.nextDouble());
      
      }
      catch(Exception FileNotFoundException ) {
         System.out.println("Could not find Files");
         return;
      }  
   
   
   
   }
   
   public Set<wVertex> getVertices()
   {
      Set<wVertex> toRet = new TreeSet<wVertex>();
      for(String str : vertexMap.keySet())
         toRet.add(vertexMap.get(str));
   
      return toRet;
   }
   public Map<String, wVertex> getVertexMap()
   {
      return vertexMap;
   }
     //this is just for codepost testing
   public wVertex getVertex(String vName)
   {
      return vertexMap.get(vName);
   }
   /* 
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(logn)
   */
   public void addVertex(String vName)
   {
   
      if(vertexMap.containsKey(vName))
         return;
   
      vertexMap.put(vName, new wVertex(vName));
      
   }
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
                     addEdge should work in O(1)
   */   
   public void addEdge(String source, String target, double d)
   {
      vertexMap.get(source).addAdjacent(vertexMap.get(target), d);
      
     
   }
   
   
   public void minimumWeightPath(String vertexName) //dijkstra
   {
     PriorityQueue<PQelement> pq = new PriorityQueue<>();
   
      wVertex source = getVertex(vertexName);
      for(String key : vertexMap.keySet())
         source.setDistanceToVertex(vertexMap.get(key), Double.POSITIVE_INFINITY);
   
      source.setDistanceToVertex(source, 0);
   
      pq.add(source.getPQelement(source));
      while(!pq.isEmpty())
      {
         PQelement temp = pq.remove();
         for(Neighbor n : temp.getVertex().getNeighbors())
         {
            double dist = n.getDistance() + source.getDistanceToVertex(temp.getVertex());
            if(dist < source.getDistanceToVertex(n.getTarget()))
            {
               PQelement elem = source.getPQelement(n.getTarget());
               elem.setDistanceToVertex(dist);
               elem.setPrevious(temp.getVertex());
               pq.add(elem);
               
            }
         }
      }
   
   
   
   
   
   }
}