package glib.data.good.interfaces;

//import java.util.ArrayList;

public interface GMap<S, T> extends GCollection {
	public T add(S key, T value);
//	public void addAll(GMap<S, T> tree);

	public T remove(S key);
//	public void removeAll(GMap<S, T> tree);

	public T get(S key);
//	public ArrayList<T> getAllValues();
	
	public boolean constainsValue(T value);
	public boolean containsKey(S key);

}
