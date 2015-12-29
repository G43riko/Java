package glib.sorts;

import java.util.Comparator;

public interface GSort<T extends Comparable<? super T>> {
	public void sort(T[] array, Comparator<T> comparator);
}
