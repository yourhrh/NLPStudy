package selab.sogang.nlpstudy.data;

import java.util.ArrayList;
import java.util.List;

public class Edge {

	String target;
	List<String> notMaking;
	private ArrayList<ArrayList<String>> making;
	int start,end;
	
	public Edge(int start,int end,String target,ArrayList<ArrayList<String>> making,List<String> rhs){
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
	public ArrayList<ArrayList<String>> getMaking() {
		return making;
	}
	public List<String> getNotMaking() {
		return notMaking;
	}
	@Override
	public String toString(){
		
		String notToString = "motMaking : ";
		String makingToString = "makings : ";
		if(notMaking != null)
			notToString += notMaking.toString();
		if(making.size() != 0){
			for(ArrayList<String> make : making)
				makingToString += make.toString() + "\n";
		}
		return start + " " + end + " "  + target + "\n" + makingToString + "\n" + notToString + '\n'; 
	}
	public String getTarget() {
		return target;
	}
	


}
