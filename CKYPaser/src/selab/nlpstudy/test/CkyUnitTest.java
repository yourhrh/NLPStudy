package selab.nlpstudy.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import selab.nlpstudy.data.CkyData;
import selab.nlpstudy.grammer.Grammar;
import selab.nlpstudy.grammer.GrammarFactory;

public class CkyUnitTest {

	@Test
	public void test() {
		//grammer 
		Grammar sGrammer = GrammarFactory.makeGrammar("S","NP","VP");
		
		assertEquals(null, sGrammer.test("NP", "abc"));
		
		Grammar singleGrammar = GrammarFactory.makeGrammar("VP", null,"V");
		
		assertEquals("VP", singleGrammar.test(null,"V"));
		
		// parse 만들고 private로 변경
//		String S = "S -> NP VP";
//		
//		ArrayList<String> parseGString = GrammarFactory.parseLine(S);
//		ArrayList<String> expect = new ArrayList<String>();
//		expect.add("S");expect.add("NP");expect.add("VP");
//		
//		assertEquals(parseGString, expect);
		
		///file 읽어 grammer 만들기
		
		ArrayList<Grammar> grammars = GrammarFactory.makeGrammars();
		
		assertTrue(grammars.size() == 15);
		
		CkyData cky = new CkyData(0,1,"with");
		cky.addData("with");
		cky.rewrite(grammars);
		ArrayList<String> expectedRewirte = new ArrayList<String>();
		expectedRewirte.add("with");expectedRewirte.add("P");
		assertEquals(expectedRewirte,cky.getCkyDatas());
	}

}
