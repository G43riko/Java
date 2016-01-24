package glib.sorts;

public interface GSort<T extends Comparable<? super T>> {
		public void sort(T[] array);
}
