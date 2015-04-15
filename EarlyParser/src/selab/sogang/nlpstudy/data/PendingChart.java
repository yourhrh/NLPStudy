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
			if(rhs != null){
				processQueue.add(new Edge(0,0, "S", null, rhs));
			}
		}
	}
	public Queue<Edge> getProcessQueue() {
		return processQueue;
	}
	public void add(Edge edge) {
		// TODO Auto-generated method stub
		processQueue.add(edge);
	}

}
