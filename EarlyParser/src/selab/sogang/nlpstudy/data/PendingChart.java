package selab.sogang.nlpstudy.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import selab.nlpstudy.grammer.Grammar;
import selab.sogang.nlpstudy.ParsingController;

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
		ParsingController.outputText.add("initial edge");
		for(Grammar grammar : grammars ){
			List<String> rhs = grammar.convertToRhs("S");
			
			if(rhs.size() != 0){
				Edge addingEdge = new Edge(0,0, "S", new ArrayList<ArrayList<String>>(), rhs);
				this.add(addingEdge);
				
				ParsingController.outputText.add(addingEdge.toString());
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
