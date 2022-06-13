package ua.lviv.iot.quickSort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import ua.lviv.iot.quickSort.QuickSort;


@TestInstance(Lifecycle.PER_CLASS)
public class QuickSortTests {
	List<Integer> arrayToList;
	LinkedList<Integer> ascSortedList;
	LinkedList<Integer> descSortedList;
	LinkedList<Integer> listToSort;
	
	@BeforeAll
	void setUp() {
		//Not sorted list
		Integer[] arrayToSort = new Integer[] {1, 6, -4, -8, 13, 5, -2, -12, 15};
		arrayToList = Arrays.asList(arrayToSort);
		listToSort = new LinkedList<>(arrayToList);
		//Ascending sorted list
		Integer[] ascSortedArray = new Integer[] {-12, -8, -4, -2, 1, 5, 6, 13, 15};
		arrayToList = Arrays.asList(ascSortedArray);
		ascSortedList = new LinkedList<Integer>(arrayToList);
		//Descending sorted list
		Integer[] descSortedArray = new Integer[] {15, 13, 6, 5, 1, -2, -4, -8, -12};
		arrayToList = Arrays.asList(descSortedArray);
		descSortedList = new LinkedList<Integer>(arrayToList);
	}
	
	@Test
	@DisplayName("Testing ascending sort")
	void testAscendingSort() {
		LinkedList<Integer> expectedResult = new LinkedList<>(ascSortedList);
		LinkedList<Integer> actualResult = new LinkedList<Integer>(listToSort);
		QuickSort.sorted(actualResult, true);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	@DisplayName("Testing descending sort")
	void testDescendingSort() { 
		LinkedList<Integer> expectedResult = new LinkedList<>(descSortedList);
		LinkedList<Integer> actualResult = new LinkedList<Integer>(listToSort);
		QuickSort.sorted(actualResult, false);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	@DisplayName("Testing ascending sort of ascending sorted list")
	void testAscSortOfAscSortedList() {
		LinkedList<Integer> expectedResult = new LinkedList<>(ascSortedList);
		LinkedList<Integer> actualResult = new LinkedList<>(ascSortedList);
		QuickSort.sorted(actualResult, true);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	@DisplayName("Testing descending sort of descending sorted list")
	void testDescSortOfDescSortedList() {
		LinkedList<Integer> expectedResult = new LinkedList<>(descSortedList);
		LinkedList<Integer> actualResult = new LinkedList<>(descSortedList);
		QuickSort.sorted(actualResult, false);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	@DisplayName("Testing ascending sort of descending sorted list")
	void testAscSortOfDescSortedList() {
		LinkedList<Integer> expectedResult = new LinkedList<>(ascSortedList);
		LinkedList<Integer> actualResult = new LinkedList<>(descSortedList);
		QuickSort.sorted(actualResult, true);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	@DisplayName("Testing descending sort of ascending sorted list")
	void testDescSortOfAscSortedList() {
		LinkedList<Integer> expectedResult = new LinkedList<>(descSortedList);
		LinkedList<Integer> actualResult = new LinkedList<>(ascSortedList);
		QuickSort.sorted(actualResult, false);
		assertEquals(expectedResult, actualResult);
	}
}
