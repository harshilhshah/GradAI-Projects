package model;

import visual.Box;

public class UniformCostSearch extends AStar{

	public UniformCostSearch(Box[][] g){
		this.grid = g;
		if(g == null || g.length == 0) throw new NullPointerException();
		this.weight = 0;
		this.rows = (short) g.length;
		this.cols = (short) g[0].length;
		this.hType = HeuristicType.NONE;
	}


}
