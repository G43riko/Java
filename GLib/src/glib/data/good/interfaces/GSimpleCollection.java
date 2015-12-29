package glib.data.good.interfaces;

public interface GSimpleCollection<T> extends GCollection {
	public void add(T item);
	public T get();
}
