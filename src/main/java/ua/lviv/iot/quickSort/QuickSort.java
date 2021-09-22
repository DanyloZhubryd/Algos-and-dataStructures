package ua.lviv.iot.quickSort;

import java.util.List;
import java.util.Random;

public class QuickSort {
	public static Integer comparisonCounter = 0;
	public static Integer swapCounter = 0;

	public static void sorted(List<Integer> listToSort, Boolean isAsc) {
		sorted(listToSort, isAsc, 0, listToSort.size() - 1);
	}

	private  static List<Integer> sorted(List<Integer> listToSort, Boolean isAsc, Integer low, Integer high) {
		if (low < high) {
			Integer pivot = partition(listToSort, isAsc, low, high);
			sorted(listToSort, isAsc, low, pivot - 1);
			sorted(listToSort, isAsc, pivot + 1, high);
		}
		return listToSort;
	}

	private static void swap(List<Integer> listToSort, Integer low, Integer high) {
		if (low == high)
			return;
		Integer temp = listToSort.get(high);
		listToSort.set(high, listToSort.get(low));
		listToSort.set(low, temp);
		swapCounter++;
	}

	private static Integer getPivot(Integer low, Integer high) {
		Random random = new Random();
		return random.nextInt(high - low + 1) + low;
	}

	private static Integer partition(List<Integer> listToSort, Boolean isAsc, Integer low, Integer high) {
		swap(listToSort, low, getPivot(low, high));
		Integer border = low + 1;
		if (isAsc) {
			for (Integer i = border; i <= high; i++) {
				if (listToSort.get(i) < listToSort.get(low)) {
					swap(listToSort, i, border++);
				}
				comparisonCounter++;
			}
		} else {
			for (Integer i = border; i <= high; i++) {
				if (listToSort.get(i) > listToSort.get(low)) {
					swap(listToSort, i, border++);
				}
				comparisonCounter++;
			}
		}
		swap(listToSort, low, border - 1);
		return border - 1;
	}

	public static Integer getComparisonCounter() {
		Integer temp = comparisonCounter;
		comparisonCounter = 0;
		return temp;
	}
	
	public static Integer getSwapCounter() {
		Integer temp = swapCounter;
		swapCounter = 0;
		return temp;
	}
}
