package ua.lviv.iot.quick_sort;

import java.io.IOException;
import java.util.LinkedList;

import ua.lviv.iot.quick_sort.QuickSort;

public class App {
	public static void main(String[] args) {
		try {
			if (args.length == 0) {
				throw new IOException();
			}
		} catch (IOException e) {
			System.out.println("Array of integers is missing!");
			System.exit(1);
		}
		try {
			if (args[0] == "asc" || args[0] == "Asc") {
				Boolean isAsc = true;
			} else if (args[0] == "desc" || args[0] == "Desc") {
				Boolean isAsc = false;
			} else {
				throw new IOException();
			}
		} catch (IOException e) {
			System.out.println("Firstly type Asc or asc for ascending sort or " + "Desc or desc for descending sort");
			System.exit(1);
		}
		LinkedList<Integer> listToSort = new LinkedList<>();
		for (Integer i = 1; i < args.length; i++) {
			try {
				Integer strToCast = Integer.parseInt(args[i]);
				listToSort.add(strToCast);
			} catch (NumberFormatException e) {
				System.out.println("Element " + i + "is not an integer!");
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		QuickSort.sorted(listToSort, isAsc);
		
	}
}
