package selab.sogang.nlpstudy.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import selab.nlpstudy.grammer.Grammar;

public class PendingChart {
	private Queue<Edge> processQueue;
	
	private PendingChart(){
		processQueue = new LinkedList<Edge>();
	}
	
	private static class PendingChartHolder{
		private static PendingChart uniquePC = new PendingChart();
	}
	
	public static PendingChart getInstance(){
		return PendingChartHolder.uniquePC;
	}
	public void init(ArrayList<Grammar> grammars){
		for(Grammar grammar : grammars ){
			List<String> rhs = grammar.convertToRhs("S");
			if(rhs.size() != 0){
				this.add(new Edge(0,0, "S", new ArrayList<String>(), rhs));
			}
		}
	}
	public Queue<Edge> getProcessQueue() {
		return processQueue;
	}
	public void add(Edge edge) {
		// TODO Auto-generated method stub
		
		//중복 제거
		if(!processQueue.contains(edge))
			processQueue.add(edge);
		
	}

}
