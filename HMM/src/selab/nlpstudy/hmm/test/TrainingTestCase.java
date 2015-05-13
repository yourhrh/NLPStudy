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

import org.junit.Test;

import selab.nlpstudy.hmm.training.TrainingData;
import selab.nlpstudy.hmm.training.TrainingParser;

public class TrainingTestCase {

	ArrayList<String> sentence;
	ArrayList<ArrayList<String>> sentenceSet;
	TrainingParser trainingParser;
	@Test
	public void test() {
		trainingParser = new TrainingParser();
		testParseLine();
		testReadSentence();
		testCountBigram();
		testMakeSentenceSet();
		
	}
	private void testParseLine(){
		ArrayList<TrainingData> actualData = trainingParser.parseLine("����	��/NNP+��/NNB");
		TrainingData[] expectedData = new TrainingData[2];
		expectedData[0] = new TrainingData("��","NNP");
		expectedData[1] = new TrainingData("��","NNB");
		ArrayList<TrainingData> expectList = new ArrayList<TrainingData>(Arrays.asList(expectedData));
		assertEquals(expectList, actualData);
	}
	private void testReadSentence(){
		String expectString = "����	��/NNP+��/NNB";
		sentence = null;
		try {
			sentence = trainingParser.readSentence
					(new BufferedReader(new FileReader(new File("./HMM/train.txt"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expectString, sentence.get(0));
		expectString = "�����ߴ�.	����/NNG+��/XSV+��/EP+��/EF+./SF";
		assertEquals(expectString, sentence.get(sentence.size()-1));
	}
	private void testCountBigram(){
		Integer expected = 2;
		String[] key = {"NNB","JX"};
		
 		HashMap<List<String>,Integer> bigramSet = trainingParser.countBigram(sentence);
 		assertEquals(expected ,bigramSet.get(Arrays.asList(key)));
	}
	private void testMakeSentenceSet(){
		String expectedFirstString = "����	��/NNP+��/NNB";
		String expectedLastString = "����.	��/VA+��/EF+./SF";
		sentenceSet = trainingParser.makeSentenceSet();
		assertEquals(expectedFirstString, sentenceSet.get(0).get(0));
		assertEquals(expectedLastString, sentenceSet.get(sentenceSet.size()-1)
				.get(sentenceSet.get(sentenceSet.size()-1).size()-1));
	}
	
	

}
