package selab.sogang.nlpstudy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import selab.nlpstudy.grammer.Grammar;
import selab.sogang.nlpstudy.data.CompleteChart;
import selab.sogang.nlpstudy.data.Edge;
import selab.sogang.nlpstudy.data.PendingChart;

public class ParsingController {
	
	ArrayList<Grammar> grammars;
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
						CompleteChart.getInstance().addEdges(new Edge(i,i+1,lhs, rhs,new ArrayList<String>()));
				}
					
			}
			
			
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}


	public ArrayList<Grammar> getGrammars() {
		// TODO Auto-generated method stub
		return grammars;
	}


	public void process3(Edge edge) {
		// TODO Auto-generated method stub
		String u1 = edge.getNotMaking().get(0);
		for(Grammar grammar : grammars){
			List<String> pendingRhs = grammar.convertToRhs(u1);
			if(pendingRhs.size() != 0){
				System.out.println(u1+" need Make :" + pendingRhs.toString());
				PendingChart.getInstance().getProcessQueue().offer(new Edge(edge.getEnd(), edge.getEnd(), u1,new ArrayList<String>(), pendingRhs)); 
			}
		}
		CompleteChart.getInstance().addEdges(edge);
	}

	public boolean process2(Edge e1) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Edge>> col = CompleteChart.getInstance().getCol(e1.getEnd());
		
		boolean adde1 = false;
		for(ArrayList<Edge> data : col){
			List<Edge> matchList = data.parallelStream().filter(e2 -> e1.getNotMaking().get(0).equals(((Edge) e2).getTarget())).collect(Collectors.toList());
			
			if(matchList.size() != 0){
				adde1 = true;
				List<Edge> matchEdge = matchList.stream().filter(e2 -> e2.getNotMaking().size() == 0).collect(Collectors.toList());
				for(Edge match : matchEdge){
					ArrayList<String> newMaking = new ArrayList<String>();
					if(e1.getMaking().size() != 0)
						newMaking.addAll(e1.getMaking());
					newMaking.add(match.getTarget());
					
					List<String> newNotMaking = new ArrayList<String>();
					if(e1.getNotMaking().size() != 0)
						newNotMaking.addAll(e1.getNotMaking());
					newNotMaking.remove(match.getTarget());
					Edge newEdge = new Edge(e1.getStart(), match.getEnd(), e1.getTarget(),newMaking, newNotMaking);
					
					System.out.println("PC 2 : " + newEdge.toString());
					PendingChart.getInstance().getProcessQueue().offer(newEdge);
				}
			}
			
			
		}
		if(adde1){
			CompleteChart.getInstance().addEdges(e1);
		}
		return adde1;
	}

	public boolean process1(Edge pc1Edge) {
		// TODO Auto-generated method stub
		boolean doProcess1 = false;
		int j = pc1Edge.getEnd();
		if (pc1Edge.getNotMaking().size() == 0) {
			doProcess1 = true;
			for (int i = 0; i < CompleteChart.getInstance().size(); i++) {
				ArrayList<Edge> find = CompleteChart.getInstance()
						.getData(i, j);
				find.stream()
						.filter(edge -> edge.getNotMaking().size() != 0
								&& edge.getNotMaking().get(0)
										.equals(pc1Edge.getTarget()))
						.forEach(
								edge -> {
									ArrayList<String> making = new ArrayList<String>();
									making.addAll(edge.getMaking());
									making.add(pc1Edge.getTarget());

									ArrayList<String> notMaking = new ArrayList<String>();
									notMaking.addAll(edge.getNotMaking());
									notMaking.remove(0);

									PendingChart.getInstance().getProcessQueue().offer(
											new Edge(edge.getStart(), pc1Edge
													.getEnd(),
													edge.getTarget(), making,
													notMaking));
									CompleteChart.getInstance().addEdges(
											pc1Edge);

								});
			}
		}
		return doProcess1;
	}
	
	public void parsing(){
		while(!PendingChart.getInstance().getProcessQueue().isEmpty()){
			Edge pendingEdge = PendingChart.getInstance().getProcessQueue().poll();
			if(!process1(pendingEdge)){
				System.out.println("Do not Process1");
				if(!process2(pendingEdge)){
					System.out.println("Do not Process2");
					process3(pendingEdge);
				}
			}
		}
		
	}
	
	
	
	

}
