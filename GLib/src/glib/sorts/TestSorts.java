package glib.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import glib.util.Utils;

public class TestSorts {
	public static void main(String[] args) {
		int num = 1000000;
		int max = 10000;
		int[] array = new int[num];
		
		for(int i=0 ; i<num ; i++)
			array[i] = (int)(Math.random() * max);
		
		testSort(Arrays.copyOf(array, num), new HeapSort());
		testSort(Arrays.copyOf(array, num), new MergeSort());
		testSort(Arrays.copyOf(array, num), new RadixSort());
		testSort(Arrays.copyOf(array, num), new QuickSort());
		
		testCountingSort(Arrays.copyOf(array, num), 0, max);
		testBucketSort(Arrays.copyOf(array, num), max);
		testBucketSort(Arrays.copyOf(array, num), 30000000);
		
		GQuickSort.SortInteger(array);
		
	}
	
	private static void testSort(int[] pole, GOldSort sort){
		long time = System.currentTimeMillis();
		sort.sort(pole);
		time = System.currentTimeMillis() - time;
		
		if(!isArraySorted(pole))
			System.out.println(sort.getClass().getSimpleName() + ": je to zle zoradené");
			
		System.out.println(sort.getClass().getSimpleName() + " trval pre " + pole.length + " prvkov: " + time + "ms");
	}
	
	private static void testCountingSort(int[] pole, int min, int max){
		CountingSort sort = new CountingSort();
		long time = System.currentTimeMillis();
		sort.sort(pole, min, max);
		time = System.currentTimeMillis() - time;
		
		if(!isArraySorted(pole))
			System.out.println(sort.getClass().getSimpleName() + ": je to zle zoradené");
			
		System.out.println(sort.getClass().getSimpleName() + " trval pre " + pole.length + " prvkov: " + time + "ms");
	}
	
	private static void testBucketSort(int[] pole, int max){
		BucketSort sort = new BucketSort();
		long time = System.currentTimeMillis();
		sort.sort(pole, max);
		time = System.currentTimeMillis() - time;
		
		if(!isArraySorted(pole))
			System.out.println(sort.getClass().getSimpleName() + ": je to zle zoradené");
			
		System.out.println(sort.getClass().getSimpleName() + " trval pre " + pole.length + " prvkov: " + time + "ms");
	}
	
	private static boolean isArraySorted(int[] array){
		int i, typ = 0, num = array.length;
		
		for(i=1 ;i<num && typ==0 ; i++)
			if(array[i-1] < array[i])
				typ = 1;
			else if(array[i-1] > array[i])
				typ = 2;
		
		if(typ == 1)
			for(; i<num ; i++)
				if(array[i-1] > array[i])
					return false;
			
		else if(typ == 2)
			for(; i<num ; i++)
				if(array[i-1] < array[i])
					return false;
			
		return true;
		
			
	}
}