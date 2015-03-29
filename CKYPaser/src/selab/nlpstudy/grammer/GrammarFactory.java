package selab.nlpstudy.grammer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GrammarFactory {

	private static Grammar makeGrammar(String result,String inOrder, String secondOrder) {
		// TODO Auto-generated method stub
		return (string,string1) -> {
		
			if(string.equals(inOrder) && string1.equals(secondOrder))
				return result;
			return null;
		};
		
	}
	private static SingleGrammar makeSingleGrammar(String result,String data){
		return string -> {
			if(string.equals(data))
				return result;
			return null;
		};
	}

	public static Grammars makeGrammars() {
		// TODO Auto-generated method stub
		BufferedReader in;
		ArrayList<Grammar> grammars = new ArrayList<Grammar>();
		ArrayList<SingleGrammar> singleGrammars = new ArrayList<SingleGrammar>();
		try {
			in = new BufferedReader(new FileReader("./grammar.txt"));
			String line;

			while ((line = in.readLine()) != null) {
				ArrayList<String> parsedGrammar = parseLine(line);
				if (parsedGrammar.size() == 2) {
					
					singleGrammars.add(makeSingleGrammar(parsedGrammar.get(0), parsedGrammar.get(1)));
				}
				else
					grammars.add(makeGrammar(parsedGrammar.get(0), parsedGrammar.get(1), parsedGrammar.get(2)));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return new Grammars(grammars, singleGrammars);
	}

	private static ArrayList<String> parseLine(String s) {
		// TODO Auto-generated method stub
		ArrayList<String> returnList = new ArrayList<String>();
		for(String string : s.split("\\s+"))
			returnList.add(string);
		returnList.remove("->");
		return returnList;
	}
	public static class Grammars{
		ArrayList<Grammar> longGrammars;
		ArrayList<SingleGrammar> singleGrammars;
		
		
		public Grammars(ArrayList<Grammar> longGrammars,ArrayList<SingleGrammar> singleGrammars) {
			this.longGrammars = longGrammars;
			this.singleGrammars = singleGrammars;
			// TODO Auto-generated constructor stub
		}
		public ArrayList<Grammar> getLongGrammars() {
			return longGrammars;
		}
		public ArrayList<SingleGrammar> getSingleGrammars() {
			return singleGrammars;
		}
	}
	

}
