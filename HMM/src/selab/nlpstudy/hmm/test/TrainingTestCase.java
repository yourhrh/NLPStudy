package selab.nlpstudy.hmm.test;

import static org.junit.Assert.*;

import org.junit.Test;

import selab.nlpstudy.hmm.training.TrainingData;
import selab.nlpstudy.hmm.training.TrainingParser;

public class TrainingTestCase {

	@Test
	public void test() {
		testLineParsing();
		
	}
	private void testLineParsing(){
		TrainingParser trainingParser = new TrainingParser();
		TrainingData[] actualData = trainingParser.parseLine("Àü¾¾	Àü/NNP+¾¾/NNB");
		TrainingData[] expectedData = new TrainingData[2];
		expectedData[0] = new TrainingData("Àü","NNP");
		expectedData[1] = new TrainingData("¾¾","NNB");
		assertArrayEquals(expectedData, actualData);
	}

}
