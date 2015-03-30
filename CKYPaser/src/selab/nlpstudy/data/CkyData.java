package selab.nlpstudy.data;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.stream.Collectors;

import selab.nlpstudy.grammer.Grammar;
import selab.nlpstudy.grammer.GrammarFactory.Grammars;
import selab.nlpstudy.grammer.SingleGrammar;

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
	}
	public void rewrite(Grammars grammars){
		// TODO Auto-generated method stub
		List<CkyData> secondOrders = CkyList.getInstance().parallelStream()
				.filter(ckyData -> ckyData.getEnd() == end && ckyData.getStart() > start).collect(Collectors.toList());
		List<CkyData> firstOrders = new ArrayList<CkyData>();
		for(CkyData secondOrder : secondOrders){
			firstOrders.add(CkyList.getInstance().parallelStream().
					filter(ckyData -> ckyData.getEnd() == secondOrder.getStart() & ckyData.getStart() == start ).findAny().get());
		}
		for(int i=0;i<firstOrders.size();i++){
			ArrayList<String> firstDatas = firstOrders.get(i).getCkyDatas();
			ArrayList<String> secondDatas = secondOrders.get(i).getCkyDatas();
			for(String first : firstDatas)
				for(String second : secondDatas)
					for(Grammar grammar : grammars.getLongGrammars()){
						String rewriteData = grammar.test(first, second);
						
						if(rewriteData != null){
							ckyDatas.add(rewriteData);
						}
					}
						
		}
		
		
		//혼자 변 할 수 있는 데이터 들을 변환 시킴 
		singleRewrite(grammars.getSingleGrammars());
	}
	public void singleRewrite(ArrayList<SingleGrammar> grammars){
		try {
			for(String singleData : ckyDatas){
				for(SingleGrammar grammar : grammars){
					String input = grammar.test(singleData);
					while(input != null){
						addData(input);
						input = grammar.test(input);
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
