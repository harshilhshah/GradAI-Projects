package controller;

import model.Node;
import model.Point;

public class BST {
	
	public Node root;
	
	public BST(){
		root = null;
	}
	
	public BST( Node node) {
		root = node;
	}
	
	public int insert(Node node){
		Node curr = null;
		for(curr = root; curr != null; ){
			if(curr.compareTo(node) == -1){
				if(curr.left == null){
					curr.left = node;
					node.parent = curr;
				}else{
					curr = curr.left;
				}
			}else if(curr.compareTo(node) == 1){
				if(curr.right == null){
					curr.right = node;
					node.parent = curr;
				}else{
					curr = curr.right;
				}
			}else{
				
			}
		}
		return 0;
	}
	public float getF_value(){
		return 0.0f;
	}
	
}
