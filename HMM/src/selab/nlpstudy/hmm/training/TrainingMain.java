package selab.nlpstudy.hmm.training;


public class TrainingMain {

	public static void main(String[] args) {
		

		TrainingFileInterface fileIO = new TrainingFileInterface();
		fileIO.makeTrainingDataFile();
		System.out.println("�н� �Ϸ� !!");
	}
}
