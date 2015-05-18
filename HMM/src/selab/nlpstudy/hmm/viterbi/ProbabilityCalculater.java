package selab.nlpstudy.hmm.viterbi;

import java.util.ArrayList;
import java.util.HashMap;

import selab.nlpstudy.hmm.training.TrainingData;

public class ProbabilityCalculater {
	
	
	HashMap<TrainingData,Integer> stateCount ;
	HashMap<String, Integer> morphemeCount;
	HashMap<ArrayList<String>, Integer> bigramCount;
	public ProbabilityCalculater(){
		stateCount = CountingDatas.parsingCountingDatas().getStateCount();
		morphemeCount = CountingDatas.parsingCountingDatas().getMorphemeCount();
		bigramCount = CountingDatas.parsingCountingDatas().getBigramCount();
	}

	public double calcuStateProb(String string, String morpheme) {
		//laplace 
		int morCount = 0;
		int staCount = 0;
		if(stateCount.get(new TrainingData(string, morpheme)) == null){
			staCount = 1;
		}
		else {
			
			staCount = stateCount.get(new TrainingData(string, morpheme)) + 1;
		}
		morCount = morphemeCount.get(morpheme) + morphemeCount.size();
		return Math.log(staCount) - Math.log(morCount);
	}

	public double calcuTransProb(String first, String second) {
		// TODO Auto-generated method stub
		ArrayList<String> key = new ArrayList<String>();
		key.add(first); key.add(second);
		
		int biCount = 0;
		int morCount = 0;
		
		if(bigramCount.get(key)==null){
			biCount = 1;
		}
		else
			biCount = bigramCount.get(key)+1;
		morCount = morphemeCount.get(first) + morphemeCount.size();
		return Math.log(biCount)-Math.log(morCount);
	}

}
