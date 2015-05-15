package selab.nlpstudy.hmm.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import selab.nlpstudy.hmm.training.TrainingCounter;
import selab.nlpstudy.hmm.training.TrainingData;
import selab.nlpstudy.hmm.training.TrainingFileInterface;

public class TrainingTestCase {

	ArrayList<String> sentence;
	ArrayList<ArrayList<String>> sentenceSet;
	TrainingFileInterface trainingParser;
	TrainingCounter trainingCounter;
	ArrayList<TrainingData> trainSet;
	@Test
	public void test() {
		trainingParser = new TrainingFileInterface();
		
		
		//testReadSentence();
		//testCountBigram();
		testMakeSentenceSet();
		trainingCounter = new TrainingCounter(sentenceSet);
		testParseLine();
		testMakeTrainSet();
	}
	
	private void testParseLine(){
		ArrayList<TrainingData> actualData = trainingCounter.parseLine("Àü¾¾	Àü/NNP+¾¾/NNB");
		TrainingData[] expectedData = new TrainingData[2];
		expectedData[0] = new TrainingData("Àü","NNP");
		expectedData[1] = new TrainingData("¾¾","NNB");
		ArrayList<TrainingData> expectList = new ArrayList<TrainingData>(Arrays.asList(expectedData));
	
		assertEquals(expectList, actualData);
	}
	
	private void testReadSentence(){
		String expectString = "Àü¾¾	Àü/NNP+¾¾/NNB";
		sentence = null;
		try {
			sentence = trainingParser.readSentence
					(new BufferedReader(new FileReader(new File("./HMM/train.txt"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expectString, sentence.get(0));
		expectString = "ÁÖÀåÇß´Ù.	ÁÖÀå/NNG+ÇÏ/XSV+¾Ò/EP+´Ù/EF+./SF";
		assertEquals(expectString, sentence.get(sentence.size()-1));
	}
	
	private void testCountBigram(){
		Integer expected = 2;
		String[] key = {"NNB","JX"};
		
 		HashMap<List<String>,Integer> bigramSet = trainingCounter.countBigram(sentence,null);
 		assertEquals(expected ,bigramSet.get(Arrays.asList(key)));
	}
	
	private void testMakeSentenceSet(){
		String expectedFirstString = "Àü¾¾	Àü/NNP+¾¾/NNB";
		String expectedLastString = "¾ø´Ù.	¾ø/VA+´Ù/EF+./SF";
		sentenceSet = trainingParser.makeSentenceSet();
		assertEquals(expectedFirstString, sentenceSet.get(0).get(0));
		assertEquals(expectedLastString, sentenceSet.get(sentenceSet.size()-1)
				.get(sentenceSet.get(sentenceSet.size()-1).size()-1));
		this.trainingCounter = new TrainingCounter(sentenceSet);
	}
	
	private void testMakeTrainSet(){
		TrainingData expectedFirst = new TrainingData("Àü","NNP");
		TrainingData expectedLast = new TrainingData(".","SF");
		
		trainSet = trainingCounter.makeTrainSet();
		assertEquals(expectedFirst,trainSet.get(0));
		assertEquals(expectedLast,trainSet.get(trainSet.size()-1));
		
		
	}
	private void testCountMorpheme(){
		
		Long countEC = new Long(259749);
		Map<String,Long> morphemeCount = trainingCounter.countMorpheme(trainSet);
		
		assertEquals(countEC, morphemeCount.get("EC"));
		Long expectedIt = new Long(20659);
		Map<TrainingData,Long> denpedentCount = trainingCounter.countDependent(trainSet);
		assertEquals(expectedIt, denpedentCount.get(new TrainingData("ÀÕ", "VV")));
	}
	
	

}
