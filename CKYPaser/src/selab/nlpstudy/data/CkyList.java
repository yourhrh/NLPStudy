package selab.nlpstudy.data;

import java.util.ArrayList;

public class CkyList extends ArrayList<ArrayList<CkyData>> {
	//firstIndex == end secondIndex == start 
	private CkyList(){
		super();
	}
	//싱글턴 구현을 위해(MutiThread 허용하는 Singleton pattern)
	private static class CkyListInstanceHolder {
		private static CkyList theUniqueCkyList = new CkyList();
	}
	
	public static CkyList getInstance(){
		return CkyListInstanceHolder.theUniqueCkyList;
		
	}

}
