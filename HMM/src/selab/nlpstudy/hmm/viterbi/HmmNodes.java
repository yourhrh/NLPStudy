package selab.nlpstudy.hmm.viterbi;

import java.util.ArrayList;

import selab.nlpstudy.hmm.training.TrainingData;

public class HmmNodes {

	ArrayList<ArrayList<ArrayList<SeperateData>>> nodeList = new ArrayList<ArrayList<ArrayList<SeperateData>>>();
	
	public HmmNodes(
			ArrayList<ArrayList<ArrayList<ArrayList<TrainingData>>>> readToTrainInput) {
		
		for(ArrayList<ArrayList<ArrayList<TrainingData>>> sentence : readToTrainInput){
			ArrayList<ArrayList<SeperateData>> sentenceNode = new ArrayList<ArrayList<SeperateData>>();
			for(ArrayList<ArrayList<TrainingData>> seperated : sentence){
				ArrayList<SeperateData> seperatedNode = new ArrayList<HmmNodes.SeperateData>();
				for(ArrayList<TrainingData> oneNode : seperated){
					seperatedNode.add(new SeperateData(oneNode));
				}
				sentenceNode.add(seperatedNode);
			}
			nodeList.add(sentenceNode);
		}
		
	}
	
	public ArrayList<ArrayList<SeperateData>> getSentence(int i) {
		return nodeList.get(i);
	}
	
	public class SeperateData{
		double prob;
		ArrayList<TrainingData> dataList;
		public SeperateData(ArrayList<TrainingData> dataList) {
			this.dataList = dataList;
			calculateProb();
		}
		private void calculateProb(){
			double allStateProb = dataList.stream().mapToDouble(data -> ProbabilityCalculater.calcuStateProb(data.string,data.morpheme)).sum();
			double allTransProb = 0;
			for(int i=0;i<dataList.size()-1;i++)
				allTransProb +=  ProbabilityCalculater.calcuTransProb(dataList.get(i).morpheme, dataList.get(i+1).morpheme);
			this.prob = allStateProb + allTransProb;
 		}
		
		public double getProb(){
			return prob;
		}
	}

}
