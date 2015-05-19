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
		private double prob = 0;
		private double prevProb = -999999999;
		private ArrayList<TrainingData> dataList;
		private SeperateData prevData;
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
		
		public double getSingleProb(){
			return prob;
		}
		public void setPrevProb(SeperateData prevData){
			
			double newProb = ProbabilityCalculater.calcuTransProb(
					prevData.getDataList().get(prevData.getDataList().size()-1).morpheme
					, this.getDataList().get(0).morpheme);
			if(newProb > prevProb){
				this.prevProb = newProb;
				this.prevData = prevData;
			}
		}
		public double getAllProb(){
			return this.prob + this.prevProb;
		}
		public ArrayList<TrainingData> getDataList() {
			return dataList;
		}
		@Override
		public String toString() {
			String s = "";
			if(prevData != null){
				s += "  " + prevData.toString();
			}
			for(int i=0;i<dataList.size();i++){
				TrainingData singleData = dataList.get(i);
				if(i==0)
					s += singleData.string+"/"+singleData.morpheme;
				
				else 
					s += "+" + singleData.string+"/"+singleData.morpheme;
			}
			return s;
		}
		
	}

	public void calculateAll() {
		// TODO Auto-generated method stub
		for(ArrayList<ArrayList<SeperateData>> sentence : nodeList){
			for(int i=0;i<sentence.size()-1;i++)
				for(SeperateData prevData : sentence.get(i))
					for(SeperateData postData : sentence.get(i+1)){
						postData.setPrevProb(prevData);
					}
			
			if(sentence.size() > 0 ){
				SeperateData maxData = sentence.get(sentence.size()-1).stream()
					.max((data1,data2) -> Double.compare(data1.getAllProb(), data2.getAllProb())).get();
				System.out.println(maxData);

			}
		}
		
		
	}

}
