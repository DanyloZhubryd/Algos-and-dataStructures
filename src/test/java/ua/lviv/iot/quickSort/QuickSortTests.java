package ua.lviv.iot.quickSort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ua.lviv.iot.quickSort.QuickSort;

public class QuickSortTests {
	List<Integer> arrayToList;
	Integer[] sortedArray;
	LinkedList<Integer> listToSort;
	
	@BeforeAll
	void setUp() {
		Integer[] arrayToSort = new Integer[] {1, 6, -4, -8, 13, 5, -2, -12, 15};
		arrayToList = Arrays.asList(arrayToSort);
		listToSort = new LinkedList<>(arrayToList);
	}
	
	@Test
	@DisplayName("Testing ascending sort")
	void testAscendingSort() {
		sortedArray = new Integer[] {-12, -8, -4, -2, 1, 5, 6, 13, 15};
		arrayToList = Arrays.asList(sortedArray);
		LinkedList<Integer> expectedResult = new LinkedList<Integer>(arrayToList);
		LinkedList<Integer> actualResult= new LinkedList<Integer>(listToSort);
		QuickSort.sorted(actualResult, true);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	@DisplayName("Testing descending sort")
	void testDescendingSort() {
		sortedArray = new Integer[] {15, 13, 6, 5, 1, -2, -4, -8, -12};
		arrayToList = Arrays.asList(sortedArray);
		LinkedList<Integer> expectedResult = new LinkedList<Integer>(arrayToList);
		LinkedList<Integer> actualResult= new LinkedList<Integer>(listToSort);
		QuickSort.sorted(actualResult, false);
		assertEquals(expectedResult, actualResult);
	}
}
