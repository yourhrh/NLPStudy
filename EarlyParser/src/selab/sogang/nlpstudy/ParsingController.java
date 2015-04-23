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
					ArrayList<ArrayList<String>> makings = new ArrayList<ArrayList<String>>();
					makings.add(rhs);
					if(lhs != null){
						System.out.println(rhs.toString());
						CompleteChart.getInstance().addEdges(new Edge(i,i+1,lhs, makings,new ArrayList<String>()));
					}
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
				ArrayList<ArrayList<String>> makings = new ArrayList<ArrayList<String>>();
				PendingChart.getInstance().add(new Edge(edge.getEnd(), edge.getEnd(), u1,makings, pendingRhs)); 
				
			}
		}
		System.out.println("P1");
		CompleteChart.getInstance().addEdges(edge);
	}

	public boolean process2(Edge e1) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Edge>> col = CompleteChart.getInstance().getCol(e1.getEnd());
		
		boolean adde1 = false;
		for(ArrayList<Edge> data : col){
			List<Edge> matchList = data.stream().filter(e2 -> e1.getNotMaking().get(0).equals(((Edge) e2).getTarget())).collect(Collectors.toList());
			
			if(matchList.size() != 0){
				
				adde1 = true;
				matchList.stream().filter(e2 -> e2.getNotMaking().size() == 0)
						.forEach(match ->{
							System.out.println("P2");
							boolean added = false;
							ArrayList<ArrayList<String>> newMaking = new ArrayList<ArrayList<String>>();
							if(e1.getMaking().size() == 0){
								newMaking.add(new ArrayList<String>());
								newMaking.get(0).add(match.getTarget());
							}
							else{
								newMaking = copyLists(match.getMaking());
								for(ArrayList<String> makings : newMaking){
									makings.add(match.getTarget());
								}
							}
								
							List<String> newNotMaking = new ArrayList<String>();
							
							newNotMaking.addAll(e1.getNotMaking());
							System.out.println(e1.toString());
							System.out.println("remove :"+ newNotMaking.remove(0));
							
							Edge newEdge = new Edge(e1.getStart(), match.getEnd(), e1.getTarget(),newMaking, newNotMaking);
							for(Edge edge : CompleteChart.getInstance().getData(newEdge.getStart(), newEdge.getEnd())){
								if(edge.getTarget().equals(newEdge.getTarget())&&edge.getNotMaking().equals(newEdge.getNotMaking())){

									edge.getMaking().addAll(newEdge.getMaking());
									added = true;
								}
							}
							
							if(!added)
								PendingChart.getInstance().add(newEdge);
						});
			}
			
			
		}
		if(adde1){
			CompleteChart.getInstance().addEdges(e1);
		}
		return adde1;
	}

	// pc1Edge == complete Edge
	public boolean process1(Edge pc1Edge) {
		// TODO Auto-generated method stub
		boolean doProcess1 = false;
		int j = pc1Edge.getStart();
		if (pc1Edge.getNotMaking().size() == 0) {
			doProcess1 = true;
			boolean added  = false;
			for (int i = 0; i <= j; i++) {
				ArrayList<Edge> find = CompleteChart.getInstance()
						.getData(i, j);
				find.stream()
						.filter(edge -> edge.getNotMaking().size() != 0
								&& edge.getNotMaking().get(0)
										.equals(pc1Edge.getTarget()))
						.forEach(
								edge -> {
									System.out.println("P1");
									ArrayList<ArrayList<String>> making = new ArrayList<ArrayList<String>>();
									if(edge.getMaking().size() == 0){
										making.add(new ArrayList<String>());
										making.get(0).add(pc1Edge.getTarget());
									}
									else{
										making = copyLists(edge.getMaking());
										for(ArrayList<String> adding : making){
											System.out.println("11"+adding.toString());
											adding.add(pc1Edge.getTarget());
										}
									}
									for(ArrayList<String> ma : making){
										if(ma.size()>=3)
											System.out.println("사이즈 3 이상!!!"+ pc1Edge.toString()+ edge.toString());
									}
									

									ArrayList<String> notMaking = new ArrayList<String>();
									notMaking.addAll(edge.getNotMaking());
									System.out.println(edge.getNotMaking());
									System.out.println("remove :"+ notMaking.remove(0));


									PendingChart.getInstance().add(
											new Edge(edge.getStart(), pc1Edge
													.getEnd(),
													edge.getTarget(), making,
													notMaking));
								});
			}
			for(Edge edge : CompleteChart.getInstance().getData(pc1Edge.getStart(), pc1Edge.getEnd())){
				if(edge.getTarget().equals(pc1Edge.getTarget())&&edge.getNotMaking().equals(pc1Edge.getNotMaking())){
					System.out.println("edge 병합 들어옴 2");
					System.out.println(edge.toString());
					System.out.println(pc1Edge.toString());
					edge.getMaking().addAll(pc1Edge.getMaking());
					added = true;
				}
			}
			if(!added)
				CompleteChart.getInstance().addEdges(pc1Edge);
			
		}
		return doProcess1;
	}
	
	public void parsing(){
		
		while(!PendingChart.getInstance().getProcessQueue().isEmpty()){
			Edge pendingEdge = PendingChart.getInstance().getProcessQueue().poll();
			System.out.println(pendingEdge.toString());
			
			if(!process1(pendingEdge)){
				if(!process2(pendingEdge)){
					process3(pendingEdge);
				}
			}
		}
		
	}
	private ArrayList<ArrayList<String>> copyLists(ArrayList<ArrayList<String>> copyed){
		ArrayList<ArrayList<String>> returnArray = new ArrayList<ArrayList<String>>();
		for(ArrayList<String> forCopy : copyed){
			ArrayList<String> addList = new ArrayList<String>();
			for(String stringCopy : forCopy){
				addList.add(stringCopy);
			}
			returnArray.add(addList);
		}
		
		return returnArray;
	}
	
	
	
	

}
