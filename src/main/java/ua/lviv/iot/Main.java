package ua.lviv.iot;

import java.io.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args)
    {
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("src/main/resources/input.txt"));
            String line = reader.readLine();

            int[] lineToInt = convertLineToIntArray(line);
            int numberOfEdges = lineToInt[0];
            int sourceVertex = lineToInt[1];
            Graph graph = new Graph();

            for (int i = 0; i < numberOfEdges; i++) {
                line = reader.readLine();
                lineToInt = convertLineToIntArray(line);
                graph.addEdge(lineToInt[0], lineToInt[1]);
            }

            writeResultToFile(graph.BFS(sourceVertex));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static int[] convertLineToIntArray(String line){
        return Stream.of(line.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void writeResultToFile(int result) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/output.out"));
            writer.write("The farthest vertex is " + result + " steps away");
            writer.close();
        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
}
