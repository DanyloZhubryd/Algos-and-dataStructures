package main.java.ua.lviv.iot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Graph {
    Integer numberOfVertices;
    HashMap<Integer, LinkedList<Edge>> vertexToEdges;

    public Integer sourceVertex;
    public Integer sinkVertex;

    private class Edge{
        public Integer neighbourVertex;
        public Integer capacity;

        private Edge(Integer neighbourVertex, Integer capacity) {
            this.neighbourVertex = neighbourVertex;
            this.capacity = capacity;
        }
    }

    public Graph() {
        vertexToEdges = new HashMap<Integer, LinkedList<Edge>>();
        numberOfVertices = 0;
    }

    public Graph(String fileName) {
        this();
        try{
            BufferedReader reader = new BufferedReader(
                    new FileReader("src/main/resources/" + fileName));
            String line = reader.readLine();
            int[] lineToInt = convertLineToIntArray(line);
            int numberOfEdges = lineToInt[0];
            this.sourceVertex = lineToInt[1];
            this.sinkVertex = lineToInt[2];

            for (int i = 0; i<numberOfEdges;i++){
                line = reader.readLine();
                lineToInt = convertLineToIntArray(line);
                addEdge(lineToInt[0], lineToInt[1], lineToInt[2]);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    // Residual graph constructor
    private Graph(HashMap<Integer, LinkedList<Edge>> initialVertexToEdges,
                  Integer initialNumberOfVertices){
        this.vertexToEdges = new HashMap<>(initialVertexToEdges);
        this.numberOfVertices = initialNumberOfVertices;
        for (Integer currentVertex : vertexToEdges.keySet()){
            for (Edge neighbour : vertexToEdges.get(currentVertex)){
                addMirrorEdgeToResidualGraph(currentVertex, neighbour.neighbourVertex);
            }
        }
    }



    // if it finds a path from source to sink in residual graph:
    // returns true and updates path
    private boolean bfs(Integer sourceVertex, Integer sinkVertex, int[] path) {
        boolean[] visited = new boolean[numberOfVertices];
        LinkedList<Integer> bfsQueue = new LinkedList<Integer>();
        bfsQueue.add(sourceVertex);
        visited[sourceVertex] = true;
        path[sourceVertex] = -1;

        // BFS loop
        while (!bfsQueue.isEmpty()) {
            int currentVertex = bfsQueue.poll();

            for (Edge neighbour : vertexToEdges.get(currentVertex)){
                if (!visited[neighbour.neighbourVertex]
                        && neighbour.capacity > 0) {
                    // if we reached the sink then no need to continue bfs
                    if (neighbour.neighbourVertex.equals(sinkVertex)){
                        path[neighbour.neighbourVertex] = currentVertex;
                        return true;
                    }
                    visited[neighbour.neighbourVertex] = true;
                    bfsQueue.add(neighbour.neighbourVertex);
                    path[neighbour.neighbourVertex] = currentVertex;
                }
            }
        }
        // if we didn't find a path
        return false;
    }

    // returns max flow from source to sink in this graph
    public Integer fordFulkerson(Integer sourceVertex, Integer sinkVertex){
        // A graph, where each edge instead of capacity has residual capacity
        // and each edge has a mirror one (with 0 capacity if it doesn't exist in initial graph
        Graph residualGraph = new Graph(vertexToEdges, numberOfVertices);
        // Here we store a BFS path
        int[] path = new int[numberOfVertices];
        Integer maxFlow = 0;

        while (residualGraph.bfs(sourceVertex, sinkVertex, path)){
            int pathFlow = Integer.MAX_VALUE;
            // find the minimal capacity in the path
            for (Integer edgeEnd = sinkVertex;
                 !edgeEnd.equals(sourceVertex);
                 edgeEnd = path[edgeEnd]) {
                Integer edgeStart = path[edgeEnd];
                pathFlow = Math.min(pathFlow,
                        getEdge(edgeStart, edgeEnd).capacity);
            }
            // add path flow to each edge in path
            for (Integer edgeEnd = sinkVertex;
                 !edgeEnd.equals(sourceVertex);
                 edgeEnd = path[edgeEnd]) {
                Integer edgeStart = path[edgeEnd];
                residualGraph.getEdge(edgeStart, edgeEnd).capacity -= pathFlow;
                residualGraph.getEdge(edgeEnd, edgeStart).capacity += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    private Edge getEdge(Integer startVertex, Integer endVertex) {
        return vertexToEdges.get(startVertex).stream()
                .filter(edge -> edge.neighbourVertex.equals(endVertex))
                .findFirst()
                .get();
    }

    private boolean edgeNotExists(Integer startVertex, Integer endVertex) {
        if (!vertexToEdges.containsKey(startVertex)) {
            return true;
        }
        if (!vertexToEdges.containsKey(endVertex)){
            return true;
        }
        return vertexToEdges.get(startVertex).stream().
                noneMatch(edge -> edge.neighbourVertex.equals(endVertex));
    }

    public void addEdge(Integer startVertex, Integer endVertex, Integer capacity) {
        if (!vertexToEdges.containsKey(startVertex)) {
            vertexToEdges.put(startVertex, new LinkedList<Edge>());
            numberOfVertices++;
        }
        if (!vertexToEdges.containsKey(endVertex)){
            vertexToEdges.put(endVertex, new LinkedList<Edge>());
            numberOfVertices++;
        }
        if (vertexToEdges.get(startVertex).stream().
                noneMatch(edge -> edge.neighbourVertex.equals(endVertex))) {
            vertexToEdges.get(startVertex).add(new Edge(endVertex, capacity));
        }
    }

    private void addMirrorEdgeToResidualGraph(Integer startVertex, Integer endVertex) {
        // we should only add a mirror Edge with 0 capacity if it doesn't exist
        // if it exists then we keep its capacity as it is
        if (edgeNotExists(endVertex, startVertex)) {
            addEdge(endVertex, startVertex, 0);
        }
    }

    // for file reading
    public static int[] convertLineToIntArray(String line){
        return Stream.of(line.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
