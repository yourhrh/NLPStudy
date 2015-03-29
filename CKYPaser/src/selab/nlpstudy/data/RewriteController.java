package selab.nlpstudy.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import selab.nlpstudy.grammer.Grammar;
import selab.nlpstudy.grammer.GrammarFactory.Grammars;

public class RewriteController {
	private Grammars grammars;
	
	public RewriteController(Grammars grammars){
		this.grammars = grammars;
		initCkyList();
	}

	public void rewrite() {
		// TODO Auto-generated method stub
		
//		for(int j=0;j< CkyList.getInstance().size(); j++){
//			   for(int i = 0;i<CkyList.getInstance().size()-j;i++){
//			      CkyList.getInstance().get(i+j).get(i).rewrite(grammars);
//			   }
//			}
		
		for(ArrayList<CkyData> dataEachLength : CkyList.getInstance()){
			for(CkyData ckyData : dataEachLength){
				ckyData.rewrite(grammars);
			}
		}
	}
	public void initCkyList(){
		try {
			BufferedReader in = new BufferedReader(new FileReader("./input.txt"));
			String inputText = in.readLine();
			String[] parsedInput = inputText.split("\\s+");
			
			for(int length = 1;length<=parsedInput.length; length++){
				ArrayList<CkyData> dataEachLength = new ArrayList<CkyData>();
				for(int start=0;start<parsedInput.length-length+1;start++){
					CkyData insertCkyData = new CkyData(start,start+length);
					if(length == 1)
						insertCkyData.addData(parsedInput[start]);
					dataEachLength.add(insertCkyData);
				}
				CkyList.getInstance().add(dataEachLength);
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
