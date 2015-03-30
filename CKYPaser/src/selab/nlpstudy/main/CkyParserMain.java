package selab.nlpstudy.main;

import selab.nlpstudy.data.RewriteController;
import selab.nlpstudy.grammer.GrammarFactory;
import selab.nlpstudy.grammer.GrammarFactory.Grammars;

public class CkyParserMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grammars grammars = GrammarFactory.makeGrammars();
		RewriteController rewriteController = new RewriteController(grammars);
		rewriteController.rewrite();
	}

}
