package selab.sogang.nlpstudy.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
//		assertEquals(expectResult, GrammarFactory.makeGrammar(newGrammar).convertToRhs("S"));
//		assertEquals("S",GrammarFactory.makeGrammar(newGrammar).convertToLhs(expectResult));
		
	}
	private void testFileGrammar(){
		ArrayList<Grammar> grammars = GrammarFactory.makeGrammars();
		
		ArrayList<String> expectResult = new ArrayList<String>();
		expectResult.add("VP");
		expectResult.add("NP");
		expectResult.add("PP");
//		assertEquals(expectResult,grammars.get(2).convertToRhs("VP"));
		
		
		
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
//		sGrammar.add("VP");
//		expectedPC.add(new Edge(0,0,"S",new ArrayList<String>(),testData));
//		expectedPC.add(new Edge(0, 0, "S", new ArrayList<String>(), sGrammar));		
		
//		assertEquals(expectedPC, pendingChart.getProcessQueue());
		
		
		
		
		//TODO : testProcess 1, 2, 3, & tomitaParsing기법 
		
		
//		//process3
//		Edge edge = pendingChart.getProcessQueue().poll();
//		parsingController.process3(edge);
//		ArrayList<String> expectedNotMaking = new ArrayList<String>();
//		expectedNotMaking.add("n");
////		assertTrue(pendingChart.getProcessQueue().contains(new Edge(0,0,"NP",null,expectedNotMaking)));
////		assertTrue(CompleteChart.getInstance().getData(0, 0).contains(edge));
//		
//		
//		//process2
//		
//		Edge pc2Edge = new Edge(0, 1, "NP",expectedNotMaking , new ArrayList<String>());
//		CompleteChart.getInstance().addEdges(pc2Edge);
//		
//		Edge edge2 = pendingChart.getProcessQueue().poll();
//		parsingController.process2(edge2);
//		ArrayList<String> vp = new ArrayList<String>();
//		ArrayList<String> np = new ArrayList<String>();
//		np.add("NP");
//		vp.add("VP");
//		assertTrue(CompleteChart.getInstance().getData(0, 1).contains(new Edge(0,1,"S",np,vp)));
//		
//		//process1
//		Edge pc1Edge = new Edge(1,1,"VP",new ArrayList<String>(),new ArrayList<String>());
//		parsingController.process1(pc1Edge);
//		np.add("VP");
//		
//		
//		assertTrue(PendingChart.getInstance().getProcessQueue().contains(new Edge(0, 1, "S",np, new ArrayList<String>())));
//		
//		
		parsingController.parsing();
		if(CompleteChart.getInstance().getData(0, CompleteChart.getInstance().size()-1).size() == 0){
			System.out.println("완성된게 없엉.. ㅠㅠ");
		}
		else{
			for(Edge edge : CompleteChart.getInstance().getData(0, CompleteChart.getInstance().size()-1)){
				System.out.println("완성 Edge");
				System.out.println(edge.toString());
				
				
			}
		}
		
	}
	

}
