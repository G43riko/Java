package glib.sorts;

public class GHeapSort<T extends Comparable<? super T>> implements GSort<T>{
    private T[] a;
    private int n;
    private int left;
    private int right;
    private int largest;

    public void sort(T[] array){
        a = array;
        buildheap(a);
        
        for(int i=n ; i>0 ; i--){
            exchange(0, i);
            n--;
            maxheap(a, 0);
        }
    }
    
    private void buildheap(T[] a){
        n = a.length - 1;
        
        for(int i=n/2 ; i>=0 ; i--)
            maxheap(a,i);
    }
    
    private void maxheap(T[] a, int i){ 
        left = 2 * i;
        right = 2 * i + 1;
        if(left <= n && a[left].compareTo(a[i]) > 0)
            largest = left;
        else
            largest = i;
        
        if(right <= n && a[right].compareTo(a[largest]) > 0)
            largest = right;
        
        if(largest != i){
            exchange(i , largest);
            maxheap(a, largest);
        }
    }
    
    private void exchange(int i, int j){
        T t = a[i];
        a[i] = a[j];
        a[j] = t; 
    }
}