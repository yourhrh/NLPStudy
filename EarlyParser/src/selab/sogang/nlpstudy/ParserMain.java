package selab.sogang.nlpstudy;

import java.util.ArrayList;

import selab.nlpstudy.grammer.Grammar;
import selab.nlpstudy.grammer.GrammarFactory;
import selab.sogang.nlpstudy.data.PendingChart;

public class ParserMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Grammar> grammars = GrammarFactory.makeGrammars();
		ParsingController parsingController = new ParsingController(grammars);
		
		PendingChart.getInstance().init(grammars);
		parsingController.init();
		parsingController.parsing();
		parsingController.writeOutput();
	}

}
