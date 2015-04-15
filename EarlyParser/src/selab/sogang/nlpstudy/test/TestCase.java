package selab.sogang.nlpstudy.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import selab.nlpstudy.grammer.Grammar;
import selab.nlpstudy.grammer.GrammarFactory;
import selab.sogang.nlpstudy.ParsingController;
import selab.sogang.nlpstudy.data.CompleteChart;
import selab.sogang.nlpstudy.data.Edge;
import selab.sogang.nlpstudy.data.PendingChart;

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
		
		assertEquals(expectResult, GrammarFactory.makeGrammar(newGrammar).convertToRhs("S"));
		assertEquals("S",GrammarFactory.makeGrammar(newGrammar).convertToLhs(expectResult));
		
	}
	private void testFileGrammar(){
		ArrayList<Grammar> grammars = GrammarFactory.makeGrammars();
		
		ArrayList<String> expectResult = new ArrayList<String>();
		expectResult.add("VP");
		expectResult.add("NP");
		expectResult.add("PP");
		assertEquals(expectResult,grammars.get(2).convertToRhs("VP"));
		
		testMakeUnitEdge(grammars);
	}
	private void testMakeUnitEdge(ArrayList<Grammar> grammars){
		
		
		ParsingController parsingController = new ParsingController(grammars);
		
		parsingController.init();
		ArrayList<String> testData = new ArrayList<String>();
		testData.add("the");
//		assertTrue(CompleteChart.getInstance().getData(0,1).contains(new Edge(0,1,"det",testData,null)));
		testPendingChart(parsingController);
		
	}
	private void testPendingChart(ParsingController parsingController){
		PendingChart pendingChart = PendingChart.getInstance();
		pendingChart.init(parsingController.getGrammars());
		
		Queue<Edge> expectedPC = new LinkedList<Edge>();
		ArrayList<String> testData = new ArrayList<String>();
		testData.add("NP");
		testData.add("VP");
		testData.add("NP");
		ArrayList<String> sGrammar = new ArrayList<String>();
		sGrammar.add("NP");
		sGrammar.add("VP");
		expectedPC.add(new Edge(0,0,"S",null,testData));
		expectedPC.add(new Edge(0, 0, "S", null, sGrammar));		
		
		assertEquals(expectedPC, pendingChart.getProcessQueue());
		
		
		
		
		
		//TODO : testProcess 1, 2, 3, & tomitaParsing±â¹ý 
		
		
		//process3
		Edge edge = pendingChart.getProcessQueue().poll();
		parsingController.prcess3(edge);
		ArrayList<String> expectedNotMaking = new ArrayList<String>();
		expectedNotMaking.add("n");
		System.out.println(pendingChart.getProcessQueue().toString());
		assertTrue(pendingChart.getProcessQueue().contains(new Edge(0,0,"NP",null,expectedNotMaking)));
	}
	

}
