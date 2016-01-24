package glib.sorts;

import java.util.Arrays;

public class GQuickSort<T extends Comparable<? super T>> implements GSort<T>{
	private T[] array;
    private int length;
    
    //SORTS
    
	public void sort(T[] array){
		if (array == null || array.length == 0)
            return;
		
        this.array = array;
        this.length = array.length;
        
        quickSort(0, length - 1);
	}
	
	public static void sort(int[] array){
		new GQuickSort<Integer>().sort(Arrays.stream(array).boxed().toArray( Integer[]::new));
	}

	public static void sort(double[] array){
		new GQuickSort<Double>().sort(Arrays.stream(array).boxed().toArray( Double[]::new));
	}
	
	public static void sort(long[] array){
		new GQuickSort<Long>().sort(Arrays.stream(array).boxed().toArray( Long[]::new));
	}
	
	//OTHER
	
	private void quickSort(int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        
        T pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
        while (i <= j) {
            while(array[i].compareTo(pivot) < 0)
                i++;
            while(array[j].compareTo(pivot) > 0)
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
