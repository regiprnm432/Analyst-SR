import java.util.LinkedList;

public class NBTree {
	Node root;
	
	public NBTree() {
		this.root = new Node("", 0, null, null);
	}
	
	public boolean isEmptyTree() {
		return root == null;
	}
	
	public void insertNode(Node node, String id, int level) {
		if(isEmptyTree()) {
			this.root = new Node(id, 0, null, null);
		} else if(id.length() == 1){
			if(node == null) {
				node = new Node(id, 0, null, null);
			} else {
				Node siblings = node.nSibling;
				while(siblings != null) {
					siblings = siblings.nSibling;
				}
				siblings = new Node(id, 0, null, null);
			}
		}
	}
	
	public void insert(String id) {
		Node currNode = this.root;
		int level = 0;
		while(true) {
			if(currNode.id.equals(id.substring(0, level))) {
				level += 1;
				if(currNode.fChild == null && level == id.length()) {
					currNode.fChild = new Node(id, 0, null, null);
					break;
				} else if(id.length() == level){
					Node siblings = currNode.fChild;
					while(siblings.nSibling != null) {
						siblings = siblings.nSibling;
					}
					siblings.nSibling = new Node(id, 0, null, null);
					break;
				}
				currNode = currNode.fChild;
			} else {
				currNode = currNode.nSibling;
			}
		}
	}
	

	public void levelOrderTransversal() {
		int countNode = 0;
		LinkedList<Node> queue = new LinkedList<>();
		queue.add(root);
		Node temp;
		while(queue.size() != 0) {
			temp = queue.poll();
			countNode++;
			//System.out.println(temp.id + " : " + temp.support);
			if(temp.support >= 6) {
				System.out.println(temp.id + " : " + temp.support);
			}
			temp = temp.fChild;
			while(temp != null) {
				queue.add(temp);
				temp = temp.nSibling;
			}
		}
		System.out.println(countNode);
	}
	
	public void addSupport(String id) {
		Node currNode = this.root;
		int level = 0;
		while(level < id.length()) {
			if(currNode.id.equals(id.substring(0, level))) {
				level += 1;
				currNode = currNode.fChild;
			} else {
				currNode = currNode.nSibling;
			}
		}
		while(true) {
			if(currNode.id.equals(id)) {
				currNode.support++;
				break;
			} else {
				currNode = currNode.nSibling;
			}
		}
	}

}
