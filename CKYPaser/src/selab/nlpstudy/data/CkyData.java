package selab.nlpstudy.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import selab.nlpstudy.grammer.Grammar;

public class CkyData {

	private int start,end;
	private ArrayList<String> ckyDatas;
	
	public CkyData(int start,int end) {
		// TODO Auto-generated constructor stub
		this.start = start;
		this.end =end;
		ckyDatas = new ArrayList<String>();
	}
	public CkyData(int start, int end, String data) {
		// TODO Auto-generated constructor stub
		this(start,end);
		ckyDatas.add(data);
	}
	public void addData(String data) {
		// TODO Auto-generated method stub
		ckyDatas.add(data);
		
	}
	public void rewrite(ArrayList<Grammar> grammars) {
		// TODO Auto-generated method stub
		List<CkyData> firstOrderList = CkyList.getInstance().stream()
				.filter(ckyData -> ckyData.getEnd() == start).collect(Collectors.toList());
		
		
	}
	public ArrayList<String> getCkyDatas() {
		// TODO Auto-generated method stub
		return null;
	}
	public int getEnd() {
		return end;
	}
	public int getStart() {
		return start;
	}
}
