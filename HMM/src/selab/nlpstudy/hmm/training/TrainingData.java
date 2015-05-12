package selab.nlpstudy.hmm.training;

public class TrainingData {
	
	public String string, morpheme;

	public TrainingData(String string, String morpheme) {
		// TODO Auto-generated constructor stub
		this.string = string;
		this.morpheme = morpheme;
	}
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof TrainingData) {
			return this.string.equals(((TrainingData)arg0).string) && this.morpheme.equals(((TrainingData)arg0).morpheme);
		}
		return false;
		
	}
	
}
