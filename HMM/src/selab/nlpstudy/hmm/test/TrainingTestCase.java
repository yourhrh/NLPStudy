package selab.nlpstudy.hmm.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

import selab.nlpstudy.hmm.training.TrainingData;
import selab.nlpstudy.hmm.training.TrainingParser;

public class TrainingTestCase {

	ArrayList<String> sentence;
	TrainingParser trainingParser;
	@Test
	public void test() {
		trainingParser = new TrainingParser();
		testParseLine();
		testReadSentence();
		testCountBigram();
		
	}
	private void testParseLine(){
		ArrayList<TrainingData> actualData = trainingParser.parseLine("Àü¾¾	Àü/NNP+¾¾/NNB");
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
 		HashMap<String[],Integer> bigramSet = trainingParser.countBigram(sentence);
 		assertEquals(bigramSet.get(key), expected);
		
	}
	
	

}
