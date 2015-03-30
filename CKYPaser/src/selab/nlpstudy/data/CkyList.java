package selab.nlpstudy.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CkyList extends ArrayList<CkyData> {
	//firstIndex == end secondIndex == start 
	private int sentenceLength;
	private ArrayList<String> usingGrammars;
	private CkyList(){
		super();
		usingGrammars = new ArrayList<String>();
	}
	//싱글턴 구현을 위해(MutiThread 허용하는 Singleton pattern)
	private static class CkyListInstanceHolder {
		private static CkyList theUniqueCkyList = new CkyList();
	}
	
	public static CkyList getInstance(){
		return CkyListInstanceHolder.theUniqueCkyList;
		
	}
	public int getSentenceLength() {
		return sentenceLength;
	}
	public void setSentenceLength(int sentenceLength) {
		this.sentenceLength = sentenceLength;
	}
	public void addUsingGrammar(String grammar){
		usingGrammars.add(grammar);
	}
	public void writeOutput(){
		try {
			File output = new File("./output.txt");
			
			output.delete();
			
			output.createNewFile();
			
			BufferedWriter out = new BufferedWriter(new FileWriter(output));
			for(String usingGrammar : usingGrammars ){
				out.write(usingGrammar); out.newLine();
			}
			CkyData finalNode = this.get(this.size()-1);
			List<String> finalSentences = finalNode.getCkyDatas().stream().filter(string -> {
				String sentence = string.split("\\s+")[0];
				return sentence.equals("S");
			}).collect(Collectors.toList());
			if(finalSentences.size() == 0)
				out.write("Can't make complete sentence from whole input");
			else
				for(String sentence : finalSentences)
					out.write(sentence);
							
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
