package octtrees;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import glib.util.vector.GVector2f;

public class Tree2D<T> {
	private final static int CHILDS = 4; 
	private Tree2D<?>[] childtrens = null;
	private Tree2D<T> parent;
	private GVector2f position;
	private GVector2f size;
	private Set<Tree2D<?>> descendants;
	
	public Tree2D(GVector2f position, GVector2f size){
		this(position, size, null);
	}
	
	public Tree2D(GVector2f position, GVector2f size, Tree2D<T> parent){
		this.position = position;
		this.size = size;
		this.parent = parent;
		
		if(parent == null)
			descendants = new HashSet<Tree2D<?>>();
		
//		System.out.println("position: " + position + ", size: " + size);
	}
	
	public List<Tree2D<?>> divide(){
		/*
		 * A---AB---B 
		 * |    |   |
		 * AC--EE--BD
		 * |   |    |
		 * C---CD---D
		 * 
		 * 
		 * 0-------1 
		 * |   |   |
		 * |---+---|
		 * |   |   |
		 * 2-------3
		 */
//		if(parent == null)
//			childrens.clear();
		
		List<Tree2D<?>> result = new LinkedList<Tree2D<?>>();
		if(!isLastChild())
			for(int i=0 ; i<CHILDS ; i++)
				result.addAll(childtrens[i].divide());
		else{
	//		GVector2f B  = new GVector2f(position.getX() + size.getX(), position.getY());
	//		GVector2f C  = new GVector2f(position.getX(), position.getY() + size.getY());
			GVector2f AB = new GVector2f(position.getX() + size.getX() / 2, position.getY());
			GVector2f AC = new GVector2f(position.getX(), position.getY() + size.getY() / 2);
	//		GVector2f CD = new GVector2f(C.getX() + size.getX() / 2, C.getY());
	//		GVector2f BD = new GVector2f(B.getX(), B.getY() + size.getY() / 2);
			GVector2f EE = position.add(size.div(2));
			
	//		System.out.println(position + " ---------- " + AB + " ---------- " + B);
	//		System.out.println();
	//		System.out.println(AC + " --------- " + EE + " --------- " + BD);
	//		System.out.println();
	//		System.out.println(C + " -------- " + CD + " -------- " + position.add(size));
			
			childtrens = new Tree2D<?>[CHILDS];
			childtrens[0] = new Tree2D<T>(position, size.div(2));
			childtrens[1] = new Tree2D<T>(AB, size.div(2));
			childtrens[2] = new Tree2D<T>(AC, size.div(2));
			childtrens[3] = new Tree2D<T>(EE, size.div(2));
			
			for(int i=0 ; i<CHILDS ; i++)
				result.add(childtrens[i]);
		}
		
		if(parent == null)
			descendants.addAll(result);
		return result;
	}
	
	public boolean isLastChild(){
		return childtrens == null;
	}
	
	@Override
	public String toString() {
		return descendants.isEmpty() ? position.toString() : descendants.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Tree2D<?>))
			return false;
		Tree2D<?> tree = (Tree2D<?>)obj;
		return position.equals(tree.position) && size.equals(tree.size);
	}
	
	public int getNumberOfChildrens(){
		return descendants == null ? 0 : descendants.size();
	}
	
	public int getNumberOfEndChildrens(){
		return descendants == null ? 0 : (int)descendants.stream().filter(a -> a.isLastChild()).count();
	}

	public Set<Tree2D<?>> getDescendants() {
		return descendants;
	}
}
