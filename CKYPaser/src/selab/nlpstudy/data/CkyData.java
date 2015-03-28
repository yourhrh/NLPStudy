package selab.nlpstudy.data;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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
	public void addData(String data) {
		// TODO Auto-generated method stub
		ckyDatas.add(data);
		System.out.println("add : " + data);
	}
	public void rewrite(ArrayList<Grammar> grammars) {
		// TODO Auto-generated method stub
		
		//끝이 같은 모든 원소를 확인후 rewrite를 통해 집어 넣음
		List<CkyData> secondOrders = CkyList.getInstance().get(end-1).stream()
				.filter(cky -> cky.getStart() > start).collect(Collectors.toList());
		for(CkyData secondOrder : secondOrders){
			CkyData firstOrder = CkyList.getInstance().get(secondOrder.getStart()).get(start);
			for(String firstData : firstOrder.getCkyDatas())
				for(String secondData : secondOrder.getCkyDatas())
					for(Grammar grammar : grammars){
						System.out.println(firstData + " " + secondData);
						String input = grammar.test(firstData, secondData);
						if(input != null){
							addData(input);
						}
					}
		}
		//혼자 변 할 수 있는 데이터 들을 변환 시킴 
		singleRewrite(grammars);
	}
	public void singleRewrite(ArrayList<Grammar> grammars){
		try {
			for(String singleData : ckyDatas){
				for(Grammar grammar : grammars){
					String input = grammar.test(null, singleData);
					if(input != null){
						addData(input);
					}
				}
			}	
		} catch (ConcurrentModificationException e) {
			// TODO: handle exception
		}
		
	}
	public ArrayList<String> getCkyDatas() {
		// TODO Auto-generated method stub
		return ckyDatas;
	}
	public int getEnd() {
		return end;
	}
	public int getStart() {
		return start;
	}
}
