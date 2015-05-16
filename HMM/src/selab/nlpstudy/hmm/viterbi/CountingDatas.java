package selab.nlpstudy.hmm.viterbi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import selab.nlpstudy.hmm.training.TrainingData;

public class CountingDatas {

	HashMap<String, Integer> morphemeCount;
	HashMap<ArrayList<String>,Integer> bigramCount;
	public void parsingCountingDatas() {
		// TODO Auto-generated method stub
		morphemeCount = new HashMap<String, Integer>();
		bigramCount = new HashMap<ArrayList<String>, Integer>();
		try {
			
			BufferedReader morphemeReader = new BufferedReader(new FileReader(new File("./MorphemeCount.txt")));
			String s;
			while((s = morphemeReader.readLine())!=null){
				String[] morphemeData = s.split("\\s+");
				morphemeCount.put(morphemeData[0],Integer.parseInt(morphemeData[1]));
			}
			
			BufferedReader bigramReader = new BufferedReader(new FileReader(new File("./BigramCount.txt")));
			while((s=bigramReader.readLine())!=null){
				s.replace("\\[",""); s.replace(",", ""); s.replace("\\]", "");
				ArrayList<String> bigramList = new ArrayList<String>(); 
				String[] bigramData = s.split("\\s+");
				bigramList.add(bigramData[0]); bigramList.add(bigramData[1]);
				bigramCount.put(bigramList, Integer.parseInt(bigramData[2]));
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public HashMap<String, Integer> getMorphemeCount() {
		// TODO Auto-generated method stub
		return morphemeCount;
	}

	public HashMap<ArrayList<String>, Integer> getBigramCount() {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<TrainingData, Integer> getStateCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
