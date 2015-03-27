package selab.nlpstudy.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import selab.nlpstudy.grammer.Grammar;
import selab.nlpstudy.grammer.GrammarFactory;

public class CkyUnitTest {

	@Test
	public void test() {
		
		Grammar sGrammer = GrammarFactory.makeGrammar("S","NP","VP");
		
		assertEquals(null, sGrammer.test("NP", "abc"));
		
		Grammar singleGrammar = GrammarFactory.makeGrammar("VP","V", null);
		
		assertEquals("VP", singleGrammar.test("V", null));
		
		
		String S = "S -> NP VP";
		
		ArrayList<String> parseGString = GrammarFactory.parseLine(S);
		ArrayList<String> expect = new ArrayList<String>();
		expect.add("S");expect.add("NP");expect.add("VP");
		
		assertEquals(parseGString, expect);
		
		ArrayList<Grammar> grammers = GrammarFactory.makeGrammars();
		
		assertTrue(grammers.size() == 15 );
		
	}

}
