package selab.nlpstudy.hmm.training;

import java.util.HashMap;
import java.util.Map;

public class TrainingMain {

	public static void main(String[] args) {
		

		TrainingFileInterface fileIO = new TrainingFileInterface();
		fileIO.makeTrainingDataFile();
		System.out.println("�н� �Ϸ� !!");
	}
}
