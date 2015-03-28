package selab.nlpstudy.data;

import java.util.ArrayList;

public class CkyList extends ArrayList<ArrayList<CkyData>> {
	//firstIndex == end secondIndex == start 
	private CkyList(){
		super();
	}
	//�̱��� ������ ����(MutiThread ����ϴ� Singleton pattern)
	private static class CkyListInstanceHolder {
		private static CkyList theUniqueCkyList = new CkyList();
	}
	
	public static CkyList getInstance(){
		return CkyListInstanceHolder.theUniqueCkyList;
		
	}

}
