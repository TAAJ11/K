import java.util.*;

class Graph {
   public int V;
   public List<List<Edge>> adj;
   public Graph(int V) {
       this.V = V;
       adj = new ArrayList<>(V);
       for (int i = 0; i < V; i++) {
           adj.add(new ArrayList<>());
       }
   }
   static class Edge {
       int src, dest, weight;
       public Edge(int src, int dest, int weight) {
           this.src = src;
           this.dest = dest;
           this.weight = weight;
       }
   }
   public void addEdge(int u, int v, int weight) {
       Edge edge1 = new Edge(u, v, weight);
       Edge edge2 = new Edge(v, u, weight);
       adj.get(u).add(edge1);
       adj.get(v).add(edge2);
   }
   public int primMST() {
       int[] key = new int[V];
       int[] parent = new int[V];
       boolean[] mstSet = new boolean[V];
       Arrays.fill(key, Integer.MAX_VALUE);
       Arrays.fill(parent, -1);
       key[0] = 0;
       PriorityQueue<Node> pq = new PriorityQueue<>(V, Comparator.comparingInt(node -> node.key));
       pq.offer(new Node(0, 0));
       while (!pq.isEmpty()) {
           int u = pq.poll().vertex;
           mstSet[u] = true;
           for (Edge e : adj.get(u)) {
               int v = e.dest;
               int weight = e.weight;
               if (!mstSet[v] && weight < key[v]) {
                   key[v] = weight;
                   parent[v] = u;
                   pq.offer(new Node(v, key[v]));
               }
           }
       }
       int totalWeight = 0;
       for (int i = 1; i < V; i++) {
           totalWeight += key[i];
       }
       return totalWeight;
   }
   static class Node {
       int vertex, key;
       public Node(int vertex, int key) {
           this.vertex = vertex;
           this.key = key;
       }
   }
}
public class ADS3 {
   public static void main(String[] args) {
       int V = 5;
       Graph graph = new Graph(V);
       graph.addEdge(0, 1, 2);
       graph.addEdge(0, 3, 6);
       graph.addEdge(1, 2, 3);
       graph.addEdge(1, 3, 8);
       graph.addEdge(1, 4, 5);
       graph.addEdge(2, 4, 7);
       graph.addEdge(3, 4, 9);
       int minWeight = graph.primMST();
       System.out.println("Total weight of minimum spanning tree: " + minWeight);
   }
}