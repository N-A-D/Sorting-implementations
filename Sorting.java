import java.util.*;
import java.lang.*;

public class Sorting {
	/*
		Practice implementations for some sorting algorithms
	*/
	
	private static Random random = new Random();
	private static final int LISTSIZE = 75000;
	private static final int MAXNUM = 10000;	
	
	public static void bubbleSort(int[] array) {
		for (int i = 0; i < array.length-1; i++) {
			boolean swapped = false;
			for (int j = 0; j < array.length - 1; j++) {
				if (array[j] > array[j + 1]) {
					swap(array, j, j+1);
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
	}
	
	public static void selectionSort(int[] array) {
		int min = 0;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[min] > array[j]) {
					min = j;
				}
			}
			swap(array, i, min);
		} 
	}
	
	public static void insertionSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int sortedIndex = i;
			int value = array[i];
			for (int j = sortedIndex; j > 0 && array[sortedIndex - 1] > value; j--) {
				array[sortedIndex] = array[sortedIndex - 1];
				sortedIndex--;
			}
			array[sortedIndex] = value;
		}
	}
	
	public static void mergeSort(int[] array) {
		if (array.length <= 1) {
			return;
		}
		else {
			int middle = array.length / 2;
			int[] left = getSubArray(array, 0, middle);
			int[] right = getSubArray(array, middle, array.length-1);
			mergeSort(left);
			mergeSort(right);
			merge(left, right, array);
		}
	}
	
	public static void merge(int[] left, int[] right, int[] array) {
		int arrayIndex = 0;
		int leftIndex = 0; int leftLength = left.length;
		int rightIndex = 0; int rightLength = right.length;
		
		while (leftIndex < leftLength && rightIndex < rightLength) {
			if (left[leftIndex] < right[rightIndex]) {
				array[arrayIndex++] = left[leftIndex++];
			}
			else {
				array[arrayIndex++] = right[rightIndex++];
			}
		}
		while (leftIndex < leftLength) {
			array[arrayIndex++] = left[leftIndex++];
		}
		while (rightIndex < rightLength) {
			array[arrayIndex++] = right[rightIndex++];
		}
	}
	
	public static int[] getSubArray(int[] array, int low, int high) {
		int[] result = new int[high - low];
		for (int i = low; i < high; i++) {
			result[i - low] = array[i];
		}
		return result;
	}
	
	public static void quickSort(int[] array) {
		qsort(array, 0, array.length-1);
	}
	
	public static void qsort(int[] array, int left, int right) {
		if (left >= right) {
			return;
		}
		else {
			int pivot = array[right];
			int part = partition(array, left, right, pivot);
			qsort(array, left, part-1);
			qsort(array, part + 1, right);
		}
	}
	
	public static int partition(int[] array, int left, int right, int pivot) {
		int leftPointer = left - 1;
		int rightPointer = right;
		
		while (true) {
			while (array[++leftPointer] < pivot) {}
			while (rightPointer > 0 && array[--rightPointer] > pivot) {}
			if (leftPointer >= rightPointer) {
				break;
			}
			else {
				swap(array, leftPointer, rightPointer);
			}
		}
		swap(array, leftPointer, right);
		return leftPointer;
	}
	
	public static void swap(int[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
	
	public static int[] generateUnsortedList() {
		int[] list = new int[LISTSIZE];
		for (int i = 0; i < list.length; i++) {
			list[i] = random.nextInt(MAXNUM) + 1;
		}
		return list;
	}
	
	public static void main(String[] args) {
		int[] list = generateUnsortedList();
		int[] list1 = Arrays.copyOf(list, list.length);
		int[] list2 = Arrays.copyOf(list, list.length);
		int[] list3 = Arrays.copyOf(list, list.length);
		int[] list4 = Arrays.copyOf(list, list.length);
		int[] list5 = Arrays.copyOf(list, list.length);
		
		
		long start = System.currentTimeMillis();
		bubbleSort(list1);
		long end = System.currentTimeMillis();
		long elapsedTime = (end - start);
		System.out.println("Time elapsed for bubble sort: " + elapsedTime);
		
		start = System.currentTimeMillis();
		quickSort(list2);
		end = System.currentTimeMillis();
		elapsedTime = (end - start);
		System.out.println("Time elapsed for insertion sort: " + elapsedTime);
		
		start = System.currentTimeMillis();
		selectionSort(list3);
		end = System.currentTimeMillis();
		elapsedTime = (end - start);
		System.out.println("Time elapsed for selection sort: " + elapsedTime);
		
		start = System.currentTimeMillis();
		quickSort(list4);
		end = System.currentTimeMillis();
		elapsedTime = (end - start);
		System.out.println("Time elapsed for quick sort: " + elapsedTime);
		
		start = System.currentTimeMillis();
		mergeSort(list5);
		end = System.currentTimeMillis();
		elapsedTime = (end - start);
		System.out.println("Time elapsed for merge sort: " + elapsedTime);
		
	}
}