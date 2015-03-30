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
		for(CkyData ckyData : CkyList.getInstance()){
			ckyData.rewrite(grammars);
		}
		CkyList.getInstance().writeOutput();
	}
	public void initCkyList(){
		try {
			BufferedReader in = new BufferedReader(new FileReader("./input.txt"));
			String inputText = in.readLine();
			String[] parsedInput = inputText.split("\\s+");
			//length 순서대로 생성
			CkyList.getInstance().setSentenceLength(parsedInput.length);
			for(int length=1;length<parsedInput.length+1;length++)
				for(int j=0;j<=parsedInput.length-length;j++){
					CkyData ckyData = new CkyData(j, j+length);
					if(length == 1)
						ckyData.addData(parsedInput[j]);
					CkyList.getInstance().add(ckyData);
					
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
