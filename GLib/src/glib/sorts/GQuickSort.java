package glib.sorts;

import java.util.Arrays;
import java.util.Comparator;

public class GQuickSort<T extends Comparable<? super T>> implements GSort<T>{
	private T[] array;
	private Comparator<T> comparator;
    private int length;

	public void sort(T[] array, Comparator<T> comparator) {
		if (array == null || array.length == 0)
            return;
		
        this.comparator = comparator;
        this.array = array;
        this.length = array.length;
        quickSort(0, length - 1);
	}
	
	public static void SortInteger(int[] array){
		new GQuickSort<Integer>().sort(Arrays.stream(array).boxed().toArray( Integer[]::new), new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
	}
	
	public static void SortString(String[] array){
		new GQuickSort<String>().sort(array, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
	}
	
	public static void SortDouble(double[] array){
		new GQuickSort<Double>().sort(Arrays.stream(array).boxed().toArray( Double[]::new), new Comparator<Double>() {
			public int compare(Double o1, Double o2) {
				return o1.compareTo(o2);
			}
		});
	}
	
	public static void SortFloat(long[] array){
		new GQuickSort<Long>().sort(Arrays.stream(array).boxed().toArray( Long[]::new), new Comparator<Long>() {
			public int compare(Long o1, Long o2) {
				return o1.compareTo(o2);
			}
		});
	}
	
    private void quickSort(int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        
        T pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
        while (i <= j) {
            while (comparator.compare(array[i], pivot) < 0)
                i++;
            while (comparator.compare(array[j], pivot) > 0)
                j--;
            
            if (i <= j)
                exchangeNumbers(i++, j--);
        }
        
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }

    private void exchangeNumbers(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
	
}
