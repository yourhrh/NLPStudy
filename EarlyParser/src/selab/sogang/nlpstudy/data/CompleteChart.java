package selab.sogang.nlpstudy.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CompleteChart extends ArrayList<ArrayList<ArrayList<Edge>>>{
	

	private static class CCInstanceHolder {
		private static CompleteChart theUniqueCC = new CompleteChart();
		
	}
	
	public static CompleteChart getInstance() {
		// TODO Auto-generated method stub
		return CCInstanceHolder.theUniqueCC;
	}
	
	
	private CompleteChart() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public void addEdges(Edge edge){
		
		this.get(edge.getStart()).get(edge.getEnd()).add(edge);

		System.out.println(edge.getStart() +"  " +  edge.getEnd() + " "+ edge.getTarget()+ " \n" +edge.getMaking()
				+ edge.getNotMaking());
	}
	
	public void initCompleteEdges(int size){
		for(int i =0;i<size;i++){
			ArrayList<ArrayList<Edge>> addingArray = new ArrayList<ArrayList<Edge>>();
			for(int j=0;j<size;j++)
				addingArray.add(new ArrayList<Edge>());
			this.add(addingArray);
		};
	}
	public ArrayList<Edge> getData(int start,int end){
		return this.get(start).get(end);
	}
	public ArrayList<ArrayList<Edge>> getCol(int start){
		return this.get(start);
	}

}
