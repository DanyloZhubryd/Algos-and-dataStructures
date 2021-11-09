package main.java.ua.lviv.iot;

import java.util.*;


public class Graph
{
    private final int numberOfVertices;   // No. of vertices
    private final LinkedList<Integer>[] neighbours; //Adjacency Lists

    public Graph(int numberOfVertices)
    {
        this.numberOfVertices = numberOfVertices;
        neighbours = new LinkedList[numberOfVertices];
        for (int i = 0; i< numberOfVertices; ++i) {
            neighbours[i] = new LinkedList<Integer>();
        }
    }

    public int BFS(int sourceVertex)
    {
        boolean[] visited = new boolean[numberOfVertices];
        LinkedList<Integer> bfsQueue = new LinkedList<Integer>();
        int[] distances = new int[neighbours.length];

        visited[sourceVertex]=true;
        bfsQueue.add(sourceVertex);

        while (!bfsQueue.isEmpty()) {
            int currentVertex = bfsQueue.poll();
            for (int neighbour : neighbours[currentVertex]) {
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
        neighbours[startVertex].add(endVertex);
    }
}
