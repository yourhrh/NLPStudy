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
		
		return arg0.toString().equals(toString());
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.string +  " " + this.morpheme;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return toString().hashCode();
	}
	
}
