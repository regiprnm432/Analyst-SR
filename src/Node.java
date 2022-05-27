
public class Node {

	String id;
	int support;
	Node fChild;
	Node nSibling;

	public Node(String id, int support, Node fChild, Node nSibling) {
		this.id = id;
		this.support = support;
		this.fChild = fChild;
		this.nSibling = nSibling;
	}
	
	public Node() {
		
	}
}