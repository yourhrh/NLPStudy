package selab.nlpstudy.hmm.viterbi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;


import selab.nlpstudy.hmm.training.TrainingCounter;
import selab.nlpstudy.hmm.training.TrainingData;

public class CountingDatas {

	HashMap<String, Integer> morphemeCount;
	HashMap<ArrayList<String>,Integer> bigramCount;
	HashMap<TrainingData, Integer> stateCount;
	
	
	private static class InstanceHolder{
		
		private static CountingDatas uniqueInstance = new CountingDatas();
	}
	
	private CountingDatas(){
		morphemeCount = new HashMap<String, Integer>();
		bigramCount = new HashMap<ArrayList<String>, Integer>();
		stateCount = new HashMap<TrainingData, Integer>();
		
		readDatatoDateStructure("./MorphemeCount.txt", (s) -> {
			String[] morphemeData = s.split("\\s+");
			morphemeCount.put(morphemeData[0],Integer.parseInt(morphemeData[1]));
			return null;
		});
		
		readDatatoDateStructure("./BigramCount.txt", (s) -> {
			String changed = s.replaceAll("\\[","").replaceAll(",", "").replaceAll("\\]", "");
			ArrayList<String> bigramList = new ArrayList<String>(); 
			String[] bigramData = changed.split("\\s+");
			System.out.println(bigramData[0] + " " + bigramData[1] + " " +  bigramData[2]);
			bigramList.add(bigramData[0]); bigramList.add(bigramData[1]);
			bigramCount.put(bigramList, Integer.parseInt(bigramData[2]));
			return null;
		});
		readDatatoDateStructure("./StateCount.txt", s-> {
			String[] stateData = s.split("\\s+");
			stateCount.put(new TrainingData(stateData[0], stateData[1]), Integer.parseInt(stateData[2]));
 			return null;
		});
	}
	
 	
	public static CountingDatas parsingCountingDatas() {
		// TODO Auto-generated method stub
		return InstanceHolder.uniqueInstance;		
		
	}
	private void readDatatoDateStructure(String fileName,Function<String, Void> makeFunction){
		try{
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String s;
			while((s = reader.readLine()) != null)
				makeFunction.apply(s);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, Integer> getMorphemeCount() {
		return morphemeCount;
	}

	public HashMap<ArrayList<String>, Integer> getBigramCount() {
		return bigramCount;
	}

	public HashMap<TrainingData, Integer> getStateCount() {
		return stateCount;
	}


	public ArrayList<ArrayList<ArrayList<ArrayList<TrainingData>>>> readToTrainInput() {
		ArrayList<ArrayList<ArrayList<ArrayList<TrainingData>>>> trainInput = 
				new ArrayList<ArrayList<ArrayList<ArrayList<TrainingData>>>>();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(new File("./HMM/result.txt")));
			String s;
			int sentenceIndex= 0;
			int seperateIndex = -1;
			trainInput.add(new ArrayList<ArrayList<ArrayList<TrainingData>>>());
			while((s= reader.readLine()) !=null){
				System.out.println(sentenceIndex + " " + seperateIndex);
				System.out.println("s : " +s);
				if(s.equals("")){
					sentenceIndex++;
					seperateIndex = -1;
					trainInput.add(new ArrayList<ArrayList<ArrayList<TrainingData>>>());
				}
				else{
					if(!s.contains(".")&&!s.contains("\\/")&&!s.contains("\\+"))
						continue;
					if(s.contains("1.")){
						trainInput.get(sentenceIndex).add(new ArrayList<ArrayList<TrainingData>>());
						seperateIndex ++;
					}
					System.out.println(sentenceIndex + " " + seperateIndex);
					
					String[] line = s.split("\\.\\s+");
					TrainingCounter.parseRhs(line[1].toCharArray());
					trainInput.get(sentenceIndex).get(seperateIndex).add(TrainingCounter.parseRhs(line[1].toCharArray()));
				}
				
					
			}
			
		}catch (FileNotFoundException e){	
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		
		
		return trainInput;
	}



}
