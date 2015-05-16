package selab.nlpstudy.hmm.viterbi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

import selab.nlpstudy.hmm.training.TrainingData;

public class CountingDatas {

	HashMap<String, Integer> morphemeCount;
	HashMap<ArrayList<String>,Integer> bigramCount;
	public void parsingCountingDatas() {
		// TODO Auto-generated method stub
		morphemeCount = new HashMap<String, Integer>();
		bigramCount = new HashMap<ArrayList<String>, Integer>();
		
		readDatatoHashMap("./MorphemeCount.txt", (s) -> {
			String[] morphemeData = s.split("\\s+");
			morphemeCount.put(morphemeData[0],Integer.parseInt(morphemeData[1]));
			return null;
		});
		
		readDatatoHashMap("./BigramCount.txt", (s) -> {
			String changed = s.replaceAll("\\[","").replaceAll(",", "").replaceAll("\\]", "");
			ArrayList<String> bigramList = new ArrayList<String>(); 
			String[] bigramData = changed.split("\\s+");
			System.out.println(bigramData[0] + " " + bigramData[1] + " " +  bigramData[2]);
			bigramList.add(bigramData[0]); bigramList.add(bigramData[1]);
			bigramCount.put(bigramList, Integer.parseInt(bigramData[2]));
			return null;
		});
		
		
	}
	
	private void readDatatoHashMap(String fileName,Function<String, Void> makeHashMap){
		try{
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String s;
			while((s = reader.readLine()) != null)
				makeHashMap.apply(s);
		}catch (FileNotFoundException e) {
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
		return bigramCount;
	}

	public HashMap<TrainingData, Integer> getStateCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
