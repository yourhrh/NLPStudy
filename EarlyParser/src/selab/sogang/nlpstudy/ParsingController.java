package selab.sogang.nlpstudy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import selab.nlpstudy.grammer.Grammar;
import selab.sogang.nlpstudy.data.CompleteChart;
import selab.sogang.nlpstudy.data.Edge;

public class ParsingController {
	
	ArrayList<Grammar> grammars;
	Queue<Edge> pendingChart;
	public ParsingController(ArrayList<Grammar> grammars) {
		// TODO Auto-generated constructor stub
		this.grammars = grammars;
	}
	
	
	public void init() {
		// TODO Auto-generated method stub
		try {
			BufferedReader in = new BufferedReader(new FileReader("./input.txt"));
			String inputText = in.readLine();
			String[] parsedInput = inputText.split("\\s+");
			
			//글자 개수 +1
			CompleteChart.getInstance().initCompleteEdges(parsedInput.length+1);
			
			for(int i=0;i<parsedInput.length;i++){
				
				ArrayList<String> rhs = new ArrayList<String>();
				rhs.add(parsedInput[i]);
				for(Grammar grammar : grammars){
					String lhs = grammar.convertToLhs(rhs);
					if(lhs != null)
						CompleteChart.getInstance().addEdges(new Edge(i,i+1,lhs, rhs, new ArrayList<String>()));
				}
					
			}
			
			
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pendingChart = new LinkedList<Edge>();
		

	}
	public Queue<Edge> getPendingChart() {
		return pendingChart;
	}


	public ArrayList<Grammar> getGrammars() {
		// TODO Auto-generated method stub
		return grammars;
	}


	public void prcess3(Edge edge) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
