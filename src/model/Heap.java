package model;

import java.util.ArrayList;

public class Heap { 
	
	private ArrayList<Node> list;
	
	public Heap (){
		list = new ArrayList<Node>();
	}
	
	public double MinKey(){
		return (list.isEmpty()) ? Integer.MAX_VALUE : list.get(0).getF_value();
	}
	
	public Node Top(){
		return (list.isEmpty()) ? null : list.get(0);
	}
	
	public void add(Node n, double key){
		n.setF_value(key);
		add(n);
	}
	
	public void add(Node node){
	list.add(node);
	siftUp();
	}
	
	private void siftUp(){
		int k = list.size() -1;
		while (k !=0){
			int parentindex = (k-1)/2;
			Node curr = list.get(k);
			Node parent = list.get(parentindex);
			if(curr.compareTo(parent) == -1 ){
				list.set(parentindex, curr);
				list.set(k, parent);
				k = parentindex;
			}else{
				break;
			}
				
		}
	}
	
	private void siftDown(){
		int k = 0;
		int left = 2*k+1;
		int right = 0;
		while(left < list.size()){
			Node curr = list.get(k);
			int min = left;
			right = left+1;
			if(right < list.size()){
				if(list.get(right).compareTo(list.get(left)) < 0){
					min++;
				}
			}
			if(curr.compareTo(list.get(min)) > 0){
				list.set(k, list.get(min));
				list.set(min, curr);
				k = min;
				left = 2*k+1;
			}else{
				break;
			}
		}
	}
	
	public Node pop(){
		if(list.size() == 0)
			return null;
		if(list.size() == 1)
			return list.remove(0);
		Node nodeToReturn = list.get(0);
		list.set(0, list.remove(list.size()-1));
		siftDown();
		return nodeToReturn;
	}
	
	
	public int getSize(){
		return list.size();
	}
	
	public boolean contains(Node node){
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).equals(node.getPoint()))
				return true;
		}
		return false;
	}
	
	public void remove(Node node){
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).equals(node.getPoint()))
				list.remove(i);
		}
	}
	
	public boolean isEmpty(){
		return list.size() == 0;
	}
	
	public String toString(){
		return list.toString();
	}
}