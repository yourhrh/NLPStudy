package selab.nlpstudy.hmm.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import selab.nlpstudy.hmm.training.TrainingData;
import selab.nlpstudy.hmm.viterbi.CountingDatas;
import selab.nlpstudy.hmm.viterbi.HmmNodes;
import selab.nlpstudy.hmm.viterbi.ProbabilityCalculater;

public class HMMTestCase {
	CountingDatas countingDatas;
	ProbabilityCalculater calculater = new ProbabilityCalculater();
	@Test
	public void testReadTrainDatas(){
		
		countingDatas = CountingDatas.parsingCountingDatas();
		
		HashMap<String,Integer> morphemeCount = countingDatas.getMorphemeCount();
		assertEquals(new Integer(74462), morphemeCount.get("VX"));
		
		ArrayList<String> keyBigram = new ArrayList<String>();
		keyBigram.add("SW");keyBigram.add("SF");		
		HashMap<ArrayList<String>,Integer> bigramCount = countingDatas.getBigramCount();
		assertEquals(new Integer(70),bigramCount.get(keyBigram));
		
		HashMap<TrainingData,Integer> stateCount = countingDatas.getStateCount();
		assertEquals(new Integer(4),stateCount.get(new TrainingData("PPM", "SL")));
		
	}
	@Test
	public void testCalcuStateProb(){
		double logProbability = calculater.calcuStateProb("PPM","SW");
		System.out.println(logProbability);
		
	}
	@Test
	public void testCalcuTransProb(){
		double transProbability = calculater.calcuTransProb("SS","SL");
		System.out.println("Trans prob:" +  transProbability);
	}
	@Test
	public void testReadInput(){
		ArrayList<ArrayList<ArrayList<ArrayList<TrainingData>>>> inputSet =  CountingDatas.parsingCountingDatas().readToTrainInput();
		assertEquals("¾È³ç",inputSet.get(0).get(0).get(0).get(0).string);
	}
	@Test
	public void testMakeHmmNodes(){
		HmmNodes hmmNodes = new HmmNodes(CountingDatas.parsingCountingDatas().readToTrainInput());
		double tested = hmmNodes.getSentence(0).get(0).get(0).getProb();
		System.out.println("tested : "+tested);
	}
}
