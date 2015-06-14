package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ControlMetric extends Metric{

	private int cyclomaticComplexity;
	private HashMap<String, Integer> operators;
	private List<String> keys;
	
	public ControlMetric( HashMap<String, Integer> op ){
		cyclomaticComplexity = 1;
		operators = op;
		keys = new ArrayList<>();
		initializeKeys();
	}

	public int getCyclomaticComplexity() {
		return cyclomaticComplexity ;
	}

	public void setCyclomaticComplexity(int cyclomaticComplexity) {
		this.cyclomaticComplexity = cyclomaticComplexity;
	}
	
	private void initializeKeys(){
		keys.add( "for" );
		keys.add( "while" );
		keys.add( "if" );
		keys.add( "case" );		
	}
	
	public void computeCyclomaticComplexity(){
		
		for( String x: keys )
			if( operators.containsKey(x))
				cyclomaticComplexity += operators.get( x );
	}

	@Override
	public String toString() {
		return "ControlMetric [cyclomaticComplexity=" + cyclomaticComplexity
				+ ", operators=" + operators + ", keys=" + keys + "]";
	}
	
}
