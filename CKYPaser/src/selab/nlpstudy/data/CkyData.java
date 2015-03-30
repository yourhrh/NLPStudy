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
		System.out.println("add : " + data);
	}
	public void rewrite(Grammars grammars){
		// TODO Auto-generated method stub
		
		//끝이 같은 모든 원소를 확인후 rewrite를 통해 집어 넣음
//		List<CkyData> secondOrders = CkyList.getInstance().get(end-1).stream()
//				.filter(cky -> cky.getStart() > start).collect(Collectors.toList());
//		for(int i=0;i<secondOrders.size();i++){
//			CkyData secondOrder = secondOrders.get(i);
//			CkyData firstOrder = CkyList.getInstance().get(secondOrder.getStart()-1).get(start);
			
//		}
		ArrayList<CkyData> secondOrders = new ArrayList<CkyData>();
		ArrayList<CkyData> firstOrders = new ArrayList<CkyData>();
		//end-start-1 length가 end-start-1보다 짦은 즉 이 CkyCell의 length 보다 짧은 것들 
		for(int i=0;i<end-start;i++){
			//끝이 같은 원소들
			//끝이 second의 시작인 원소들 end는 +1이기 떄문에 
			
			if(end-start-i-2 >= 0){
				
				CkyData secondOrder = CkyList.getInstance().get(i).get(end-i-1);
				secondOrders.add(secondOrder);
				firstOrders.add(CkyList.getInstance().get(end-start-i-2).get(secondOrder.getStart()-1));
			}
				
		}
			
		
		for(int i=0;i<secondOrders.size();i++){
			
			CkyData firstOrder = firstOrders.get(i);
			CkyData secondOrder = secondOrders.get(i);
			for(int j=0;j<firstOrder.getCkyDatas().size();j++){
				String firstData = firstOrder.getCkyDatas().get(j);
				for(int k=0;k<secondOrder.getCkyDatas().size();k++){
					String secondData = secondOrder.getCkyDatas().get(k);
					for(Grammar grammar : grammars.getLongGrammars()){
       					System.out.println(firstData + " " + secondData);
						String input = grammar.test(firstData, secondData);
						if(input != null){
							addData(input);
						}
					}
				}
			}
			
			
			
		}
		
		//혼자 변 할 수 있는 데이터 들을 변환 시킴 
		singleRewrite(grammars.getSingleGrammars());
		System.out.println(start + " " +  end + " : " + ckyDatas.toString() );
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
