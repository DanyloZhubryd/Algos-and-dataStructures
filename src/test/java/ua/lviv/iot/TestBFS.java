package test.java.ua.lviv.iot;

import main.java.ua.lviv.iot.Graph;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestBFS {
    @Test
    public void testBFSCommonGraph() {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        assertEquals(2, graph.BFS(2));
    }
    @Test
    public void testBFSOneElemGraph() {
        Graph graph = new Graph();
        graph.addEdge(0, 0);
        assertEquals(0 ,graph.BFS(0));
    }
}
