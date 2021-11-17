package test.java.ua.lviv.iot;

import static org.junit.Assert.assertEquals;

import main.java.ua.lviv.iot.Graph;
import org.junit.Test;

public class TestMaxFlow {
    @Test
    public void testFordFulkerson() {
        Graph graph = new Graph("input.in");
        Integer actualResult = graph.fordFulkerson(graph.sourceVertex,graph.sinkVertex);
        System.out.println(actualResult);
        Integer expectedResult = 5;
        assertEquals(expectedResult, actualResult);
    }
}
