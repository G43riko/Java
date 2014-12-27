package data.oneDirList;

public class ListO {
	private Node p_prvy;
	private int num;
	private boolean vzostupne;
	
	public ListO(int a,int b){
		p_prvy = null;
		num = 0; 
		vzostupne = a<b;
	}
	
	public void remove(int key){
		Node p_act,p_pred;
		for(p_act = p_pred = p_prvy; p_act.getP_dalsi()!=null ; p_pred=p_act,p_act=p_act.getP_dalsi()){
			if(p_act.getKey() == key){
				if(p_act==p_prvy){
					p_prvy = p_prvy.getP_dalsi();
					num--;
					return;
				}
				p_pred.setP_dalsi(p_act.getP_dalsi());
				num--;
				return;
			}
		}
		if(p_act.getKey()==key){
			num--;
			p_pred.setP_dalsi(null);
		}
		
	}
	
	public void set(int key, String value){
		Node find = getNode(key);
		if(find!=null){
			find.setValue(value);
			return;
		}
	}
	
	public String get(int key){
		Node find = getNode(key);
		if(find!=null){
			return find.getValue();
		}
		return null;
	}
	
	public void vypisAll(){
		Node p_act;
		for(p_act=p_prvy ; p_act!=null ; p_act = p_act.getP_dalsi()){
			System.out.println(p_act.getKey()+" == "+p_act.getValue());
		}
	}
	
	private Node getNode(int key){
		Node p_act;
		for(p_act=p_prvy ; p_act!=null ; p_act = p_act.getP_dalsi()){
			if(p_act.getKey()==key){
				return p_act;
			}
		}
		return null;
	}
	
	public void add(int key, String value){
		Node p_novy = new Node(key,value);
		p_novy.setP_dalsi( null );
		num++;
		if(p_prvy == null){
			p_prvy = p_novy;
		}
		else{
			Node p_act,p_pred;
			int i;
			for(p_act = p_pred = p_prvy, i=0 ; p_act!=null ; p_pred=p_act,p_act=p_act.getP_dalsi(), i++){
				if(p_novy.getKey()==p_act.getKey()){
					return;
				}
				if(vzostupne?p_novy.getKey()<p_act.getKey():p_novy.getKey()>p_act.getKey()){
					if(i==0){
						p_prvy = p_novy;
						p_prvy.setP_dalsi(p_act);
						return;
					}
					p_pred.setP_dalsi(p_novy);
					p_novy.setP_dalsi(p_act);
					return;
				}
			}
			p_pred.setP_dalsi(p_novy);
		}
	}
}
