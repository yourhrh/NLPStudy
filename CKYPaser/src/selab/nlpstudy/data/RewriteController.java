package selab.nlpstudy.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import selab.nlpstudy.grammer.Grammar;

public class RewriteController {
	private ArrayList<Grammar> grammars;
	
	public RewriteController(ArrayList<Grammar> grammars){
		this.grammars = grammars;
		initCkyList();
	}

	public void rewrite() {
		// TODO Auto-generated method stub
		
		for(int j=0;j< CkyList.getInstance().size(); j++)
			for(int i = j;i<CkyList.getInstance().size();i++){
				CkyList.getInstance().get(i).get(i-j).rewrite(grammars);
			}
	}
	public void initCkyList(){
		try {
			BufferedReader in = new BufferedReader(new FileReader("./input.txt"));
			String inputText = in.readLine();
			String[] parsedInput = inputText.split("\\s+");
			for(int i = 0;i<parsedInput.length; i++){
				ArrayList<CkyData> endCkys = new ArrayList<CkyData>();
				CkyList.getInstance().add(endCkys);
				for(int j= i; j>=0; j--){
					//end 는 1부터 시작한다.
					CkyData data = new CkyData(j,i+1);
					// 단일 칸일 경우(단어 하나가 들어가는 경우 단어를 넣어 초기화)
					if(j == i){
						data.addData(parsedInput[i]);
					}
					
					endCkys.add(data);
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
