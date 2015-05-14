package selab.nlpstudy.hmm.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TrainingCounter {
	private ArrayList<ArrayList<String>> sentenceSet;
	
	
	
	public TrainingCounter(ArrayList<ArrayList<String>> sentenceSet) {
		this.sentenceSet = sentenceSet;
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
	
	public ArrayList<TrainingData> parseLine(String string) {
		
		ArrayList<TrainingData> parsedString = new ArrayList<TrainingData>();
 		String[] firstSplit= string.split("\\s+");
		char[] rhs = firstSplit[1].toCharArray();
		int savePoint = 0;
		int i =0;
		while(i<rhs.length){
			if(rhs[i]== '/'&&i>=1){
				
				char[] chank = new char[30];
				char[] morpheme = new char[10];
				
				if(i>=2&& rhs[i-2] != '+'&&rhs[i-1] =='+')
					continue;	
				
				int startingPoint=0;
				for(int j=savePoint  ; j < i;j++){
					chank[startingPoint] = rhs[j];
					startingPoint++;
				}
				
				savePoint = i;
				startingPoint=0;
				
				while(savePoint != rhs.length &&rhs[savePoint]!='+'){
					morpheme[startingPoint] = rhs[savePoint];
					startingPoint ++;	savePoint ++;
				}
				
				parsedString.add(new TrainingData(new String(chank),new String(morpheme)));
				i = savePoint+1;
			}
			else
				i ++;
			
			
		}
//		String[] morphemies = rhs.split("\\+");
//		for(String morpheme : morphemies){
//			String[] morphemeData = morpheme.split("\\/");
//			for(String a : morphemeData){
//				System.out.print(a+ " ");
//			}
//			System.out.println();
//			parsedString.add(new TrainingData(morphemeData[0], morphemeData[1]));
//		}
		return parsedString;
	}

	public ArrayList<TrainingData> makeTrainSet() {
		ArrayList<TrainingData> trainSet = new ArrayList<TrainingData>();
		for(ArrayList<String> sentence : sentenceSet)
			for(String morphrome : sentence)
				trainSet.addAll(parseLine(morphrome));
		
		
		return trainSet;
	}
}
