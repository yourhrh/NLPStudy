package selab.sogang.nlpstudy.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CompleteChart {
	
	private ArrayList<ArrayList<ArrayList<Edge>>> completeEdges;

	private static class CCInstanceHolder {
		private static CompleteChart theUniqueCC = new CompleteChart();
		
	}
	
	public static CompleteChart getInstance() {
		// TODO Auto-generated method stub
		return CCInstanceHolder.theUniqueCC;
	}
	
	
	private CompleteChart() {
		// TODO Auto-generated constructor stub
		completeEdges = new ArrayList<ArrayList<ArrayList<Edge>>>();
	}
	public void addEdges(Edge edge){
		
	}
	
	public void initCompleteEdges(int size){
		for(int i =0;i<size;i++){
			ArrayList<ArrayList<Edge>> addingArray = new ArrayList<ArrayList<Edge>>();
			for(int j=0;j<size;j++)
				addingArray.add(new ArrayList<Edge>());
			completeEdges.add(addingArray);
		}
	}

}
