package glib.data.oneDirList;

public class Node {
	private int key;
	private String value;
	private Node p_dalsi;
	
	public Node(int key, String value){
		this.key = key;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getP_dalsi() {
		return p_dalsi;
	}

	public void setP_dalsi(Node p_dalsi) {
		this.p_dalsi = p_dalsi;
	}

	public int getKey() {
		return key;
	}
	
	public boolean equals(Node node){
		if(this.key == node.getKey()&&this.getValue()==node.getValue()){
			return true;
		}
		return false;
	}
}
