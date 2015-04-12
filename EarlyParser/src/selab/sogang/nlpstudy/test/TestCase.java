package selab.sogang.nlpstudy.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import selab.nlpstudy.grammer.Grammar;
import selab.nlpstudy.grammer.GrammarFactory;
import selab.sogang.nlpstudy.ParsingController;
import selab.sogang.nlpstudy.data.CompleteChart;
import selab.sogang.nlpstudy.data.Edge;

public class TestCase {

	@Test
	public void test() {
		testSingleGrammar();
		
		testFileGrammar();
		
				
	}
	private void testSingleGrammar(){
		
		ArrayList<String> newGrammar = GrammarFactory.parseLine("S -> NP VP NP");
		ArrayList<String> expectResult = new ArrayList<String>();
		expectResult.add("NP");
		expectResult.add("VP");
		expectResult.add("NP");
		
		assertEquals(expectResult, GrammarFactory.makeGrammar(newGrammar).test("S"));
		
	}
	private void testFileGrammar(){
		ArrayList<Grammar> grammars = GrammarFactory.makeGrammars();
		
		ArrayList<String> expectResult = new ArrayList<String>();
		expectResult.add("VP");
		expectResult.add("NP");
		expectResult.add("PP");
		assertEquals(expectResult,grammars.get(2).test("VP"));
		
		testMakeUnitEdge(grammars);
	}
	private void testMakeUnitEdge(ArrayList<Grammar> grammars){
		
		
		ParsingController parsingController = new ParsingController(grammars);
		
		parsingController.initCompleteChart();
		
		assertTrue(CompleteChart.getInstance().getData(0,1).contains(new Edge(0,1,"det",new ArrayList<String>().add("the"),null)));
		
	}
	

}
