package selab.nlpstudy.grammer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GrammarFactory {

	public static Grammar makeGrammar(String result,String inOrder, String secondOrder) {
		// TODO Auto-generated method stub
		return (string,string1) -> {
			if(string == null){
				if(string1.equals(secondOrder))
					return result;
				else return null;
			}
			if(string.equals(inOrder) && string1.equals(secondOrder))
				return result;
			return null;
		};
		
	}

	public static ArrayList<Grammar> makeGrammars() {
		// TODO Auto-generated method stub
		BufferedReader in;
		ArrayList<Grammar> grammars = new ArrayList<Grammar>();
		try {
			in = new BufferedReader(new FileReader("./grammar.txt"));
			String line;

			while ((line = in.readLine()) != null) {
				ArrayList<String> parsedGrammar = parseLine(line);
				if (parsedGrammar.size() == 2) {
					
					grammars.add(makeGrammar(parsedGrammar.get(0), null, parsedGrammar.get(1)));
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

		return grammars;
	}

	private static ArrayList<String> parseLine(String s) {
		// TODO Auto-generated method stub
		ArrayList<String> returnList = new ArrayList<String>();
		for(String string : s.split("\\s+"))
			returnList.add(string);
		returnList.remove("->");
		return returnList;
	}
	

}
