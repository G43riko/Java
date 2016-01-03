package octtrees;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import glib.util.vector.GVector3f;

public class Tree3D<T> {
	private final static int CHILDS = 8;
	private Tree3D<?>[] childtrens = null;
	private Tree3D<T> parent;
	private GVector3f position;
	private GVector3f size;
	private Set<Tree3D<?>> childrens;
	
	public Tree3D(GVector3f position, GVector3f size){
		this(position, size, null);
	}
	
	public Tree3D(GVector3f position, GVector3f size, Tree3D<T> parent){
		this.position = position;
		this.size = size;
		this.parent = parent;
		
		if(parent == null)
			childrens = new HashSet<Tree3D<?>>();
		
//		System.out.println("position: " + position + ", size: " + size);
	}
	
//	public List<Tree3D<?>> divide(){
//		/*
//		 * A---AB---B 
//		 * |    |   |
//		 * AC--EE--BD
//		 * |   |    |
//		 * C---CD---D
//		 *   
//		 * E---EF---F 
//		 * |    |   |
//		 * EH--BB--FG
//		 * |   |    |
//		 * H---HG---G
//		 *   
//		 * 4---EF---5 
//		 * |    |   |
//		 * EH--BB--FG
//		 * |   |    |
//		 * 7---HG---6
//		 * 
//		 * 0---AB---1 
//		 * |    |   |
//		 * AC--EE--BD
//		 * |   |    |
//		 * 2---CD---3
//		 *   4--------5
//		 *  /|       /|
//		 * 0--------1 |
//		 * | |      | |
//		 * | 7------+-6
//		 * |/       |/
//		 * 2--------3
//		 */
//		if(parent == null)
//			childrens.clear();
//		
//		List<Tree3D<?>> result = new LinkedList<Tree3D<?>>();
//		if(!isLastChild())
//			for(int i=0 ; i<CHILDS ; i++)
//				result.addAll(childtrens[i].divide());
//		else{
//	//		GVector2f B  = new GVector2f(position.getX() + size.getX(), position.getY());
//	//		GVector2f C  = new GVector2f(position.getX(), position.getY() + size.getY());
//			GVector3f AB = new GVector3f(position.getX() + size.getX() / 2, position.getY());
//			GVector3f AC = new GVector3f(position.getX(), position.getY() + size.getY() / 2);
//	//		GVector2f CD = new GVector2f(C.getX() + size.getX() / 2, C.getY());
//	//		GVector2f BD = new GVector2f(B.getX(), B.getY() + size.getY() / 2);
//			GVector3f EE = position.add(size.div(2));
//			
//	//		System.out.println(position + " ---------- " + AB + " ---------- " + B);
//	//		System.out.println();
//	//		System.out.println(AC + " --------- " + EE + " --------- " + BD);
//	//		System.out.println();
//	//		System.out.println(C + " -------- " + CD + " -------- " + position.add(size));
//			
//			childtrens = new Tree3D<?>[CHILDS];
//			childtrens[0] = new Tree3D<T>(position, size.div(2));
//			childtrens[1] = new Tree3D<T>(AB, size.div(2));
//			childtrens[2] = new Tree3D<T>(AC, size.div(2));
//			childtrens[3] = new Tree3D<T>(EE, size.div(2));
//			
//			for(int i=0 ; i<CHILDS ; i++)
//				result.add(childtrens[i]);
//		}
//		
//		if(parent == null)
//			childrens.addAll(result);
//		return result;
//	}
	
	public boolean isLastChild(){
		return childtrens == null;
	}
	
	@Override
	public String toString() {
		return childrens.isEmpty() ? position.toString() : childrens.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Tree3D<?>))
			return false;
		Tree3D<?> tree = (Tree3D<?>)obj;
		return position.equals(tree.position) && size.equals(tree.size);
	}
	
	public int getNumberOfChildrens(){
		return childrens == null ? 0 : childrens.size();
	}
	
	public int getNumberOfEndChildrens(){
		return childrens == null ? 0 : (int)childrens.stream().filter(a -> a.isLastChild()).count();
	}
}
