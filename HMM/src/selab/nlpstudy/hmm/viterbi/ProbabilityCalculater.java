package selab.nlpstudy.hmm.viterbi;

import java.util.ArrayList;
import java.util.HashMap;

import selab.nlpstudy.hmm.training.TrainingData;

public class ProbabilityCalculater {
	
	
	HashMap<TrainingData,Integer> stateCount ;
	HashMap<String, Integer> morphemeCount;
	HashMap<ArrayList<String>, Integer> bigramCount;
	public ProbabilityCalculater(){
		
		
		bigramCount = CountingDatas.parsingCountingDatas().getBigramCount();
	}

	public static double calcuStateProb(String string, String morpheme) {
		//laplace 
		int morCount = 0;
		int staCount = 0;
		HashMap<TrainingData,Integer> stateCount = CountingDatas.parsingCountingDatas().getStateCount();
		HashMap<String, Integer> morphemeCount = CountingDatas.parsingCountingDatas().getMorphemeCount();
		if(stateCount.get(new TrainingData(string, morpheme)) == null)
			staCount = 1;
		
		else 
			staCount = stateCount.get(new TrainingData(string, morpheme)) + 1;
		
		morCount = morphemeCount.get(morpheme) + morphemeCount.size();
		return Math.log(staCount) - Math.log(morCount);
	}

	public static double calcuTransProb(String first, String second) {
		HashMap<ArrayList<String>, Integer> bigramCount = CountingDatas.parsingCountingDatas().getBigramCount();
		HashMap<String, Integer> morphemeCount = CountingDatas.parsingCountingDatas().getMorphemeCount();
		ArrayList<String> key = new ArrayList<String>();
		key.add(first); key.add(second);
		
		int biCount = 0;
		int morCount = 0;
		
		if(bigramCount.get(key)==null)
			biCount = 1;
		
		else
			biCount = bigramCount.get(key)+1;
		morCount = morphemeCount.get(first) + morphemeCount.size();
		return Math.log(biCount)-Math.log(morCount);
	}

}
