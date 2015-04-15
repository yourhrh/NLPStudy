package selab.nlpstudy.grammer;

import java.util.List;

public interface Grammar {

	List<String> convertToRhs(String lhs);
	String convertToLhs(List<String> rhs);


}
