package selab.sogang.nlpstudy.data;

import java.util.ArrayList;
import java.util.List;

public class Edge {

	String target;
	List<String> notMaking;
	private ArrayList<String> making;
	int start,end;
	
	public Edge(int start,int end,String target,ArrayList<String> making,List<String> rhs){
		this.start= start;
		this.end = end;
		this.target = target;
		this.notMaking = rhs;
		this.making = making;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Edge compareEdge = (Edge)obj;
		return start == compareEdge.start&&end == compareEdge.end && 
				compareEdge.target.equals(target)&&
				(compareEdge.notMaking == notMaking || compareEdge.notMaking.equals(notMaking))&&
				(compareEdge.making == making || compareEdge.making.equals(making));

	}
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	public ArrayList<String> getMaking() {
		return making;
	}
	public List<String> getNotMaking() {
		return notMaking;
	}
	@Override
	public String toString(){
		
		String notToString = "";
		String makingToString = "";
		if(notMaking != null)
			notToString = notMaking.toString();
		if(making != null)
			makingToString = making.toString(); 
		
		return start + " " + end + " "  + target + "\n" + notToString + "\n" + makingToString; 
	}


}
