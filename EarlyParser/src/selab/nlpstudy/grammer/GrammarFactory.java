package selab.nlpstudy.grammer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrammarFactory {



	public static ArrayList<Grammar> makeGrammars() {
		// TODO Auto-generated method stub
		BufferedReader in;
		ArrayList<Grammar> grammars = new ArrayList<Grammar>();
		try {
			in = new BufferedReader(new FileReader("./grammar.txt"));
			String line;

			while ((line = in.readLine()) != null) {
				ArrayList<String> parsedGrammar = parseLine(line);
				grammars.add(makeGrammar(parsedGrammar));
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

	public static ArrayList<String> parseLine(String s) {
		// TODO Auto-generated method stub
		ArrayList<String> returnList = new ArrayList<String>();
		for(String string : s.split("\\s+"))
			returnList.add(string);
		returnList.remove("->");
		return returnList;
	}

	

	public static Grammar makeGrammar(ArrayList<String> newGrammar) {
		// TODO Auto-generated method stub
		return new Grammar() {
			
			@Override
			public List<String> convertToRhs(String lhs) {

				if(newGrammar.get(0).equals(lhs))
					return newGrammar.subList(1, newGrammar.size());
				return null;
			
			}
			
			@Override
			public String convertToLhs(List<String> rhs) {
				// TODO Auto-generated method stub
				if(newGrammar.subList(1, newGrammar.size()).equals(rhs))
					return newGrammar.get(0);
				return null;
			}
		};
			
	}

	

}
