package selab.nlpstudy.hmm.training;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TrainingFileInterface {

	

	public ArrayList<String> readSentence(BufferedReader reader) {
		// TODO Auto-generated method stub
		ArrayList<String> sentence = new ArrayList<String>();
		try {
			String s;
			while((s = reader.readLine())!= null && !s.equals("")){
				sentence.add(s);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sentence;
	}

	
	

	public ArrayList<ArrayList<String>> makeSentenceSet() {
		ArrayList<ArrayList<String>> sentenceSet = new ArrayList<ArrayList<String>>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./HMM/train.txt")));
			while(reader.ready())
				sentenceSet.add(readSentence(reader));
			return sentenceSet;
				
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}
	public void makeTrainingDataFile(){
		TrainingCounter counter = new TrainingCounter(makeSentenceSet());
		HashMap<List<String>, Integer> bigramCount = counter.countAllBigram();
		Map<String, Long> morphemeCount = counter.countMorpheme();
		Map<TrainingData,Long> stateCount = counter.countState();
		
		writeFile(bigramCount,"./BigramCount.txt");
		writeFile(morphemeCount,"./MorphemeCount.txt");
		writeFile(stateCount,"./StateCount.txt");
	}
	private void writeFile(Map<?, ?> readData, String fileName){
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)));
			writer.flush();
			readData.forEach((key,count) -> {
				try {
					writer.write(key.toString() + " " + count.toString());
					writer.newLine();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
