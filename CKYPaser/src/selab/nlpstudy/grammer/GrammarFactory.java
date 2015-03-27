package selab.nlpstudy.grammer;

import java.util.ArrayList;

public class GrammarFactory {

	public static Grammar makeGrammar(String result,String inOrder, String secondOrder) {
		// TODO Auto-generated method stub
		return (string,string1) -> {
			if(string1 == null){
				if(string.equals(inOrder))
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
		
		
		
		return null;
	}

	public static ArrayList<String> parseLine(String s) {
		// TODO Auto-generated method stub
		ArrayList<String> returnList = new ArrayList<String>();
		for(String string : s.split("\\s+"))
			returnList.add(string);
		returnList.remove("->");
		return returnList;
	}
	

}
