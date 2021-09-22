package ua.lviv.iot.quickSort;

import java.io.IOException;
import java.util.LinkedList;

import ua.lviv.iot.quickSort.QuickSort;

public class App {
	public static Boolean convertToBoolean(String toConvert) throws IOException {
		if ((toConvert.equals("asc")) || (toConvert.equals("Asc"))) {
			return true;
		} else if ((toConvert.equals("desc")) || (toConvert.equals("Desc"))) {
			return false;
		} else {
			throw new IOException();
		}
	}
	
	public static void main(String[] args) {
		try {
			if (args.length == 0) {
				throw new IOException();
			}
		} catch (IOException e) {
			System.out.println("Array of integers is missing!");
			System.exit(1);
		}
		Boolean isAsc = true;
		try {
			isAsc = convertToBoolean(args[0]);
		} catch (IOException e) {
			System.out.println("Firstly type Asc or asc for ascending sort or " 
								+ "Desc or desc for descending sort");
			System.exit(1);
		}
		LinkedList<Integer> listToSort = new LinkedList<>();
		for (Integer i = 1; i < args.length; i++) {
			try {
				Integer strToCast = Integer.parseInt(args[i]);
				listToSort.add(strToCast);
			} catch (NumberFormatException e) {
				System.out.println("Element " + i + " is not an integer!");
				System.exit(1);
			}
		}
		Long startSort = System.nanoTime();
		QuickSort.sorted(listToSort, isAsc);
		Long endSort = System.nanoTime();
		Long sortDuration = (endSort - startSort)/1000;
		listToSort.stream().forEach(element -> System.out.print(element + " "));
		System.out.println("\nComparisons: " + QuickSort.getComparisonCounter());
		System.out.println("Swaps: " + QuickSort.getSwapCounter());
		System.out.println("Sort duration " + sortDuration + " mks");
	}
}
