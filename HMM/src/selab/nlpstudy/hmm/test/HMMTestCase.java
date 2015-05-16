package selab.nlpstudy.hmm.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import selab.nlpstudy.hmm.training.TrainingData;
import selab.nlpstudy.hmm.viterbi.CountingDatas;

public class HMMTestCase {

	@Test
	public void testReadTrainDatas(){
		CountingDatas countingDatas = new CountingDatas();
		countingDatas.parsingCountingDatas();
		
		HashMap<String,Integer> morphemeCount = countingDatas.getMorphemeCount();
		assertEquals(new Integer(74462), morphemeCount.get("VX"));
		
		ArrayList<String> keyBigram = new ArrayList<String>();
		keyBigram.add("SW");keyBigram.add("SF");		
		HashMap<ArrayList<String>,Integer> bigramCount = countingDatas.getBigramCount();
		assertEquals(new Integer(70),bigramCount.get(keyBigram));
		
		HashMap<TrainingData,Integer> stateCount = countingDatas.getStateCount();
		assertEquals(new Integer(4),stateCount.get(new TrainingData("PPM", "SL")));
		
		
	}


}
