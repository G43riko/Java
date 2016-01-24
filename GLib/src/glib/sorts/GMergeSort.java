package glib.sorts;

public class GMergeSort<T extends Comparable<? super T>> implements GSort<T>{
	private T[] array;
	private T[] tempMergArr;
    private int length;
     
    @SuppressWarnings("unchecked")
	public void sort(T[] array) {
        this.array = array;
        this.length = array.length;
        this.tempMergArr = (T[])new Object[length];
        doMergeSort(0, length - 1);
    }
 
    private void doMergeSort(int lowerIndex, int higherIndex) {
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            doMergeSort(lowerIndex, middle);
            doMergeSort(middle + 1, higherIndex);
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }
 
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
        for (int i=lowerIndex; i <= higherIndex; i++)
            tempMergArr[i] = array[i];
        
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i].compareTo(tempMergArr[j]) <= 0)
                array[k] = tempMergArr[i++];
             else
                array[k] = tempMergArr[j++];
            k++;
        }
        while (i <= middle)
            array[k++] = tempMergArr[i++];
   
    }
}
