package selab.nlpstudy.hmm.viterbi;

public class HMMMain {
	
	public static void main(String[] args) {
		HmmNodes hmmNodes = new HmmNodes(CountingDatas.parsingCountingDatas().readToTrainInput());
		hmmNodes.calculateAll();
	}
}
