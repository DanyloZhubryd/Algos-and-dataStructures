package main.java.ua.lviv.iot;

import java.util.*;


public class Graph
{
    private  int numberOfVertices;   // No. of vertices
    private final Map<Integer, LinkedList<Integer>> vertexToNeighbours; /* Map containing edges:
     key: Vertex, value: list of vertex's neighbours */

    public Graph() {
        vertexToNeighbours = new HashMap<>();
        numberOfVertices = 0;
    }

    public int BFS(int sourceVertex)
    {
        // Empty graph
        try {
            if (vertexToNeighbours.isEmpty()){
                throw new RuntimeException();
            }
        } catch (RuntimeException exception) {
            System.out.println("Graph is empty");
            System.exit(1);
        }
        // No such vertex in graph
        try {
            if (!vertexToNeighbours.containsKey(sourceVertex)){
                throw new RuntimeException();
            }
        } catch (RuntimeException exception) {
            System.out.println("Graph doesn't contain vertex " + sourceVertex
                    + ". Rewrite file values.");
            System.exit(1);
        }

        boolean[] visited = new boolean[numberOfVertices];
        int[] distances = new int[numberOfVertices];
        LinkedList<Integer> bfsQueue = new LinkedList<>();

        visited[sourceVertex]=true;
        bfsQueue.add(sourceVertex);

        while (!bfsQueue.isEmpty()) {
            int currentVertex = bfsQueue.poll();
            for (int neighbour : vertexToNeighbours.get(currentVertex)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    bfsQueue.add(neighbour);
                    distances[neighbour] = distances[currentVertex]+1;
                }
            }
        }
        return max(distances);
    }

    public static int max(int[] inputArray){
        int max = inputArray[0];
        for (int i : inputArray) {
            if (i>max) { max = i; }
        }
        return max;
    }

    public void addEdge(int startVertex,int endVertex)
    {
        if (!vertexToNeighbours.containsKey(startVertex)) {
            vertexToNeighbours.put(startVertex, new LinkedList<Integer>());
            numberOfVertices++;
        }
        if (!vertexToNeighbours.containsKey(endVertex)){
            vertexToNeighbours.put(endVertex, new LinkedList<Integer>());
            numberOfVertices++;
        }
        if (!vertexToNeighbours.get(startVertex).contains(endVertex)) {
            vertexToNeighbours.get(startVertex).add(endVertex);
        }
    }
}
