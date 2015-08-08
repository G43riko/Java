package glib.data.good.interfaces;

import java.util.ArrayList;
import java.util.function.Consumer;

public interface GList<T> {
	
	public int getSize();
	public void add(int key, T value);
	public void addAll(GList<T> list);
	public void remove(int key);
	public void removeAll(GList<T> list);
	public T get(int key);
	public boolean containsKey(int key);
	public boolean containsValue(T value);
	public void clear();
	public void forEachElement(Consumer<? super T> action);
	public String toString();
	public ArrayList<T> getAllValues();
	
}
