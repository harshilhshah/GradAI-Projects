package model;

import controller.Utility;
import visual.Box;

public abstract class AStar {
	
	public int numNodesExpanded = 0;
	
	short rows;
	short cols;
	double weight;
	double totalPathcost;
	Box[][] grid;
	Node[][] node_grid;
	HeuristicType hType;
	
	AStar(Box[][] g){
		if(g == null || g.length == 0) throw new NullPointerException();
		grid = g;
		rows = (short) g.length;
		cols = (short) g[0].length;
	}
	
	
    public Node runAStar(Point startPoint, Point goalPoint){
    	Node aStarNodeSolution = null;
    	Heap open_list = new Heap();
    	boolean[][] closed_list = new boolean[rows][cols];
    	Node curr = grid[startPoint.getY()][startPoint.getX()].getNode();
    	curr.setH_value(hType.getDistance(startPoint, goalPoint)*weight);
    	open_list.add(curr); 
    	
    	while(!open_list.isEmpty()){
    		curr = open_list.pop();
    		if(curr.getPoint().equals(goalPoint)){
    			return curr;
    		}/*Path found!*/
    		int cx = curr.getPoint().getX();
    		int cy = curr.getPoint().getY();
    		closed_list[cy][cx] = true; /*marked that point as visited, added to closed list*/
    		numNodesExpanded++;

    		//Find 8 surrounding neighbors
    		for(Neighbor n: Neighbor.neighnbors){
    			if(cx+n.getXChange() < 0 || cx+n.getXChange() >= cols || cy+n.getYChange() < 0 || cy+n.getYChange() >= rows){ /*checking for out of bounds*/
    				continue;
    			}
    			else if(grid[cy+n.getYChange()][cx+n.getXChange()].getTerrain() == Terrain.BLOCKED_CELL){ /*checking for blocked cells*/
    				continue;
    			}
    			else if(closed_list[cy+n.getYChange()][cx+n.getXChange()]){ /*checking if already visited this point before*/
    				continue;
    			}
    			else{
    				Node sPrime = grid[cy+n.getYChange()][cx+n.getXChange()].getNode();
    				sPrime.setH_value(hType.getDistance(sPrime.getPoint(), goalPoint)*weight);
    				if(!open_list.contains(sPrime)){
    					sPrime.setF_value(Integer.MAX_VALUE); 
    					sPrime.parent = null;
    				}
    				double cost = Utility.getCost(grid[curr.getPoint().getY()][curr.getPoint().getX()], grid[sPrime.getPoint().getY()][sPrime.getPoint().getX()], n.isDiagonal()); 
    				updateVertex(curr, sPrime, cost, open_list);
    			}
    		}		
    	}
    	
    	System.out.println("Number of nodes expanded:" + numNodesExpanded);
    	System.out.println("Done");
    	return aStarNodeSolution;
    }
	
	public void updateVertex(Node s, Node sPrime, double cost, Heap open_list){
    	if(s.getF_value() + cost + sPrime.getH_value() < sPrime.getF_value()){
    		sPrime.setG_value(s.getG_value() + cost);
    		sPrime.setF_value(sPrime.getG_value() + sPrime.getH_value());
    		sPrime.parent = s;
    		if(open_list.contains(sPrime)){
    			open_list.remove(sPrime);
    		}
    		open_list.add(sPrime);
    	}
	}
	
}

