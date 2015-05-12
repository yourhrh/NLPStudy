package selab.nlpstudy.hmm.training;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class TrainingParser {

	public ArrayList<TrainingData> parseLine(String string) {
		// TODO Auto-generated method stub
		
		ArrayList<TrainingData> parsedString = new ArrayList<TrainingData>();
 		String[] firstSplit= string.split("\\s+");
		String rhs = firstSplit[1];
		String[] morphemies = rhs.split("\\+");
		for(String morpheme : morphemies){
			String[] morphemeData = morpheme.split("\\/");
			parsedString.add(new TrainingData(morphemeData[0], morphemeData[1]));
		}
		
		
		return parsedString;
	}

	public ArrayList<String> readSentence(BufferedReader reader) {
		// TODO Auto-generated method stub
		ArrayList<String> sentence = new ArrayList<String>();
		try {
			String s;
			while((s = reader.readLine())!= null && !s.equals("")){
				sentence.add(s);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sentence;
	}

	public HashMap<String[], Integer> countBigram(ArrayList<String> sentence) {
		// TODO Auto-generated method stub
		return null;
	}

}
