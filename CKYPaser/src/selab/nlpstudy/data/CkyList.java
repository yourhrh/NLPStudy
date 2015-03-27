package selab.nlpstudy.data;

import java.util.ArrayList;

public class CkyList extends ArrayList<CkyData> {
	
	//�̱��� ������ ����(MutiThread ����ϴ� Singleton pattern)
	private static class CkyListInstanceHolder {
		private static CkyList theUniqueCkyList = new CkyList();
	}
	
	public static CkyList getInstance(){
		return CkyListInstanceHolder.theUniqueCkyList;
		
	}

}
