package glib.data.good.interfaces;

import java.util.ArrayList;
import java.util.function.Consumer;

public interface GMap<S, T> {
	public void add(S key, T value);
	public void remove(S key);
	public boolean constainsKey(S key);
	public boolean constainsValue(T value);
	public void forEachElement(Consumer<? super T> condition);
	public int getSize();
	public ArrayList<T> getAllValues();
	public void clear();
	public String toString();
	
}
