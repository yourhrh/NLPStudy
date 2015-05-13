package selab.nlpstudy.hmm.training;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


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

	public HashMap<List<String>, Integer> countBigram(ArrayList<String> sentence,HashMap<List<String>, Integer> maked) {
		
		
		HashMap<List<String>, Integer> bigramMap;
		if(maked != null)
			bigramMap = maked;
		else
			bigramMap= new HashMap<List<String>, Integer>();
		
		ArrayList<TrainingData> dataSet = makeSentenceToTrainingData(sentence);
		
		for(int i=0;i<dataSet.size()-1;i++){
			String[] bigramData = {dataSet.get(i).morpheme,dataSet.get(i+1).morpheme};
			List<String> key = Arrays.asList(bigramData);
			if(bigramMap.containsKey(key)){
				Integer value = new Integer(bigramMap.get(key).intValue() +1);
				bigramMap.replace(key, value);
			}
			else{
				bigramMap.put(key, new Integer(1));
			}				
		}		
		return bigramMap;
	}
	private ArrayList<TrainingData> makeSentenceToTrainingData(ArrayList<String> sentence){
		ArrayList<TrainingData> dataSet = new ArrayList<TrainingData>();
		for(String line : sentence){
			dataSet.addAll(parseLine(line));
		}
		return dataSet;
	}

	public ArrayList<ArrayList<String>> makeSentenceSet() {
		ArrayList<ArrayList<String>> sentenceSet = new ArrayList<ArrayList<String>>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./HMM/train.txt")));
			while(reader.ready())
				sentenceSet.add(readSentence(reader));
			return sentenceSet;
				
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

}
